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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cherry.foundation.invoker.InvokerImpl;
import cherry.foundation.invoker.InvokerService;
import cherry.foundation.invoker.InvokerServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class InvokerServiceFactoryBeanSupport implements FactoryBean<InvokerService>, ApplicationContextAware,
		InitializingBean {

	private ApplicationContext applicationContext;

	private InvokerService invokerService;

	protected abstract ObjectMapper getObjectMapper();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() {
		InvokerImpl invoker = new InvokerImpl();
		invoker.setObjectMapper(getObjectMapper());
		invoker.setApplicationContext(applicationContext);
		InvokerServiceImpl service = new InvokerServiceImpl();
		service.setObjectMapper(getObjectMapper());
		service.setInvoker(invoker);
		invokerService = service;
	}

	@Override
	public InvokerService getObject() throws Exception {
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
