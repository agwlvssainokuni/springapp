/*
 *   Copyright 2014 agwlvssainokuni
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package cherry.spring.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppCtxUtil {

	private static ApplicationContext appCtx = null;

	public static ApplicationContext getAppCtx() {
		if (appCtx == null) {
			appCtx = new ClassPathXmlApplicationContext(
					"classpath:config/applicationContext-test.xml");
		}
		return appCtx;
	}

	public static <T> T getBean(Class<T> klass) {
		return getAppCtx().getBean(klass);
	}

	public static void clearAppCtx() {
		appCtx = null;
	}

}
