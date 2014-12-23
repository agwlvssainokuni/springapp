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

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

public class MailDataHandlerImplTest {

	@Test
	public void testFullAddress() {
		MailDataHandler handler = create("name", "from@addr", "other@addr",
				"cc@addr", "bcc@addr", "subject", "body");
		Model model = new Model();
		model.setParam("PARAM");
		MailData mailData = handler.createMailData("name", "to@addr", model);
		assertNotNull(mailData);
		assertEquals("from@addr", mailData.getFromAddr());
		assertEquals(2, mailData.getToAddr().size());
		assertEquals("to@addr", mailData.getToAddr().get(0));
		assertEquals("other@addr", mailData.getToAddr().get(1));
		assertEquals(1, mailData.getCcAddr().size());
		assertEquals("cc@addr", mailData.getCcAddr().get(0));
		assertEquals(1, mailData.getBccAddr().size());
		assertEquals("bcc@addr", mailData.getBccAddr().get(0));
		assertEquals("subject", mailData.getSubject());
		assertEquals("body", mailData.getBody());
	}

	@Test
	public void testEmptyTemplate() {
		MailDataHandler handler = create("name", "from@addr", null, null, null,
				"", "");
		Model model = new Model();
		model.setParam("PARAM");
		MailData mailData = handler.createMailData("name", "to@addr", model);
		assertNotNull(mailData);
		assertEquals("from@addr", mailData.getFromAddr());
		assertEquals(1, mailData.getToAddr().size());
		assertEquals("to@addr", mailData.getToAddr().get(0));
		assertNull(mailData.getCcAddr());
		assertNull(mailData.getBccAddr());
		assertEquals("", mailData.getSubject());
		assertEquals("", mailData.getBody());
	}

	@Test
	public void testTemplateEvaluation() {
		MailDataHandler handler = create("name", "from@addr", null, null, null,
				"param=${model.param}", "param is ${model.param}");
		Model model = new Model();
		model.setParam("PARAM");
		MailData mailData = handler.createMailData("name", "to@addr", model);
		assertNotNull(mailData);
		assertEquals("from@addr", mailData.getFromAddr());
		assertEquals(1, mailData.getToAddr().size());
		assertEquals("to@addr", mailData.getToAddr().get(0));
		assertNull(mailData.getCcAddr());
		assertNull(mailData.getBccAddr());
		assertEquals("param=PARAM", mailData.getSubject());
		assertEquals("param is PARAM", mailData.getBody());
	}

	private MailDataHandler create(String name, String fromAddr, String toAddr,
			String ccAddr, String bccAddr, String subject, String body) {

		MailData mailData = new MailData();
		mailData.setFromAddr(fromAddr);
		if (isNotEmpty(toAddr)) {
			mailData.setToAddr(Arrays.asList(toAddr));
		}
		if (isNotEmpty(ccAddr)) {
			mailData.setCcAddr(Arrays.asList(ccAddr));
		}
		if (isNotEmpty(bccAddr)) {
			mailData.setBccAddr(Arrays.asList(bccAddr));
		}
		mailData.setSubject(subject);
		mailData.setBody(body);

		TemplateStore templateStore = mock(TemplateStore.class);
		when(templateStore.getTemplate(eq(name))).thenReturn(mailData);

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();

		MailDataHandlerImpl impl = new MailDataHandlerImpl();
		impl.setTemplateStore(templateStore);
		impl.setVelocityEngine(velocityEngine);
		return impl;
	}

	@Setter
	@Getter
	public static class Model implements MailModel {
		private String param;
	}

}
