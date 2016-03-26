/*
 * Copyright 2016 agwlvssainokuni
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

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

public class NoMarkupTagValidator implements ConstraintValidator<NoMarkupTag, String> {

	private Set<String> acceptable;

	private final Pattern pattern = Pattern.compile("<([a-z][0-9a-z]*)", Pattern.CASE_INSENSITIVE);
	private final int groupNum = 1;

	@Override
	public void initialize(NoMarkupTag annotation) {
		this.acceptable = Sets.newHashSet(annotation.acceptable());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(value)) {
			return true;
		}

		Matcher matcher = pattern.matcher(value);
		while (matcher.find()) {
			String tag = matcher.group(groupNum).toLowerCase();
			if (acceptable.contains(tag)) {
				continue;
			}
			return false;
		}

		return true;
	}

}
