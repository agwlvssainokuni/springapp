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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.spring.common.MailId;
import cherry.spring.common.db.gen.dto.SignupRequest;
import cherry.spring.common.db.gen.mapper.SignupRequestMapper;
import cherry.spring.common.helper.bizdate.BizdateHelper;
import cherry.spring.common.helper.mail.MailMessageHelper;
import cherry.spring.common.helper.mail.MailModel;
import cherry.spring.common.helper.signup.SignupRequestDao;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;
import cherry.spring.entree.app.controller.signup.SignupRegisterController;

@Component
public class SignupEntryServiceImpl implements SignupEntryService {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private SignupRequestMapper signupRequestMapper;

	@Autowired
	private SignupRequestDao signupRequestDao;

	@Autowired
	private BizdateHelper bizdateHelper;

	@Autowired
	private MailMessageHelper mailMessageHelper;

	@Autowired
	private MailSender mailSender;

	@Value("${entree.app.signup.entry.intervalInSec}")
	private Integer intervalInSec;

	@Value("${entree.app.signup.entry.rangeInSec}")
	private Integer rangeInSec;

	@Value("${entree.app.signup.entry.numOfReq}")
	private Integer numOfReq;

	@Transactional
	@Override
	public boolean createSignupRequest(String mailAddr,
			HttpServletRequest request, Locale locale) {

		LocalDateTime now = bizdateHelper.now();

		if (!signupRequestDao.validateMailAddr(mailAddr,
				now.minusSeconds(intervalInSec), now.minusSeconds(rangeInSec),
				numOfReq)) {
			if (log.isDebugEnabled()) {
				log.debug(
						"Invalid: mailAddr={0}, intervalInSec={1}, rangeInSec={2}, numOfReq={3}",
						mailAddr, intervalInSec, rangeInSec, numOfReq);
			}
			return false;
		}

		UUID token = UUID.randomUUID();

		SignupRequest entity = new SignupRequest();
		entity.setMailAddr(mailAddr);
		entity.setToken(token.toString());
		int count = signupRequestMapper.insertSelective(entity);
		assert count == 1;
		if (log.isDebugEnabled()) {
			log.debug(
					"signup_requests is created, id={0}, mailAddr={1}, token={2}",
					entity.getId(), mailAddr, token);
		}

		Model model = new Model();
		model.setMailAddr(mailAddr);
		model.setSignupUri(buildSignupUri(token, request));

		SimpleMailMessage message = mailMessageHelper.createMailMessage(
				MailId.SIGNUP_ENTRY, mailAddr, model, locale);
		mailSender.send(message);

		return true;
	}

	private String buildSignupUri(UUID token, HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(SignupRegisterController.PATH_VAR, token.toString());
		return UriComponentsBuilder.newInstance().scheme(request.getScheme())
				.host(request.getServerName()).port(request.getServerPort())
				.path(request.getContextPath())
				.path(SignupRegisterController.URI_PATH).build()
				.expand(paramMap).toUriString();
	}

	public static class Model extends MailModel {

		private String mailAddr;

		private String signupUri;

		public String getMailAddr() {
			return mailAddr;
		}

		public void setMailAddr(String mailAddr) {
			this.mailAddr = mailAddr;
		}

		public String getSignupUri() {
			return signupUri;
		}

		public void setSignupUri(String signupUri) {
			this.signupUri = signupUri;
		}
	}

}
