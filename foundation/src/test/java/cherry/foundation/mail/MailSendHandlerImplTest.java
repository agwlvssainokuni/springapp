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

package cherry.foundation.mail;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import cherry.foundation.bizdtm.BizDateTime;

public class MailSendHandlerImplTest {

	private MailSender mailSender;

	@Test
	public void testSendLater() {
		LocalDateTime now = LocalDateTime.now();
		MailSendHandler handler = create(now);

		long messageId = handler.sendLater("loginId", "messageName",
				"from@addr", asList("to@addr"), asList("cc@addr"),
				asList("bcc@addr"), "subject", "body", now);
		assertEquals(1L, messageId);

		List<Long> list = handler.listMessage(now);
		assertEquals(1, list.size());
		assertEquals(1L, list.get(0).longValue());

		ArgumentCaptor<SimpleMailMessage> message = ArgumentCaptor
				.forClass(SimpleMailMessage.class);
		doNothing().when(mailSender).send(message.capture());
		boolean first = handler.sendMessage(1L);

		assertTrue(first);
		assertEquals("from@addr", message.getValue().getFrom());
		assertEquals(1, message.getValue().getTo().length);
		assertEquals("to@addr", message.getValue().getTo()[0]);
		assertEquals(1, message.getValue().getCc().length);
		assertEquals("cc@addr", message.getValue().getCc()[0]);
		assertEquals(1, message.getValue().getBcc().length);
		assertEquals("bcc@addr", message.getValue().getBcc()[0]);
		assertEquals("subject", message.getValue().getSubject());
		assertEquals("body", message.getValue().getText());

		boolean second = handler.sendMessage(1L);
		assertFalse(second);
	}

	@Test
	public void testSendNow() {
		LocalDateTime now = LocalDateTime.now();
		MailSendHandler handler = create(now);

		ArgumentCaptor<SimpleMailMessage> message = ArgumentCaptor
				.forClass(SimpleMailMessage.class);
		doNothing().when(mailSender).send(message.capture());

		long messageId = handler.sendNow("loginId", "messageName", "from@addr",
				asList("to@addr"), asList("cc@addr"), asList("bcc@addr"),
				"subject", "body");
		assertEquals(1L, messageId);

		assertEquals("from@addr", message.getValue().getFrom());
		assertEquals(1, message.getValue().getTo().length);
		assertEquals("to@addr", message.getValue().getTo()[0]);
		assertEquals(1, message.getValue().getCc().length);
		assertEquals("cc@addr", message.getValue().getCc()[0]);
		assertEquals(1, message.getValue().getBcc().length);
		assertEquals("bcc@addr", message.getValue().getBcc()[0]);
		assertEquals("subject", message.getValue().getSubject());
		assertEquals("body", message.getValue().getText());

		boolean result = handler.sendMessage(1L);
		assertFalse(result);
	}

	private MailSendHandler create(LocalDateTime now) {
		BizDateTime bizDateTime = mock(BizDateTime.class);
		when(bizDateTime.now()).thenReturn(now);

		MessageStore messageStore = new MessageStoreImpl();

		mailSender = mock(MailSender.class);

		MailSendHandlerImpl impl = new MailSendHandlerImpl();
		impl.setBizDateTime(bizDateTime);
		impl.setMessageStore(messageStore);
		impl.setMailSender(mailSender);
		return impl;
	}

	public static class MessageStoreImpl implements MessageStore {

		private long messageId = 1L;

		private Map<Long, SimpleMailMessage> messageMap = new LinkedHashMap<>();

		@Override
		public long createMessage(String launcherId, String messageName,
				LocalDateTime scheduledAt, String from, List<String> to,
				List<String> cc, List<String> bcc, String subject, String body) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo(to.toArray(new String[to.size()]));
			message.setCc(cc.toArray(new String[cc.size()]));
			message.setBcc(bcc.toArray(new String[bcc.size()]));
			message.setSubject(subject);
			message.setText(body);
			messageMap.put(messageId, message);
			return messageId++;
		}

		@Override
		public List<Long> listMessage(LocalDateTime dtm) {
			return new ArrayList<Long>(messageMap.keySet());
		}

		@Override
		public SimpleMailMessage getMessage(long messageId) {
			return messageMap.get(messageId);
		}

		@Override
		public void finishMessage(long messageId) {
			messageMap.remove(messageId);
		}
	}

}