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
import static org.junit.Assert.assertNull;

import java.sql.Types;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.db.query.QConversionTest;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLInsertClause;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class LocalDateTimeTypeTest {

	@Autowired
	private JdbcOperations jdbcOperations;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QConversionTest ct = new QConversionTest("ct");

	@Test
	public void testSaveAndLoad() {

		LocalDateTime orig = LocalDateTime.now();
		SQLInsertClause insert = queryFactory.insert(ct);
		insert.set(ct.jodaDatetime, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(ct);
		LocalDateTime result = query.uniqueResult(ct.jodaDatetime);
		assertEquals(orig, result);

		assertEquals(Integer.valueOf(1), jdbcOperations.queryForObject(
				"SELECT COUNT(*) FROM conversion_test WHERE joda_datetime=?", Integer.class,
				orig.toString("yyyy-MM-dd HH:mm:ss.SSS")));
	}

	@Test
	public void testSaveAndLoad_plus1d() {

		LocalDateTime orig = LocalDateTime.now().plusDays(1);
		SQLInsertClause insert = queryFactory.insert(ct);
		insert.set(ct.jodaDatetime, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(ct);
		LocalDateTime result = query.uniqueResult(ct.jodaDatetime);
		assertEquals(orig, result);

		assertEquals(Integer.valueOf(1), jdbcOperations.queryForObject(
				"SELECT COUNT(*) FROM conversion_test WHERE joda_datetime=?", Integer.class,
				orig.toString("yyyy-MM-dd HH:mm:ss.SSS")));
	}

	@Test
	public void testSaveAndLoad_null() {

		SQLInsertClause insert = queryFactory.insert(ct);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(ct);
		LocalDateTime result = query.uniqueResult(ct.jodaDatetime);
		assertNull(result);
	}

	@Test
	public void testLoad() {
		jdbcOperations.execute("INSERT INTO conversion_test(joda_datetime) VALUES ('2015-01-23 12:34:56.789')");
		LocalDateTime result = queryFactory.from(ct).uniqueResult(ct.jodaDatetime);
		assertEquals(new LocalDateTime(2015, 1, 23, 12, 34, 56, 789), result);
	}

	@Test
	public void testMisc() {

		LocalDateTimeType type = new LocalDateTimeType(Types.TIMESTAMP);
		assertEquals(1, type.getSQLTypes().length);
		assertEquals(Types.TIMESTAMP, type.getSQLTypes()[0]);

		assertEquals("2015-01-23 12:34:56", type.getLiteral(new LocalDateTime(2015, 1, 23, 12, 34, 56)));
	}

}
