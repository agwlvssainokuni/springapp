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

package cherry.spring.entree.app.controller.secure.passwd;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import cherry.spring.common.validator.MailAddrSize;
import cherry.spring.common.validator.PasswordSize;
import cherry.spring.entree.app.controller.BaseForm;

public class PasswdForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@MailAddrSize
	@Email
	private String loginId;

	@NotNull
	@PasswordSize
	private String password;

	@NotNull
	@PasswordSize
	private String newPassword;

	@NotNull
	@PasswordSize
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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConf() {
		return newPasswordConf;
	}

	public void setNewPasswordConf(String newPasswordConf) {
		this.newPasswordConf = newPasswordConf;
	}

}
