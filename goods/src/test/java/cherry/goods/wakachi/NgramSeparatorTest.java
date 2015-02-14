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
		separator.setApplyToAscii(true);
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
	public void testSeparate_1FULLCHAR() {
		List<String> list = separator.separate("あ");
		assertEquals(1, list.size());
		assertEquals("あ", list.get(0));
	}

	@Test
	public void testSeparate_2CHAR() {
		List<String> list = separator.separate("ab");
		assertEquals(1, list.size());
		assertEquals("ab", list.get(0));
	}

	@Test
	public void testSeparate_2FULLCHAR() {
		List<String> list = separator.separate("あい");
		assertEquals(1, list.size());
		assertEquals("あい", list.get(0));
	}

	@Test
	public void testSeparate_3CHAR() {
		List<String> list = separator.separate("abc");
		assertEquals(2, list.size());
		assertEquals("ab", list.get(0));
		assertEquals("bc", list.get(1));
	}

	@Test
	public void testSeparate_3FULLCHAR() {
		List<String> list = separator.separate("あいう");
		assertEquals(2, list.size());
		assertEquals("あい", list.get(0));
		assertEquals("いう", list.get(1));
	}

	@Test
	public void testSeparate_5CHAR() {
		List<String> list = separator.separate("abcde");
		assertEquals(4, list.size());
		assertEquals("ab", list.get(0));
		assertEquals("bc", list.get(1));
		assertEquals("cd", list.get(2));
		assertEquals("de", list.get(3));
	}

	@Test
	public void testSeparate_5FULLCHAR() {
		List<String> list = separator.separate("あいうえお");
		assertEquals(4, list.size());
		assertEquals("あい", list.get(0));
		assertEquals("いう", list.get(1));
		assertEquals("うえ", list.get(2));
		assertEquals("えお", list.get(3));
	}

	@Test
	public void testSeparate_3GRAM_5CHAR() {
		NgramSeparator sep = new NgramSeparator();
		sep.setApplyToAscii(true);
		sep.setLength(3);
		List<String> list = sep.separate("abcde");
		assertEquals(3, list.size());
		assertEquals("abc", list.get(0));
		assertEquals("bcd", list.get(1));
		assertEquals("cde", list.get(2));
	}

	@Test
	public void testSeparate_3GRAM_5FULLCHAR() {
		NgramSeparator sep = new NgramSeparator();
		sep.setApplyToAscii(true);
		sep.setLength(3);
		List<String> list = sep.separate("あいうえお");
		assertEquals(3, list.size());
		assertEquals("あいう", list.get(0));
		assertEquals("いうえ", list.get(1));
		assertEquals("うえお", list.get(2));
	}

	@Test
	public void testSeparate_5CHAR_THROUGH_ASCII() {
		NgramSeparator sep = new NgramSeparator();
		sep.setApplyToAscii(false);
		sep.setLength(2);
		List<String> list = sep.separate("abcde");
		assertEquals(1, list.size());
		assertEquals("abcde", list.get(0));
	}

	@Test
	public void testSeparate_5FULLCHAR_THROUGH_ASCII() {
		NgramSeparator sep = new NgramSeparator();
		sep.setApplyToAscii(false);
		sep.setLength(2);
		List<String> list = sep.separate("あいうえお");
		assertEquals(4, list.size());
		assertEquals("あい", list.get(0));
		assertEquals("いう", list.get(1));
		assertEquals("うえ", list.get(2));
		assertEquals("えお", list.get(3));
	}

}
