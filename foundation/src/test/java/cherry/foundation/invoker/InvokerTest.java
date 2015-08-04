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

package cherry.foundation.invoker;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class InvokerTest {

	@Autowired
	private Invoker invoker;

	@Test
	public void testNoArgNoRet() throws Exception {
		assertEquals("null", invoker.invoke(null, ToBeInvoked.class.getName(), "method0", 0, null, null));
		assertEquals("null", invoker.invoke("toBeInvokedImpl", ToBeInvoked.class.getName(), "method0", 0, null, null));
	}

	@Test
	public void testPrimitive() throws Exception {
		assertEquals("579", invoker.invoke(null, ToBeInvoked.class.getName(), "method1", 0, asList("123", "456"), null));
	}

	@Test
	public void testLong() throws Exception {
		assertEquals("579", invoker.invoke(null, ToBeInvoked.class.getName(), "method2", 0, asList("123", "456"), null));
		assertEquals("null", invoker.invoke(null, ToBeInvoked.class.getName(), "method2", 0, asList(null, "456"), null));
		assertEquals("null", invoker.invoke(null, ToBeInvoked.class.getName(), "method2", 0, asList("123"), null));
		assertEquals("null", invoker.invoke(null, ToBeInvoked.class.getName(), "method2", 0, null, null));
	}

	@Test
	public void testJodaTime() throws Exception {
		assertEquals("\"2015-01-23T12:34:56.000\"", invoker.invoke(null, ToBeInvoked.class.getName(), "method3", 0,
				asList("\"2015-01-23\"", "\"12:34:56\""), null));
	}

	@Test
	public void testFlatDto() throws Exception {
		assertEquals(
				"{\"val1\":68,\"val2\":112}",
				invoker.invoke(null, ToBeInvoked.class.getName(), "method4", 0,
						asList("{\"val1\":12,\"val2\":34}", "{\"val1\":56,\"val2\":78}"),
						asList(ToBeInvoked.Dto1.class.getName())));
	}

	@Test
	public void testNestedDto() throws Exception {
		assertEquals("{\"val1\":{\"val1\":6,\"val2\":8},\"val2\":{\"val1\":10,\"val2\":12}}", invoker.invoke(
				null,
				ToBeInvoked.class.getName(),
				"method5",
				0,
				asList("{\"val1\":{\"val1\":1,\"val2\":2},\"val2\":{\"val1\":3,\"val2\":4}}",
						"{\"val1\":{\"val1\":5,\"val2\":6},\"val2\":{\"val1\":7,\"val2\":8}}"), null));
	}

	@Test
	public void testMethodIndex() throws Exception {
		assertEquals("-1", invoker.invoke(null, ToBeInvoked.class.getName(), "method6", 0, asList("1", "2"), null));
		assertEquals("1", invoker.invoke(null, ToBeInvoked.class.getName(), "method6", 1, asList("1", "2"), null));
	}

	@Test(expected = ClassNotFoundException.class)
	public void testClassNotFound() throws Exception {
		invoker.invoke(null, "NoClass", "method0", 0, null, null);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testMethoNotFound() throws Exception {
		invoker.invoke(null, ToBeInvoked.class.getName(), "method", 0, null, null);
	}

	@Test(expected = ClassNotFoundException.class)
	public void testArgClassNotFound() throws Exception {
		invoker.invoke(null, ToBeInvoked.class.getName(), "method4", 0,
				asList("{\"val1\":12,\"val2\":34}", "{\"val1\":56,\"val2\":78}"), asList("NoClass"));
	}

	@Test
	public void testInitialize() {
		assertNotNull(invoker);
	}

}
