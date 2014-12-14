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

import java.util.Locale;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.mail.MailFacade;
import cherry.foundation.mail.MailModel;
import cherry.foundation.mail.Message;
import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;
import cherry.spring.common.helper.signup.SignupRequestHelper;

@Service
public class SignupEntryServiceImpl implements SignupEntryService {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private SignupRequestHelper signupRequestHelper;

	@Autowired
	private BizDateTime bizdateHelper;

	@Autowired
	private MailFacade mailFacade;

	@Value("${entree.app.signup.entry.intervalInSec}")
	private Integer intervalInSec;

	@Value("${entree.app.signup.entry.rangeInSec}")
	private Integer rangeInSec;

	@Value("${entree.app.signup.entry.numOfReq}")
	private Integer numOfReq;

	@Transactional
	@Override
	public boolean createSignupRequest(String mailAddr, Locale locale,
			UriComponentsSource source) {

		LocalDateTime now = bizdateHelper.now();

		if (!signupRequestHelper.validateMailAddr(mailAddr,
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
		LocalDateTime appliedAt = bizdateHelper.now();

		Integer id = signupRequestHelper.createSignupRequest(mailAddr,
				token.toString(), appliedAt);
		if (id == null) {
			if (log.isDebugEnabled()) {
				log.debug(
						"Failed to create: mailAddr={0}, token={1}, appliedAt={2}",
						mailAddr, token.toString(), appliedAt);
			}
			return false;
		}

		if (log.isDebugEnabled()) {
			log.debug(
					"signup_requests is created, id={0}, mailAddr={1}, token={2}",
					id, mailAddr, token);
		}

		Model model = new Model();
		model.setMailAddr(mailAddr);
		model.setSignupUri(source.buildUriComponents(token).toUriString());

		Message msg = mailFacade.createMessage("SIGNUP_ENTRY", mailAddr, model);
		mailFacade.send("unknown", "SIGNUP_ENTRY", msg.getFromAddr(),
				msg.getToAddr(), msg.getCcAddr(), msg.getBccAddr(),
				msg.getSubject(), msg.getBody());

		return true;
	}

	@Setter
	@Getter
	public static class Model extends MailModel {
		private String mailAddr;
		private String signupUri;
	}

}
