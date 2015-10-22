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

package cherry.foundation.download;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Function;

public class Rfc5987Encoder implements Function<String, String> {

	private Charset charset;

	private String languageTag;

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void setLanguageTag(String languageTag) {
		this.languageTag = languageTag;
	}

	@Override
	public String apply(String input) {

		byte[] b = input.getBytes(charset);
		StringBuilder builder = new StringBuilder(b.length * 3);

		builder.append(charset.name()).append("'");
		if (StringUtils.isNotBlank(languageTag)) {
			builder.append(languageTag);
		}
		builder.append("'");

		for (int i = 0; i < b.length; i++) {
			if (isAttrChar((char) b[i])) {
				builder.append((char) b[i]);
			} else {
				builder.append('%');
				int c = 0xff & b[i];
				if (c < 0x10) {
					builder.append('0');
				}
				builder.append(Integer.toHexString(c));
			}
		}
		return builder.toString();
	}

	private boolean isAttrChar(char c) {
		// DIGIT
		if (c >= '0' && c <= '9') {
			return true;
		}
		// ALPHA
		if (c >= 'A' && c <= 'Z') {
			return true;
		}
		if (c >= 'a' && c <= 'z') {
			return true;
		}
		// token except ( "*" / "'" / "%" )
		switch (c) {
		case '!':
		case '#':
		case '$':
		case '&':
		case '+':
		case '-':
		case '.':
		case '^':
		case '_':
		case '`':
		case '|':
		case '~':
			return true;
		default:
			return false;
		}
	}

}
