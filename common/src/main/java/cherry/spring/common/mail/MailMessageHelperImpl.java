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

package cherry.spring.common.mail;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import cherry.spring.common.db.app.dto.MailTemplateAddressDto;
import cherry.spring.common.db.app.dto.MailTemplateDto;
import cherry.spring.common.db.app.mapper.MailTemplateMapper;

@Component
public class MailMessageHelperImpl implements MailMessageHelper,
		InitializingBean {

	@Autowired
	private MailTemplateMapper mailTemplateMapper;

	private VelocityEngine velocityEngine;

	@Override
	public void afterPropertiesSet() {
		velocityEngine = new VelocityEngine();
		velocityEngine.init();
	}

	@Override
	public SimpleMailMessage createMailMessage(IMailId mailId, String to,
			MailModel mailModel, Locale locale) {

		VelocityContext context = new VelocityContext();
		context.put("model", mailModel);

		MailTemplateDto template = mailTemplateMapper.findByName(
				mailId.templateName(), locale.toString());
		if (template == null) {
			template = mailTemplateMapper.findByName(mailId.templateName(),
					Locale.getDefault().toString());
		}

		List<String> cc = new ArrayList<>();
		List<String> bcc = new ArrayList<>();
		for (MailTemplateAddressDto addr : template.getMailAddrList()) {
			if (addr.isCc()) {
				cc.add(addr.getMailAddr());
			}
			if (addr.isBcc()) {
				bcc.add(addr.getMailAddr());
			}
		}

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(template.getSender());
		message.setTo(to);
		message.setCc(cc.toArray(new String[cc.size()]));
		message.setBcc(bcc.toArray(new String[bcc.size()]));
		message.setSubject(evaluate(template.getSubject(), context));
		message.setText(evaluate(template.getBody(), context));

		return message;
	}

	private String evaluate(String template, VelocityContext context) {
		StringWriter writer = new StringWriter();
		if (!velocityEngine.evaluate(context, writer, getClass().getName(),
				template)) {
			throw new IllegalStateException("Failed to evaluate mail template");
		}
		return writer.toString();
	}

}
