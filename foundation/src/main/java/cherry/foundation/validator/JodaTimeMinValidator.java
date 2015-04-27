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

public class JodaTimeMinValidator implements ConstraintValidator<JodaTimeMin, ReadablePartial> {

	private String minValue;

	private boolean inclusive;

	@Override
	public void initialize(JodaTimeMin annotation) {
		minValue = annotation.value();
		inclusive = annotation.inclusive();
	}

	@Override
	public boolean isValid(ReadablePartial value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else if (value instanceof LocalDate) {
			LocalDate min = LocalDate.parse(minValue);
			if (inclusive) {
				return min.compareTo(value) <= 0;
			} else {
				return min.compareTo(value) < 0;
			}
		} else if (value instanceof LocalDateTime) {
			LocalDateTime min = LocalDateTime.parse(minValue);
			if (inclusive) {
				return min.compareTo(value) <= 0;
			} else {
				return min.compareTo(value) < 0;
			}
		} else {
			return true;
		}
	}

}
