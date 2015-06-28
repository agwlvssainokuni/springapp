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

package cherry.spring.common.foundation.impl;

import static org.joda.time.LocalDate.now;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizcal.WorkdayStore;
import cherry.spring.common.db.gen.dto.DayoffMaster;
import cherry.spring.common.db.gen.mapper.DayoffMasterMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class WorkdayStoreImplTest {

	@Autowired
	private WorkdayStore workdayStore;

	@Autowired
	private DayoffMasterMapper mapper;

	@Before
	public void before() {
		createDayoffMaster("TEST", 3);
		createDayoffMaster("TEST", 4);
		createDayoffMaster("TEST", 8);
		createDayoffMaster("TEST", 9);
	}

	@Test
	public void testGetNumberOfWorkday() {
		// 基本形
		assertEquals(1, workdayStore.getNumberOfWorkday("TEST", now(), now()));
		assertEquals(2, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(1)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(2)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(3)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(4)));
		assertEquals(4, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(5)));
		assertEquals(5, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(6)));
		assertEquals(6, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(7)));
		assertEquals(6, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(8)));
		assertEquals(6, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(9)));
		assertEquals(7, workdayStore.getNumberOfWorkday("TEST", now(), now().plusDays(10)));
		// 起点日が非営業日(1日目)
		assertEquals(0, workdayStore.getNumberOfWorkday("TEST", now().plusDays(3), now().plusDays(3)));
		assertEquals(0, workdayStore.getNumberOfWorkday("TEST", now().plusDays(3), now().plusDays(4)));
		assertEquals(1, workdayStore.getNumberOfWorkday("TEST", now().plusDays(3), now().plusDays(5)));
		assertEquals(2, workdayStore.getNumberOfWorkday("TEST", now().plusDays(3), now().plusDays(6)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now().plusDays(3), now().plusDays(7)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now().plusDays(3), now().plusDays(8)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now().plusDays(3), now().plusDays(9)));
		assertEquals(4, workdayStore.getNumberOfWorkday("TEST", now().plusDays(3), now().plusDays(10)));
		// 起点日が非営業日(2日目)
		assertEquals(0, workdayStore.getNumberOfWorkday("TEST", now().plusDays(4), now().plusDays(4)));
		assertEquals(1, workdayStore.getNumberOfWorkday("TEST", now().plusDays(4), now().plusDays(5)));
		assertEquals(2, workdayStore.getNumberOfWorkday("TEST", now().plusDays(4), now().plusDays(6)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now().plusDays(4), now().plusDays(7)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now().plusDays(4), now().plusDays(8)));
		assertEquals(3, workdayStore.getNumberOfWorkday("TEST", now().plusDays(4), now().plusDays(9)));
		assertEquals(4, workdayStore.getNumberOfWorkday("TEST", now().plusDays(4), now().plusDays(10)));
	}

	@Test
	public void testGetNextWorkday() {
		// 基本形
		assertEquals(now(), workdayStore.getNextWorkday("TEST", now(), 1));
		assertEquals(now().plusDays(1), workdayStore.getNextWorkday("TEST", now(), 2));
		assertEquals(now().plusDays(2), workdayStore.getNextWorkday("TEST", now(), 3));
		assertEquals(now().plusDays(5), workdayStore.getNextWorkday("TEST", now(), 4));
		assertEquals(now().plusDays(6), workdayStore.getNextWorkday("TEST", now(), 5));
		assertEquals(now().plusDays(7), workdayStore.getNextWorkday("TEST", now(), 6));
		assertEquals(now().plusDays(10), workdayStore.getNextWorkday("TEST", now(), 7));
		// 起点日が非営業日(1日目)
		assertEquals(now().plusDays(5), workdayStore.getNextWorkday("TEST", now().plusDays(3), 1));
		assertEquals(now().plusDays(6), workdayStore.getNextWorkday("TEST", now().plusDays(3), 2));
		assertEquals(now().plusDays(7), workdayStore.getNextWorkday("TEST", now().plusDays(3), 3));
		assertEquals(now().plusDays(10), workdayStore.getNextWorkday("TEST", now().plusDays(3), 4));
		// 起点日が非営業日(1日目)
		assertEquals(now().plusDays(5), workdayStore.getNextWorkday("TEST", now().plusDays(4), 1));
		assertEquals(now().plusDays(6), workdayStore.getNextWorkday("TEST", now().plusDays(4), 2));
		assertEquals(now().plusDays(7), workdayStore.getNextWorkday("TEST", now().plusDays(4), 3));
		assertEquals(now().plusDays(10), workdayStore.getNextWorkday("TEST", now().plusDays(4), 4));
	}

	private void createDayoffMaster(String name, int n) {
		DayoffMaster record;
		record = new DayoffMaster();
		record.setName(name);
		record.setDt(now().plusDays(n));
		mapper.insertSelective(record);
	}

}
