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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.velocity.app.VelocityEngine;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

@Setter
public class CreateFormTask extends DefaultTask {

	private List<File> definitionFiles;

	private File outputDir;

	private String basePackageName;

	private String templateName;

	@TaskAction
	public void create() throws IOException {
		System.out.println("CreateFormTask");
		System.out.println(definitionFiles);
		System.out.println(outputDir);
		System.out.println(basePackageName);
		System.out.println(templateName);

		List<FormDef> list = new LinkedList<>();
		for (File file : definitionFiles) {
			list.addAll(parseDef(file));
		}

		VelocityEngine engine = new VelocityEngine();
		engine.init();
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
		}
		return formDef;
	}

	@Setter
	@Getter
	public static class FormDef {
		private String packageName;
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
