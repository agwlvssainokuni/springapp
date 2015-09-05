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

import java.util.LinkedList;
import java.util.List;

public class StubImpl<T> implements Stub<T> {

	private Item<T> always;

	private final List<Item<T>> list = new LinkedList<>();

	@Override
	public boolean hasNext() {
		if (always != null) {
			return true;
		}
		return !list.isEmpty();
	}

	@Override
	public T next() throws Throwable {
		return doNext().getValue();
	}

	@Override
	public T peek() throws Throwable {
		return doPeek().getValue();
	}

	@Override
	public String peekType() throws Throwable {
		return doPeek().getType();
	}

	@Override
	public boolean isThrowable() {
		return doPeek().getThrowable() != null;
	}

	@Override
	public Class<? extends Throwable> nextThrowable() {
		return doNext().getThrowable();
	}

	@Override
	public Class<? extends Throwable> peekThrowable() {
		return doPeek().getThrowable();
	}

	private Item<T> doNext() {
		if (always != null) {
			return always;
		}
		if (list.isEmpty()) {
			throw new IllegalStateException("Empty stub");
		}
		return list.remove(0);
	}

	private Item<T> doPeek() {
		if (always != null) {
			return always;
		}
		if (list.isEmpty()) {
			throw new IllegalStateException("Empty stub");
		}
		return list.get(0);
	}

	@Override
	public Stub<T> clear() {
		always = null;
		list.clear();
		return this;
	}

	@Override
	public <E extends T> Stub<T> alwaysReturn(E value) {
		return alwaysReturn(value, value == null ? null : value.getClass().getCanonicalName());
	}

	@Override
	public <E extends T> Stub<T> alwaysReturn(E value, String type) {
		Item<T> item = new Item<>();
		item.setValue(value);
		item.setType(type);
		always = item;
		list.clear();
		return this;
	}

	@Override
	public <E extends T> Stub<T> thenReturn(E value) {
		return thenReturn(value, value == null ? null : value.getClass().getCanonicalName());
	}

	@Override
	public <E extends T> Stub<T> thenReturn(E value, String type) {
		Item<T> item = new Item<>();
		item.setValue(value);
		item.setType(type);
		list.add(item);
		always = null;
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
	public <E extends T> Stub<T> thenReturn(List<E> list, String type) {
		for (E value : list) {
			thenReturn(value, type);
		}
		return this;
	}

	@Override
	public Stub<T> alwaysThrows(Class<? extends Throwable> klass) {
		Item<T> item = new Item<>();
		item.setThrowable(klass);
		always = item;
		list.clear();
		return this;
	}

	@Override
	public Stub<T> thenThrows(Class<? extends Throwable> klass) {
		Item<T> item = new Item<>();
		item.setThrowable(klass);
		list.add(item);
		always = null;
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

		private String type = null;

		private Class<? extends Throwable> throwable = null;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Class<? extends Throwable> getThrowable() {
			return throwable;
		}

		public void setThrowable(Class<? extends Throwable> throwable) {
			this.throwable = throwable;
		}
	}

}
