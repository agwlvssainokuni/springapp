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
			case '\u0009': // HT
			case '\n': // LF (0x000A)
			case '\u000B': // VT
			case '\u000C': // NP
			case '\r': // CR (0x000D)
			case '\u001C': // FS
			case '\u001D': // GS
			case '\u001E': // RS
			case '\u001F': // US
			case ' ': // (0x0020)
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
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
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
			if ('A' <= entry.getWin31j() && 'Z' >= entry.getWin31j()) {
				assertThat(CharType.isAlpha(entry.getUnicode()), is(true));
			} else if ('a' <= entry.getWin31j() && 'z' >= entry.getWin31j()) {
				assertThat(CharType.isAlpha(entry.getUnicode()), is(true));
			} else {
				assertThat(CharType.isAlpha(entry.getUnicode()), is(false));
			}
		}
	}

	@Test
	public void testIsUpper() {
		for (Entry entry : tableReader.getEntries()) {
			if ('A' <= entry.getWin31j() && 'Z' >= entry.getWin31j()) {
				assertThat(CharType.isUpper(entry.getUnicode()), is(true));
			} else {
				assertThat(CharType.isUpper(entry.getUnicode()), is(false));
			}
		}
	}

	@Test
	public void testIsLower() {
		for (Entry entry : tableReader.getEntries()) {
			if ('a' <= entry.getWin31j() && 'z' >= entry.getWin31j()) {
				assertThat(CharType.isLower(entry.getUnicode()), is(true));
			} else {
				assertThat(CharType.isLower(entry.getUnicode()), is(false));
			}
		}
	}

}
