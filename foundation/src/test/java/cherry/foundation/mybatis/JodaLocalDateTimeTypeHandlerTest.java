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

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.db.dto.ConversionTest;
import cherry.foundation.type.db.mapper.ConversionTestMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class JodaLocalDateTimeTypeHandlerTest {

	@Autowired
	private ConversionTestMapper mapper;

	@Autowired
	private JdbcOperations jdbcOperations;

	@Test
	public void testSaveAndLoad() {
		LocalDateTime orig = LocalDateTime.now();
		ConversionTest record = new ConversionTest();
		record.setJodaDatetime(orig);

		int count = mapper.insert(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0)));

		List<ConversionTest> list = mapper.selectAll();
		assertThat(list.isEmpty(), is(false));
		ConversionTest r = list.get(0);
		assertThat(r.getJodaDatetime(), is(orig));

		assertEquals(Integer.valueOf(1), jdbcOperations.queryForObject(
				"SELECT COUNT(*) FROM conversion_test WHERE joda_datetime=?", Integer.class,
				orig.toString("yyyy-MM-dd HH:mm:ss.SSS")));
	}

	@Test
	public void testSaveAndLoad_plus1d() {
		LocalDateTime orig = LocalDateTime.now().plusDays(1);
		ConversionTest record = new ConversionTest();
		record.setJodaDatetime(orig);

		int count = mapper.insert(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0)));

		List<ConversionTest> list = mapper.selectAll();
		assertThat(list.isEmpty(), is(false));
		ConversionTest r = list.get(0);
		assertThat(r.getJodaDatetime(), is(orig));

		assertEquals(Integer.valueOf(1), jdbcOperations.queryForObject(
				"SELECT COUNT(*) FROM conversion_test WHERE joda_datetime=?", Integer.class,
				orig.toString("yyyy-MM-dd HH:mm:ss.SSS")));
	}

}
