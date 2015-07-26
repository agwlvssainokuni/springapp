/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.sqlman.admin.mailmgmt;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.mail.MailSendHandler;

@Controller
public class MailmgmtControllerImpl implements MailmgmtController {

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private MailSendHandler mailSendHandler;

	@Override
	public ModelAndView start(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		return new ModelAndView();
	}

	@Override
	public SendResult execute(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {

		long totalCount = 0L;
		long okCount = 0L;
		long ngCount = 0L;
		List<Long> okId = new ArrayList<>();
		List<Long> ngId = new ArrayList<>();
		LocalDateTime now = bizDateTime.now();
		for (Long messageId : mailSendHandler.listMessage(now)) {
			try {
				mailSendHandler.sendMessage(messageId);
				okCount += 1;
				okId.add(messageId);
			} catch (MailException ex) {
				ngCount += 1;
				ngId.add(messageId);
			}
			totalCount += 1;
		}

		SendResult result = new SendResult();
		result.setTotalCount(totalCount);
		result.setOkCount(okCount);
		result.setNgCount(ngCount);
		result.setOkId(okId);
		result.setNgId(ngId);
		return result;
	}

}
