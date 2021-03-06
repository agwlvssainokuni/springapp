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

package cherry.foundation.testtool.factory;

import static cherry.foundation.testtool.factory.BeanName.YAML_INVOKER;
import static cherry.foundation.testtool.factory.BeanName.YAML_INVOKER_SERVICE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cherry.foundation.testtool.invoker.Invoker;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component(YAML_INVOKER_SERVICE)
public class YamlInvokerServiceFactoryBean extends InvokerServiceFactoryBeanSupport {

	@Autowired
	@Qualifier("yamlObjectMapper")
	private ObjectMapper objectMapper;

	@Autowired
	@Qualifier(YAML_INVOKER)
	private Invoker invoker;

	@Override
	protected ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	@Override
	protected Invoker getInvoker() {
		return invoker;
	}

}
