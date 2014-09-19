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

package cherry.spring.common.lib.mask;

public abstract class StringMasker implements Masker<String> {

	public static StringMasker nullMasker() {
		return new NullMaskerImpl();
	}

	public static StringMasker tailMasker(String maskChar, int count) {
		return new TailMaskerImpl(maskChar, count);
	}

	public static StringMasker fixedTailMasker(String maskChar, int count,
			int fixedLength) {
		return new FixedTailMaskerImpl(maskChar, count, fixedLength);
	}

	static class NullMaskerImpl extends StringMasker {

		@Override
		public String mask(String value) {
			return value;
		}
	}

	static class TailMaskerImpl extends StringMasker {

		private final String maskChar;

		private final int count;

		public TailMaskerImpl(String maskChar, int count) {
			this.maskChar = maskChar;
			this.count = count;
		}

		@Override
		public String mask(String value) {
			if (value == null) {
				return value;
			}
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < value.length(); i++) {
				if (i < count) {
					s.append(value.charAt(i));
				} else {
					s.append(maskChar);
				}
			}
			return s.toString();
		}
	}

	static class FixedTailMaskerImpl extends StringMasker {

		private final String maskChar;

		private final int count;

		private final int fixedLength;

		public FixedTailMaskerImpl(String maskChar, int count, int fixedLength) {
			this.maskChar = maskChar;
			this.count = count;
			this.fixedLength = fixedLength;
		}

		@Override
		public String mask(String value) {
			if (value == null) {
				return value;
			}
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < fixedLength; i++) {
				if (i < count && i < value.length()) {
					s.append(value.charAt(i));
				} else {
					s.append(maskChar);
				}
			}
			return s.toString();
		}
	}

}
