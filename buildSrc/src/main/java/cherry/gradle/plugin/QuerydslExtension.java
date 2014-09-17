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

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.codegen.MetaDataExporter;

public class QuerydslExtension {

	private String jdbcDriverClass;

	private String dbUrl;

	private String dbUsername;

	private String dbPassword;

	private final MetaDataExporter metaDataExporter = new MetaDataExporter();

	private final Configuration configuration = new Configuration(
			SQLTemplates.DEFAULT);

	public <T> T exporter(Closure<T> action) {
		action.setDelegate(metaDataExporter);
		return action.call();
	}

	public <T> T configuration(Closure<T> action) {
		action.setDelegate(configuration);
		return action.call();
	}

	public String getJdbcDriverClass() {
		return jdbcDriverClass;
	}

	public void setJdbcDriverClass(String jdbcDriverClass) {
		this.jdbcDriverClass = jdbcDriverClass;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public MetaDataExporter getMetaDataExporter() {
		return metaDataExporter;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

}
