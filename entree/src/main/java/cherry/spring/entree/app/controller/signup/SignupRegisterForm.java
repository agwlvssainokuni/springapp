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

package cherry.spring.entree.app.controller.signup;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import cherry.spring.common.validator.DisplayNameSize;
import cherry.spring.common.validator.MailAddrSize;
import cherry.spring.entree.app.controller.BaseForm;

public class SignupRegisterForm extends BaseForm {

	/** シリアルバージョン。 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "signupRegisterForm";
	public static final String PREFIX = NAME + ".";

	public static final String EMAIL = "email";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String AGREE = "agree";

	@NotEmpty
	@MailAddrSize
	@Email
	private String email;

	@NotEmpty
	@DisplayNameSize
	private String firstName;

	@NotEmpty
	@DisplayNameSize
	private String lastName;

	private boolean agree;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

}
