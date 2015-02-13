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

package cherry.spring.common.foundation.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.code.CodeEntry;
import cherry.foundation.code.CodeStore;
import cherry.spring.common.db.gen.dto.CodeMaster;
import cherry.spring.common.db.gen.dto.CodeMasterCriteria;
import cherry.spring.common.db.gen.mapper.CodeMasterMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class CodeStoreImplTest {

	@Autowired
	private CodeStore codeStore;

	@Autowired
	private CodeMasterMapper mapper;

	@Before
	public void before() {

		CodeMaster entity;
		entity = new CodeMaster();
		entity.setName("CODE0");
		entity.setValue("01");
		entity.setLabel("LABEL01");
		entity.setSortOrder(1);
		mapper.insertSelective(entity);

		entity = new CodeMaster();
		entity.setName("CODE0");
		entity.setValue("02");
		entity.setLabel("LABEL02");
		entity.setSortOrder(2);
		mapper.insertSelective(entity);

		entity = new CodeMaster();
		entity.setName("CODE0");
		entity.setValue("03");
		entity.setLabel("LABEL03");
		entity.setSortOrder(3);
		mapper.insertSelective(entity);
	}

	@After
	public void after() {
		CodeMasterCriteria c = new CodeMasterCriteria();
		c.createCriteria().andNameEqualTo("CODE0");
		mapper.deleteByExample(c);
	}

	@Test
	public void testInitialization() {
		assertNotNull(codeStore);
	}

	@Test
	public void testFindByValue() {
		assertNull(codeStore.findByValue("CODE0", "00"));

		CodeEntry entry;
		entry = codeStore.findByValue("CODE0", "01");
		assertEquals("01", entry.getValue());
		assertEquals("LABEL01", entry.getLabel());
		assertEquals(1, entry.getSortOrder());

		entry = codeStore.findByValue("CODE0", "02");
		assertEquals("02", entry.getValue());
		assertEquals("LABEL02", entry.getLabel());
		assertEquals(2, entry.getSortOrder());

		entry = codeStore.findByValue("CODE0", "03");
		assertEquals("03", entry.getValue());
		assertEquals("LABEL03", entry.getLabel());
		assertEquals(3, entry.getSortOrder());

		assertNull(codeStore.findByValue("CODE0", "04"));
		assertNull(codeStore.findByValue("CODE1", "01"));
	}

	@Test
	public void testGetCodeList() {

		List<CodeEntry> list = codeStore.getCodeList("CODE0");
		assertEquals(3, list.size());
		for (int i = 1; i <= list.size(); i++) {
			CodeEntry entry = list.get(i - 1);
			assertEquals("0" + i, entry.getValue());
			assertEquals("LABEL0" + i, entry.getLabel());
			assertEquals(i, entry.getSortOrder());
		}

		assertTrue(codeStore.getCodeList("CODE1").isEmpty());
	}

}
