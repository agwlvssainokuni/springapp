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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
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

	@Before
	public void before() throws NoSuchMethodException {
		method = ToolTester.class.getDeclaredMethod("toBeStubbed", Long.class, Long.class);
	}

	@After
	public void after() {
		for (Method m : repository.getStubbedMethod()) {
			repository.clear(m);
		}
	}

	@Test
	public void testNext() {
		Stub<?> stub = repository.get(method).thenReturn(Long.valueOf(123L), Long.class.getCanonicalName());
		assertTrue(stub.hasNext());
		assertFalse(stub.isMock());
		assertFalse(stub.isThrowable());
		assertEquals(Long.class.getCanonicalName(), stub.peekType());
		assertEquals(Long.valueOf(123L), stub.next());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testPeek() {
		Stub<?> stub = repository.get(method).thenReturn(Long.valueOf(123L), Long.class.getCanonicalName());
		assertTrue(stub.hasNext());
		assertFalse(stub.isMock());
		assertFalse(stub.isThrowable());
		assertEquals(Long.class.getCanonicalName(), stub.peekType());
		assertEquals(Long.valueOf(123L), stub.peek());
		assertTrue(stub.hasNext());
		assertEquals(Long.valueOf(123L), stub.next());
		assertFalse(stub.hasNext());
	}

	@Test(expected = IllegalStateException.class)
	public void testNextWhenEmpty() {
		Stub<?> stub = repository.get(method);
		assertFalse(stub.hasNext());
		stub.next();
	}

	@Test(expected = IllegalStateException.class)
	public void testPeekWhenEmpty() {
		Stub<?> stub = repository.get(method);
		assertFalse(stub.hasNext());
		stub.peek();
	}

	@Test
	public void testNextMock() {
		ToolTester mock = mock(ToolTester.class);
		Stub<?> stub = repository.get(method).thenMock(mock);
		assertTrue(stub.hasNext());
		assertTrue(stub.isMock());
		assertFalse(stub.isThrowable());
		assertEquals(mock, stub.nextMock());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testPeekMock() {
		ToolTester mock = mock(ToolTester.class);
		Stub<?> stub = repository.get(method).thenMock(mock);
		assertTrue(stub.hasNext());
		assertTrue(stub.isMock());
		assertFalse(stub.isThrowable());
		assertEquals(mock, stub.peekMock());
		assertTrue(stub.hasNext());
		assertEquals(mock, stub.nextMock());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testNextThrowable() {
		Stub<?> stub = repository.get(method).thenThrows(IllegalArgumentException.class);
		assertTrue(stub.hasNext());
		assertFalse(stub.isMock());
		assertTrue(stub.isThrowable());
		assertEquals(IllegalArgumentException.class, stub.nextThrowable());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testPeekThrowable() {
		Stub<?> stub = repository.get(method).thenThrows(IllegalArgumentException.class);
		assertTrue(stub.hasNext());
		assertFalse(stub.isMock());
		assertTrue(stub.isThrowable());
		assertEquals(IllegalArgumentException.class, stub.peekThrowable());
		assertTrue(stub.hasNext());
		assertEquals(IllegalArgumentException.class, stub.nextThrowable());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysReturn1() {
		Stub<?> stub = repository.get(method).alwaysReturn(Long.valueOf(123L));
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isMock());
			assertFalse(stub.isThrowable());
			assertEquals(Long.class.getCanonicalName(), stub.peekType());
			assertEquals(Long.valueOf(123L), stub.peek());
			assertEquals(Long.valueOf(123L), stub.next());
		}
		stub.clear();
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysReturn1_null() {
		Stub<?> stub = repository.get(method).alwaysReturn((Long) null);
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isMock());
			assertFalse(stub.isThrowable());
			assertNull(stub.peekType());
			assertNull(stub.peek());
			assertNull(stub.next());
		}
		stub.clear();
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysReturn2() {
		Stub<?> stub = repository.get(method).alwaysReturn(Long.valueOf(123L), "long");
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isMock());
			assertFalse(stub.isThrowable());
			assertEquals("long", stub.peekType());
			assertEquals(Long.valueOf(123L), stub.peek());
			assertEquals(Long.valueOf(123L), stub.next());
		}
		stub.clear();
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenReturn1() {
		Stub<?> stub = repository.get(method).thenReturn(Long.valueOf(123L));
		assertTrue(stub.hasNext());
		assertFalse(stub.isMock());
		assertFalse(stub.isThrowable());
		assertEquals(Long.class.getCanonicalName(), stub.peekType());
		assertEquals(Long.valueOf(123L), stub.peek());
		assertEquals(Long.valueOf(123L), stub.next());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenReturn1_null() {
		Stub<?> stub = repository.get(method).thenReturn((Long) null);
		assertTrue(stub.hasNext());
		assertFalse(stub.isMock());
		assertFalse(stub.isThrowable());
		assertNull(stub.peekType());
		assertNull(stub.peek());
		assertNull(stub.next());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenReturn2() {
		Stub<?> stub = repository.get(method).thenReturn(Long.valueOf(123L), "long");
		assertTrue(stub.hasNext());
		assertFalse(stub.isMock());
		assertFalse(stub.isThrowable());
		assertEquals("long", stub.peekType());
		assertEquals(Long.valueOf(123L), stub.peek());
		assertEquals(Long.valueOf(123L), stub.next());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysMock() {
		ToolTester mock = mock(ToolTester.class);
		Stub<?> stub = repository.get(method).alwaysMock(mock);
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertTrue(stub.isMock());
			assertFalse(stub.isThrowable());
			assertEquals(mock, stub.peekMock());
			assertEquals(mock, stub.nextMock());
		}
		stub.clear();
		assertFalse(stub.hasNext());
	}

	@Test
	public void testThenMock() {
		ToolTester mock = mock(ToolTester.class);
		Stub<?> stub = repository.get(method).thenMock(mock);
		assertTrue(stub.hasNext());
		assertTrue(stub.isMock());
		assertFalse(stub.isThrowable());
		assertEquals(mock, stub.peekMock());
		assertEquals(mock, stub.nextMock());
		assertFalse(stub.hasNext());
	}

	@Test
	public void testAlwaysThrows() {
		Stub<?> stub = repository.get(method).alwaysThrows(IllegalArgumentException.class);
		for (int i = 0; i < 100; i++) {
			assertTrue(stub.hasNext());
			assertFalse(stub.isMock());
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
		assertFalse(stub.isMock());
		assertTrue(stub.isThrowable());
		assertEquals(IllegalArgumentException.class, stub.peekThrowable());
		assertEquals(IllegalArgumentException.class, stub.nextThrowable());
		assertFalse(stub.hasNext());
	}

}
