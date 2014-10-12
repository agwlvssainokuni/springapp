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

package cherry.spring.entree.controller.secure.passwd;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@EqualsAndHashCode
public class PasswdForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.hibernate.validator.constraints.NotEmpty
	@cherry.spring.common.validator.MaxLength(512)
	@org.hibernate.validator.constraints.Email
	private String loginId;

	@javax.validation.constraints.NotNull
	@cherry.spring.common.validator.MinLength(8)
	@cherry.spring.common.validator.MaxLength(16)
	private String password;

	@javax.validation.constraints.NotNull
	@cherry.spring.common.validator.MinLength(8)
	@cherry.spring.common.validator.MaxLength(16)
	private String newPassword;

	@javax.validation.constraints.NotNull
	@cherry.spring.common.validator.MinLength(8)
	@cherry.spring.common.validator.MaxLength(16)
	private String newPasswordConf;

	/**
	 * フォームの文字列表記。
	 */
	@Override
	public String toString() {
		PasswdForm masked = new PasswdForm();
		masked.setLoginId(loginId);
		masked.setPassword("<MASKED>");
		masked.setNewPassword("<MASKED>");
		masked.setNewPasswordConf("<MASKED>");
		return ToStringBuilder.reflectionToString(masked,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
