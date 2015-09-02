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

package cherry.foundation.testtool.reflect;

import java.lang.reflect.Method;
import java.util.List;

public interface ReflectionResolver {

	List<String> resolveBeanName(String beanClassName) throws ClassNotFoundException;

	List<String> resolveBeanName(Class<?> beanClass);

	List<Method> resolveMethod(String beanClassName, String methodName, int numOfArgs) throws ClassNotFoundException;

	List<Method> resolveMethod(Class<?> beanClass, String methodName, int numOfArgs);

}
