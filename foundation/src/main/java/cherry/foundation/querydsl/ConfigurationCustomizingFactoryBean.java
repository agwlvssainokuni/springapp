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

import static java.lang.Integer.parseInt;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.google.common.base.Splitter;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLListener;
import com.mysema.query.sql.types.Type;

public class ConfigurationCustomizingFactoryBean implements FactoryBean<Configuration>, InitializingBean {

	private Configuration configuration;

	private List<SQLListener> listeners;

	private List<Type<?>> customTypes;

	private List<String> numericTypeSpecs;

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void setListeners(List<SQLListener> listeners) {
		this.listeners = listeners;
	}

	public void setCustomTypes(List<Type<?>> customTypes) {
		this.customTypes = customTypes;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (listeners != null) {
			for (SQLListener l : listeners) {
				configuration.addListener(l);
			}
		}
		if (customTypes != null) {
			for (Type<?> t : customTypes) {
				configuration.register(t);
			}
		}
		if (numericTypeSpecs != null) {
			for (String spec : numericTypeSpecs) {
				List<String> l = Splitter.onPattern(",").trimResults().splitToList(spec);
				if (l.size() == 3) {
					configuration.registerNumeric(parseInt(l.get(0)), parseInt(l.get(1)), Class.forName(l.get(2)));
				} else if (l.size() == 5) {
					configuration.registerNumeric(parseInt(l.get(0)), parseInt(l.get(1)), parseInt(l.get(2)),
							parseInt(l.get(3)), Class.forName(l.get(4)));
				} else {
					throw new IllegalArgumentException(
							"numericTypeSpecs must be \"{total},{decimal},{javaType}\" or \"{beginTotal},{endTotal},{beginDecimal},{endDecimal},{javaType}\"");
				}
			}
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
