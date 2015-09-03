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

import static cherry.foundation.testtool.factory.BeanName.JSON_INVOKER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component(JSON_INVOKER)
public class JsonInvokerFactoryBean extends InvokerFactoryBeanSupport {

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	@Override
	protected ObjectMapper getObjectMapper() {
		return objectMapper;
	}

}
