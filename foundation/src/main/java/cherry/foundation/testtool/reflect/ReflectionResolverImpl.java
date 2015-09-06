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

import static java.util.Arrays.asList;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ReflectionResolverImpl implements ReflectionResolver, ApplicationContextAware {

	private ApplicationContext appCtx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		appCtx = applicationContext;
	}

	@Override
	public List<String> resolveBeanName(String beanClassName) throws ClassNotFoundException {
		Class<?> beanClass = getClass().getClassLoader().loadClass(beanClassName);
		return resolveBeanName(beanClass);
	}

	@Override
	public List<String> resolveBeanName(Class<?> beanClass) {
		return asList(appCtx.getBeanNamesForType(beanClass));
	}

	@Override
	public List<Method> resolveMethod(String beanClassName, String methodName) throws ClassNotFoundException {
		Class<?> beanClass = getClass().getClassLoader().loadClass(beanClassName);
		return resolveMethod(beanClass, methodName);
	}

	@Override
	public List<Method> resolveMethod(Class<?> beanClass, String methodName) {
		List<Method> list = new ArrayList<>();
		for (Method m : beanClass.getDeclaredMethods()) {
			if (StringUtils.equals(m.getName(), methodName)) {
				list.add(m);
			}
		}
		return list;
	}

}
