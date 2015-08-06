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

package cherry.foundation.invoker;

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvokerImpl implements Invoker, ApplicationContextAware {

	private ObjectMapper objectMapper;

	private ApplicationContext appCtx;

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.appCtx = applicationContext;
	}

	@Override
	public String invoke(String beanName, String className, String methodName, int numOfArgs, int methodIndex,
			List<String> args, List<String> argTypes) throws ClassNotFoundException, NoSuchMethodException {
		try {

			Class<?> beanClass = getClass().getClassLoader().loadClass(className);
			Object targetBean;
			if (StringUtils.isEmpty(beanName)) {
				targetBean = appCtx.getBean(beanClass);
			} else {
				targetBean = appCtx.getBean(beanName, beanClass);
			}

			List<Method> methodList = getMethodList(beanClass, methodName, numOfArgs);
			if (methodList.isEmpty()) {
				throw new NoSuchMethodException(format("{0}#{1}() not found", className, methodName));
			}
			Method method = (methodList.size() == 1 ? methodList.get(0) : methodList.get(methodIndex));
			Class<?>[] paramType = method.getParameterTypes();
			Object[] param = new Object[paramType.length];
			for (int i = 0; i < paramType.length; i++) {
				String arg = getOrNull(args, i);
				String argType = getOrNull(argTypes, i);
				if (StringUtils.isNotEmpty(argType)) {
					param[i] = resolve(arg, getClass().getClassLoader().loadClass(argType));
				} else {
					param[i] = resolve(arg, paramType[i]);
				}
			}

			Object result = method.invoke(targetBean, param);
			return convert(result);
		} catch (InvocationTargetException | IllegalAccessException | IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public List<Method> resolveMethod(String className, String methodName, int numOfArgs) throws ClassNotFoundException {
		Class<?> beanClass = getClass().getClassLoader().loadClass(className);
		return getMethodList(beanClass, methodName, numOfArgs);
	}

	private List<Method> getMethodList(Class<?> beanClass, String methodName, int numOfArgs) {
		List<Method> list = new ArrayList<>();
		for (Method m : beanClass.getDeclaredMethods()) {
			if (StringUtils.equals(m.getName(), methodName)) {
				if (numOfArgs < 0 || numOfArgs == m.getParameterTypes().length) {
					list.add(m);
				}
			}
		}
		return list;
	}

	private String getOrNull(List<String> list, int index) {
		if (list == null) {
			return null;
		} else if (list.size() <= index) {
			return null;
		} else {
			return list.get(index);
		}
	}

	private Object resolve(String arg, Class<?> paramType) throws JsonProcessingException, IOException {
		if (arg == null) {
			return null;
		}
		return objectMapper.readValue(arg, paramType);
	}

	private String convert(Object result) throws JsonProcessingException {
		return objectMapper.writeValueAsString(result);
	}

}
