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

package cherry.foundation.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;

public class MailSendHandlerImpl implements MailSendHandler {

	private BizDateTime bizDateTime;

	private MessageStore messageStore;

	private JavaMailSender mailSender;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Transactional
	@Override
	public long sendLater(String launcherId, String messageName, String from, List<String> to, List<String> cc,
			List<String> bcc, String subject, String body, LocalDateTime scheduledAt) {
		return messageStore.createMessage(launcherId, messageName, scheduledAt, from, to, cc, bcc, subject, body);
	}

	@Transactional
	@Override
	public long sendNow(String launcherId, String messageName, String from, List<String> to, List<String> cc,
			List<String> bcc, String subject, String body, DataSource... attachment) {
		LocalDateTime now = bizDateTime.now();
		long messageId = messageStore.createMessage(launcherId, messageName, now, from, to, cc, bcc, subject, body);
		SimpleMailMessage msg = messageStore.getMessage(messageId);
		messageStore.finishMessage(messageId);
		send(msg, attachment);
		return messageId;
	}

	@Transactional
	@Override
	public List<Long> listMessage(LocalDateTime dtm) {
		return messageStore.listMessage(dtm);
	}

	@Transactional
	@Override
	public boolean sendMessage(long messageId, DataSource... attachment) {
		SimpleMailMessage msg = messageStore.getMessage(messageId);
		if (msg == null) {
			return false;
		}
		messageStore.finishMessage(messageId);
		send(msg, attachment);
		return true;
	}

	private void send(final SimpleMailMessage msg, final DataSource... attachment) {
		if (attachment.length <= 0) {
			mailSender.send(msg);
		} else {
			mailSender.send(new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
					helper.setTo(msg.getTo());
					helper.setCc(msg.getCc());
					helper.setBcc(msg.getBcc());
					helper.setFrom(msg.getFrom());
					helper.setSubject(msg.getSubject());
					helper.setText(msg.getText());
					for (final DataSource ds : attachment) {
						InputStreamSource in = new InputStreamSource() {
							@Override
							public InputStream getInputStream() throws IOException {
								return ds.getInputStream();
							}
						};
						if (StringUtils.isEmpty(ds.getContentType())) {
							helper.addAttachment(ds.getName(), in);
						} else {
							helper.addAttachment(ds.getName(), in, ds.getContentType());
						}
					}
				}
			});
		}
	}

}
