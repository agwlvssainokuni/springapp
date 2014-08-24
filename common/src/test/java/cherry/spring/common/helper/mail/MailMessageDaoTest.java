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

package cherry.spring.common.helper.mail;

import static cherry.spring.common.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import cherry.spring.common.db.gen.dto.MailTemplateAddress;
import cherry.spring.common.db.gen.dto.MailTemplateText;
import cherry.spring.common.db.gen.dto.MailTemplate;
import cherry.spring.common.db.gen.mapper.MailTemplateAddressMapper;
import cherry.spring.common.db.gen.mapper.MailTemplateTextMapper;
import cherry.spring.common.db.gen.mapper.MailTemplateMapper;

public class MailMessageDaoTest {

	@Test
	public void testFindByName00() {

		MailMessageDao mapper = getBean(MailMessageDao.class);
		try {
			mapper.findTemplate("test2", Locale.JAPAN);
		} catch (EmptyResultDataAccessException ex) {
			// NOTHING
		} catch (Exception ex) {
			fail("Unexpected exception must not be thrown");
		}
	}

	@Test
	public void testFindByName10() {

		MailMessageDao mapper = getBean(MailMessageDao.class);
		MailTemplateDto template = mapper.findTemplate("test", Locale.JAPAN);
		List<MailTemplateAddressDto> addrList = mapper.findAddresses("test");

		assertEquals("test", template.getName());
		assertEquals("ja_JP", template.getLocale());
		assertEquals("メール件名", template.getSubject());
		assertEquals("メール本文", template.getBody());

		assertEquals(2, addrList.size());
		assertEquals("cc@test.com", addrList.get(0).getMailAddr());
		assertEquals("CC", addrList.get(0).getRcptType());
		assertEquals("bcc@test.com", addrList.get(1).getMailAddr());
		assertEquals("BCC", addrList.get(1).getRcptType());
	}

	@Test
	public void testFindByName11() {

		MailMessageDao mapper = getBean(MailMessageDao.class);
		MailTemplateDto template = mapper.findTemplate("test", Locale.US);
		List<MailTemplateAddressDto> addrList = mapper.findAddresses("test");

		assertEquals("test", template.getName());
		assertEquals("en_US", template.getLocale());
		assertEquals("Mail Subject", template.getSubject());
		assertEquals("Mail Body", template.getBody());

		assertEquals(2, addrList.size());
		assertEquals("cc@test.com", addrList.get(0).getMailAddr());
		assertEquals("CC", addrList.get(0).getRcptType());
		assertEquals("bcc@test.com", addrList.get(1).getMailAddr());
		assertEquals("BCC", addrList.get(1).getRcptType());
	}

	@BeforeClass
	public static void setup() {

		MailTemplateMapper templateMapper = getBean(MailTemplateMapper.class);
		MailTemplateAddressMapper addressMapper = getBean(MailTemplateAddressMapper.class);
		MailTemplateTextMapper textMapper = getBean(MailTemplateTextMapper.class);

		LocalDateTime date = LocalDateTime.now();

		MailTemplate template = new MailTemplate();
		template.setName("test");
		template.setSender("sender@test.com");
		template.setCreatedAt(date);
		template.setUpdatedAt(date);
		template.setLockVersion(1);
		template.setDeletedFlg(0);
		templateMapper.insert(template);

		MailTemplateAddress addressCc = new MailTemplateAddress();
		addressCc.setMailTemplateId(3);
		addressCc.setRcptType("CC");
		addressCc.setMailAddr("cc@test.com");
		addressCc.setCreatedAt(date);
		addressCc.setUpdatedAt(date);
		addressCc.setLockVersion(1);
		addressCc.setDeletedFlg(0);
		addressMapper.insert(addressCc);

		MailTemplateAddress addressBcc = new MailTemplateAddress();
		addressBcc.setMailTemplateId(3);
		addressBcc.setRcptType("BCC");
		addressBcc.setMailAddr("bcc@test.com");
		addressBcc.setCreatedAt(date);
		addressBcc.setUpdatedAt(date);
		addressBcc.setLockVersion(1);
		addressBcc.setDeletedFlg(0);
		addressMapper.insert(addressBcc);

		MailTemplateText textJa = new MailTemplateText();
		textJa.setMailTemplateId(3);
		textJa.setLocale("ja_JP");
		textJa.setSubject("メール件名");
		textJa.setBody("メール本文");
		textJa.setCreatedAt(date);
		textJa.setUpdatedAt(date);
		textJa.setLockVersion(1);
		textJa.setDeletedFlg(0);
		textMapper.insert(textJa);

		MailTemplateText textEn = new MailTemplateText();
		textEn.setMailTemplateId(3);
		textEn.setLocale("en_US");
		textEn.setSubject("Mail Subject");
		textEn.setBody("Mail Body");
		textEn.setCreatedAt(date);
		textEn.setUpdatedAt(date);
		textEn.setLockVersion(1);
		textEn.setDeletedFlg(0);
		textMapper.insert(textEn);
	}

}
