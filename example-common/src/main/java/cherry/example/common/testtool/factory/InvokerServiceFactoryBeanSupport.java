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

package cherry.example.common.testtool.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import cherry.foundation.testtool.invoker.Invoker;
import cherry.foundation.testtool.invoker.InvokerService;
import cherry.foundation.testtool.invoker.InvokerServiceImpl;
import cherry.foundation.testtool.reflect.ReflectionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class InvokerServiceFactoryBeanSupport implements FactoryBean<InvokerService>, InitializingBean {

	@Autowired
	private ReflectionResolver reflectionResolver;

	private InvokerService invokerService;

	protected abstract ObjectMapper getObjectMapper();

	protected abstract Invoker getInvoker();

	@Override
	public void afterPropertiesSet() {
		InvokerServiceImpl service = new InvokerServiceImpl();
		service.setReflectionResolver(reflectionResolver);
		service.setObjectMapper(getObjectMapper());
		service.setInvoker(getInvoker());
		invokerService = service;
	}

	@Override
	public InvokerService getObject() {
		return invokerService;
	}

	@Override
	public Class<?> getObjectType() {
		return InvokerService.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
