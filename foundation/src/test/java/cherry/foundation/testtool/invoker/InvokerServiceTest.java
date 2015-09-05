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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.testtool.ToolTester;
import cherry.foundation.testtool.reflect.ReflectionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class InvokerServiceTest {

	@Autowired
	@Qualifier("testJsonInvokerService")
	private InvokerService invokerService;

	@Autowired
	private ReflectionResolver resolver;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	@Test
	public void testResolveBeanName() {
		List<String> list = invokerService.resolveBeanName(ToolTester.class.getName());
		assertEquals(1, list.size());
		assertEquals("toolTesterImpl", list.get(0));
	}

	@Test
	public void testResolveBeanName_ClassNotFound() {
		List<String> list = invokerService.resolveBeanName(ToolTester.class.getName() + "NotExist");
		assertEquals(0, list.size());
	}

	@Test
	public void testResolveMethod() {
		List<Method> list = invokerService.resolveMethod(ToolTester.class.getName(), "toBeInvoked0", -1);
		assertEquals(1, list.size());
		Method m = list.get(0);
		assertEquals(0, m.getParameterTypes().length);
		assertEquals(Void.TYPE, m.getReturnType());
	}

	@Test
	public void testResolveMethod_ClassNotFound() {
		List<Method> list = invokerService.resolveMethod(ToolTester.class.getName() + "NotExist", "toBeInvoked6", -1);
		assertEquals(0, list.size());
	}

	@Test
	public void testInvoke_NORMAL1() {
		assertEquals("579", invokerService.invoke("toolTesterImpl", ToolTester.class.getName(), "toBeInvoked1", -1, 0,
				"[123,456]", "[\"java.lang.Long\",\"java.lang.Long\"]"));
	}

	@Test
	public void testInvoke_NORMAL2_NullArgs() {
		assertEquals("579", invokerService.invoke("toolTesterImpl", ToolTester.class.getName(), "toBeInvoked2", -1, 0,
				"[123,456]", "[\"java.lang.Long\",\"java.lang.Long\"]"));
		assertEquals("null",
				invokerService.invoke("toolTesterImpl", ToolTester.class.getName(), "toBeInvoked2", -1, 0, null, null));
	}

	@Test
	public void testInvoke_NORMAL3_MultiMethod() {
		List<Method> list = resolver.resolveMethod(ToolTester.class, "toBeInvoked6", -1);
		int index0 = list.get(0).getReturnType() == Integer.TYPE ? 0 : 1;
		assertEquals("333", invokerService.invoke("toolTesterImpl", ToolTester.class.getName(), "toBeInvoked6", -1,
				index0, "[123,456]", null));
		int index1 = list.get(0).getReturnType() == Long.TYPE ? 0 : 1;
		assertEquals("-333", invokerService.invoke("toolTesterImpl", ToolTester.class.getName(), "toBeInvoked6", -1,
				index1, "[123,456]", null));
	}

	@Test
	public void testInvoke_ClassNotFound() throws IOException {
		String result = invokerService.invoke("toolTesterImpl", ToolTester.class.getName() + "NotExist",
				"toBeInvoked1", -1, 0, "[123,456]", "[\"java.lang.Long\",\"java.lang.Long\"]");
		Map<?, ?> map = objectMapper.readValue(result, Map.class);
		assertEquals("java.lang.ClassNotFoundException", map.get("type"));
	}

	@Test
	public void testInvoke_NoSuchMethod() throws IOException {
		String result = invokerService.invoke("toolTesterImpl", ToolTester.class.getName(), "noSuchMethod", -1, 0,
				null, null);
		Map<?, ?> map = objectMapper.readValue(result, Map.class);
		assertEquals("java.lang.NoSuchMethodException", map.get("type"));
	}

}
