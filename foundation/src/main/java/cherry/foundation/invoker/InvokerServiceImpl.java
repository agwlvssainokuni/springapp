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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cherry.goods.util.ToMapUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvokerServiceImpl implements InvokerService {

	private Invoker invoker;

	private ObjectMapper objectMapper;

	public void setInvoker(Invoker invoker) {
		this.invoker = invoker;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public String invoke(String beanName, String className, String methodName, int numOfArgs, int methodIndex,
			String args, String argTypes) {
		try {
			return invoker.invoke(beanName, className, methodName, numOfArgs, methodIndex, resolveArgs(args),
					resolveArgTypes(argTypes));
		} catch (ClassNotFoundException | NoSuchMethodException ex) {
			return fromThrowableToString(ex);
		} catch (IOException ex) {
			return fromThrowableToString(ex);
		} catch (IllegalStateException ex) {
			if (ex.getCause() instanceof InvocationTargetException || ex.getCause() instanceof IllegalAccessException
					|| ex.getCause() instanceof IOException) {
				return fromThrowableToString(ex.getCause());
			} else {
				return fromThrowableToString(ex);
			}
		} catch (Exception ex) {
			return fromThrowableToString(ex);
		}
	}

	@Override
	public List<Method> resolveMethod(String className, String methodName, int numOfArgs) {
		try {
			return invoker.resolveMethod(className, methodName, numOfArgs);
		} catch (ClassNotFoundException ex) {
			return new ArrayList<>();
		}
	}

	private List<String> resolveArgs(String param) throws JsonProcessingException, IOException {
		if (StringUtils.isEmpty(param)) {
			return null;
		}
		List<String> list = new ArrayList<>();
		for (Object value : objectMapper.readValue(param, List.class)) {
			list.add(objectMapper.writeValueAsString(value));
		}
		return list;
	}

	private List<String> resolveArgTypes(String param) throws JsonProcessingException, IOException {
		if (StringUtils.isEmpty(param)) {
			return null;
		}
		return objectMapper.readValue(param, new TypeReference<List<String>>() {
		});
	}

	private String fromThrowableToString(Throwable ex) {
		Map<String, ?> map = ToMapUtil.fromThrowable(ex, Integer.MAX_VALUE);
		try {
			return objectMapper.writeValueAsString(map);
		} catch (IOException ex2) {
			return ex.getMessage();
		}
	}

}
