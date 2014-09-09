/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.common.lib.chartype;

import static cherry.spring.common.lib.chartype.StringType.ALPHA;
import static cherry.spring.common.lib.chartype.StringType.FULL_ALPHA;
import static cherry.spring.common.lib.chartype.StringType.FULL_HIRAGANA;
import static cherry.spring.common.lib.chartype.StringType.FULL_KATAKANA;
import static cherry.spring.common.lib.chartype.StringType.FULL_LOWER;
import static cherry.spring.common.lib.chartype.StringType.FULL_NUMERIC;
import static cherry.spring.common.lib.chartype.StringType.FULL_SPACE;
import static cherry.spring.common.lib.chartype.StringType.FULL_UPPER;
import static cherry.spring.common.lib.chartype.StringType.HALF_KATAKANA;
import static cherry.spring.common.lib.chartype.StringType.LOWER;
import static cherry.spring.common.lib.chartype.StringType.NUMERIC;
import static cherry.spring.common.lib.chartype.StringType.SPACE;
import static cherry.spring.common.lib.chartype.StringType.UPPER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringTypeTest {

	@Test
	public void testIsValidNone() {
		assertThat(StringType.isValid(' ', 0), is(false));
		assertThat(StringType.isValid('0', 0), is(false));
		assertThat(StringType.isValid('A', 0), is(false));
		assertThat(StringType.isValid('a', 0), is(false));
	}

	@Test
	public void testIsValidSpace() {
		assertThat(StringType.isValid(' ', SPACE), is(true));
		assertThat(StringType.isValid('0', SPACE), is(false));
		assertThat(StringType.isValid('A', SPACE), is(false));
		assertThat(StringType.isValid('a', SPACE), is(false));
	}

	@Test
	public void testIsValidNumeric() {
		assertThat(StringType.isValid(' ', NUMERIC), is(false));
		assertThat(StringType.isValid('0', NUMERIC), is(true));
		assertThat(StringType.isValid('A', NUMERIC), is(false));
		assertThat(StringType.isValid('a', NUMERIC), is(false));
	}

	@Test
	public void testIsValidAlpha() {
		assertThat(StringType.isValid(' ', ALPHA), is(false));
		assertThat(StringType.isValid('0', ALPHA), is(false));
		assertThat(StringType.isValid('A', ALPHA), is(true));
		assertThat(StringType.isValid('a', ALPHA), is(true));
	}

	@Test
	public void testIsValidUpper() {
		assertThat(StringType.isValid(' ', ALPHA), is(false));
		assertThat(StringType.isValid('0', ALPHA), is(false));
		assertThat(StringType.isValid('A', UPPER), is(true));
		assertThat(StringType.isValid('a', UPPER), is(false));
	}

	@Test
	public void testIsValidLower() {
		assertThat(StringType.isValid(' ', ALPHA), is(false));
		assertThat(StringType.isValid('0', ALPHA), is(false));
		assertThat(StringType.isValid('A', LOWER), is(false));
		assertThat(StringType.isValid('a', LOWER), is(true));
	}

	@Test
	public void testIsValidFullSpace() {
		assertThat(StringType.isValid('　', FULL_SPACE), is(true));
		assertThat(StringType.isValid('０', FULL_SPACE), is(false));
		assertThat(StringType.isValid('Ａ', FULL_SPACE), is(false));
		assertThat(StringType.isValid('ａ', FULL_SPACE), is(false));
	}

	@Test
	public void testIsValidFullNumeric() {
		assertThat(StringType.isValid('　', FULL_NUMERIC), is(false));
		assertThat(StringType.isValid('０', FULL_NUMERIC), is(true));
		assertThat(StringType.isValid('Ａ', FULL_NUMERIC), is(false));
		assertThat(StringType.isValid('ａ', FULL_NUMERIC), is(false));
	}

	@Test
	public void testIsValidFullAlpha() {
		assertThat(StringType.isValid('　', FULL_ALPHA), is(false));
		assertThat(StringType.isValid('０', FULL_ALPHA), is(false));
		assertThat(StringType.isValid('Ａ', FULL_ALPHA), is(true));
		assertThat(StringType.isValid('ａ', FULL_ALPHA), is(true));
	}

	@Test
	public void testIsValidFullUpper() {
		assertThat(StringType.isValid('　', FULL_UPPER), is(false));
		assertThat(StringType.isValid('０', FULL_UPPER), is(false));
		assertThat(StringType.isValid('Ａ', FULL_UPPER), is(true));
		assertThat(StringType.isValid('ａ', FULL_UPPER), is(false));
	}

	@Test
	public void testIsValidFullLower() {
		assertThat(StringType.isValid('　', FULL_LOWER), is(false));
		assertThat(StringType.isValid('０', FULL_LOWER), is(false));
		assertThat(StringType.isValid('Ａ', FULL_LOWER), is(false));
		assertThat(StringType.isValid('ａ', FULL_LOWER), is(true));
	}

	@Test
	public void testIsValidFullHiragana() {
		assertThat(StringType.isValid('あ', FULL_HIRAGANA), is(true));
		assertThat(StringType.isValid('ア', FULL_HIRAGANA), is(false));
		assertThat(StringType.isValid('\uFF71', FULL_HIRAGANA), is(false));
	}

	@Test
	public void testIsValidFullKatakana() {
		assertThat(StringType.isValid('あ', FULL_KATAKANA), is(false));
		assertThat(StringType.isValid('ア', FULL_KATAKANA), is(true));
		assertThat(StringType.isValid('\uFF71', FULL_KATAKANA), is(false));
	}

	@Test
	public void testIsValidHalfKatakana() {
		assertThat(StringType.isValid('あ', HALF_KATAKANA), is(false));
		assertThat(StringType.isValid('ア', HALF_KATAKANA), is(false));
		assertThat(StringType.isValid('\uFF71', HALF_KATAKANA), is(true));
	}

	@Test
	public void testIsValidWithAcceptableNull() {
		assertThat(StringType.isValid(' ', SPACE, null), is(true));
		assertThat(StringType.isValid('0', SPACE, null), is(false));
		assertThat(StringType.isValid('A', SPACE, null), is(false));
		assertThat(StringType.isValid('a', SPACE, null), is(false));
	}

	@Test
	public void testIsValidWithAcceptable0() {
		int[] acceptable = new int[] {};
		assertThat(StringType.isValid(' ', SPACE, acceptable), is(true));
		assertThat(StringType.isValid('0', SPACE, acceptable), is(false));
		assertThat(StringType.isValid('A', SPACE, acceptable), is(false));
		assertThat(StringType.isValid('a', SPACE, acceptable), is(false));
	}

	@Test
	public void testIsValidWithAcceptable1() {
		int[] acceptable = new int[] { '0' };
		assertThat(StringType.isValid(' ', SPACE, acceptable), is(true));
		assertThat(StringType.isValid('0', SPACE, acceptable), is(true));
		assertThat(StringType.isValid('A', SPACE, acceptable), is(false));
		assertThat(StringType.isValid('a', SPACE, acceptable), is(false));
	}

	@Test
	public void testIsValidWithAcceptable2() {
		int[] acceptable = new int[] { '0', 'A' };
		assertThat(StringType.isValid(' ', SPACE, acceptable), is(true));
		assertThat(StringType.isValid('0', SPACE, acceptable), is(true));
		assertThat(StringType.isValid('A', SPACE, acceptable), is(true));
		assertThat(StringType.isValid('a', SPACE, acceptable), is(false));
	}

}
