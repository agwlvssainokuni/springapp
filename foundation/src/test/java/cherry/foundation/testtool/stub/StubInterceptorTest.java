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
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.testtool.ToolTester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class StubInterceptorTest {

	@Autowired
	private StubRepository repository;

	@Autowired
	private ToolTester tester;

	@After
	public void after() {
		for (Method m : repository.getStubbedMethod()) {
			repository.clear(m);
		}
	}

	@Test
	public void testMethodLong_RETURN() throws NoSuchMethodException {
		Method method = ToolTester.class.getDeclaredMethod("toBeStubbed", Long.class, Long.class);

		assertEquals(Long.valueOf(1234L), tester.toBeStubbed(1030L, 204L));

		repository.get(method).thenReturn(1L);
		assertEquals(Long.valueOf(1L), tester.toBeStubbed(1030L, 204L));
		assertEquals(Long.valueOf(1234L), tester.toBeStubbed(1030L, 204L));

		repository.get(method).thenReturn(1L).thenReturn(2L).thenReturn(3L);
		assertEquals(Long.valueOf(1L), tester.toBeStubbed(1030L, 204L));
		assertEquals(Long.valueOf(2L), tester.toBeStubbed(1030L, 204L));
		assertEquals(Long.valueOf(3L), tester.toBeStubbed(1030L, 204L));
		assertEquals(Long.valueOf(1234L), tester.toBeStubbed(1030L, 204L));

		repository.get(method).alwaysReturn(1L);
		assertEquals(Long.valueOf(1L), tester.toBeStubbed(1030L, 204L));
		assertEquals(Long.valueOf(1L), tester.toBeStubbed(1030L, 204L));
		repository.get(method).clear();
		assertEquals(Long.valueOf(1234L), tester.toBeStubbed(1030L, 204L));
	}

	@Test
	public void testMethodBigDecimal_THROWABLE() throws NoSuchMethodException {
		Method method = ToolTester.class.getDeclaredMethod("toBeStubbed", BigDecimal.class, BigDecimal.class);

		assertEquals(BigDecimal.valueOf(1234L), tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L)));

		repository.get(method).thenThrows(IllegalArgumentException.class);
		try {
			tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L));
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
		assertEquals(BigDecimal.valueOf(1234L), tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L)));

		repository.get(method).thenThrows(IllegalArgumentException.class).thenThrows(Throwable.class);
		try {
			tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L));
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
		try {
			tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L));
			fail("Exception must be thrown");
		} catch (Throwable ex) {
			// OK
		}
		assertEquals(BigDecimal.valueOf(1234L), tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L)));

		repository.get(method).alwaysThrows(IllegalArgumentException.class);
		try {
			tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L));
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
		try {
			tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L));
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
		repository.get(method).clear();
		assertEquals(BigDecimal.valueOf(1234L), tester.toBeStubbed(BigDecimal.valueOf(1030L), BigDecimal.valueOf(204L)));
	}

}
