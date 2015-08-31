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

package cherry.foundation.stub;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
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
				Map<Class<?>, List<StubConfig>> map = objectMapper.readValue(in,
						new TypeReference<LinkedHashMap<Class<?>, List<StubConfig>>>() {
						});
				for (Map.Entry<Class<?>, List<StubConfig>> entry : map.entrySet()) {
					for (StubConfig config : entry.getValue()) {
						String data = objectMapper.writeValueAsString(config.getReturnData());
						for (Method m : entry.getKey().getDeclaredMethods()) {
							if (!m.getName().equals(config.getMethodName())) {
								continue;
							}
							Class<?> returnType = m.getReturnType();
							if (m.getReturnType().isAssignableFrom(config.getReturnType())) {
								returnType = config.getReturnType();
							}
							Object value = objectMapper.readValue(data, returnType);
							repository.get(m).alwaysReturn(value);
						}
					}
				}
			} catch (IOException ex) {
				throw new IllegalStateException(ex);
			}
		}
	}

	public static class StubConfig {

		private String methodName;

		private Class<?> returnType;

		private Object returnData;

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		public Class<?> getReturnType() {
			return returnType;
		}

		public void setReturnType(Class<?> returnType) {
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
