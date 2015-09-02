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

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cherry.foundation.testtool.reflect.ReflectionResolver;
import cherry.goods.util.ToMapUtil;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StubConfigServiceImpl implements StubConfigService {

	private ReflectionResolver reflectionResolver;

	private StubRepository repository;

	private ObjectMapper objectMapper;

	public void setReflectionResolver(ReflectionResolver reflectionResolver) {
		this.reflectionResolver = reflectionResolver;
	}

	public void setRepository(StubRepository repository) {
		this.repository = repository;
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public List<String> resolveBeanName(String className) {
		try {
			return reflectionResolver.resolveBeanName(className);
		} catch (ClassNotFoundException ex) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Method> resolveMethod(String className, String methodName, int numOfArgs) {
		try {
			return reflectionResolver.resolveMethod(className, methodName, numOfArgs);
		} catch (ClassNotFoundException ex) {
			return new ArrayList<>();
		}
	}

	@Override
	public String clear(String className, String methodName, int numOfArgs, int methodIndex) {
		return execute(className, methodName, numOfArgs, methodIndex, new Callback() {
			@Override
			public void apply(Method method) {
				repository.clear(method);
			}
		});
	}

	@Override
	public String alwaysReturn(String className, String methodName, int numOfArgs, int methodIndex, final String value,
			final String valueType) {
		return execute(className, methodName, numOfArgs, methodIndex, new Callback() {
			@Override
			public void apply(Method method) throws IOException {
				JavaType returnType = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
				if (StringUtils.isNotEmpty(valueType)) {
					returnType = objectMapper.getTypeFactory().constructFromCanonical(valueType);
				}
				Object v = objectMapper.readValue(value, returnType);
				repository.get(method).alwaysReturn(v);
			}
		});
	}

	@Override
	public String thenReturn(String className, String methodName, int numOfArgs, int methodIndex, final String value,
			final String valueType) {
		return execute(className, methodName, numOfArgs, methodIndex, new Callback() {
			@Override
			public void apply(Method method) throws IOException {
				JavaType returnType = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
				if (StringUtils.isNotEmpty(valueType)) {
					returnType = objectMapper.getTypeFactory().constructFromCanonical(valueType);
				}
				Object v = objectMapper.readValue(value, returnType);
				repository.get(method).thenReturn(v);
			}
		});
	}

	@Override
	public String thenReturn(String className, String methodName, int numOfArgs, int methodIndex,
			final List<String> list, final String valueType) {
		return execute(className, methodName, numOfArgs, methodIndex, new Callback() {
			@Override
			public void apply(Method method) throws IOException {
				JavaType returnType = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
				if (StringUtils.isNotEmpty(valueType)) {
					returnType = objectMapper.getTypeFactory().constructFromCanonical(valueType);
				}
				List<Object> valueList = new ArrayList<>(list.size());
				for (String value : list) {
					Object v = objectMapper.readValue(value, returnType);
					valueList.add(v);
				}
				repository.get(method).thenReturn(valueList);
			}
		});
	}

	@Override
	public String alwaysThrows(String className, String methodName, int numOfArgs, int methodIndex,
			final String throwableClassName) {
		return execute(className, methodName, numOfArgs, methodIndex, new Callback() {
			@Override
			public void apply(Method method) throws IOException {
				JavaType type = objectMapper.getTypeFactory().constructFromCanonical(throwableClassName);
				@SuppressWarnings("unchecked")
				Class<? extends Throwable> klass = (Class<? extends Throwable>) type.getRawClass();
				repository.get(method).alwaysThrows(klass);
			}
		});
	}

	@Override
	public String thenThrows(String className, String methodName, int numOfArgs, int methodIndex,
			final String throwableClassName) {
		return execute(className, methodName, numOfArgs, methodIndex, new Callback() {
			@Override
			public void apply(Method method) throws IOException {
				JavaType type = objectMapper.getTypeFactory().constructFromCanonical(throwableClassName);
				@SuppressWarnings("unchecked")
				Class<? extends Throwable> klass = (Class<? extends Throwable>) type.getRawClass();
				repository.get(method).thenThrows(klass);
			}
		});
	}

	@Override
	public String thenThrows(String className, String methodName, int numOfArgs, int methodIndex,
			final List<String> list) {
		return execute(className, methodName, numOfArgs, methodIndex, new Callback() {
			@Override
			public void apply(Method method) throws IOException {
				List<Class<? extends Throwable>> klassList = new ArrayList<>(list.size());
				for (String throwableClassName : list) {
					JavaType type = objectMapper.getTypeFactory().constructFromCanonical(throwableClassName);
					@SuppressWarnings("unchecked")
					Class<? extends Throwable> klass = (Class<? extends Throwable>) type.getRawClass();
					klassList.add(klass);
				}
				repository.get(method).thenThrows(klassList);
			}
		});
	}

	private String execute(String className, String methodName, int numOfArgs, int methodIndex, Callback callback) {
		try {
			List<Method> list = reflectionResolver.resolveMethod(className, methodName, numOfArgs);
			if (methodIndex >= list.size()) {
				return objectMapper.writeValueAsString(Boolean.FALSE);
			}
			callback.apply(list.get(methodIndex));
			return objectMapper.writeValueAsString(Boolean.TRUE);
		} catch (ClassNotFoundException | IOException | IllegalArgumentException ex) {
			Map<String, ?> map = ToMapUtil.fromThrowable(ex, Integer.MAX_VALUE);
			try {
				return objectMapper.writeValueAsString(map);
			} catch (IOException ex2) {
				return ex.getMessage();
			}
		}
	}

	interface Callback {
		void apply(Method method) throws IOException;
	}

}
