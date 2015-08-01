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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
		assertNull(invoker.invoke(null, ToBeInvoked.class.getName(), "method0", 0));
		assertNull(invoker.invoke("toBeInvokedImpl", ToBeInvoked.class.getName(), "method0", 0));
	}

	@Test
	public void testPrimitive() throws Exception {
		assertEquals("579", invoker.invoke(null, ToBeInvoked.class.getName(), "method1", 0, "123", "456"));
	}

	@Test
	public void testLong() throws Exception {
		assertEquals("579", invoker.invoke(null, ToBeInvoked.class.getName(), "method2", 0, "123", "456"));
		assertNull(invoker.invoke(null, ToBeInvoked.class.getName(), "method2", 0, null, "456"));
		assertNull(invoker.invoke(null, ToBeInvoked.class.getName(), "method2", 0, "123", null));
		assertNull(invoker.invoke(null, ToBeInvoked.class.getName(), "method2", 0, null, null));
	}

	@Test
	public void testJodaTime() throws Exception {
		assertEquals("2015/01/23 12:34:56",
				invoker.invoke(null, ToBeInvoked.class.getName(), "method3", 0, "2015/01/23", "12:34:56"));
	}

	@Test
	public void testFlatDto() throws Exception {
		assertEquals("{\"val1\":68,\"val2\":112}", invoker.invoke(null, ToBeInvoked.class.getName(), "method4", 0,
				"{\"val1\":12,\"val2\":34}", "{\"val1\":56,\"val2\":78}"));
	}

	@Test
	public void testNestedDto() throws Exception {
		assertEquals("{\"val1\":{\"val1\":6,\"val2\":8},\"val2\":{\"val1\":10,\"val2\":12}}", invoker.invoke(null,
				ToBeInvoked.class.getName(), "method5", 0,
				"{\"val1\":{\"val1\":1,\"val2\":2},\"val2\":{\"val1\":3,\"val2\":4}}",
				"{\"val1\":{\"val1\":5,\"val2\":6},\"val2\":{\"val1\":7,\"val2\":8}}"));
	}

	@Test
	public void testMethodIndex() throws Exception {
		assertEquals("-1", invoker.invoke(null, ToBeInvoked.class.getName(), "method6", 0, "1", "2"));
		assertEquals("1", invoker.invoke(null, ToBeInvoked.class.getName(), "method6", 1, "1", "2"));
	}

	@Test(expected = ClassNotFoundException.class)
	public void testClassNotFound() throws Exception {
		invoker.invoke(null, "cherry.foundation.invoker.NoClass", "method0", 0);
	}

	@Test(expected = NoSuchMethodException.class)
	public void testMethoNotFound() throws Exception {
		invoker.invoke(null, ToBeInvoked.class.getName(), "method", 0);
	}

	@Test
	public void testInitialize() {
		assertNotNull(invoker);
	}

}
