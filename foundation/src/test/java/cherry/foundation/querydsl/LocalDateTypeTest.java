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

import org.joda.time.LocalDate;
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
public class LocalDateTypeTest {

	@Autowired
	private JdbcOperations jdbcOperations;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QVerifyDatetime vd = new QVerifyDatetime("vd");

	@Test
	public void testSaveAndLoad() {

		LocalDate orig = LocalDate.now();
		SQLInsertClause insert = queryFactory.insert(vd);
		insert.set(vd.dt, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(vd);
		LocalDate result = query.uniqueResult(vd.dt);
		assertEquals(orig, result);

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE dt=?", Integer.class,
						orig.toString("yyyy-MM-dd")));
	}

	@Test
	public void testSaveAndLoad_plus1d() {

		LocalDate orig = LocalDate.now().plusDays(1);
		SQLInsertClause insert = queryFactory.insert(vd);
		insert.set(vd.dt, orig);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(vd);
		LocalDate result = query.uniqueResult(vd.dt);
		assertEquals(orig, result);

		assertEquals(
				Integer.valueOf(1),
				jdbcOperations.queryForObject("SELECT COUNT(*) FROM verify_datetime WHERE dt=?", Integer.class,
						orig.toString("yyyy-MM-dd")));
	}

	@Test
	public void testSaveAndLoad_null() {

		SQLInsertClause insert = queryFactory.insert(vd);
		long count = insert.execute();
		assertEquals(1L, count);

		SQLQuery query = queryFactory.from(vd);
		LocalDate result = query.uniqueResult(vd.dt);
		assertNull(result);
	}

	@Test
	public void testLoad() {
		jdbcOperations.execute("INSERT INTO verify_datetime(dt) VALUES ('2015-01-23')");
		LocalDate result = queryFactory.from(vd).uniqueResult(vd.dt);
		assertEquals(new LocalDate(2015, 1, 23), result);
	}

	@Test
	public void testMisc() {

		LocalDateType type = new LocalDateType(Types.DATE);
		assertEquals(1, type.getSQLTypes().length);
		assertEquals(Types.DATE, type.getSQLTypes()[0]);

		assertEquals("2015-01-23", type.getLiteral(new LocalDate(2015, 1, 23)));
	}

}
