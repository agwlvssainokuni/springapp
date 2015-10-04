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

package cherry.entree.secure.passwd;

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
public abstract class PasswdFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MaxLength(value = 512, groups = { javax.validation.groups.Default.class })
	@org.hibernate.validator.constraints.Email(groups = { javax.validation.groups.Default.class })
	private String loginId;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MinLength(value = 8, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MaxLength(value = 16, groups = { javax.validation.groups.Default.class })
	private String password;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MinLength(value = 8, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MaxLength(value = 16, groups = { javax.validation.groups.Default.class })
	private String newPassword;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MinLength(value = 8, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MaxLength(value = 16, groups = { javax.validation.groups.Default.class })
	private String newPasswordConf;

	@Getter
	public enum Prop {
		LoginId("loginId", "passwdForm.loginId"), //
		Password("password", "passwdForm.password"), //
		NewPassword("newPassword", "passwdForm.newPassword"), //
		NewPasswordConf("newPasswordConf", "passwdForm.newPasswordConf"), //
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
