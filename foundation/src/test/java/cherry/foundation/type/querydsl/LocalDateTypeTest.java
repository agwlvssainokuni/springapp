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

package cherry.foundation.type.querydsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Types;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.db.query.QConversionTest;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class LocalDateTypeTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	private final QConversionTest ct = new QConversionTest("ct");

	@After
	public void after() {
		queryDslJdbcOperations.delete(ct, new SqlDeleteCallback() {
			@Override
			public long doInSqlDeleteClause(SQLDeleteClause delete) {
				return delete.execute();
			}
		});
	}

	@Test
	public void testSaveAndLoad() {

		final LocalDate orig = LocalDate.now();
		long count = queryDslJdbcOperations.insert(ct, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(ct.jodaDate, orig);
				return insert.execute();
			}
		});
		assertEquals(1L, count);

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(ct);
		LocalDate result = queryDslJdbcOperations.queryForObject(query, ct.jodaDate);
		assertEquals(orig, result);
	}

	@Test
	public void testSaveAndLoad_plus1d() {

		final LocalDate orig = LocalDate.now().plusDays(1);
		long count = queryDslJdbcOperations.insert(ct, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(ct.jodaDate, orig);
				return insert.execute();
			}
		});
		assertEquals(1L, count);

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(ct);
		LocalDate result = queryDslJdbcOperations.queryForObject(query, ct.jodaDate);
		assertEquals(orig, result);
	}

	@Test
	public void testSaveAndLoad_null() {

		long count = queryDslJdbcOperations.insert(ct, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				return insert.execute();
			}
		});
		assertEquals(1L, count);

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(ct);
		LocalDate result = queryDslJdbcOperations.queryForObject(query, ct.jodaDate);
		assertNull(result);
	}

	@Test
	public void testMisc() {

		LocalDateType type = new LocalDateType(Types.DATE);
		assertEquals(1, type.getSQLTypes().length);
		assertEquals(Types.DATE, type.getSQLTypes()[0]);

		assertEquals("2015-01-23", type.getLiteral(new LocalDate(2015, 1, 23)));
	}

}
