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

package cherry.spring.common.invoker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import cherry.foundation.invoker.InvokerService;

import com.google.common.base.Joiner;

@Controller
public class InvokerControllerImpl implements InvokerController {

	@Autowired
	@Qualifier("jsonInvokerService")
	private InvokerService jsonInvokerService;

	@Autowired
	@Qualifier("yamlInvokerService")
	private InvokerService yamlInvokerService;

	@Override
	public String invokeJson(String beanName, String className, String methodName, int numOfArgs, int methodIndex,
			String args, String argTypes) {
		return jsonInvokerService.invoke(beanName, className, methodName, numOfArgs, methodIndex, args, argTypes);
	}

	@Override
	public String invokeYaml(String beanName, String className, String methodName, int numOfArgs, int methodIndex,
			String args, String argTypes) {
		return yamlInvokerService.invoke(beanName, className, methodName, numOfArgs, methodIndex, args, argTypes);
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

}
