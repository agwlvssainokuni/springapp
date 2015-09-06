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
import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class StubServiceImpl implements StubService {

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
	public List<Method> resolveMethod(String className, String methodName) {
		try {
			return reflectionResolver.resolveMethod(className, methodName);
		} catch (ClassNotFoundException ex) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Method> getStubbedMethod(String className) {
		try {
			if (StringUtils.isEmpty(className)) {
				return repository.getStubbedMethod();
			}
			Class<?> klass = objectMapper.getTypeFactory().constructFromCanonical(className).getRawClass();
			List<Method> list = new ArrayList<>();
			for (Method m : repository.getStubbedMethod()) {
				if (klass == m.getDeclaringClass()) {
					list.add(m);
				}
			}
			return list;
		} catch (IllegalArgumentException ex) {
			return new ArrayList<>();
		}
	}

	@Override
	public boolean hasNext(String className, String methodName, int methodIndex) {
		return execute(className, methodName, methodIndex, new Predicate<Method>() {
			@Override
			public boolean apply(Method method) {
				return repository.get(method).hasNext();
			}
		});
	}

	@Override
	public String peek(String className, String methodName, int methodIndex) {
		return executeWithMapping(className, methodName, methodIndex, new Function<Method, Object>() {
			@Override
			public Object apply(Method method) {
				return repository.get(method).peek();
			}
		});
	}

	@Override
	public String peekType(String className, String methodName, int methodIndex) {
		return execute(className, methodName, methodIndex, new Function<Method, String>() {
			@Override
			public String apply(Method method) {
				return repository.get(method).peekType();
			}
		});
	}

	@Override
	public boolean isThrowable(String className, String methodName, int methodIndex) {
		return execute(className, methodName, methodIndex, new Predicate<Method>() {
			@Override
			public boolean apply(Method method) {
				return repository.get(method).isThrowable();
			}
		});
	}

	@Override
	public String peekThrowable(String className, String methodName, int methodIndex) {
		return execute(className, methodName, methodIndex, new Function<Method, String>() {
			@Override
			public String apply(Method method) {
				return repository.get(method).peekThrowable().getCanonicalName();
			}
		});
	}

	@Override
	public String clear(String className, String methodName, int methodIndex) {
		return executeWithMapping(className, methodName, methodIndex, new Function<Method, Boolean>() {
			@Override
			public Boolean apply(Method method) {
				repository.clear(method);
				return Boolean.TRUE;
			}
		});
	}

	@Override
	public String alwaysReturn(String className, String methodName, int methodIndex, final String value,
			final String valueType) {
		return executeWithMapping(className, methodName, methodIndex, new Function<Method, Object>() {
			@Override
			public Object apply(Method method) {
				JavaType returnType = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
				if (StringUtils.isNotEmpty(valueType)) {
					returnType = objectMapper.getTypeFactory().constructFromCanonical(valueType);
				}
				try {
					Object v = objectMapper.readValue(value, returnType);
					repository.get(method).alwaysReturn(v, returnType.toCanonical());
					return Boolean.TRUE;
				} catch (IOException ex) {
					return ToMapUtil.fromThrowable(ex, Integer.MAX_VALUE);
				}
			}
		});
	}

	@Override
	public String thenReturn(String className, String methodName, int methodIndex, final String value,
			final String valueType) {
		return executeWithMapping(className, methodName, methodIndex, new Function<Method, Object>() {
			@Override
			public Object apply(Method method) {
				JavaType returnType = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
				if (StringUtils.isNotEmpty(valueType)) {
					returnType = objectMapper.getTypeFactory().constructFromCanonical(valueType);
				}
				try {
					Object v = objectMapper.readValue(value, returnType);
					repository.get(method).thenReturn(v, returnType.toCanonical());
					return Boolean.TRUE;
				} catch (IOException ex) {
					return ToMapUtil.fromThrowable(ex, Integer.MAX_VALUE);
				}
			}
		});
	}

	@Override
	public String thenReturn(String className, String methodName, int methodIndex, final List<String> list,
			final String valueType) {
		return executeWithMapping(className, methodName, methodIndex, new Function<Method, Object>() {
			@Override
			public Object apply(Method method) {
				JavaType returnType = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
				if (StringUtils.isNotEmpty(valueType)) {
					returnType = objectMapper.getTypeFactory().constructFromCanonical(valueType);
				}
				try {
					List<Object> valueList = new ArrayList<>(list.size());
					for (String value : list) {
						Object v = objectMapper.readValue(value, returnType);
						valueList.add(v);
					}
					repository.get(method).thenReturn(valueList, returnType.toCanonical());
					return Boolean.TRUE;
				} catch (IOException ex) {
					return ToMapUtil.fromThrowable(ex, Integer.MAX_VALUE);
				}
			}
		});
	}

	@Override
	public String alwaysThrows(String className, String methodName, int methodIndex, final String throwableClassName) {
		return executeWithMapping(className, methodName, methodIndex, new Function<Method, Boolean>() {
			@Override
			public Boolean apply(Method method) {
				JavaType type = objectMapper.getTypeFactory().constructFromCanonical(throwableClassName);
				@SuppressWarnings("unchecked")
				Class<? extends Throwable> klass = (Class<? extends Throwable>) type.getRawClass();
				repository.get(method).alwaysThrows(klass);
				return Boolean.TRUE;
			}
		});
	}

	@Override
	public String thenThrows(String className, String methodName, int methodIndex, final String throwableClassName) {
		return executeWithMapping(className, methodName, methodIndex, new Function<Method, Boolean>() {
			@Override
			public Boolean apply(Method method) {
				JavaType type = objectMapper.getTypeFactory().constructFromCanonical(throwableClassName);
				@SuppressWarnings("unchecked")
				Class<? extends Throwable> klass = (Class<? extends Throwable>) type.getRawClass();
				repository.get(method).thenThrows(klass);
				return Boolean.TRUE;
			}
		});
	}

	@Override
	public String thenThrows(String className, String methodName, int methodIndex, final List<String> list) {
		return executeWithMapping(className, methodName, methodIndex, new Function<Method, Boolean>() {
			@Override
			public Boolean apply(Method method) {
				List<Class<? extends Throwable>> klassList = new ArrayList<>(list.size());
				for (String throwableClassName : list) {
					JavaType type = objectMapper.getTypeFactory().constructFromCanonical(throwableClassName);
					@SuppressWarnings("unchecked")
					Class<? extends Throwable> klass = (Class<? extends Throwable>) type.getRawClass();
					klassList.add(klass);
				}
				repository.get(method).thenThrows(klassList);
				return Boolean.TRUE;
			}
		});
	}

	private boolean execute(String className, String methodName, int methodIndex, Predicate<Method> predicate) {
		try {
			List<Method> list = reflectionResolver.resolveMethod(className, methodName);
			if (methodIndex >= list.size()) {
				return false;
			}
			return predicate.apply(list.get(methodIndex));
		} catch (ClassNotFoundException ex) {
			return false;
		}
	}

	private String execute(String className, String methodName, int methodIndex, Function<Method, String> function) {
		try {
			List<Method> list = reflectionResolver.resolveMethod(className, methodName);
			if (methodIndex >= list.size()) {
				return String.valueOf(false);
			}
			return function.apply(list.get(methodIndex));
		} catch (ClassNotFoundException ex) {
			return ex.getMessage();
		}
	}

	private <T> String executeWithMapping(String className, String methodName, int methodIndex,
			Function<Method, T> function) {
		try {
			List<Method> list = reflectionResolver.resolveMethod(className, methodName);
			if (methodIndex >= list.size()) {
				return objectMapper.writeValueAsString(Boolean.FALSE);
			}
			T result = function.apply(list.get(methodIndex));
			return objectMapper.writeValueAsString(result);
		} catch (ClassNotFoundException | IOException | IllegalArgumentException ex) {
			Map<String, ?> map = ToMapUtil.fromThrowable(ex, Integer.MAX_VALUE);
			try {
				return objectMapper.writeValueAsString(map);
			} catch (IOException ex2) {
				return ex.getMessage();
			}
		}
	}

}
