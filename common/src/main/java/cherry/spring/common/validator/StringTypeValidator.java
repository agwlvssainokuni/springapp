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

package cherry.spring.common.validator;

import static cherry.spring.common.lib.chartype.StringType.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import cherry.spring.common.lib.chartype.StringTypeResult;

public class StringTypeValidator implements
		ConstraintValidator<StringType, String> {

	private int mode;

	private int[] acceptable;

	@Override
	public void initialize(StringType annotation) {
		this.mode = annotation.mode();
		if (annotation.acceptable() == null) {
			this.acceptable = null;
		} else {
			String acc = annotation.acceptable();
			this.acceptable = new int[acc.codePointCount(0, acc.length())];
			for (int i = 0, j = 0; i < acc.length(); i++) {
				if (Character.isLowSurrogate(acc.charAt(i))) {
					continue;
				}
				this.acceptable[j++] = Character.codePointAt(acc, i);
			}
		}
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isEmpty(value)) {
			return true;
		}
		StringTypeResult result = validate(value, mode, acceptable);
		return result.isValid();
	}

}
