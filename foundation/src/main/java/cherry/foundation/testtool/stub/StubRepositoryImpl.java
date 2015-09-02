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

package cherry.foundation.testtool.stub;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.map.LazyMap;

public class StubRepositoryImpl implements StubRepository {

	private Map<Method, Stub<?>> repository = LazyMap.lazyMap(new HashMap<Method, Stub<?>>(), new Factory<Stub<?>>() {
		@Override
		public Stub<?> create() {
			return new StubImpl<>();
		}
	});

	@Override
	public List<Method> getStubbedMethod() {
		return new ArrayList<>(repository.keySet());
	}

	@Override
	public boolean contains(Method method) {
		return repository.containsKey(method);
	}

	@Override
	public <T> Stub<T> get(Method method) {
		@SuppressWarnings("unchecked")
		Stub<T> stub = (Stub<T>) repository.get(method);
		return stub;
	}

	@Override
	public void clear(Method method) {
		repository.remove(method);
	}

}
