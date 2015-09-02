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

package cherry.foundation.testtool.invoker;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.testtool.reflect.ReflectionResolver;
import cherry.foundation.testtool.reflect.ReflectionResolverImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class InvokerTest implements InitializingBean {

	@Autowired
	private ApplicationContext appCtx;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	private Invoker invoker;

	private ReflectionResolver reflectionResolver;

	@Override
	public void afterPropertiesSet() {
		InvokerImpl impl = new InvokerImpl();
		impl.setApplicationContext(appCtx);
		impl.setObjectMapper(objectMapper);
		invoker = impl;
		ReflectionResolverImpl resolver = new ReflectionResolverImpl();
		resolver.setApplicationContext(appCtx);
		reflectionResolver = resolver;
	}

	@Test
	public void testNoArgNoRet() throws Exception {
		List<Method> list = reflectionResolver.resolveMethod(ToBeInvoked.class, "method0", -1);
		assertEquals("null", invoker.invoke(null, ToBeInvoked.class, list.get(0), null, null));
		assertEquals("null", invoker.invoke("toBeInvokedImpl", ToBeInvoked.class, list.get(0), null, null));
	}

	@Test
	public void testPrimitive() throws Exception {
		List<Method> list = reflectionResolver.resolveMethod(ToBeInvoked.class, "method1", -1);
		assertEquals("579", invoker.invoke(null, ToBeInvoked.class, list.get(0), asList("123", "456"), null));
	}

	@Test
	public void testLong() throws Exception {
		List<Method> list = reflectionResolver.resolveMethod(ToBeInvoked.class, "method2", -1);
		assertEquals("579", invoker.invoke(null, ToBeInvoked.class, list.get(0), asList("123", "456"), null));
		assertEquals("null", invoker.invoke(null, ToBeInvoked.class, list.get(0), asList(null, "456"), null));
		assertEquals("null", invoker.invoke(null, ToBeInvoked.class, list.get(0), asList("123"), null));
		assertEquals("null", invoker.invoke(null, ToBeInvoked.class, list.get(0), null, null));
	}

	@Test
	public void testJodaTime() throws Exception {
		List<Method> list = reflectionResolver.resolveMethod(ToBeInvoked.class, "method3", -1);
		assertEquals("\"2015-01-23T12:34:56.000\"",
				invoker.invoke(null, ToBeInvoked.class, list.get(0), asList("\"2015-01-23\"", "\"12:34:56\""), null));
	}

	@Test
	public void testFlatDto() throws Exception {
		List<Method> list = reflectionResolver.resolveMethod(ToBeInvoked.class, "method4", -1);
		assertEquals(
				"{\"val1\":68,\"val2\":112}",
				invoker.invoke(null, ToBeInvoked.class, list.get(0),
						asList("{\"val1\":12,\"val2\":34}", "{\"val1\":56,\"val2\":78}"),
						asList(ToBeInvoked.Dto1.class.getName())));
	}

	@Test
	public void testNestedDto() throws Exception {
		List<Method> list = reflectionResolver.resolveMethod(ToBeInvoked.class, "method5", -1);
		assertEquals("{\"val1\":{\"val1\":6,\"val2\":8},\"val2\":{\"val1\":10,\"val2\":12}}", invoker.invoke(
				null,
				ToBeInvoked.class,
				list.get(0),
				asList("{\"val1\":{\"val1\":1,\"val2\":2},\"val2\":{\"val1\":3,\"val2\":4}}",
						"{\"val1\":{\"val1\":5,\"val2\":6},\"val2\":{\"val1\":7,\"val2\":8}}"), null));
	}

	@Test
	public void testMethodIndex() throws Exception {
		List<Method> list = reflectionResolver.resolveMethod(ToBeInvoked.class, "method6", -1);
		int m0;
		int m1;
		if (list.get(0).getReturnType() == Long.TYPE) {
			m0 = 0;
			m1 = 1;
		} else {
			m0 = 1;
			m1 = 0;
		}
		assertEquals("-1", invoker.invoke(null, ToBeInvoked.class, list.get(m0), asList("1", "2"), null));
		assertEquals("1", invoker.invoke(null, ToBeInvoked.class, list.get(m1), asList("1", "2"), null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArgClassNotFound() throws Exception {
		List<Method> list = reflectionResolver.resolveMethod(ToBeInvoked.class, "method4", -1);
		invoker.invoke(null, ToBeInvoked.class, list.get(0),
				asList("{\"val1\":12,\"val2\":34}", "{\"val1\":56,\"val2\":78}"), asList("NoClass"));
	}

	@Test
	public void testInitialize() {
		assertNotNull(invoker);
	}

}
