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

import static cherry.goods.util.ReflectionUtil.getMethodDescription;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StubConfigurer {

	private ObjectMapper objectMapper;

	private List<Resource> resources;

	private StubRepository repository;

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public void setRepository(StubRepository repository) {
		this.repository = repository;
	}

	public void configure() {
		for (Resource r : resources) {
			if (!r.exists()) {
				continue;
			}
			try (InputStream in = r.getInputStream()) {
				Map<Class<?>, Map<String, Config>> map = objectMapper.readValue(in,
						new TypeReference<LinkedHashMap<Class<?>, Map<String, Config>>>() {
						});
				for (Map.Entry<Class<?>, Map<String, Config>> entry : map.entrySet()) {
					Map<String, Method> methodMap = createMethodMap(entry.getKey().getDeclaredMethods());
					for (Map.Entry<String, Config> ent : entry.getValue().entrySet()) {
						if (!methodMap.containsKey(ent.getKey())) {
							continue;
						}
						Method method = methodMap.get(ent.getKey());
						String data = objectMapper.writeValueAsString(ent.getValue().getReturnData());
						JavaType type = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
						if (StringUtils.isNotEmpty(ent.getValue().getReturnType())) {
							type = objectMapper.getTypeFactory().constructFromCanonical(ent.getValue().getReturnType());
						}
						Object v = objectMapper.readValue(data, type);
						repository.get(method).alwaysReturn(v);
					}
				}
			} catch (IOException ex) {
				throw new IllegalStateException(ex);
			}
		}
	}

	private Map<String, Method> createMethodMap(Method[] methods) {
		Map<String, Method> map = new HashMap<>();
		for (Method m : methods) {
			map.put(getMethodDescription(m, false, false, true, true, false), m);
			map.put(getMethodDescription(m, false, false, true, true, true), m);
		}
		return map;
	}

	public static class Config {

		private String returnType;

		private Object returnData;

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

		public String getReturnType() {
			return returnType;
		}

		public void setReturnType(String returnType) {
			this.returnType = returnType;
		}

		public Object getReturnData() {
			return returnData;
		}

		public void setReturnData(Object returnData) {
			this.returnData = returnData;
		}
	}

}
