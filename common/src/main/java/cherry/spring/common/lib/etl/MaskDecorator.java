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

package cherry.spring.common.lib.etl;

public class MaskDecorator implements Decorator {

	private int maskBegin = 1;

	private int maskEnd = 0;

	private String maskChar = "X";

	public void setMaskBegin(int maskBegin) {
		this.maskBegin = maskBegin;
	}

	public void setMaskEnd(int maskEnd) {
		this.maskEnd = maskEnd;
	}

	public void setMaskChar(String maskChar) {
		this.maskChar = maskChar;
	}

	@Override
	public Object decorate(Object field) {
		if (field == null) {
			return null;
		}
		String s = field.toString();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (i >= maskBegin && i < s.length() - maskEnd) {
				builder.append(maskChar);
			} else {
				builder.append(s.charAt(i));
			}
		}
		return builder.toString();
	}

}
