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

import static cherry.spring.common.lib.chartype.CharType.isAlpha;
import static cherry.spring.common.lib.chartype.CharType.isFullAlpha;
import static cherry.spring.common.lib.chartype.CharType.isFullHiragana;
import static cherry.spring.common.lib.chartype.CharType.isFullKatakana;
import static cherry.spring.common.lib.chartype.CharType.isFullLower;
import static cherry.spring.common.lib.chartype.CharType.isFullNumeric;
import static cherry.spring.common.lib.chartype.CharType.isFullSpace;
import static cherry.spring.common.lib.chartype.CharType.isFullUpper;
import static cherry.spring.common.lib.chartype.CharType.isHalfKatakana;
import static cherry.spring.common.lib.chartype.CharType.isLower;
import static cherry.spring.common.lib.chartype.CharType.isNumeric;
import static cherry.spring.common.lib.chartype.CharType.isSpace;
import static cherry.spring.common.lib.chartype.CharType.isUpper;
import static java.lang.Character.codePointAt;
import static java.lang.Character.isLowSurrogate;

public class StringType {

	public static final int SPACE = (1 << 0);

	public static final int NUMERIC = (1 << 1);

	public static final int ALPHA = (1 << 2);

	public static final int UPPER = (1 << 3);

	public static final int LOWER = (1 << 4);

	public static final int FULL_SPACE = (1 << 5);

	public static final int FULL_NUMERIC = (1 << 6);

	public static final int FULL_ALPHA = (1 << 7);

	public static final int FULL_UPPER = (1 << 8);

	public static final int FULL_LOWER = (1 << 9);

	public static final int FULL_HIRAGANA = (1 << 10);

	public static final int FULL_KATAKANA = (1 << 11);

	public static final int HALF_KATAKANA = (1 << 12);

	public static boolean isValid(int codePoint, int mode) {
		if ((mode & SPACE) != 0 && isSpace(codePoint)) {
			return true;
		}
		if ((mode & NUMERIC) != 0 && isNumeric(codePoint)) {
			return true;
		}
		if ((mode & ALPHA) != 0 && isAlpha(codePoint)) {
			return true;
		}
		if ((mode & UPPER) != 0 && isUpper(codePoint)) {
			return true;
		}
		if ((mode & LOWER) != 0 && isLower(codePoint)) {
			return true;
		}
		if ((mode & SPACE) != 0 && isSpace(codePoint)) {
			return true;
		}
		if ((mode & FULL_SPACE) != 0 && isFullSpace(codePoint)) {
			return true;
		}
		if ((mode & FULL_NUMERIC) != 0 && isFullNumeric(codePoint)) {
			return true;
		}
		if ((mode & FULL_ALPHA) != 0 && isFullAlpha(codePoint)) {
			return true;
		}
		if ((mode & FULL_UPPER) != 0 && isFullUpper(codePoint)) {
			return true;
		}
		if ((mode & FULL_LOWER) != 0 && isFullLower(codePoint)) {
			return true;
		}
		if ((mode & FULL_HIRAGANA) != 0 && isFullHiragana(codePoint)) {
			return true;
		}
		if ((mode & FULL_KATAKANA) != 0 && isFullKatakana(codePoint)) {
			return true;
		}
		if ((mode & HALF_KATAKANA) != 0 && isHalfKatakana(codePoint)) {
			return true;
		}
		return false;
	}

	public static boolean isValid(int codePoint, int mode, int[] acceptable) {
		if (isValid(codePoint, mode)) {
			return true;
		}
		if (acceptable != null) {
			for (int ch : acceptable) {
				if (codePoint == ch) {
					return true;
				}
			}
		}
		return false;
	}

	public static StringTypeResult validate(CharSequence seq, int mode,
			int[] acceptable) {
		for (int i = 0; i < seq.length(); i++) {
			if (isLowSurrogate(seq.charAt(i))) {
				continue;
			}
			if (isValid(codePointAt(seq, i), mode, acceptable)) {
				continue;
			}
			return new StringTypeResult(false, i, codePointAt(seq, i));
		}
		return new StringTypeResult();
	}

}
