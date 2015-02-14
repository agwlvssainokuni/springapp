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

public class SpaceCharSeparatorTest {

	private SpaceCharSeparator separator;

	@Before
	public void before() {
		separator = new SpaceCharSeparator();
	}

	@Test
	public void testSeparate_EMPTY() {
		List<String> list = separator.separate("");
		assertEquals(0, list.size());
	}

	@Test
	public void testSeparate_SPACE() {
		List<String> list = separator.separate(" ");
		assertEquals(0, list.size());
	}

	@Test
	public void testSeparate_FULLSPACE() {
		List<String> list = separator.separate("　");
		assertEquals(0, list.size());
	}

	@Test
	public void testSeparate_1ELEM() {
		List<String> list = separator.separate("aaa");
		assertEquals(1, list.size());
		assertEquals("aaa", list.get(0));
	}

	@Test
	public void testSeparate_1ELEM_HEAD_SPACE() {
		List<String> list = separator.separate(" aaa");
		assertEquals(1, list.size());
		assertEquals("aaa", list.get(0));
	}

	@Test
	public void testSeparate_1ELEM_TAIL_SPACE() {
		List<String> list = separator.separate("aaa ");
		assertEquals(1, list.size());
		assertEquals("aaa", list.get(0));
	}

	@Test
	public void testSeparate_3ELEM() {
		List<String> list = separator.separate("aaa bbb ccc");
		assertEquals(3, list.size());
		assertEquals("aaa", list.get(0));
		assertEquals("bbb", list.get(1));
		assertEquals("ccc", list.get(2));
	}

	@Test
	public void testSeparate_3ELEM_FULLSPACE() {
		List<String> list = separator.separate("aaa　bbb　ccc");
		assertEquals(3, list.size());
		assertEquals("aaa", list.get(0));
		assertEquals("bbb", list.get(1));
		assertEquals("ccc", list.get(2));
	}

}
