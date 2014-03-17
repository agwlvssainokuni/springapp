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

import static org.joda.time.format.DateTimeFormat.forPattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class DateTimeOptPatternValidator implements
		ConstraintValidator<DateTimeOptPattern, String> {

	private DateTimeFormatter formatter;

	@Override
	public void initialize(DateTimeOptPattern annon) {
		formatter = new DateTimeFormatterBuilder()
				.append(forPattern(annon.datePattern()))
				.appendOptional(forPattern(annon.timePattern()).getParser())
				.toFormatter();
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
