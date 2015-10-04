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

package cherry.common.testtool;

import static cherry.goods.util.ReflectionUtil.getMethodDescription;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import cherry.foundation.testtool.stub.StubService;

@Controller
public class StubReposControllerImpl implements StubReposController {

	@Autowired
	@Qualifier("jsonStubService")
	private StubService jsonStubService;

	@Autowired
	@Qualifier("yamlStubService")
	private StubService yamlStubService;

	@Override
	public String alwaysReturnJson(String className, String methodName, int methodIndex, String value, String valueType) {
		if (StringUtils.isEmpty(value)) {
			return jsonStubService.clear(className, methodName, methodIndex);
		} else {
			return jsonStubService.alwaysReturn(className, methodName, methodIndex, value, valueType);
		}
	}

	@Override
	public String alwaysReturnYaml(String className, String methodName, int methodIndex, String value, String valueType) {
		if (StringUtils.isEmpty(value)) {
			return yamlStubService.clear(className, methodName, methodIndex);
		} else {
			return yamlStubService.alwaysReturn(className, methodName, methodIndex, value, valueType);
		}
	}

	@Override
	public List<String> peekStubJson(String className, String methodName, int methodIndex) {
		if (!jsonStubService.hasNext(className, methodName, methodIndex)) {
			return null;
		}
		List<String> list = new ArrayList<>();
		list.add(jsonStubService.peek(className, methodName, methodIndex));
		list.add(jsonStubService.peekType(className, methodName, methodIndex));
		return list;
	}

	@Override
	public List<String> peekStubYaml(String className, String methodName, int methodIndex) {
		if (!jsonStubService.hasNext(className, methodName, methodIndex)) {
			return null;
		}
		List<String> list = new ArrayList<>();
		list.add(yamlStubService.peek(className, methodName, methodIndex));
		list.add(yamlStubService.peekType(className, methodName, methodIndex));
		return list;
	}

	@Override
	public List<String> resolveBeanName(String className) {
		return jsonStubService.resolveBeanName(className);
	}

	@Override
	public List<String> resolveMethod(String className, String methodName) {
		List<String> list = new ArrayList<>();
		for (Method m : jsonStubService.resolveMethod(className, methodName)) {
			list.add(getMethodDescription(m, false, false, false, true, false));
		}
		return list;
	}

	@Override
	public List<String> getStubbedMethod(String className) {
		List<String> list = new ArrayList<>();
		for (Method m : jsonStubService.getStubbedMethod(className)) {
			list.add(getMethodDescription(m, false, true, true, true, true));
		}
		return list;
	}

}
