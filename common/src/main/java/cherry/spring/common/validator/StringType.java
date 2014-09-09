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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { StringTypeValidator.class })
public @interface StringType {

	public static final int SPACE = cherry.spring.common.lib.chartype.StringType.SPACE;
	public static final int NUMERIC = cherry.spring.common.lib.chartype.StringType.NUMERIC;
	public static final int ALPHA = cherry.spring.common.lib.chartype.StringType.ALPHA;
	public static final int UPPER = cherry.spring.common.lib.chartype.StringType.UPPER;
	public static final int LOWER = cherry.spring.common.lib.chartype.StringType.LOWER;
	public static final int FULL_SPACE = cherry.spring.common.lib.chartype.StringType.FULL_SPACE;
	public static final int FULL_NUMERIC = cherry.spring.common.lib.chartype.StringType.FULL_NUMERIC;
	public static final int FULL_ALPHA = cherry.spring.common.lib.chartype.StringType.FULL_ALPHA;
	public static final int FULL_UPPER = cherry.spring.common.lib.chartype.StringType.FULL_UPPER;
	public static final int FULL_LOWER = cherry.spring.common.lib.chartype.StringType.FULL_LOWER;
	public static final int FULL_HIRAGANA = cherry.spring.common.lib.chartype.StringType.FULL_HIRAGANA;
	public static final int FULL_KATAKANA = cherry.spring.common.lib.chartype.StringType.FULL_KATAKANA;
	public static final int HALF_KATAKANA = cherry.spring.common.lib.chartype.StringType.HALF_KATAKANA;

	int mode() default 0;

	String acceptable() default "";

	String message() default "{cherry.spring.common.validator.StringType.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD })
	@Retention(RUNTIME)
	public @interface List {
		StringType[] value();
	}

}
