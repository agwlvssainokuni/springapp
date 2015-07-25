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

package cherry.sqlman.password;

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
public abstract class PasswordRequestFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G1.class, cherry.foundation.validator.groups.G2.class })
	@cherry.foundation.validator.MaxLength(value = 300, groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G1.class, cherry.foundation.validator.groups.G2.class })
	private String mailAddr;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	@cherry.foundation.validator.MinLength(value = 4, groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	@cherry.foundation.validator.MaxLength(value = 32, groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	private String password;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	@cherry.foundation.validator.MinLength(value = 4, groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	@cherry.foundation.validator.MaxLength(value = 32, groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	private String passwordConf;

	@Getter
	public enum Prop {
		MailAddr("mailAddr", "passwordRequestForm.mailAddr"), //
		Password("password", "passwordRequestForm.password"), //
		PasswordConf("passwordConf", "passwordRequestForm.passwordConf"), //
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
