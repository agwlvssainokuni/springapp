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

import static cherry.spring.common.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;

import cherry.spring.common.MailId;

public class MailMessageHelperTest {

	private MailMessageHelper helper = getBean(MailMessageHelper.class);

	@Test
	public void testSignupEntry() {
		TestModel model = new TestModel();
		model.setMailAddr("to@test.com");
		model.setSignupUri("http://site.test.com/signup/token");
		MailMessage message = helper.createMailMessage(MailId.SIGNUP_ENTRY,
				"to@test.com", model, Locale.US);
		SimpleMailMessage msg = (SimpleMailMessage) message;
		assertEquals("noreply@test.com", msg.getFrom());
		assertEquals("to@test.com", msg.getTo()[0]);
		assertEquals("cc@test.com", msg.getCc()[0]);
		assertEquals("bcc@test.com", msg.getBcc()[0]);
		assertEquals("Signup entry", msg.getSubject());
		assertEquals("to@test.com, Go to http://site.test.com/signup/token",
				msg.getText());
	}

	@Test
	public void testSignupRegister() {
		TestModel model = new TestModel();
		model.setMailAddr("to@test.com");
		MailMessage message = helper.createMailMessage(MailId.SIGNUP_REGISTER,
				"to@test.com", model, Locale.JAPAN);
		SimpleMailMessage msg = (SimpleMailMessage) message;
		assertEquals("noreply@test.com", msg.getFrom());
		assertEquals("to@test.com", msg.getTo()[0]);
		assertEquals("cc@test.com", msg.getCc()[0]);
		assertEquals("bcc@test.com", msg.getBcc()[0]);
		assertEquals("サインアップ登録", msg.getSubject());
		assertEquals("to@test.com, 受け付けました。", msg.getText());
	}

	public static class TestModel extends MailModel {

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
