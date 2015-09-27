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

import cherry.foundation.db.gen.dto.VerifyDatetime;
import cherry.foundation.db.gen.mapper.VerifyDatetimeMapper;
import cherry.foundation.db.gen.query.QVerifyDatetime;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class InteropTest {

	@Autowired
	private VerifyDatetimeMapper mapper;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QVerifyDatetime vd = new QVerifyDatetime("vd");

	@Test
	public void testQuerydslToMyBatis() {

		LocalDateTime ldtm = LocalDateTime.now();
		LocalDate ldt = LocalDate.now();
		LocalTime ltm = LocalTime.now();

		long count = queryFactory.insert(vd).set(vd.dtm, ldtm).set(vd.dt, ldt).set(vd.tm, ltm).execute();
		assertEquals(1L, count);

		List<VerifyDatetime> list = mapper.selectByExample(null);
		assertEquals(1, list.size());
		VerifyDatetime record = list.get(0);
		assertEquals(ldtm, record.getDtm());
		assertEquals(ldt, record.getDt());
		assertEquals(ltm, record.getTm());
	}

	@Test
	public void testMyBatisToQuerydsl() {

		LocalDateTime ldtm = LocalDateTime.now();
		LocalDate ldt = LocalDate.now();
		LocalTime ltm = LocalTime.now();

		VerifyDatetime record = new VerifyDatetime();
		record.setDtm(ldtm);
		record.setDt(ldt);
		record.setTm(ltm);
		int count = mapper.insertSelective(record);
		assertEquals(1, count);

		Tuple tuple = queryFactory.from(vd).uniqueResult(vd.dtm, vd.dt, vd.tm);
		assertEquals(ldtm, tuple.get(vd.dtm));
		assertEquals(ldt, tuple.get(vd.dt));
		assertEquals(ltm, tuple.get(vd.tm));
	}

}
