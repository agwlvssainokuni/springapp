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

package cherry.spring.common.lib.querydsl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.db.gen.dto.UserCriteria;
import cherry.spring.common.db.gen.mapper.UserMapper;
import cherry.spring.common.db.gen.query.QUser;
import cherry.spring.common.type.jdbc.RowMapperCreator;

import com.mysema.query.QueryException;
import com.mysema.query.sql.SQLQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class SQLQueryTemplateTest {

	@Autowired
	private SQLQueryOperations sqlQueryOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Autowired
	private UserMapper userMapper;

	@Before
	public void before() {
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setLoginId("mail" + i + "@example.com");
			user.setFirstName("FIRST " + i);
			user.setLastName("LAST " + i);
			user.setRegisteredAt(LocalDateTime.now());
			user.setPassword("password");
			userMapper.insertSelective(user);
		}
	}

	@After
	public void after() {
		UserCriteria criteria = new UserCriteria();
		UserCriteria.Criteria c = criteria.createCriteria();
		c.andLoginIdLike("mail_@example.com");
		userMapper.deleteByExample(criteria);
	}

	@Test
	public void testGetJdbcOperations() {
		assertThat(sqlQueryOperations.getJdbcOperations(), is(notNullValue()));
	}

	@Test
	public void testCreateSQLQuery() {
		assertThat(sqlQueryOperations.createSQLQuery(), is(notNullValue()));
	}

	@Test
	public void testCount00() {
		SQLQuery q = sqlQueryOperations.createSQLQuery();
		QUser u = new QUser("u");
		q.from(u);
		q.where(u.deletedFlg.eq(0).and(u.lockVersion.eq(1))
				.and(u.loginId.startsWith("mail")));
		assertThat(sqlQueryOperations.count(q), is(10L));
	}

	@Test
	public void testCount01() {
		SQLQuery q = sqlQueryOperations.createSQLQuery();
		QUser u = new QUser("u");
		q.from(u);
		q.where(u.deletedFlg.eq(0).and(u.lockVersion.eq(1))
				.and(u.loginId.startsWith("mail1")));
		assertThat(sqlQueryOperations.count(q), is(1L));
	}

	@Test
	public void testQuery00() {
		SQLQuery q = sqlQueryOperations.createSQLQuery();
		QUser u = new QUser("u");
		q.from(u);
		q.where(u.deletedFlg.eq(0).and(u.lockVersion.eq(1))
				.and(u.loginId.startsWith("mail")));
		q.orderBy(u.loginId.desc());
		q.limit(3).offset(3);
		RowMapper<User> rowMapper = rowMapperCreator.create(User.class);
		List<User> list = sqlQueryOperations.query(q, rowMapper, u.id,
				u.loginId, u.firstName, u.lastName, u.registeredAt,
				u.updatedAt, u.createdAt, u.lockVersion, u.deletedFlg);
		assertThat(list.size(), is(3));
		assertThat(list.get(0).getLoginId(), is("mail6@example.com"));
		assertThat(list.get(1).getLoginId(), is("mail5@example.com"));
		assertThat(list.get(2).getLoginId(), is("mail4@example.com"));
	}

	@Test
	public void testQueryForObject00() throws SQLException {
		ResultSetExtractor<?> extractor = mock(ResultSetExtractor.class);
		ResultSet anyResultSet = any();
		when(extractor.extractData(anyResultSet)).thenThrow(
				new QueryException("message"));
		SQLQuery q = sqlQueryOperations.createSQLQuery();
		QUser u = new QUser("u");
		q.from(u);
		try {
			sqlQueryOperations.queryForObject(q, extractor, u.loginId);
			fail("Exception must be thrown");
		} catch (UncategorizedQueryException ex) {
			// OK
		}
	}

	@Test
	public void testQueryForObject01() throws SQLException {
		ResultSetExtractor<?> extractor = mock(ResultSetExtractor.class);
		ResultSet anyResultSet = any();
		when(extractor.extractData(anyResultSet)).thenThrow(
				new QueryException("message", new SQLException()));
		SQLQuery q = sqlQueryOperations.createSQLQuery();
		QUser u = new QUser("u");
		q.from(u);
		try {
			sqlQueryOperations.queryForObject(q, extractor, u.loginId);
			fail("Exception must be thrown");
		} catch (DataAccessException ex) {
			// OK
		}
	}

	@Test
	public void testQueryForObject02() throws SQLException {
		ResultSetExtractor<?> extractor = mock(ResultSetExtractor.class);
		ResultSet anyResultSet = any();
		when(extractor.extractData(anyResultSet)).thenThrow(
				new IllegalStateException("message"));
		SQLQuery q = sqlQueryOperations.createSQLQuery();
		QUser u = new QUser("u");
		q.from(u);
		try {
			sqlQueryOperations.queryForObject(q, extractor, u.loginId);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

}
