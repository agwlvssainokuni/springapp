/*
 * Copyright 2014 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.gradle.task;

import static java.lang.System.out;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

@Setter
public class GenerateForm extends DefaultTask {

	private boolean verbose = false;

	private List<File> definitionFiles = new ArrayList<>();

	private String velocityConfig = "velocity.properties";

	private String templateJava = "form/java.vm";

	private String templateProperties = "form/properties.vm";

	private String templateEncoding = "UTF-8";

	private File javaBaseDir = new File("src/generated/java");

	private String javaBasePackage;

	private String javaEncoding = "UTF-8";

	private File propertiesFile = new File(
			"src/generated/resources/form.properties");

	private String propertiesEncoding = "UTF-8";

	@TaskAction
	public void generate() throws IOException {

		message("Starting to generate form files.");

		List<FormDef> list = new LinkedList<>();
		for (File file : definitionFiles) {
			message("Parsing a file {0}", file);
			list.addAll(parseDef(file));
		}

		VelocityEngine engine = new VelocityEngine();
		try (InputStream in = new FileInputStream(velocityConfig)) {
			Properties props = new Properties();
			props.load(in);
			engine.init(props);
		}

		message("Loading templates.");
		Template javaTempl = engine.getTemplate(templateJava, templateEncoding);
		Template propTempl = engine.getTemplate(templateProperties,
				templateEncoding);

		message("Preparing destination directories.");

		propertiesFile.getParentFile().mkdirs();
		javaBaseDir.mkdirs();

		message("Generating {0}", propertiesFile);

		try (OutputStream pout = new FileOutputStream(propertiesFile);
				Writer pwriter = new OutputStreamWriter(pout,
						propertiesEncoding)) {
			for (FormDef formDef : list) {
				VelocityContext context = new VelocityContext();
				context.put("formDef", formDef);

				propTempl.merge(context, pwriter);
				pwriter.flush();
			}
		}

		message("Generating forms.");

		for (FormDef formDef : list) {
			VelocityContext context = new VelocityContext();
			context.put("formDef", formDef);

			message("{0}.{1}", formDef.getPackageName(), formDef.getClassName());

			String pkg = formDef.getPackageName();
			File pkgDir = new File(javaBaseDir, pkg.replaceAll("\\.", "/"));
			pkgDir.mkdirs();
			try (OutputStream jout = new FileOutputStream(new File(pkgDir,
					formDef.getClassName() + ".java"));
					Writer jwriter = new OutputStreamWriter(jout, javaEncoding)) {
				javaTempl.merge(context, jwriter);
				jwriter.flush();
			}
		}

		message("Completed.");
	}

	private void message(String msg, Object... args) {
		if (verbose) {
			out.println(MessageFormat.format(msg, args));
		}
	}

	private List<FormDef> parseDef(File file) throws IOException {
		List<FormDef> list = new LinkedList<>();
		try (InputStream in = new FileInputStream(file)) {
			Workbook workbook = WorkbookFactory.create(in);
			int numOfSheets = workbook.getNumberOfSheets();
			for (int i = 0; i < numOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				FormDef formDef = parseSheet(sheet);
				if (formDef != null) {
					list.add(formDef);
				}
			}
		} catch (InvalidFormatException ex) {
			throw new IllegalArgumentException(ex);
		}
		return list;
	}

	private FormDef parseSheet(Sheet sheet) {
		FormDef formDef = new FormDef();
		for (Row row : sheet) {
			int num = row.getFirstCellNum();
			if (num < 0) {
				continue;
			}
			// TODO 定義書解析ロジックを実装する。
		}
		// TODO ダミー定義。
		formDef.setPackageName("cherry.spring.form");
		formDef.setClassName("FooForm");
		return formDef;
	}

	@Setter
	@Getter
	public static class FormDef {
		private String packageName;
		private String className;
		private String formName;
		private List<PropertyDef> propertyDef;
	}

	@Setter
	@Getter
	public static class PropertyDef {
		private String label;
		private String name;
		private String javaType;
		private List<ValidationDef> validationDef;
	}

	@Setter
	@Getter
	public static class ValidationDef {
		private boolean required;
		private String pattern;
		private String type;
		private Integer minLength;
		private Integer maxLength;
		private Integer minValue;
		private Integer maxValue;
	}

}
