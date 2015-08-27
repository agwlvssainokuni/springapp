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

package cherry.foundation.stub;

import java.util.LinkedList;
import java.util.List;

public class StubImpl<T> implements Stub<T> {

	private final List<Item<T>> list = new LinkedList<>();

	@Override
	public boolean hasNext() {
		return !list.isEmpty();
	}

	@Override
	public T next() throws Throwable {
		Item<T> item = list.remove(0);
		if (item.getThrowable() == null) {
			return item.getValue();
		} else {
			throw item.getThrowable().newInstance();
		}
	}

	@Override
	public boolean isThrowable() {
		return list.get(0).getThrowable() != null;
	}

	@Override
	public Class<? extends Throwable> nextThrowable() {
		Item<T> item = list.remove(0);
		if (item.getThrowable() == null) {
			throw new IllegalStateException();
		} else {
			return item.getThrowable();
		}
	}

	@Override
	public Stub<T> clear() {
		list.clear();
		return this;
	}

	@Override
	public <E extends T> Stub<T> thenReturn(E value) {
		Item<T> item = new Item<>();
		item.setValue(value);
		list.add(item);
		return this;
	}

	@Override
	public <E extends T> Stub<T> thenReturn(List<E> list) {
		for (E value : list) {
			thenReturn(value);
		}
		return this;
	}

	@Override
	public Stub<T> thenThrows(Class<? extends Throwable> klass) {
		Item<T> item = new Item<>();
		item.setThrowable(klass);
		list.add(item);
		return this;
	}

	@Override
	public Stub<T> thenThrows(List<Class<? extends Throwable>> list) {
		for (Class<? extends Throwable> klass : list) {
			thenThrows(klass);
		}
		return this;
	}

	static class Item<T> {

		private T value = null;

		private Class<? extends Throwable> throwable = null;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Class<? extends Throwable> getThrowable() {
			return throwable;
		}

		public void setThrowable(Class<? extends Throwable> throwable) {
			this.throwable = throwable;
		}
	}

}
