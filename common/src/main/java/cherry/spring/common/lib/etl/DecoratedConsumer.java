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

package cherry.spring.common.lib.etl;

import java.io.IOException;
import java.util.Map;

public class DecoratedConsumer implements Consumer {

	private final Consumer delegate;

	private final Map<String, Decorator> decoratorMap;

	private Decorator[] decorator;

	public DecoratedConsumer(Consumer delegate,
			Map<String, Decorator> decoratorMap) {
		this.delegate = delegate;
		this.decoratorMap = decoratorMap;
		this.decorator = null;
	}

	@Override
	public void begin(Column[] col) throws IOException {

		decorator = new Decorator[col.length];
		for (int i = 0; i < col.length; i++) {
			final String label = col[i].getLabel();
			if (decoratorMap.containsKey(label)) {
				decorator[i] = decoratorMap.get(label);
			} else {
				decorator[i] = new Decorator() {

					@Override
					public String getLabel() {
						return label;
					}

					@Override
					public Object decorate(Object field) {
						return field;
					}
				};
			}
		}

		Column[] adjusted = new Column[col.length];
		for (int i = 0; i < col.length; i++) {
			adjusted[i] = new Column();
			if (decorator[i].getLabel() == null) {
				adjusted[i].setLabel(col[i].getLabel());
			} else {
				adjusted[i].setLabel(decorator[i].getLabel());
			}
			adjusted[i].setType(col[i].getType());
		}

		delegate.begin(adjusted);
	}

	@Override
	public void consume(Object[] record) throws IOException {
		Object[] adjusted = new Object[record.length];
		for (int i = 0; i < record.length; i++) {
			adjusted[i] = decorator[i].decorate(record[i]);
		}
		delegate.consume(adjusted);
	}

	@Override
	public void end() throws IOException {
		delegate.end();
	}

}
