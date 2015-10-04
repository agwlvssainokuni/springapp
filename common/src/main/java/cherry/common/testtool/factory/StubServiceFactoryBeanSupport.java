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

package cherry.common.testtool.factory;

import static cherry.common.testtool.factory.BeanName.REFLECTION_RESOLVER;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cherry.foundation.testtool.reflect.ReflectionResolver;
import cherry.foundation.testtool.stub.StubRepository;
import cherry.foundation.testtool.stub.StubService;
import cherry.foundation.testtool.stub.StubServiceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class StubServiceFactoryBeanSupport implements FactoryBean<StubService>, InitializingBean {

	@Autowired
	@Qualifier(REFLECTION_RESOLVER)
	private ReflectionResolver reflectionResolver;

	@Autowired
	private StubRepository repository;

	private StubService service;

	protected abstract ObjectMapper getObjectMapper();

	@Override
	public void afterPropertiesSet() {
		StubServiceImpl impl = new StubServiceImpl();
		impl.setReflectionResolver(reflectionResolver);
		impl.setRepository(repository);
		impl.setObjectMapper(getObjectMapper());
		service = impl;
	}

	@Override
	public StubService getObject() {
		return service;
	}

	@Override
	public Class<?> getObjectType() {
		return StubService.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
