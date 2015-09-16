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

package cherry.ruletutorial.spring;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class KieBaseFactoryBean implements FactoryBean<KieBase>, InitializingBean {

	private KieBase kieBase;

	private String kieBaseName;

	public void setKieBaseName(String kieBaseName) {
		this.kieBaseName = kieBaseName;
	}

	@Override
	public void afterPropertiesSet() {
		kieBase = KieServices.Factory.get().getKieClasspathContainer(getClass().getClassLoader())
				.getKieBase(kieBaseName);
	}

	@Override
	public KieBase getObject() throws Exception {
		return kieBase;
	}

	@Override
	public Class<?> getObjectType() {
		return KieBase.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
