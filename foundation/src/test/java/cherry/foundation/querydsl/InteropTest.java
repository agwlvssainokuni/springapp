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

package cherry.foundation.querydsl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.db.dto.ConversionTest;
import cherry.foundation.type.db.mapper.ConversionTestMapper;
import cherry.foundation.type.db.query.QConversionTest;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class InteropTest {

	@Autowired
	private ConversionTestMapper mapper;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QConversionTest ct = new QConversionTest("ct");

	@Test
	public void testQuerydslToMyBatis() {

		LocalDateTime ldt = LocalDateTime.now();
		LocalDate ld = LocalDate.now();
		LocalTime lt = LocalTime.now();

		long count = queryFactory.insert(ct).set(ct.jodaDatetime, ldt).set(ct.jodaDate, ld).set(ct.jodaTime, lt)
				.execute();
		assertEquals(1L, count);

		List<ConversionTest> list = mapper.selectAll();
		assertEquals(1, list.size());
		ConversionTest record = list.get(0);
		assertEquals(ldt, record.getJodaDatetime());
		assertEquals(ld, record.getJodaDate());
		assertEquals(lt, record.getJodaTime());
	}

	@Test
	public void testMyBatisToQuerydsl() {

		LocalDateTime ldt = LocalDateTime.now();
		LocalDate ld = LocalDate.now();
		LocalTime lt = LocalTime.now();

		ConversionTest record = new ConversionTest();
		record.setJodaDatetime(ldt);
		record.setJodaDate(ld);
		record.setJodaTime(lt);
		int count = mapper.insert(record);
		assertEquals(1, count);

		Tuple tuple = queryFactory.from(ct).uniqueResult(ct.jodaDatetime, ct.jodaDate, ct.jodaTime);
		assertEquals(ldt, tuple.get(ct.jodaDatetime));
		assertEquals(ld, tuple.get(ct.jodaDate));
		assertEquals(lt, tuple.get(ct.jodaTime));
	}

}
