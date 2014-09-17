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

package cherry.gradle.plugin;

import groovy.lang.Closure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.codegen.MetaDataExporter;
import com.mysema.query.sql.types.LocalDateTimeType;
import com.mysema.query.sql.types.LocalDateType;
import com.mysema.query.sql.types.LocalTimeType;

public class QuerydslCodegenPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		final QuerydslCodegenExtension extension = project.getExtensions()
				.create("querydsl", QuerydslCodegenExtension.class);
		@SuppressWarnings("serial")
		Closure<Boolean> closure = new Closure<Boolean>(this) {
			@Override
			public Boolean call() {
				setupMetaDataExporter(extension.getMetaDataExporter());
				try {
					Class.forName(extension.getJdbcDriverClass());
					try (Connection c = DriverManager.getConnection(
							extension.getDbUrl(), extension.getDbUsername(),
							extension.getDbPassword())) {
						extension.getMetaDataExporter().export(c.getMetaData());
					}
					return true;
				} catch (ClassNotFoundException | SQLException ex) {
					throw new IllegalArgumentException(ex);
				}
			}
		};
		project.getTasks().create("querydslcodegen").doLast(closure);
	}

	private void setupMetaDataExporter(MetaDataExporter exporter) {
		Configuration configuration = new Configuration(SQLTemplates.DEFAULT);
		configuration.register(new LocalDateType());
		configuration.register(new LocalTimeType());
		configuration.register(new LocalDateTimeType());
		exporter.setConfiguration(configuration);
	}

}
