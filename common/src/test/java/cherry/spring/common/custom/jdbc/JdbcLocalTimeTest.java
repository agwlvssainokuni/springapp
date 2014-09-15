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

package cherry.spring.common.custom.jdbc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.spring.common.db.gen.dto.ConversionTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class JdbcLocalTimeTest {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private JdbcDao jdbcDao;

	@After
	public void after() {
		namedParameterJdbcOperations.update("DELETE FROM conversion_test",
				new HashMap<String, Object>());
	}

	@Test
	public void testSaveAndLoad() {
		LocalTime orig = LocalTime.now();
		ConversionTest record = new ConversionTest();
		record.setJodaTime(orig);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = jdbcDao.insert(record, keyHolder);

		assertThat(count, is(1));
		assertThat(keyHolder.getKey().intValue(), is(not(0)));

		List<ConversionTest> list = jdbcDao.selectAll();
		assertThat(list.isEmpty(), is(false));
		ConversionTest r = list.get(0);
		assertThat(r.getJodaTime(), is(orig));
	}

	@Test
	public void testSaveAndLoad_plus1h() {
		LocalTime orig = LocalTime.now().plusHours(1);
		ConversionTest record = new ConversionTest();
		record.setJodaTime(orig);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = jdbcDao.insert(record, keyHolder);

		assertThat(count, is(1));
		assertThat(keyHolder.getKey().intValue(), is(not(0)));

		List<ConversionTest> list = jdbcDao.selectAll();
		assertThat(list.isEmpty(), is(false));
		ConversionTest r = list.get(0);
		assertThat(r.getJodaTime(), is(orig));
	}

}
