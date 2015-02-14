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

package cherry.goods.wakachi;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class NgramSeparatorTest {

	private NgramSeparator separator;

	@Before
	public void before() {
		separator = new NgramSeparator();
		separator.setLength(2);
	}

	@Test
	public void testSeparate_EMPTY() {
		List<String> list = separator.separate("");
		assertEquals(0, list.size());
	}

	@Test
	public void testSeparate_1CHAR() {
		List<String> list = separator.separate("a");
		assertEquals(1, list.size());
		assertEquals("a", list.get(0));
	}

	@Test
	public void testSeparate_2CHAR() {
		List<String> list = separator.separate("ab");
		assertEquals(1, list.size());
		assertEquals("ab", list.get(0));
	}

	@Test
	public void testSeparate_3CHAR() {
		List<String> list = separator.separate("abc");
		assertEquals(2, list.size());
		assertEquals("ab", list.get(0));
		assertEquals("bc", list.get(1));
	}

	@Test
	public void testSeparate_10CHAR() {
		List<String> list = separator.separate("abcdefghij");
		assertEquals(9, list.size());
		assertEquals("ab", list.get(0));
		assertEquals("bc", list.get(1));
		assertEquals("cd", list.get(2));
		assertEquals("de", list.get(3));
		assertEquals("ef", list.get(4));
		assertEquals("fg", list.get(5));
		assertEquals("gh", list.get(6));
		assertEquals("hi", list.get(7));
		assertEquals("ij", list.get(8));
	}

	@Test
	public void testSeparate_3GRAM_10CHAR() {
		NgramSeparator sep = new NgramSeparator();
		sep.setLength(3);
		List<String> list = sep.separate("abcdefghij");
		assertEquals(8, list.size());
		assertEquals("abc", list.get(0));
		assertEquals("bcd", list.get(1));
		assertEquals("cde", list.get(2));
		assertEquals("def", list.get(3));
		assertEquals("efg", list.get(4));
		assertEquals("fgh", list.get(5));
		assertEquals("ghi", list.get(6));
		assertEquals("hij", list.get(7));
	}

}
