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

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.testtool.ToolTester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class StubTest {

	@Autowired
	private StubRepository repository;

	private Method method;

	@After
	public void after() throws NoSuchMethodException {
		method = ToolTester.class.getDeclaredMethod("toBeStubbed", Long.class, Long.class);
		for (Method m : repository.getStubbedMethod()) {
			repository.clear(m);
		}
	}

	@Test
	public void testNext() throws Throwable {
		Stub<?> stub = repository.get(method).thenReturn(Long.valueOf(123L), Long.class.getCanonicalName());
		assertTrue(stub.hasNext());
		assertFalse(stub.isThrowable());
		assertEquals(Long.class.getCanonicalName(), stub.peekType());
		assertEquals(Long.valueOf(123L), stub.next());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testPeek() throws Throwable {
		Stub<?> stub = repository.get(method).thenReturn(Long.valueOf(123L), Long.class.getCanonicalName());
		assertTrue(stub.hasNext());
		assertFalse(stub.isThrowable());
		assertEquals(Long.class.getCanonicalName(), stub.peekType());
		assertEquals(Long.valueOf(123L), stub.peek());
		assertTrue(stub.hasNext());
		assertEquals(Long.valueOf(123L), stub.next());
		assertFalse(stub.hasNext());
	}

	@Test(expected = IllegalStateException.class)
	public void testNextWhenEmpty() throws Throwable {
		Stub<?> stub = repository.get(method);
		assertFalse(stub.hasNext());
		stub.next();
	}

	@Test(expected = IllegalStateException.class)
	public void testPeekWhenEmpty() throws Throwable {
		Stub<?> stub = repository.get(method);
		assertFalse(stub.hasNext());
		stub.peek();
	}

	@Test
	public void testNextThrowable() throws Throwable {
		Stub<?> stub = repository.get(method).thenThrows(IllegalArgumentException.class);
		assertTrue(stub.hasNext());
		assertTrue(stub.isThrowable());
		assertEquals(IllegalArgumentException.class, stub.nextThrowable());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testPeekThrowable() throws Throwable {
		Stub<?> stub = repository.get(method).thenThrows(IllegalArgumentException.class);
		assertTrue(stub.hasNext());
		assertTrue(stub.isThrowable());
		assertEquals(IllegalArgumentException.class, stub.peekThrowable());
		assertTrue(stub.hasNext());
		assertEquals(IllegalArgumentException.class, stub.nextThrowable());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysReturn1() throws Throwable {
		Stub<?> stub = repository.get(method).alwaysReturn(Long.valueOf(123L));
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isThrowable());
			assertEquals(Long.class.getCanonicalName(), stub.peekType());
			assertEquals(Long.valueOf(123L), stub.peek());
			assertEquals(Long.valueOf(123L), stub.next());
		}
		stub.clear();
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysReturn1_null() throws Throwable {
		Stub<?> stub = repository.get(method).alwaysReturn((Long) null);
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isThrowable());
			assertNull(stub.peekType());
			assertNull(stub.peek());
			assertNull(stub.next());
		}
		stub.clear();
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysReturn2() throws Throwable {
		Stub<?> stub = repository.get(method).alwaysReturn(Long.valueOf(123L), "long");
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isThrowable());
			assertEquals("long", stub.peekType());
			assertEquals(Long.valueOf(123L), stub.peek());
			assertEquals(Long.valueOf(123L), stub.next());
		}
		stub.clear();
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenReturn1() throws Throwable {
		Stub<?> stub = repository.get(method).thenReturn(Long.valueOf(123L));
		assertTrue(stub.hasNext());
		assertFalse(stub.isThrowable());
		assertEquals(Long.class.getCanonicalName(), stub.peekType());
		assertEquals(Long.valueOf(123L), stub.peek());
		assertEquals(Long.valueOf(123L), stub.next());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenReturn1_null() throws Throwable {
		Stub<?> stub = repository.get(method).thenReturn((Long) null);
		assertTrue(stub.hasNext());
		assertFalse(stub.isThrowable());
		assertNull(stub.peekType());
		assertNull(stub.peek());
		assertNull(stub.next());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenReturn2() throws Throwable {
		Stub<?> stub = repository.get(method).thenReturn(Long.valueOf(123L), "long");
		assertTrue(stub.hasNext());
		assertFalse(stub.isThrowable());
		assertEquals("long", stub.peekType());
		assertEquals(Long.valueOf(123L), stub.peek());
		assertEquals(Long.valueOf(123L), stub.next());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenReturnList1() throws Throwable {
		Stub<?> stub = repository.get(method).thenReturn(asList(Long.valueOf(1L), Long.valueOf(2L), Long.valueOf(3L)));
		for (long l = 1L; l <= 3L; l++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isThrowable());
			assertEquals(Long.class.getCanonicalName(), stub.peekType());
			assertEquals(Long.valueOf(l), stub.peek());
			assertEquals(Long.valueOf(l), stub.next());
		}
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenReturnList2() throws Throwable {
		Stub<?> stub = repository.get(method).thenReturn(asList(Long.valueOf(1L), Long.valueOf(2L), Long.valueOf(3L)),
				"long");
		for (long l = 1L; l <= 3L; l++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isThrowable());
			assertEquals("long", stub.peekType());
			assertEquals(Long.valueOf(l), stub.peek());
			assertEquals(Long.valueOf(l), stub.next());
		}
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysThrows() {
		Stub<?> stub = repository.get(method).alwaysThrows(IllegalArgumentException.class);
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertTrue(stub.isThrowable());
			assertEquals(IllegalArgumentException.class, stub.peekThrowable());
			assertEquals(IllegalArgumentException.class, stub.nextThrowable());
		}
		stub.clear();
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenThrows() {
		Stub<?> stub = repository.get(method).thenThrows(IllegalArgumentException.class);
		assertTrue(stub.hasNext());
		assertTrue(stub.isThrowable());
		assertEquals(IllegalArgumentException.class, stub.peekThrowable());
		assertEquals(IllegalArgumentException.class, stub.nextThrowable());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenThrowsList() {
		List<Class<? extends Throwable>> list = asList((Class<? extends Throwable>) IllegalArgumentException.class,
				IllegalStateException.class, IllegalArgumentException.class);
		Stub<?> stub = repository.get(method).thenThrows(list);
		for (Class<? extends Throwable> c : list) {
			assertTrue(stub.hasNext());
			assertTrue(stub.isThrowable());
			assertEquals(c, stub.peekThrowable());
			assertEquals(c, stub.nextThrowable());
		}
		assertFalse(stub.hasNext());
	}

}
