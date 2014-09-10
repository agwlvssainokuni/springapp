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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.spring.common.db.gen.dto.MailTemplate;
import cherry.spring.common.db.gen.dto.MailTemplateAddress;
import cherry.spring.common.db.gen.dto.MailTemplateText;
import cherry.spring.common.db.gen.mapper.MailTemplateAddressMapper;
import cherry.spring.common.db.gen.mapper.MailTemplateMapper;
import cherry.spring.common.db.gen.mapper.MailTemplateTextMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class MailMessageDaoTest {

	@Autowired
	private MailMessageDao mailMessageDao;

	@Autowired
	private MailTemplateMapper templateMapper;

	@Autowired
	private MailTemplateAddressMapper addressMapper;

	@Autowired
	private MailTemplateTextMapper textMapper;

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Test
	public void testFindByName00() {
		try {
			mailMessageDao.findTemplate("test2", Locale.JAPAN);
		} catch (EmptyResultDataAccessException ex) {
			// NOTHING
		} catch (Exception ex) {
			fail("Unexpected exception must not be thrown");
		}
	}

	@Test
	public void testFindByName10() {

		MailTemplateDto template = mailMessageDao.findTemplate("test",
				Locale.JAPAN);
		List<MailTemplateAddressDto> addrList = mailMessageDao
				.findAddresses("test");

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

		MailTemplateDto template = mailMessageDao.findTemplate("test",
				Locale.US);
		List<MailTemplateAddressDto> addrList = mailMessageDao
				.findAddresses("test");

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

	@Before
	public void setup() {

		LocalDateTime date = LocalDateTime.now();

		MailTemplate template = new MailTemplate();
		template.setName("test");
		template.setSender("sender@test.com");
		template.setCreatedAt(date);
		template.setUpdatedAt(date);
		template.setLockVersion(1);
		template.setDeletedFlg(0);
		templateMapper.insert(template);

		Integer id = namedParameterJdbcOperations.queryForObject(
				"SELECT id FROM mail_template WHERE name='test'",
				new HashMap<String, Object>(), Integer.class);

		MailTemplateAddress addressCc = new MailTemplateAddress();
		addressCc.setMailTemplateId(id);
		addressCc.setRcptType("CC");
		addressCc.setMailAddr("cc@test.com");
		addressCc.setCreatedAt(date);
		addressCc.setUpdatedAt(date);
		addressCc.setLockVersion(1);
		addressCc.setDeletedFlg(0);
		addressMapper.insert(addressCc);

		MailTemplateAddress addressBcc = new MailTemplateAddress();
		addressBcc.setMailTemplateId(id);
		addressBcc.setRcptType("BCC");
		addressBcc.setMailAddr("bcc@test.com");
		addressBcc.setCreatedAt(date);
		addressBcc.setUpdatedAt(date);
		addressBcc.setLockVersion(1);
		addressBcc.setDeletedFlg(0);
		addressMapper.insert(addressBcc);

		MailTemplateText textJa = new MailTemplateText();
		textJa.setMailTemplateId(id);
		textJa.setLocale("ja_JP");
		textJa.setSubject("メール件名");
		textJa.setBody("メール本文");
		textJa.setCreatedAt(date);
		textJa.setUpdatedAt(date);
		textJa.setLockVersion(1);
		textJa.setDeletedFlg(0);
		textMapper.insert(textJa);

		MailTemplateText textEn = new MailTemplateText();
		textEn.setMailTemplateId(id);
		textEn.setLocale("en_US");
		textEn.setSubject("Mail Subject");
		textEn.setBody("Mail Body");
		textEn.setCreatedAt(date);
		textEn.setUpdatedAt(date);
		textEn.setLockVersion(1);
		textEn.setDeletedFlg(0);
		textMapper.insert(textEn);
	}

	@After
	public void tearDown() {
		MailTemplateDto dto = mailMessageDao.findTemplate("test", Locale.JAPAN);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", dto.getId());
		namedParameterJdbcOperations.update(
				"DELETE FROM mail_template_address WHERE mail_template_id=:id",
				paramMap);
		namedParameterJdbcOperations.update(
				"DELETE FROM mail_template_text WHERE mail_template_id=:id",
				paramMap);
		namedParameterJdbcOperations.update(
				"DELETE FROM mail_template WHERE id=:id", paramMap);
	}
}
