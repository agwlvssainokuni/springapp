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

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cherry.foundation.testtool.invoker.Invoker;
import cherry.foundation.testtool.invoker.InvokerImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class InvokerFactoryBeanSupport implements FactoryBean<Invoker>, ApplicationContextAware,
		InitializingBean {

	private ApplicationContext applicationContext;

	private Invoker invoker;

	protected abstract ObjectMapper getObjectMapper();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() {
		InvokerImpl impl = new InvokerImpl();
		impl.setObjectMapper(getObjectMapper());
		impl.setApplicationContext(applicationContext);
		invoker = impl;
	}

	@Override
	public Invoker getObject() {
		return invoker;
	}

	@Override
	public Class<?> getObjectType() {
		return Invoker.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
