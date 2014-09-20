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

package cherry.spring.common.lib.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CamelCaseUtilTest {

	@Test
	public void testFromUnderscoreDelimited() {
		assertEquals("abcDefGhi",
				CamelCaseUtil.fromUnderscoreDelimited("abc_def_ghi", false));
		assertEquals("abcDefGhi",
				CamelCaseUtil.fromUnderscoreDelimited("ABC_DEF_GHI", false));
		assertEquals("AbcDefGhi",
				CamelCaseUtil.fromUnderscoreDelimited("abc_def_ghi", true));
		assertEquals("AbcDefGhi",
				CamelCaseUtil.fromUnderscoreDelimited("ABC_DEF_GHI", true));
	}

	@Test
	public void testFromWhitespaceDelimited() {
		assertEquals("abcDefGhi",
				CamelCaseUtil.fromWhitespaceDelimited("abc def ghi", false));
		assertEquals("abcDefGhi",
				CamelCaseUtil.fromWhitespaceDelimited("ABC DEF GHI", false));
		assertEquals("AbcDefGhi",
				CamelCaseUtil.fromWhitespaceDelimited("abc def ghi", true));
		assertEquals("AbcDefGhi",
				CamelCaseUtil.fromWhitespaceDelimited("ABC DEF GHI", true));
	}

}
