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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvokerImpl implements Invoker, ApplicationContextAware {

	@Autowired
	private ConversionService conversionService;

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
	public String invoke(String beanName, String className, String methodName, String... args) {
		try {

			Class<?> beanClass = getClass().getClassLoader().loadClass(className);
			Object targetBean;
			if (StringUtils.isEmpty(beanName)) {
				targetBean = appCtx.getBean(beanClass);
			} else {
				targetBean = appCtx.getBean(beanName, beanClass);
			}

			Method method = null;
			for (Method m : beanClass.getDeclaredMethods()) {
				if (StringUtils.equals(m.getName(), methodName)) {
					method = m;
					break;
				}
			}
			if (method == null) {
				throw new IllegalArgumentException(format("{0}#{1}() not found", className, methodName));
			}
			Class<?>[] paramType = method.getParameterTypes();
			Object[] param = new Object[paramType.length];
			for (int i = 0; i < paramType.length; i++) {
				param[i] = resolve(args[i], paramType[i]);
			}

			Object result = method.invoke(targetBean, param);
			return convert(result);
		} catch (ClassNotFoundException ex) {
			throw new IllegalArgumentException(format("{0}#{1}() not found", className, methodName), ex);
		} catch (InvocationTargetException | IllegalAccessException | IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private Object resolve(String arg, Class<?> paramType) throws JsonProcessingException, IOException {
		if (arg == null) {
			return null;
		}
		if (conversionService.canConvert(arg.getClass(), paramType)) {
			return conversionService.convert(arg, paramType);
		}
		return objectMapper.readValue(arg, paramType);
	}

	private String convert(Object result) throws JsonProcessingException {
		if (result == null) {
			return null;
		}
		if (conversionService.canConvert(result.getClass(), String.class)) {
			return conversionService.convert(result, String.class);
		}
		return objectMapper.writeValueAsString(result);
	}

}
