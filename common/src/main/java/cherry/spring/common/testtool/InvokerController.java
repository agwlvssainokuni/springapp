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

package cherry.spring.common.testtool;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/invoker")
public interface InvokerController {

	@RequestMapping({ "", "json" })
	@ResponseBody()
	String invokeJson(@RequestParam(value = "beanName", required = false) String beanName,
			@RequestParam("className") String className, @RequestParam("methodName") String methodName,
			@RequestParam(value = "numOfArgs", defaultValue = "-1") int numOfArgs,
			@RequestParam(value = "methodIndex", defaultValue = "0") int methodIndex,
			@RequestParam("args") String args, @RequestParam("argTypes") String argTypes);

	@RequestMapping({ "yaml" })
	@ResponseBody()
	String invokeYaml(@RequestParam(value = "beanName", required = false) String beanName,
			@RequestParam("className") String className, @RequestParam("methodName") String methodName,
			@RequestParam(value = "numOfArgs", defaultValue = "-1") int numOfArgs,
			@RequestParam(value = "methodIndex", defaultValue = "0") int methodIndex,
			@RequestParam("args") String args, @RequestParam("argTypes") String argTypes);

	@RequestMapping(value = { "", "json", "yaml" }, params = { "bean" })
	@ResponseBody()
	List<String> resolveBeanName(@RequestParam("className") String className);

	@RequestMapping(value = { "", "json", "yaml" }, params = { "method" })
	@ResponseBody()
	List<String> resolveMethod(@RequestParam("className") String className,
			@RequestParam("methodName") String methodName,
			@RequestParam(value = "numOfArgs", defaultValue = "-1") int numOfArgs);

}
