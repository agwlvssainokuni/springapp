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

package cherry.spring.common.validator.alt;

import static org.joda.time.format.DateTimeFormat.forPattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

public class DateTimePatternValidator implements
		ConstraintValidator<DateTimePattern, String> {

	private DateTimeFormatter formatter;

	@Override
	public void initialize(DateTimePattern annon) {
		formatter = forPattern(annon.pattern());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(value)) {
			return true;
		}

		try {
			DateTime.parse(value, formatter);
			return true;
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

}
