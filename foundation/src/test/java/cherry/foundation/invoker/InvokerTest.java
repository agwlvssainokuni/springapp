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
	public void testNoArgNoRet() {
		assertNull(invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked", "method0"));
		assertNull(invoker.invoke("toBeInvokedImpl", "cherry.foundation.invoker.ToBeInvoked", "method0"));
	}

	@Test
	public void testPrimitive() {
		assertEquals("579", invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked", "method1", "123", "456"));
	}

	@Test
	public void testLong() {
		assertEquals("579", invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked", "method2", "123", "456"));
		assertNull(invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked", "method2", null, "456"));
		assertNull(invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked", "method2", "123", null));
		assertNull(invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked", "method2", null, null));
	}

	@Test
	public void testJodaTime() {
		assertEquals("2015/01/23 12:34:56",
				invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked", "method3", "2015/01/23", "12:34:56"));
	}

	@Test
	public void testFlatDto() {
		assertEquals("{\"val1\":68,\"val2\":112}", invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked",
				"method4", "{\"val1\":12,\"val2\":34}", "{\"val1\":56,\"val2\":78}"));
	}

	@Test
	public void testNestedDto() {
		assertEquals("{\"val1\":{\"val1\":6,\"val2\":8},\"val2\":{\"val1\":10,\"val2\":12}}", invoker.invoke(null,
				"cherry.foundation.invoker.ToBeInvoked", "method5",
				"{\"val1\":{\"val1\":1,\"val2\":2},\"val2\":{\"val1\":3,\"val2\":4}}",
				"{\"val1\":{\"val1\":5,\"val2\":6},\"val2\":{\"val1\":7,\"val2\":8}}"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testClassNotFound() {
		invoker.invoke(null, "cherry.foundation.invoker.NoClass", "method0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMethoNotFound() {
		invoker.invoke(null, "cherry.foundation.invoker.ToBeInvoked", "method");
	}

	@Test
	public void testInitialize() {
		assertNotNull(invoker);
	}

}
