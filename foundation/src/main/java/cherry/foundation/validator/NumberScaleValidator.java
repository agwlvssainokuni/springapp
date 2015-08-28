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

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberScaleValidator implements ConstraintValidator<NumberScale, Number> {

	private int scale;

	@Override
	public void initialize(NumberScale annotation) {
		scale = annotation.value();
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		if (value instanceof BigDecimal) {
			BigDecimal d = (BigDecimal) value;
			return d.compareTo(d.setScale(scale, RoundingMode.DOWN)) == 0;
		} else if (value instanceof Double) {
			BigDecimal d = BigDecimal.valueOf(value.doubleValue());
			return value.doubleValue() == d.setScale(scale, RoundingMode.DOWN).doubleValue();
		} else if (value instanceof Float) {
			BigDecimal d = BigDecimal.valueOf(value.floatValue());
			return value.floatValue() == d.setScale(scale, RoundingMode.DOWN).floatValue();
		} else {
			return true;
		}
	}

}
