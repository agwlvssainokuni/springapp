/*
 * Copyright 2016 agwlvssainokuni
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

package cherry.foundation.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class MapperLazyInitializingPostProcesssor implements BeanDefinitionRegistryPostProcessor {

	private boolean enabled = true;

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		// 何もしない
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
		if (!enabled) {
			return;
		}
		for (String beanName : registry.getBeanDefinitionNames()) {
			BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
			if (MapperFactoryBean.class.getName().equals(beanDefinition.getBeanClassName())) {
				beanDefinition.setBeanClassName(LazyFactoryBean.class.getName());
			}
		}
	}

	public static class LazyFactoryBean<T> implements FactoryBean<T> {

		private MapperFactoryBean<T> delegate = new MapperFactoryBean<T>();

		private boolean initialized = false;

		public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
			delegate.setSqlSessionFactory(sqlSessionFactory);
		}

		public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
			delegate.setSqlSessionTemplate(sqlSessionTemplate);
		}

		public void setMapperInterface(Class<T> mapperInterface) {
			delegate.setMapperInterface(mapperInterface);
		}

		public void setAddToConfig(boolean addToConfig) {
			delegate.setAddToConfig(addToConfig);
		}

		@Override
		public T getObject() throws Exception {
			if (!initialized) {
				delegate.afterPropertiesSet();
				initialized = true;
			}
			return delegate.getObject();
		}

		@Override
		public Class<?> getObjectType() {
			return delegate.getObjectType();
		}

		@Override
		public boolean isSingleton() {
			return delegate.isSingleton();
		}
	}

}
