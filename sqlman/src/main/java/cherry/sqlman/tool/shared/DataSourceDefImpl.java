/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.sqlman.tool.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;

public class DataSourceDefImpl implements DataSourceDef, InitializingBean {

	private String defaultName;

	private String systemName;

	private Map<String, DataSource> dataSourceMap;

	private List<String> names;

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}

	@Override
	public void afterPropertiesSet() {
		names = new ArrayList<>(dataSourceMap.size());
		DataSource systemDataSource = dataSourceMap.get(systemName);
		for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
			if (!entry.getValue().equals(systemDataSource) || entry.getKey().equals(systemName)) {
				names.add(entry.getKey());
			}
		}
	}

	@Override
	public String getDefaultName() {
		return defaultName;
	}

	@Override
	public List<String> getNames() {
		return names;
	}

	@Override
	public DataSource getDataSource(String name) {
		return dataSourceMap.get(name);
	}

}
