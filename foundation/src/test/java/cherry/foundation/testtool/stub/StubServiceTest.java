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

package cherry.foundation.testtool.stub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.LocalDateTime;
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
public class StubServiceTest {

	@Autowired
	@Qualifier("testJsonStubService")
	private StubService stubService;

	@Autowired
	private ReflectionResolver resolver;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	@Test
	public void testResolveBeanName() {
		List<String> list = stubService.resolveBeanName(ToolTester.class.getName());
		assertEquals(1, list.size());
		assertEquals("toolTesterImpl", list.get(0));
	}

	@Test
	public void testResolveBeanName_ClassNotFound() {
		List<String> list = stubService.resolveBeanName(ToolTester.class.getName() + "NotExist");
		assertEquals(0, list.size());
	}

	@Test
	public void testResolveMethod() {
		List<Method> list = stubService.resolveMethod(ToolTester.class.getName(), "toBeStubbed", -1);
		assertEquals(3, list.size());
		Set<Class<?>> returnType = new HashSet<>();
		returnType.add(Long.class);
		returnType.add(BigDecimal.class);
		returnType.add(LocalDateTime.class);
		for (Method m : list) {
			assertEquals(2, m.getParameterTypes().length);
			returnType.remove(m.getReturnType());
		}
		assertTrue(returnType.isEmpty());
	}

	@Test
	public void testResolveMethod_ClassNotFound() {
		List<Method> list = stubService.resolveMethod(ToolTester.class.getName() + "NotExist", "toBeStubbed", -1);
		assertEquals(0, list.size());
	}

}
