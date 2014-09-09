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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import cherry.spring.common.lib.chartype.TableReader.Entry;

public class CharTypeTest {

	private TableReader tableReader = new TableReader();

	@Test
	public void testIsSpace() {
		for (Entry entry : tableReader.getEntries()) {
			switch (entry.getWin31j()) {
			case 0x0009: // HT
			case 0x000A: // LF
			case 0x000B: // VT
			case 0x000C: // NP
			case 0x000D: // CR
			case 0x001C: // FS
			case 0x001D: // GS
			case 0x001E: // RS
			case 0x001F: // US
			case 0x0020: // SPC
				assertThat(CharType.isSpace(entry.getUnicode()), is(true));
				break;
			default:
				assertThat(CharType.isSpace(entry.getUnicode()), is(false));
				break;
			}
		}
	}

	@Test
	public void testIsNumeric() {
		for (Entry entry : tableReader.getEntries()) {
			switch (entry.getWin31j()) {
			case 0x0030: // '0'
			case 0x0031: // '1'
			case 0x0032: // '2'
			case 0x0033: // '3'
			case 0x0034: // '4'
			case 0x0035: // '5'
			case 0x0036: // '6'
			case 0x0037: // '7'
			case 0x0038: // '8'
			case 0x0039: // '9'
				assertThat(CharType.isNumeric(entry.getUnicode()), is(true));
				break;
			default:
				assertThat(CharType.isNumeric(entry.getUnicode()), is(false));
				break;
			}
		}
	}

	@Test
	public void testIsAlpha() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			if (0x0041 <= win31j && 0x005A >= win31j) {// A-Z
				assertThat(CharType.isAlpha(entry.getUnicode()), is(true));
			} else if (0x0061 <= win31j && 0x007A >= win31j) {// a-z
				assertThat(CharType.isAlpha(entry.getUnicode()), is(true));
			} else {
				assertThat(CharType.isAlpha(entry.getUnicode()), is(false));
			}
		}
	}

	@Test
	public void testIsUpper() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			if (0x0041 <= win31j && 0x005A >= win31j) {// A-Z
				assertThat(CharType.isUpper(entry.getUnicode()), is(true));
			} else {
				assertThat(CharType.isUpper(entry.getUnicode()), is(false));
			}
		}
	}

	@Test
	public void testIsLower() {
		for (Entry entry : tableReader.getEntries()) {
			int win31j = entry.getWin31j();
			if (0x0061 <= win31j && 0x007A >= win31j) {// a-z
				assertThat(CharType.isLower(entry.getUnicode()), is(true));
			} else {
				assertThat(CharType.isLower(entry.getUnicode()), is(false));
			}
		}
	}

	@Test
	public void testIsFullSpace() {
		for (Entry entry : tableReader.getEntries()) {
			switch (entry.getWin31j()) {
			case 0x8140: // IDEOGRAPHIC SPACE "　"
				assertThat(CharType.isFullSpace(entry.getUnicode()), is(true));
				break;
			default:
				assertThat(CharType.isFullSpace(entry.getUnicode()), is(false));
				break;
			}
		}
	}

}
