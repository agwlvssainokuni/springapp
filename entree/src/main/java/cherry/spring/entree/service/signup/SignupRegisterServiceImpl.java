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
package cherry.spring.entree.service.signup;

import static com.google.common.base.Preconditions.checkState;

import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.MailId;
import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.db.gen.mapper.UserMapper;
import cherry.spring.common.helper.bizdate.BizdateHelper;
import cherry.spring.common.helper.mail.MailMessageHelper;
import cherry.spring.common.helper.mail.MailModel;
import cherry.spring.common.helper.signup.SignupRequestHelper;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;

@Service
public class SignupRegisterServiceImpl implements SignupRegisterService {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SignupRequestHelper signupRequestHelper;

	@Autowired
	private BizdateHelper bizdateHelper;

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

		LocalDateTime now = bizdateHelper.now();

		if (!signupRequestHelper.validateToken(mailAddr, token,
				now.minusSeconds(validInSec))) {
			if (log.isDebugEnabled()) {
				log.debug("Invalid: mailAddr={0}, token={1}, validInSec={2}",
						mailAddr, token, validInSec);
			}
			return false;
		}

		String rawPassword = RandomStringUtils.random(pwdLength, pwdChars);
		String password = passwordEncoder.encode(rawPassword);

		User entity = new User();
		entity.setLoginId(mailAddr);
		entity.setPassword(password);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		int count = userMapper.insertSelective(entity);
		checkState(count == 1, "failed to create user: user={0}, count={1}",
				entity, count);

		if (log.isDebugEnabled()) {
			log.debug("user is created: user={0}", entity);
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