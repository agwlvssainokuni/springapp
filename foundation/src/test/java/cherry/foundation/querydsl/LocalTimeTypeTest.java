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

import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.db.gen.query.QVerifyDatetime;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLInsertClause;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class LocalTimeTypeTest {

	@Autowired
	private JdbcOperations jdbcOperations;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QVerifyDatetime vd = new QVerifyDatetime("vd");

	@Test
	public void testSaveAndLoad() {

		LocalTime orig = LocalTime.now();
		SQLInsertClause insert = queryFactory.insert(vd);
		insert.set(vd.tm, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(vd);
		LocalTime result = query.uniqueResult(vd.tm);
		assertEquals(orig, result);

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE tm=?", Integer.class,
						orig.toString("HH:mm:ss.SSS")));
	}

	@Test
	public void testSaveAndLoad_plus1h() {

		LocalTime orig = LocalTime.now().plusHours(1);
		SQLInsertClause insert = queryFactory.insert(vd);
		insert.set(vd.tm, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(vd);
		LocalTime result = query.uniqueResult(vd.tm);
		assertEquals(orig, result);

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE tm=?", Integer.class,
						orig.toString("HH:mm:ss.SSS")));
	}

	@Test
	public void testSaveAndLoad_null() {

		SQLInsertClause insert = queryFactory.insert(vd);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(vd);
		LocalTime result = query.uniqueResult(vd.tm);
		assertNull(result);
	}

	@Test
	public void testLoad() {
		jdbcOperations.execute("INSERT INTO verify_datetime(tm) VALUES ('12:34:56.789')");
		LocalTime result = queryFactory.from(vd).uniqueResult(vd.tm);
		assertEquals(new LocalTime(12, 34, 56, 789), result);
	}

	@Test
	public void testMisc() {

		LocalTimeType type = new LocalTimeType(Types.TIMESTAMP);
		assertEquals(1, type.getSQLTypes().length);
		assertEquals(Types.TIMESTAMP, type.getSQLTypes()[0]);

		assertEquals("12:34:56", type.getLiteral(new LocalTime(12, 34, 56)));
	}

}
