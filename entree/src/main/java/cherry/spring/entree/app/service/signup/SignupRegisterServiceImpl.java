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
package cherry.spring.entree.app.service.signup;

import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.MailId;
import cherry.spring.common.db.app.mapper.SignupRequestMapper;
import cherry.spring.common.db.app.mapper.UserMapper;
import cherry.spring.common.db.gen.dto.Users;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;
import cherry.spring.common.mail.MailMessageHelper;
import cherry.spring.common.mail.MailModel;

@Component
public class SignupRegisterServiceImpl implements SignupRegisterService {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private SignupRequestMapper signupRequestMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MailMessageHelper mailMessageHelper;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${entree.app.signup.register.validInSec}")
	private Integer validInSec;

	@Value("${entree.app.signup.register.pwdLength}")
	private Integer pwdLength;

	@Value("${entree.app.signup.register.pwdChars}")
	private String pwdChars;

	@Transactional
	@Override
	public boolean createUser(String mailAddr, String token, String firstName,
			String lastName, Locale locale) {

		if (!signupRequestMapper.validateToken(mailAddr, token, validInSec)) {
			if (log.isDebugEnabled()) {
				log.debug("Invalid: mailAddr={0}, token={1}, validInSec={2}",
						mailAddr, token, validInSec);
			}
			return false;
		}

		String rawPassword = RandomStringUtils.random(pwdLength, pwdChars);
		String password = passwordEncoder.encode(rawPassword);

		Users entity = new Users();
		entity.setMailAddr(mailAddr);
		entity.setPassword(password);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		int count = userMapper.createUser(entity);
		assert count == 1;
		if (log.isDebugEnabled()) {
			log.debug(
					"users is created: id={0}, mailAddr={1}, password={2}, firstName={3}, lastName={4}",
					entity.getId(), mailAddr, password, firstName, lastName);
		}

		Model model = new Model();
		model.setMailAddr(mailAddr);
		model.setPassword(rawPassword);
		model.setFirstName(firstName);
		model.setLastName(lastName);

		SimpleMailMessage message = mailMessageHelper.createMailMessage(
				MailId.SIGNUP_REGISTER, mailAddr, model, locale);
		mailSender.send(message);

		return true;
	}

	public static class Model extends MailModel {

		private String mailAddr;

		private String password;

		private String firstName;

		private String lastName;

		public String getMailAddr() {
			return mailAddr;
		}

		public void setMailAddr(String mailAddr) {
			this.mailAddr = mailAddr;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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
	}

}
