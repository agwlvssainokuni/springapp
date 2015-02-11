/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.generator.test;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.context.MessageSourceResolvable;

import cherry.foundation.logicalerror.LogicalErrorUtil;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class StringFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	private String string1;

	@cherry.foundation.validator.MinLength(value = 10, groups = { javax.validation.groups.Default.class })
	private String string2;

	@cherry.foundation.validator.MaxLength(value = 10, groups = { javax.validation.groups.Default.class })
	private String string3;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	private String string4;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MinLength(value = 10, groups = { javax.validation.groups.Default.class })
	private String string5;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MaxLength(value = 10, groups = { javax.validation.groups.Default.class })
	private String string6;

	@org.hibernate.validator.constraints.Email(groups = { javax.validation.groups.Default.class })
	private String string7;

	@cherry.foundation.validator.CharTypeBasicLatin(groups = { javax.validation.groups.Default.class })
	private String string8;

	@cherry.foundation.validator.CharTypeHalfWidth(groups = { javax.validation.groups.Default.class })
	private String string9;

	@cherry.foundation.validator.CharTypeNumeric(groups = { javax.validation.groups.Default.class })
	private String string10;

	@cherry.foundation.validator.CharTypeAlpha(groups = { javax.validation.groups.Default.class })
	private String string11;

	@cherry.foundation.validator.CharTypeAlphaNumeric(groups = { javax.validation.groups.Default.class })
	private String string12;

	@cherry.foundation.validator.CharTypeHalfKatakana(groups = { javax.validation.groups.Default.class })
	private String string13;

	@cherry.foundation.validator.CharTypeFullWidth(groups = { javax.validation.groups.Default.class })
	private String string14;

	@cherry.foundation.validator.CharTypeFullNumeric(groups = { javax.validation.groups.Default.class })
	private String string15;

	@cherry.foundation.validator.CharTypeFullAlpha(groups = { javax.validation.groups.Default.class })
	private String string16;

	@cherry.foundation.validator.CharTypeFullAlphaNumeric(groups = { javax.validation.groups.Default.class })
	private String string17;

	@cherry.foundation.validator.CharTypeFullHiragana(groups = { javax.validation.groups.Default.class })
	private String string18;

	@cherry.foundation.validator.CharTypeFullKatakana(groups = { javax.validation.groups.Default.class })
	private String string19;

	private String string20;

	@Getter
	public enum Prop {
		String1("string1", "stringForm.string1"), //
		String2("string2", "stringForm.string2"), //
		String3("string3", "stringForm.string3"), //
		String4("string4", "stringForm.string4"), //
		String5("string5", "stringForm.string5"), //
		String6("string6", "stringForm.string6"), //
		String7("string7", "stringForm.string7"), //
		String8("string8", "stringForm.string8"), //
		String9("string9", "stringForm.string9"), //
		String10("string10", "stringForm.string10"), //
		String11("string11", "stringForm.string11"), //
		String12("string12", "stringForm.string12"), //
		String13("string13", "stringForm.string13"), //
		String14("string14", "stringForm.string14"), //
		String15("string15", "stringForm.string15"), //
		String16("string16", "stringForm.string16"), //
		String17("string17", "stringForm.string17"), //
		String18("string18", "stringForm.string18"), //
		String19("string19", "stringForm.string19"), //
		String20("string20", "stringForm.string20"), //
		DUMMY("dummy", "dummy");

		private final String name;
		private final String nameWithForm;

		private Prop(String name, String nameWithForm) {
			this.name = name;
			this.nameWithForm = nameWithForm;
		}

		public MessageSourceResolvable resolve() {
			return LogicalErrorUtil.resolve(nameWithForm);
		}
	}

}
