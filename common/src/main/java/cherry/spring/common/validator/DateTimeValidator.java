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

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class DateTimeValidator implements ConstraintValidator<DateTime, String> {

	private String[] datetimeRegex;

	@Override
	public void initialize(DateTime annon) {
		List<String> list = new ArrayList<>();
		list.add("^" + annon.dateRegex() + " +" + annon.timeRegex() + "$");
		if (annon.timeIsOptional()) {
			list.add("^" + annon.dateRegex() + "$");
		}
		datetimeRegex = list.toArray(new String[list.size()]);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(value)) {
			return true;
		}

		for (String regex : datetimeRegex) {
			if (value.matches(regex)) {
				return true;
			}
		}

		return false;
	}

}
