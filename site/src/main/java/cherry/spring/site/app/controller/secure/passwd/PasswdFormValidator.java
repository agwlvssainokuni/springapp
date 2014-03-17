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

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("passwdFormValidator")
public class PasswdFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return PasswdForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswdForm form = (PasswdForm) target;
		checkNewPasswordConfirmation(form, errors);
	}

	void checkNewPasswordConfirmation(PasswdForm form, Errors errors) {
		if (StringUtils.isEmpty(form.getNewPassword())) {
			return;
		}
		if (form.getNewPassword().equals(form.getNewPasswordConf())) {
			return;
		}
		errors.rejectValue(PasswdForm.NEW_PASSWORD_CONF,
				"PasswdFormValidator.confirm", "password confirmation failed.");
	}

}
