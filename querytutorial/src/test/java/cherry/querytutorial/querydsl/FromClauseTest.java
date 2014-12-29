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

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.text.MessageFormat;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.FlagCode;
import cherry.querytutorial.db.gen.query.QAccount;
import cherry.querytutorial.db.gen.query.QTodo;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.path.PathBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class FromClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.posted_by,
	 *     b.name AS poster_name
	 * FROM
	 *     todo AS a
	 *     JOIN account AS b
	 *     ON
	 *         b.login_id = a.posted_by
	 *         AND
	 *         b.deleted_flg = 0
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void 内部結合() {

		QTodo a = new QTodo("a");
		QAccount b = new QAccount("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a)
				.join(b)
				.on(b.loginId.eq(a.postedBy),
						b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, b.name.as("poster_name")));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(b.name.as("poster_name"));
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, posterName={2}", valId, valPostedBy,
					valPosterName));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.posted_by,
	 *     b.name AS poster_name
	 * FROM
	 *     todo AS a
	 *     LEFT JOIN account AS b
	 *     ON
	 *         b.login_id = a.posted_by
	 *         AND
	 *         b.deleted_flg = 0
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void 左外部結合() {

		QTodo a = new QTodo("a");
		QAccount b = new QAccount("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a)
				.leftJoin(b)
				.on(b.loginId.eq(a.postedBy),
						b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, b.name.as("poster_name")));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(b.name.as("poster_name"));
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, posterName={2}", valId, valPostedBy,
					valPosterName));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.posted_by,
	 *     b.name AS poster_name
	 * FROM
	 *     todo AS a
	 *     RIGHT JOIN account AS b
	 *     ON
	 *         b.login_id = a.posted_by
	 *         AND
	 *         b.deleted_flg = 0
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void 右外部結合() {

		QTodo a = new QTodo("a");
		QAccount b = new QAccount("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a)
				.rightJoin(b)
				.on(b.loginId.eq(a.postedBy),
						b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, b.name.as("poster_name")));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(b.name.as("poster_name"));
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, posterName={2}", valId, valPostedBy,
					valPosterName));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.posted_by,
	 *     b.name AS poster_name
	 * FROM
	 *     todo AS a
	 *     FULL JOIN account AS b
	 *     ON
	 *         b.login_id = a.posted_by
	 *         AND
	 *         b.deleted_flg = 0
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	// @Test
	public void 全外部結合() {

		QTodo a = new QTodo("a");
		QAccount b = new QAccount("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a)
				.fullJoin(b)
				.on(b.loginId.eq(a.postedBy),
						b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, b.name.as("poster_name")));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(b.name.as("poster_name"));
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, posterName={2}", valId, valPostedBy,
					valPosterName));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     b.id,
	 *     b.posted_by,
	 *     b.posted_at,
	 *     b.done_at
	 * FROM
	 *     (
	 *         SELECT
	 *            a.id,
	 *            a.posted_by,
	 *            a.posted_at,
	 *            a.done_at
	 *         FROM
	 *             todo AS a
	 *         WHERE
	 *             a.done_flg = 1
	 *             AND
	 *             a.deleted_flg = 0
	 *     ) AS b
	 * ORDER BY
	 *     b.id ASC
	 * </pre>
	 */
	@Test
	public void FROM句に全選択() {

		QTodo a = new QTodo("a");
		QTodo b = new QTodo("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(
				new SQLSubQuery()
						.from(a)
						.where(a.doneFlg.eq(FlagCode.TRUE.code()),
								a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
						.list(a.id, a.postedBy, a.postedAt, a.doneAt),
				new PathBuilder<Tuple>(Tuple.class, "b"));
		query.orderBy(b.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(b.id,
				b.postedBy, b.postedAt, b.doneAt));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(b.id);
			String valPostedBy = tuple.get(b.postedBy);
			LocalDateTime valPostedAt = tuple.get(b.postedAt);
			LocalDateTime valDoneAt = tuple.get(b.doneAt);
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, postedAt={2}, doneAt={3}", valId,
					valPostedBy, valPostedAt, valDoneAt));
		}
	}

}
