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

import java.util.List;

public interface Stub<T> {

	boolean hasNext();

	T next();

	T peek();

	String peekType();

	boolean isThrowable();

	Class<? extends Throwable> nextThrowable();

	Class<? extends Throwable> peekThrowable();

	Stub<T> clear();

	<E extends T> Stub<T> alwaysReturn(E value);

	<E extends T> Stub<T> alwaysReturn(E value, String type);

	<E extends T> Stub<T> thenReturn(E value);

	<E extends T> Stub<T> thenReturn(E value, String type);

	<E extends T> Stub<T> thenReturn(List<E> list);

	<E extends T> Stub<T> thenReturn(List<E> list, String type);

	Stub<T> alwaysThrows(Class<? extends Throwable> klass);

	Stub<T> thenThrows(Class<? extends Throwable> klass);

	Stub<T> thenThrows(List<Class<? extends Throwable>> list);

}
