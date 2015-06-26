/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.foundation.querydsl;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLListener;

public class ConfigurationCustomizingFactoryBean implements FactoryBean<Configuration>, InitializingBean {

	private Configuration configuration;

	private List<SQLListener> listeners;

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void setListeners(List<SQLListener> listeners) {
		this.listeners = listeners;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		for (SQLListener l : listeners) {
			configuration.addListener(l);
		}
	}

	@Override
	public Configuration getObject() throws Exception {
		return configuration;
	}

	@Override
	public Class<?> getObjectType() {
		return Configuration.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
