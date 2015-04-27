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

package cherry.foundation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePartial;

public class JodaTimeMaxValidator implements ConstraintValidator<JodaTimeMax, ReadablePartial> {

	private String maxValue;

	private boolean inclusive;

	@Override
	public void initialize(JodaTimeMax annotation) {
		maxValue = annotation.value();
		inclusive = annotation.inclusive();
	}

	@Override
	public boolean isValid(ReadablePartial value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else if (value instanceof LocalDate) {
			LocalDate max = LocalDate.parse(maxValue);
			if (inclusive) {
				return max.compareTo(value) >= 0;
			} else {
				return max.compareTo(value) > 0;
			}
		} else if (value instanceof LocalDateTime) {
			LocalDateTime max = LocalDateTime.parse(maxValue);
			if (inclusive) {
				return max.compareTo(value) >= 0;
			} else {
				return max.compareTo(value) > 0;
			}
		} else {
			return true;
		}
	}

}
