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

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.isWhitespace;
import static java.lang.Character.UnicodeBlock.BASIC_LATIN;
import static java.lang.Character.UnicodeBlock.HIRAGANA;
import static java.lang.Character.UnicodeBlock.KATAKANA;
import static java.lang.Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS;
import static java.lang.Character.UnicodeBlock.of;

public class CharType {

	public static boolean isSpace(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isWhitespace(codePoint);
	}

	public static boolean isNumeric(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isDigit(codePoint);
	}

	public static boolean isAlpha(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isAlphabetic(codePoint);
	}

	public static boolean isUpper(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isUpperCase(codePoint);
	}

	public static boolean isLower(int codePoint) {
		return of(codePoint) == BASIC_LATIN && isLowerCase(codePoint);
	}

	public static boolean isMinus(int codePoint) {
		return codePoint == '-';
	}

	public static boolean isUnderscore(int codePoint) {
		return codePoint == '_';
	}

	public static boolean isFullSpace(int codePoint) {
		return of(codePoint) != BASIC_LATIN && isWhitespace(codePoint);
	}

	public static boolean isFullNumeric(int codePoint) {
		return of(codePoint) != BASIC_LATIN && isDigit(codePoint);
	}

	public static boolean isFullAlpha(int codePoint) {
		return of(codePoint) != BASIC_LATIN && isAlphabetic(codePoint);
	}

	public static boolean isFullUpper(int codePoint) {
		return of(codePoint) != BASIC_LATIN && isUpperCase(codePoint);
	}

	public static boolean isFullLower(int codePoint) {
		return of(codePoint) != BASIC_LATIN && isLowerCase(codePoint);
	}

	public static boolean isFullHiragana(int codePoint) {
		return of(codePoint) == HIRAGANA || codePoint == 'ー'
				|| codePoint == '・';
	}

	public static boolean isFullKatakana(int codePoint) {
		return of(codePoint) == KATAKANA
				|| of(codePoint) == KATAKANA_PHONETIC_EXTENSIONS;
	}

	public static boolean isFullMinus(int codePoint) {
		return codePoint == '−';
	}

}
