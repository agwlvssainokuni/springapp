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

package cherry.foundation.querydsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.etl.Column;
import cherry.foundation.etl.Consumer;
import cherry.foundation.etl.CsvConsumer;
import cherry.foundation.etl.Limiter;
import cherry.foundation.etl.LimiterException;
import cherry.foundation.etl.TimeLimiter;
import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.db.dto.ConversionTest;
import cherry.foundation.type.db.query.QConversionTest;
import cherry.foundation.type.jdbc.RowMapperCreator;
import cherry.goods.paginate.PagedList;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.QTuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class QueryDslSupportImplTest {

	@Autowired
	private QueryDslSupport queryDslSupport;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QConversionTest ct = new QConversionTest("ct");

	private final LocalDate localDate = LocalDate.now();

	private final LocalDateTime localDateTime = LocalDateTime.now();

	@Before
	public void before() {
		SQLInsertClause insert = queryFactory.insert(ct);
		for (int i = -100; i <= 100; i++) {
			insert.set(ct.jodaDate, localDate.plusDays(i));
			insert.set(ct.jodaDatetime, localDateTime.plusDays(i));
			insert.set(ct.deletedFlg, 0);
			insert.addBatch();
		}
		assertEquals(201L, insert.execute());
	}

	@Test
	public void testSearch1_OK() {

		PagedList<ConversionTest> pagedList = queryDslSupport.search(commonClause(ct), orderByClause(ct), 10, 5,
				rowMapperCreator.create(ConversionTest.class), ct.id, ct.jodaDate, ct.jodaDatetime);

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		int i = 50;
		for (ConversionTest item : pagedList.getList()) {
			assertEquals(localDate.plusDays(i), item.getJodaDate());
			assertEquals(localDateTime.plusDays(i), item.getJodaDatetime());
			i -= 1;
		}
	}

	@Test(expected = DataAccessException.class)
	public void testSearch1_SQLException() {
		queryDslSupport.search(commonClause(ct), orderByClause(ct), 10, 5, new RowMapper<ConversionTest>() {
			@Override
			public ConversionTest mapRow(ResultSet rs, int rowNum) throws SQLException {
				rs.getObject(0);
				return null;
			}
		}, ct.id, ct.jodaDate, ct.jodaDatetime);
	}

	@Test
	public void testSearch2_OK() {

		Predicate<Long> cancelPredicate = Predicates.alwaysFalse();
		PagedList<ConversionTest> pagedList = queryDslSupport.search(commonClause(ct), orderByClause(ct), 10, 5,
				cancelPredicate, rowMapperCreator.create(ConversionTest.class), ct.id, ct.jodaDate, ct.jodaDatetime);

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		int i = 50;
		for (ConversionTest item : pagedList.getList()) {
			assertEquals(localDate.plusDays(i), item.getJodaDate());
			assertEquals(localDateTime.plusDays(i), item.getJodaDatetime());
			i -= 1;
		}
	}

	@Test
	public void testSearch2_Cancel() {

		Predicate<Long> cancelPredicate = Predicates.alwaysTrue();
		PagedList<ConversionTest> pagedList = queryDslSupport.search(commonClause(ct), orderByClause(ct), 10, 5,
				cancelPredicate, rowMapperCreator.create(ConversionTest.class), ct.id, ct.jodaDate, ct.jodaDatetime);

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		assertNull(pagedList.getList());
	}

	@Test
	public void testSearch3() {

		PagedList<Tuple> pagedList = queryDslSupport.search(commonClause(ct), orderByClause(ct), 10, 5, new QTuple(
				ct.id, ct.jodaDate, ct.jodaDatetime));

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		int i = 50;
		for (Tuple item : pagedList.getList()) {
			assertEquals(localDate.plusDays(i), item.get(ct.jodaDate));
			assertEquals(localDateTime.plusDays(i), item.get(ct.jodaDatetime));
			i -= 1;
		}
	}

	@Test
	public void testSearch4_OK() {

		Predicate<Long> cancelPredicate = Predicates.alwaysFalse();
		PagedList<Tuple> pagedList = queryDslSupport.search(commonClause(ct), orderByClause(ct), 10, 5,
				cancelPredicate, new QTuple(ct.id, ct.jodaDate, ct.jodaDatetime));

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		int i = 50;
		for (Tuple item : pagedList.getList()) {
			assertEquals(localDate.plusDays(i), item.get(ct.jodaDate));
			assertEquals(localDateTime.plusDays(i), item.get(ct.jodaDatetime));
			i -= 1;
		}
	}

	@Test
	public void testSearch4_Cancel() {

		Predicate<Long> cancelPredicate = Predicates.alwaysTrue();
		PagedList<Tuple> pagedList = queryDslSupport.search(commonClause(ct), orderByClause(ct), 10, 5,
				cancelPredicate, new QTuple(ct.id, ct.jodaDate, ct.jodaDatetime));

		assertEquals(101, pagedList.getPageSet().getTotalCount());
		assertEquals(10, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(50, pagedList.getPageSet().getCurrent().getFrom());
		assertEquals(54, pagedList.getPageSet().getCurrent().getTo());
		assertEquals(5, pagedList.getPageSet().getCurrent().getCount());

		assertNull(pagedList.getList());
	}

	@Test
	public void testDownload1_OK() throws IOException {
		try (StringWriter w = new StringWriter()) {

			CsvConsumer consumer = new CsvConsumer(w, false);
			long count = queryDslSupport.download(commonClause(ct), orderByClause(ct), consumer, ct.jodaDate);
			assertEquals(101L, count);

			List<String> list = new ArrayList<>();
			for (int i = 100; i >= 0; i--) {
				list.add("\"" + localDate.plusDays(i).toString() + "\"\r\n");
			}
			assertEquals(Joiner.on("").join(list), w.toString());
		}
	}

	@Test(expected = IOException.class)
	public void testDownload1_IOException() throws IOException {
		try (StringWriter w = new StringWriter()) {
			queryDslSupport.download(commonClause(ct), orderByClause(ct), new Consumer() {

				@Override
				public void begin(Column[] col) throws IOException {
					throw new IOException();
				}

				@Override
				public void consume(Object[] record) throws IOException {
					// 何もしない
				}

				@Override
				public void end() throws IOException {
					// 何もしない
				}
			}, ct.jodaDate);
		}
	}

	@Test
	public void testDownload2_OK() throws IOException {
		try (StringWriter w = new StringWriter()) {

			Limiter limiter = new TimeLimiter(3600000L);
			CsvConsumer consumer = new CsvConsumer(w, false);
			long count = queryDslSupport.download(commonClause(ct), orderByClause(ct), consumer, limiter, ct.jodaDate);
			assertEquals(101L, count);

			List<String> list = new ArrayList<>();
			for (int i = 100; i >= 0; i--) {
				list.add("\"" + localDate.plusDays(i).toString() + "\"\r\n");
			}
			assertEquals(Joiner.on("").join(list), w.toString());
		}
	}

	@Test(expected = LimiterException.class)
	public void testDownload2_LimiterException() throws IOException {
		try (StringWriter w = new StringWriter()) {
			Limiter limiter = new TimeLimiter(-1L);
			CsvConsumer consumer = new CsvConsumer(w, false);
			queryDslSupport.download(commonClause(ct), orderByClause(ct), consumer, limiter, ct.jodaDate);
		}
	}

	private QueryConfigurer commonClause(final QConversionTest qct) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.from(qct);
				query.where(qct.jodaDate.goe(localDate), qct.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return query;
			}
		};
	}

	private QueryConfigurer orderByClause(final QConversionTest qct) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.orderBy(qct.jodaDate.desc());
				return query;
			}
		};
	}

}
