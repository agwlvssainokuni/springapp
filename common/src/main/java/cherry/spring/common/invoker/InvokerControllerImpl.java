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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import cherry.foundation.invoker.Invoker;
import cherry.goods.util.ToMapUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class InvokerControllerImpl implements InvokerController {

	@Autowired
	private Invoker invoker;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	@Override
	public String invoke(String beanName, String className, String methodName, int methodIndex, List<String> args,
			List<String> argTypes) {
		try {
			return invoker.invoke(beanName, className, methodName, methodIndex, args, argTypes);
		} catch (ClassNotFoundException | NoSuchMethodException ex) {
			return fromThrowableToString(ex);
		} catch (IllegalStateException ex) {
			return fromThrowableToString(ex.getCause());
		}
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
