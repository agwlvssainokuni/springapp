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

package cherry.spring.common.stub;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import cherry.foundation.invoker.InvokerService;
import cherry.foundation.stub.StubRepository;
import cherry.goods.util.ToMapUtil;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;

@Controller
public class StubReposControllerImpl implements StubReposController {

	@Autowired
	private StubRepository stubRepository;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper jsonObjectMapper;

	@Autowired
	@Qualifier("yamlObjectMapper")
	private ObjectMapper yamlObjectMapper;

	@Autowired
	@Qualifier("jsonInvokerService")
	private InvokerService jsonInvokerService;

	@Override
	public String alwaysReturnJson(String className, String methodName, int numOfArgs, int methodIndex, String value,
			String valueType) {
		return alwaysReturn(jsonObjectMapper, className, methodName, numOfArgs, methodIndex, value, valueType);
	}

	@Override
	public String alwaysReturnYaml(String className, String methodName, int numOfArgs, int methodIndex, String value,
			String valueType) {
		return alwaysReturn(yamlObjectMapper, className, methodName, numOfArgs, methodIndex, value, valueType);
	}

	@Override
	public List<String> resolveBeanName(String className) {
		return jsonInvokerService.resolveBeanName(className);
	}

	@Override
	public List<String> resolveMethod(String className, String methodName, int numOfArgs) {
		List<String> list = new ArrayList<>();
		for (Method m : jsonInvokerService.resolveMethod(className, methodName, numOfArgs)) {
			List<String> names = new ArrayList<>(m.getParameterTypes().length);
			for (Class<?> t : m.getParameterTypes()) {
				names.add(t.getSimpleName());
			}
			list.add(Joiner.on(",").join(names));
		}
		return list;
	}

	public String alwaysReturn(ObjectMapper objectMapper, String className, String methodName, int numOfArgs,
			int methodIndex, String value, String valueType) {
		try {

			List<Method> list = jsonInvokerService.resolveMethod(className, methodName, numOfArgs);
			if (methodIndex >= list.size()) {
				return objectMapper.writeValueAsString(Boolean.FALSE);
			}

			Method method = list.get(methodIndex);
			JavaType returnType = objectMapper.getTypeFactory().constructType(method.getGenericReturnType());
			if (StringUtils.isNotEmpty(valueType)) {
				returnType = objectMapper.getTypeFactory().constructFromCanonical(valueType);
			}

			if (StringUtils.isEmpty(value)) {
				stubRepository.clear(method);
			} else {
				Object v = objectMapper.readValue(value, returnType);
				stubRepository.get(method).alwaysReturn(v);
			}

			return objectMapper.writeValueAsString(Boolean.TRUE);

		} catch (IOException | IllegalArgumentException ex) {
			Map<String, ?> map = ToMapUtil.fromThrowable(ex, Integer.MAX_VALUE);
			try {
				return objectMapper.writeValueAsString(map);
			} catch (IOException ex2) {
				return ex.getMessage();
			}
		}
	}

}
