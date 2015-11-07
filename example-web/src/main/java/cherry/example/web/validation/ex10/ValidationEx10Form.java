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

package cherry.example.web.validation.ex10;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

import cherry.foundation.validator.CharTypeAlpha;
import cherry.foundation.validator.CharTypeAlphaNumeric;
import cherry.foundation.validator.CharTypeBasicLatin;
import cherry.foundation.validator.CharTypeCp932;
import cherry.foundation.validator.CharTypeFullAlpha;
import cherry.foundation.validator.CharTypeFullAlphaNumeric;
import cherry.foundation.validator.CharTypeFullHiragana;
import cherry.foundation.validator.CharTypeFullKatakana;
import cherry.foundation.validator.CharTypeFullNumeric;
import cherry.foundation.validator.CharTypeFullWidth;
import cherry.foundation.validator.CharTypeHalfKatakana;
import cherry.foundation.validator.CharTypeHalfWidth;
import cherry.foundation.validator.CharTypeNumeric;
import cherry.foundation.validator.MaxLength;
import cherry.foundation.validator.MinLength;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ValidationEx10Form {

	@NotEmpty()
	private String notempty;

	@MinLength(5)
	private String minlength05;

	@MaxLength(10)
	private String maxlength10;

	@CharTypeBasicLatin()
	private String basiclatin;

	@CharTypeAlpha()
	private String alpha;

	@CharTypeNumeric()
	private String numeric;

	@CharTypeAlphaNumeric()
	private String alphanumeric;

	@CharTypeHalfWidth()
	private String halfwidth;

	@CharTypeHalfKatakana()
	private String halfkatakana;

	@CharTypeFullWidth()
	private String fullwidth;

	@CharTypeFullAlpha()
	private String fullalpha;

	@CharTypeFullNumeric()
	private String fullnumeric;

	@CharTypeFullAlphaNumeric()
	private String fullalphanumeric;

	@CharTypeFullHiragana()
	private String fullhiragana;

	@CharTypeFullKatakana()
	private String fullkatakana;

	@CharTypeCp932()
	private String cp932;

}
