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

public abstract class IntegerMasker implements Masker<Integer> {

	public static IntegerMasker lowerDigitMasker(int mask, int count) {
		return new LowerDigitMaskerImpl(mask, count);
	}

	static class LowerDigitMaskerImpl extends IntegerMasker {

		private final int mask;

		private final int count;

		public LowerDigitMaskerImpl(int mask, int count) {
			this.mask = mask;
			this.count = count;
		}

		@Override
		public Integer mask(Integer value) {
			if (value == null) {
				return value;
			}

			int masked = 0;
			int curValue = Math.abs(value);
			int curMask = mask;
			for (int i = 0, digit = 1;; i++, digit *= 10) {
				if (i < count) {
					masked += (curValue % 10) * digit;
				} else {
					masked += (curMask % 10) * digit;
				}
				curValue = curValue / 10;
				curMask = curMask / 10;
				if (curValue == 0) {
					break;
				}
			}

			if (value < 0) {
				return -masked;
			} else {
				return masked;
			}
		}
	}

}
