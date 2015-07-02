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

package cherry.spring.entree.signup;

import static com.google.common.base.Preconditions.checkState;

import java.util.Locale;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.mail.MailData;
import cherry.foundation.mail.MailFacade;
import cherry.foundation.mail.MailModel;
import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;
import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.db.gen.mapper.UserMapper;
import cherry.spring.common.helper.signup.SignupRequestHelper;

@Service
public class SignupRegisterServiceImpl implements SignupRegisterService {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SignupRequestHelper signupRequestHelper;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private MailFacade mailFacade;

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
	public boolean createUser(String mailAddr, String token, String firstName, String lastName, Locale locale) {

		LocalDateTime now = bizDateTime.now();

		if (!signupRequestHelper.validateToken(mailAddr, token, now.minusSeconds(validInSec))) {
			if (log.isDebugEnabled()) {
				log.debug("Invalid: mailAddr={0}, token={1}, validInSec={2}", mailAddr, token, validInSec);
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
		checkState(count == 1, "failed to create user: user=%s, count=%s", entity, count);

		if (log.isDebugEnabled()) {
			log.debug("user is created: user={0}", entity);
		}

		Model model = new Model();
		model.setMailAddr(mailAddr);
		model.setPassword(rawPassword);
		model.setFirstName(firstName);
		model.setLastName(lastName);

		MailData msg = mailFacade.createMailData("SIGNUP_REGISTER", mailAddr, model);
		mailFacade.send("unknown", "SIGNUP_REGISTER", msg.getFromAddr(), msg.getToAddr(), msg.getCcAddr(),
				msg.getBccAddr(), msg.getReplyToAddr(), msg.getSubject(), msg.getBody());

		return true;
	}

	@Setter
	@Getter
	@EqualsAndHashCode
	@ToString
	public static class Model implements MailModel {
		private String mailAddr;
		private String password;
		private String firstName;
		private String lastName;
	}

}
