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

package cherry.example.common.foundation.impl;

import static com.mysema.query.types.expr.DateTimeExpression.currentTimestamp;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePartial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.example.db.gen.query.QBizdatetimeMaster;
import cherry.foundation.bizdtm.BizDateTime;

import com.mysema.query.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-common-test.xml")
@Transactional
public class BizDateTimeTest {

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QBizdatetimeMaster bm = new QBizdatetimeMaster("bm");

	@Test
	public void testTodayWithoutMaster() {
		LocalDate pre = LocalDate.now();
		LocalDate today = bizDateTime.today();
		LocalDate post = LocalDate.now();
		assertThat(today, greaterThanOrEqualTo((ReadablePartial) pre));
		assertThat(today, lessThanOrEqualTo((ReadablePartial) post));
	}

	@Test
	public void testTodayWithMaster() {

		LocalDate orig = LocalDate.now().plusDays(14);
		assertEquals(1L, queryFactory.insert(bm).set(bm.bizdate, orig).execute());

		assertThat(bizDateTime.today(), is(orig));
	}

	@Test
	public void testNowWithoutMaster() {
		LocalDateTime pre = LocalDateTime.now();
		LocalDateTime now = bizDateTime.now();
		LocalDateTime post = LocalDateTime.now();
		assertThat(now, greaterThanOrEqualTo((ReadablePartial) pre));
		assertThat(now, lessThanOrEqualTo((ReadablePartial) post));
	}

	@Test
	public void testNowWithMaster() {

		assertEquals(1L, queryFactory.insert(bm).columns(bm.offsetDay, bm.offsetHour, bm.offsetMinute, bm.offsetSecond)
				.values(1, 2, 3, 4).execute());

		LocalDateTime curDtm = queryFactory.query().uniqueResult(currentTimestamp(LocalDateTime.class));
		LocalDateTime expected = curDtm.plusDays(1).plusHours(2).plusMinutes(3).plusSeconds(4);

		LocalDateTime now = bizDateTime.now();
		assertThat(now, is(expected));
	}

}
