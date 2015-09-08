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
import java.util.List;

public interface StubService {

	List<String> resolveBeanName(String className);

	List<Method> resolveMethod(String className, String methodName);

	List<Method> getStubbedMethod(String className);

	boolean hasNext(String className, String methodName, int methodIndex);

	String peek(String className, String methodName, int methodIndex);

	String peekType(String className, String methodName, int methodIndex);

	boolean isThrowable(String className, String methodName, int methodIndex);

	String peekThrowable(String className, String methodName, int methodIndex);

	String clear(String className, String methodName, int methodIndex);

	String alwaysReturn(String className, String methodName, int methodIndex, String value, String valueType);

	String thenReturn(String className, String methodName, int methodIndex, String value, String valueType);

	String alwaysThrows(String className, String methodName, int methodIndex, String throwableClassName);

	String thenThrows(String className, String methodName, int methodIndex, String throwableClassName);

}
