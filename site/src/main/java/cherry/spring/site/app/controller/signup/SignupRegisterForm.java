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

package cherry.spring.site.app.controller.signup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import cherry.spring.site.app.controller.BaseForm;

public class SignupRegisterForm extends BaseForm {

	/** シリアルバージョン。 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "signupRegisterForm";
	public static final String PREFIX = NAME + ".";

	public static final String EMAIL = "email";

	public static final String FIRST_NAME = "firstName";
	public static final int FIRST_NAME_MIN_LENGTH = 1;
	public static final int FIRST_NAME_MAX_LENGTH = 32;

	public static final String LAST_NAME = "lastName";
	public static final int LAST_NAME_MIN_LENGTH = 1;
	public static final int LAST_NAME_MAX_LENGTH = 32;

	public static final String AGREE = "agree";

	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Size(min = FIRST_NAME_MIN_LENGTH, max = FIRST_NAME_MAX_LENGTH)
	private String firstName;

	@NotNull
	@Size(min = LAST_NAME_MIN_LENGTH, max = LAST_NAME_MAX_LENGTH)
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
