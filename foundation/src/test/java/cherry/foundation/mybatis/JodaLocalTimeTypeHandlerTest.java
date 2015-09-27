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

package cherry.foundation.mybatis;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.db.gen.dto.VerifyDatetime;
import cherry.foundation.db.gen.mapper.VerifyDatetimeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class JodaLocalTimeTypeHandlerTest {

	@Autowired
	private VerifyDatetimeMapper mapper;

	@Autowired
	private JdbcOperations jdbcOperations;

	@Test
	public void testSaveAndLoad() {
		LocalTime orig = LocalTime.now();
		VerifyDatetime record = new VerifyDatetime();
		record.setTm(orig);

		int count = mapper.insertSelective(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0L)));

		List<VerifyDatetime> list = mapper.selectByExample(null);
		assertThat(list.isEmpty(), is(false));
		VerifyDatetime r = list.get(0);
		assertThat(r.getTm(), is(orig));

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE tm=?", Integer.class,
						orig.toString("HH:mm:ss.SSS")));
	}

	@Test
	public void testSaveAndLoad_plus1h() {
		LocalTime orig = LocalTime.now().plusHours(1);
		VerifyDatetime record = new VerifyDatetime();
		record.setTm(orig);

		int count = mapper.insertSelective(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0L)));

		List<VerifyDatetime> list = mapper.selectByExample(null);
		assertThat(list.isEmpty(), is(false));
		VerifyDatetime r = list.get(0);
		assertThat(r.getTm(), is(orig));

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE tm=?", Integer.class,
						orig.toString("HH:mm:ss.SSS")));
	}

	@Test
	public void testLoad() {
		jdbcOperations.execute("INSERT INTO verify_datetime(tm) VALUES ('12:34:56.789')");
		List<VerifyDatetime> list = mapper.selectByExample(null);
		assertThat(list.isEmpty(), is(false));
		VerifyDatetime r = list.get(0);
		assertEquals(new LocalTime(12, 34, 56, 789), r.getTm());
	}

}
