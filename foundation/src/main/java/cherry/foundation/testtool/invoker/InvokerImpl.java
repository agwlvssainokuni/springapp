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

package cherry.foundation.testtool.invoker;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
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
	public String invoke(String beanName, Class<?> beanClass, Method method, List<String> args, List<String> argTypes) {
		try {

			Object targetBean;
			if (StringUtils.isEmpty(beanName)) {
				targetBean = appCtx.getBean(beanClass);
			} else {
				targetBean = appCtx.getBean(beanName, beanClass);
			}

			Type[] paramType = method.getGenericParameterTypes();
			Object[] param = new Object[paramType.length];
			for (int i = 0; i < paramType.length; i++) {
				String arg = getOrNull(args, i);
				String argType = getOrNull(argTypes, i);
				JavaType javaType;
				if (StringUtils.isNotEmpty(argType)) {
					javaType = objectMapper.getTypeFactory().constructFromCanonical(argType);
				} else {
					javaType = objectMapper.getTypeFactory().constructType(paramType[i]);
				}
				param[i] = resolve(arg, javaType);
			}

			Object result = method.invoke(targetBean, param);
			return convert(result);
		} catch (InvocationTargetException | IllegalAccessException | IOException ex) {
			throw new IllegalStateException(ex);
		}
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

	private Object resolve(String arg, JavaType paramType) throws JsonProcessingException, IOException {
		if (arg == null) {
			return null;
		}
		return objectMapper.readValue(arg, paramType);
	}

	private String convert(Object result) throws JsonProcessingException {
		return objectMapper.writeValueAsString(result);
	}

}
