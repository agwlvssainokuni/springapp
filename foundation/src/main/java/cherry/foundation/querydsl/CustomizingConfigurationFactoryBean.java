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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.google.common.base.Splitter;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.SQLListener;
import com.mysema.query.sql.types.Type;

public class CustomizingConfigurationFactoryBean implements FactoryBean<Configuration>, InitializingBean {

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

	public void setNumericTypeSpecs(List<String> numericTypeSpecs) {
		this.numericTypeSpecs = numericTypeSpecs;
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
				if (l.size() != 3) {
					throw new IllegalArgumentException(
							"numericTypeSpecs must be \"{total},{decimal},{javaType}\" or \"{beginTotal}-{endTotal},{beginDecimal}-{endDecimal},{javaType}\"");
				}
				Pair<Integer, Integer> total = parsePair(l.get(0));
				Pair<Integer, Integer> decimal = parsePair(l.get(1));
				configuration.registerNumeric(total.getLeft(), total.getRight(), decimal.getLeft(), decimal.getRight(),
						Class.forName(l.get(2)));
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

	private Pair<Integer, Integer> parsePair(String s) {
		Matcher mSingle = Pattern.compile("^(\\d+)$").matcher(s);
		if (mSingle.matches()) {
			return Pair.of(new Integer(mSingle.group(1)), new Integer(mSingle.group(1)));
		}
		Matcher mRange = Pattern.compile("^(\\d+)-(\\d+)$").matcher(s);
		if (mRange.matches()) {
			return Pair.of(new Integer(mRange.group(1)), new Integer(mRange.group(2)));
		}
		throw new IllegalArgumentException(
				"numericTypeSpecs must be \"{total},{decimal},{javaType}\" or \"{beginTotal},{endTotal},{beginDecimal},{endDecimal},{javaType}\"");
	}

}
