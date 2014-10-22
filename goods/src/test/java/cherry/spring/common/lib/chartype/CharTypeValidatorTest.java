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

import static cherry.spring.common.lib.chartype.CharTypeValidator.ALPHA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.BASIC_LATIN;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_ALPHA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_HIRAGANA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_KATAKANA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_LOWER;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_NUMERIC;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_SPACE;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_UPPER;
import static cherry.spring.common.lib.chartype.CharTypeValidator.FULL_WIDTH;
import static cherry.spring.common.lib.chartype.CharTypeValidator.HALF_KATAKANA;
import static cherry.spring.common.lib.chartype.CharTypeValidator.HALF_WIDTH;
import static cherry.spring.common.lib.chartype.CharTypeValidator.LOWER;
import static cherry.spring.common.lib.chartype.CharTypeValidator.NUMERIC;
import static cherry.spring.common.lib.chartype.CharTypeValidator.SPACE;
import static cherry.spring.common.lib.chartype.CharTypeValidator.UPPER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CharTypeValidatorTest {

	@Test
	public void testIsValidNone() {
		assertThat(CharTypeValidator.isValid(' ', 0), is(false));
		assertThat(CharTypeValidator.isValid('0', 0), is(false));
		assertThat(CharTypeValidator.isValid('A', 0), is(false));
		assertThat(CharTypeValidator.isValid('a', 0), is(false));
	}

	@Test
	public void testIsBasicLatin() {
		assertThat(CharTypeValidator.isValid(' ', BASIC_LATIN), is(true));
		assertThat(CharTypeValidator.isValid('0', BASIC_LATIN), is(true));
		assertThat(CharTypeValidator.isValid('A', BASIC_LATIN), is(true));
		assertThat(CharTypeValidator.isValid('a', BASIC_LATIN), is(true));
		assertThat(CharTypeValidator.isValid('\uFF71', BASIC_LATIN), is(false));
		assertThat(CharTypeValidator.isValid('Ａ', BASIC_LATIN), is(false));
	}

	@Test
	public void testIsHalfWidth() {
		assertThat(CharTypeValidator.isValid(' ', HALF_WIDTH), is(true));
		assertThat(CharTypeValidator.isValid('0', HALF_WIDTH), is(true));
		assertThat(CharTypeValidator.isValid('A', HALF_WIDTH), is(true));
		assertThat(CharTypeValidator.isValid('a', HALF_WIDTH), is(true));
		assertThat(CharTypeValidator.isValid('\uFF71', HALF_WIDTH), is(true));
		assertThat(CharTypeValidator.isValid('Ａ', HALF_WIDTH), is(false));
	}

	@Test
	public void testIsFullWidth() {
		assertThat(CharTypeValidator.isValid(' ', FULL_WIDTH), is(false));
		assertThat(CharTypeValidator.isValid('0', FULL_WIDTH), is(false));
		assertThat(CharTypeValidator.isValid('A', FULL_WIDTH), is(false));
		assertThat(CharTypeValidator.isValid('a', FULL_WIDTH), is(false));
		assertThat(CharTypeValidator.isValid('\uFF71', FULL_WIDTH), is(false));
		assertThat(CharTypeValidator.isValid('Ａ', FULL_WIDTH), is(true));
	}

	@Test
	public void testIsValidSpace() {
		assertThat(CharTypeValidator.isValid(' ', SPACE), is(true));
		assertThat(CharTypeValidator.isValid('0', SPACE), is(false));
		assertThat(CharTypeValidator.isValid('A', SPACE), is(false));
		assertThat(CharTypeValidator.isValid('a', SPACE), is(false));
	}

	@Test
	public void testIsValidNumeric() {
		assertThat(CharTypeValidator.isValid(' ', NUMERIC), is(false));
		assertThat(CharTypeValidator.isValid('0', NUMERIC), is(true));
		assertThat(CharTypeValidator.isValid('A', NUMERIC), is(false));
		assertThat(CharTypeValidator.isValid('a', NUMERIC), is(false));
	}

	@Test
	public void testIsValidAlpha() {
		assertThat(CharTypeValidator.isValid(' ', ALPHA), is(false));
		assertThat(CharTypeValidator.isValid('0', ALPHA), is(false));
		assertThat(CharTypeValidator.isValid('A', ALPHA), is(true));
		assertThat(CharTypeValidator.isValid('a', ALPHA), is(true));
	}

	@Test
	public void testIsValidUpper() {
		assertThat(CharTypeValidator.isValid(' ', ALPHA), is(false));
		assertThat(CharTypeValidator.isValid('0', ALPHA), is(false));
		assertThat(CharTypeValidator.isValid('A', UPPER), is(true));
		assertThat(CharTypeValidator.isValid('a', UPPER), is(false));
	}

	@Test
	public void testIsValidLower() {
		assertThat(CharTypeValidator.isValid(' ', ALPHA), is(false));
		assertThat(CharTypeValidator.isValid('0', ALPHA), is(false));
		assertThat(CharTypeValidator.isValid('A', LOWER), is(false));
		assertThat(CharTypeValidator.isValid('a', LOWER), is(true));
	}

	@Test
	public void testIsValidFullSpace() {
		assertThat(CharTypeValidator.isValid('　', FULL_SPACE), is(true));
		assertThat(CharTypeValidator.isValid('０', FULL_SPACE), is(false));
		assertThat(CharTypeValidator.isValid('Ａ', FULL_SPACE), is(false));
		assertThat(CharTypeValidator.isValid('ａ', FULL_SPACE), is(false));
	}

	@Test
	public void testIsValidFullNumeric() {
		assertThat(CharTypeValidator.isValid('　', FULL_NUMERIC), is(false));
		assertThat(CharTypeValidator.isValid('０', FULL_NUMERIC), is(true));
		assertThat(CharTypeValidator.isValid('Ａ', FULL_NUMERIC), is(false));
		assertThat(CharTypeValidator.isValid('ａ', FULL_NUMERIC), is(false));
	}

	@Test
	public void testIsValidFullAlpha() {
		assertThat(CharTypeValidator.isValid('　', FULL_ALPHA), is(false));
		assertThat(CharTypeValidator.isValid('０', FULL_ALPHA), is(false));
		assertThat(CharTypeValidator.isValid('Ａ', FULL_ALPHA), is(true));
		assertThat(CharTypeValidator.isValid('ａ', FULL_ALPHA), is(true));
	}

	@Test
	public void testIsValidFullUpper() {
		assertThat(CharTypeValidator.isValid('　', FULL_UPPER), is(false));
		assertThat(CharTypeValidator.isValid('０', FULL_UPPER), is(false));
		assertThat(CharTypeValidator.isValid('Ａ', FULL_UPPER), is(true));
		assertThat(CharTypeValidator.isValid('ａ', FULL_UPPER), is(false));
	}

	@Test
	public void testIsValidFullLower() {
		assertThat(CharTypeValidator.isValid('　', FULL_LOWER), is(false));
		assertThat(CharTypeValidator.isValid('０', FULL_LOWER), is(false));
		assertThat(CharTypeValidator.isValid('Ａ', FULL_LOWER), is(false));
		assertThat(CharTypeValidator.isValid('ａ', FULL_LOWER), is(true));
	}

	@Test
	public void testIsValidFullHiragana() {
		assertThat(CharTypeValidator.isValid('あ', FULL_HIRAGANA), is(true));
		assertThat(CharTypeValidator.isValid('ア', FULL_HIRAGANA), is(false));
		assertThat(CharTypeValidator.isValid('\uFF71', FULL_HIRAGANA),
				is(false));
	}

	@Test
	public void testIsValidFullKatakana() {
		assertThat(CharTypeValidator.isValid('あ', FULL_KATAKANA), is(false));
		assertThat(CharTypeValidator.isValid('ア', FULL_KATAKANA), is(true));
		assertThat(CharTypeValidator.isValid('\uFF71', FULL_KATAKANA),
				is(false));
	}

	@Test
	public void testIsValidHalfKatakana() {
		assertThat(CharTypeValidator.isValid('あ', HALF_KATAKANA), is(false));
		assertThat(CharTypeValidator.isValid('ア', HALF_KATAKANA), is(false));
		assertThat(CharTypeValidator.isValid('\uFF71', HALF_KATAKANA), is(true));
	}

	@Test
	public void testIsValidWithAcceptableNull() {
		assertThat(CharTypeValidator.isValid(' ', SPACE, null), is(true));
		assertThat(CharTypeValidator.isValid('0', SPACE, null), is(false));
		assertThat(CharTypeValidator.isValid('A', SPACE, null), is(false));
		assertThat(CharTypeValidator.isValid('a', SPACE, null), is(false));
	}

	@Test
	public void testIsValidWithAcceptable0() {
		int[] acceptable = new int[] {};
		assertThat(CharTypeValidator.isValid(' ', SPACE, acceptable), is(true));
		assertThat(CharTypeValidator.isValid('0', SPACE, acceptable), is(false));
		assertThat(CharTypeValidator.isValid('A', SPACE, acceptable), is(false));
		assertThat(CharTypeValidator.isValid('a', SPACE, acceptable), is(false));
	}

	@Test
	public void testIsValidWithAcceptable1() {
		int[] acceptable = new int[] { '0' };
		assertThat(CharTypeValidator.isValid(' ', SPACE, acceptable), is(true));
		assertThat(CharTypeValidator.isValid('0', SPACE, acceptable), is(true));
		assertThat(CharTypeValidator.isValid('A', SPACE, acceptable), is(false));
		assertThat(CharTypeValidator.isValid('a', SPACE, acceptable), is(false));
	}

	@Test
	public void testIsValidWithAcceptable2() {
		int[] acceptable = new int[] { '0', 'A' };
		assertThat(CharTypeValidator.isValid(' ', SPACE, acceptable), is(true));
		assertThat(CharTypeValidator.isValid('0', SPACE, acceptable), is(true));
		assertThat(CharTypeValidator.isValid('A', SPACE, acceptable), is(true));
		assertThat(CharTypeValidator.isValid('a', SPACE, acceptable), is(false));
	}

}
