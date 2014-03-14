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

package cherry.spring.site.app.controller.secure.passwd;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cherry.spring.site.app.controller.BaseForm;

public class PasswdForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "passwdForm";
	public static final String PREFIX = NAME + ".";

	public static final String PASSWORD = "password";
	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int PASSWORD_MAX_LENGTH = 16;

	public static final String NEW_PASSWORD = "newPassword";
	public static final int NEW_PASSWORD_MIN_LENGTH = 8;
	public static final int NEW_PASSWORD_MAX_LENGTH = 16;

	public static final String NEW_PASSWORD_CONF = "newPasswordConf";
	public static final int NEW_PASSWORD_CONF_MIN_LENGTH = 8;
	public static final int NEW_PASSWORD_CONF_MAX_LENGTH = 16;

	@NotNull
	@Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
	private String password;

	@NotNull
	@Size(min = NEW_PASSWORD_MIN_LENGTH, max = NEW_PASSWORD_MAX_LENGTH)
	private String newPassword;

	@NotNull
	@Size(min = NEW_PASSWORD_CONF_MIN_LENGTH, max = NEW_PASSWORD_CONF_MAX_LENGTH)
	private String newPasswordConf;

	/**
	 * フォームの文字列表記。
	 */
	@Override
	public String toString() {
		PasswdForm masked = new PasswdForm();
		masked.setPassword("<MASKED>");
		masked.setNewPassword("<MASKED>");
		masked.setNewPasswordConf("<MASKED>");
		return ToStringBuilder.reflectionToString(masked,
				ToStringStyle.SHORT_PREFIX_STYLE);
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
