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

package cherry.example.common.foundation.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.example.db.gen.query.QNumberingMaster;
import cherry.foundation.numbering.NumberingManager;
import cherry.foundation.type.Code;

import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLInsertClause;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-common-test.xml")
@Transactional
public class NumberingStoreImplTest {

	@Autowired
	private NumberingManager numberingManager;

	@Autowired
	private SQLQueryFactory queryFactory;

	private QNumberingMaster nm = new QNumberingMaster("nm");

	enum NumberType implements Code<String> {
		NUMA, NUMB;
		@Override
		public String code() {
			return name();
		}
	}

	@Before
	public void before() {
		SQLInsertClause clause = queryFactory.insert(nm);
		clause.columns(nm.name, nm.template, nm.minValue, nm.maxValue, nm.currentValue)
				.values("NUMA", "AA{0,number,00000000}", 1L, 99999999L, 0L).addBatch();
		clause.columns(nm.name, nm.template, nm.minValue, nm.maxValue, nm.currentValue)
				.values("NUMB", "BB{0,number,00000000}", 1L, 99999999L, 0L).addBatch();
		assertEquals(2L, clause.execute());
	}

	@Test
	public void testInitialize() {
		assertNotNull(numberingManager);
	}

	@Test
	public void testIssueAsString() {
		NumberFormat fmt = new DecimalFormat("AA00000000");
		for (int i = 0; i < 100; i++) {
			assertEquals(fmt.format(i + 1), numberingManager.issueAsString(NumberType.NUMA));
		}
		for (int i = 0; i < 100; i++) {
			assertEquals(fmt.format(100 + i + 1), numberingManager.issueAsString("NUMA"));
		}
		String[] n200 = numberingManager.issueAsString(NumberType.NUMA, 100);
		for (int i = 0; i < 100; i++) {
			assertEquals(fmt.format(200 + i + 1), n200[i]);
		}
		String[] n300 = numberingManager.issueAsString("NUMA", 100);
		for (int i = 0; i < 100; i++) {
			assertEquals(fmt.format(300 + i + 1), n300[i]);
		}
	}

	@Test
	public void testIssueAsLong() {
		for (int i = 0; i < 100; i++) {
			assertEquals(i + 1, numberingManager.issueAsLong(NumberType.NUMB));
		}
		for (int i = 0; i < 100; i++) {
			assertEquals(100 + i + 1, numberingManager.issueAsLong("NUMB"));
		}
		long[] n200 = numberingManager.issueAsLong(NumberType.NUMB, 100);
		for (int i = 0; i < 100; i++) {
			assertEquals(200 + i + 1, n200[i]);
		}
		long[] n300 = numberingManager.issueAsLong("NUMB", 100);
		for (int i = 0; i < 100; i++) {
			assertEquals(300 + i + 1, n300[i]);
		}
	}

}
