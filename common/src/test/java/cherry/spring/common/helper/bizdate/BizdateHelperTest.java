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

package cherry.spring.common.helper.bizdate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.spring.common.db.gen.dto.BizdatetimeMaster;
import cherry.spring.common.db.gen.mapper.BizdatetimeMasterMapper;
import cherry.spring.fwcore.bizdtm.BizDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class BizdateHelperTest {

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private BizdatetimeMasterMapper bizdatetimeMasterMapper;

	@After
	public void after() {
		namedParameterJdbcOperations.update("DELETE FROM bizdatetime_master",
				new HashMap<String, Object>());
	}

	@Test
	public void testTodayWithoutMaster() {
		LocalDate pre = LocalDate.now();
		LocalDate today = bizDateTime.today();
		LocalDate post = LocalDate.now();
		assertThat(today.isBefore(pre), is(false));
		assertThat(today.isAfter(post), is(false));
	}

	@Test
	public void testTodayWithMaster() {

		LocalDate orig = LocalDate.now().plusDays(14);
		BizdatetimeMaster record = new BizdatetimeMaster();
		record.setBizdate(orig);
		bizdatetimeMasterMapper.insertSelective(record);

		assertThat(bizDateTime.today(), is(orig));
	}

	@Test
	public void testNowWithoutMaster() {
		LocalDateTime pre = LocalDateTime.now();
		LocalDateTime now = bizDateTime.now();
		LocalDateTime post = LocalDateTime.now();
		assertThat(now.isBefore(pre), is(false));
		assertThat(now.isAfter(post), is(false));
	}

	@Test
	public void testNowWithMaster() {

		BizdatetimeMaster record = new BizdatetimeMaster();
		record.setOffsetDay(1);
		record.setOffsetHour(2);
		record.setOffsetMinute(3);
		record.setOffsetSecond(4);
		bizdatetimeMasterMapper.insertSelective(record);

		LocalDateTime pre = LocalDateTime.now().plusDays(record.getOffsetDay())
				.plusHours(record.getOffsetHour())
				.plusMinutes(record.getOffsetMinute())
				.plusSeconds(record.getOffsetSecond());
		LocalDateTime now = bizDateTime.now();
		LocalDateTime post = LocalDateTime.now()
				.plusDays(record.getOffsetDay())
				.plusHours(record.getOffsetHour())
				.plusMinutes(record.getOffsetMinute())
				.plusSeconds(record.getOffsetSecond());
		assertThat(now.isBefore(pre), is(false));
		assertThat(now.isAfter(post), is(false));
	}

}
