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

package cherry.spring.common.testtool.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import cherry.foundation.testtool.reflect.ReflectionResolver;
import cherry.foundation.testtool.reflect.ReflectionResolverImpl;

@Component("reflectionResolver")
public class ReflectionResolverFactoryBean implements FactoryBean<ReflectionResolver>, ApplicationContextAware,
		InitializingBean {

	private ReflectionResolver reflectionResolver;

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() {
		ReflectionResolverImpl impl = new ReflectionResolverImpl();
		impl.setApplicationContext(applicationContext);
		reflectionResolver = impl;
	}

	@Override
	public ReflectionResolver getObject() {
		return reflectionResolver;
	}

	@Override
	public Class<?> getObjectType() {
		return ReflectionResolver.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
