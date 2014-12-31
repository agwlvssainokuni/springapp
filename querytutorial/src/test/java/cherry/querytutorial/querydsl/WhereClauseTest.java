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

package cherry.querytutorial.querydsl;

import static com.mysema.query.support.Expressions.constant;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.text.MessageFormat;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.FlagCode;
import cherry.querytutorial.db.gen.query.QAuthor;
import cherry.querytutorial.db.gen.query.QTodo;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.QTuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class WhereClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     '2015-02-01',
	 *     '2015-02-01 00:00',
	 *     a.posted_at,
	 *     a.due_dt,
	 *     a.done_at
	 * FROM
	 *     todo AS a
	 * WHERE
	 *     a.due_dt &lt; '2015-02-01'
	 *     OR
	 *     a.posted_at &lt; '2015-02-01 00:00'
	 *     OR
	 *     a.done_at &gt; '2015-02-01 00:00'
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void ANDとORの組合せ_1() {

		QTodo a = new QTodo("a");

		Expression<LocalDate> baseDt = constant(new LocalDate(2015, 2, 1));
		Expression<LocalDateTime> baseDtm = constant(new LocalDateTime(2015, 2,
				1, 0, 0));

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.dueDt.before(baseDt).or(a.postedAt.before(baseDtm))
				.or(a.doneAt.after(baseDtm)));
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				baseDt, baseDtm, a.postedAt, a.dueDt, a.doneAt));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDate valBaseDt = tuple.get(baseDt);
			LocalDateTime valBaseDtm = tuple.get(baseDtm);
			LocalDateTime valPostedAt = tuple.get(a.postedAt);
			LocalDate valDueDt = tuple.get(a.dueDt);
			LocalDateTime valDoneAt = tuple.get(a.doneAt);
			System.out
					.println(MessageFormat
							.format("{0}: baseDt={1}, baseDtm={2}, postedAt={3}, dueDt={4}, doneAt={5}",
									valId, valBaseDt, valBaseDtm, valPostedAt,
									valDueDt, valDoneAt));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     '2015-02-01',
	 *     '2015-02-01 00:00',
	 *     a.posted_at,
	 *     a.due_dt,
	 *     a.done_at
	 * FROM
	 *     todo AS a
	 * WHERE
	 *     (
	 *         a.due_dt &lt; '2015-02-01'
	 *         OR
	 *         a.posted_at &lt; '2015-02-01 00:00'
	 *     )
	 *     AND
	 *     a.done_at &gt; '2015-02-01 00:00'
	 *     OR
	 *     a.deleted_flg = 0
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void ANDとORの組合せ_2() {

		QTodo a = new QTodo("a");

		Expression<LocalDate> baseDt = constant(new LocalDate(2015, 2, 1));
		Expression<LocalDateTime> baseDtm = constant(new LocalDateTime(2015, 2,
				1, 0, 0));

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.dueDt.before(baseDt).or(a.postedAt.before(baseDtm))
				.and(a.doneAt.after(baseDtm))
				.or(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code())));
		query.orderBy(a.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				baseDt, baseDtm, a.postedAt, a.dueDt, a.doneAt));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDate valBaseDt = tuple.get(baseDt);
			LocalDateTime valBaseDtm = tuple.get(baseDtm);
			LocalDateTime valPostedAt = tuple.get(a.postedAt);
			LocalDate valDueDt = tuple.get(a.dueDt);
			LocalDateTime valDoneAt = tuple.get(a.doneAt);
			System.out
					.println(MessageFormat
							.format("{0}: baseDt={1}, baseDtm={2}, postedAt={3}, dueDt={4}, doneAt={5}",
									valId, valBaseDt, valBaseDtm, valPostedAt,
									valDueDt, valDoneAt));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     b.id,
	 *     b.login_id
	 * FROM
	 *     author AS b
	 * WHERE
	 *     b.deleted_flg = 0
	 *     AND
	 *     EXISTS (
	 *         SELECT 1 FROM todo AS a
	 *         WHERE
	 *             a.posted_at = b.login_id
	 *             AND
	 *             a.done_flg = 1
	 *             AND
	 *             a.deleted_flg = 0
	 *     )
	 * </pre>
	 */
	@Test
	public void EXISTS_1() {

		QTodo a = new QTodo("a");
		QAuthor b = new QAuthor("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(b);
		query.where(b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.where(new SQLSubQuery()
				.from(a)
				.where(a.postedBy.eq(b.loginId),
						a.doneFlg.eq(FlagCode.TRUE.code()),
						a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.exists());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(b.id,
				b.loginId));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(b.id);
			String valLoginId = tuple.get(b.loginId);
			System.out.println(MessageFormat.format("{0}: loginId={1}", valId,
					valLoginId));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     b.id,
	 *     b.login_id
	 * FROM
	 *     author AS b
	 * WHERE
	 *     b.deleted_flg = 0
	 *     AND
	 *     b.login_id IN (
	 *         SELECT a.posted_by FROM todo AS a
	 *         WHERE
	 *             a.done_flg = 1
	 *             AND
	 *             a.deleted_flg = 0
	 *     )
	 * </pre>
	 */
	@Test
	public void IN_1() {

		QTodo a = new QTodo("a");
		QAuthor b = new QAuthor("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(b);
		query.where(b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.where(b.loginId.in(new SQLSubQuery()
				.from(a)
				.where(a.doneFlg.eq(FlagCode.TRUE.code()),
						a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.list(a.postedBy)));

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(b.id,
				b.loginId));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(b.id);
			String valLoginId = tuple.get(b.loginId);
			System.out.println(MessageFormat.format("{0}: loginId={1}", valId,
					valLoginId));
		}
	}

}
