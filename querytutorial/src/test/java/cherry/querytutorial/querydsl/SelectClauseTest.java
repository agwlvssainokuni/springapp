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

import static com.mysema.query.support.Expressions.as;
import static com.mysema.query.support.Expressions.cases;
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
import cherry.querytutorial.db.gen.query.QAccount;
import cherry.querytutorial.db.gen.query.QTodo;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.QTuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class SelectClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.posted_by,
	 *     a.posted_at,
	 *     a.due_dt,
	 *     a.done_at,
	 *     a.done_flg,
	 *     a.description,
	 *     a.updated_at,
	 *     a.created_at,
	 *     a.lock_version,
	 *     a.deleted_flg
	 * FROM
	 *     todo AS a
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void 全列を取得する() {

		QTodo a = new QTodo("a");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			System.out.println(tuple.toString());
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.posted_by,
	 *     a.posted_at
	 * FROM
	 *     todo AS a
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void 取得する列を指定する() {

		QTodo a = new QTodo("a");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, a.postedAt));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			LocalDateTime valPostedAt = tuple.get(a.postedAt);
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, postedAt={2}", valId, valPostedBy,
					valPostedAt));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.id, 
	 *     a.lock_version,
	 *     a.lock_version + 1 AS lv1,
	 *     a.lock_version - 1 AS lv2,
	 *     a.id + a.id        AS id3
	 * FROM
	 *     todo AS a
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void 算術計算式を指定する() {

		QTodo a = new QTodo("a");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());

		Expression<Integer> lv1 = a.lockVersion.add(1).as("lv1");
		Expression<Integer> lv2 = a.lockVersion.subtract(1).as("lv2");
		Expression<Long> id3 = a.id.add(a.id).as("id3");
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.lockVersion, lv1, lv2, id3));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			Integer valLockVersion = tuple.get(a.lockVersion);
			Integer valLv1 = tuple.get(lv1);
			Integer valLv2 = tuple.get(lv2);
			Long valId3 = tuple.get(id3);
			System.out.println(MessageFormat.format(
					"{0}: lv={1}, lv + 1={2}, lv - 1={3}, id + id={4}", valId,
					valLockVersion, valLv1, valLv2, valId3));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.description,
	 *     LENGTH(a.description) AS desc1
	 *     (a.description || ' ' || a.posted_by) AS desc2
	 * FROM
	 *     todo AS a
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void 関数呼出しを指定する() {

		QTodo a = new QTodo("a");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());

		Expression<Integer> desc1 = a.description.length().as("desc1");
		Expression<String> desc2 = a.description.append(" ").append(a.postedBy)
				.as("desc2");
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.description, desc1, desc2));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valDescription = tuple.get(a.description);
			Integer valDesc1 = tuple.get(desc1);
			String valDesc2 = tuple.get(desc2);
			System.out.println(MessageFormat.format(
					"{0}: description={1}, desc1={2}, desc2={3}", valId,
					valDescription, valDesc1, valDesc2));
		}
	}

/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.due_dt,
	 *     a.done_flg,
	 *     '2015-02-01',
	 *     CASE
	 *         WHEN a.done_flg = 1 THEN '実施済'
	 *         WHEN a.due_dt < '2015-02-01' THEN '未実施(期限内)'
	 *         ELSE '未実施(期限切)'
	 *     END
	 * FROM
	 *     todo AS a
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void CASE式を指定する() {

		QTodo a = new QTodo("a");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());

		Expression<LocalDate> baseDt = constant(new LocalDate(2015, 2, 1));
		Expression<String> doneDesc = cases().when(a.doneFlg.eq(1)).then("実施済")
				.when(a.dueDt.lt(baseDt)).then("未実施(期限内)")
				.otherwise("未実施(期限切)");

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.dueDt, a.doneFlg, baseDt, doneDesc));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDate valDueDt = tuple.get(a.dueDt);
			Integer valDoneFlg = tuple.get(a.doneFlg);
			LocalDate valBaseDt = tuple.get(baseDt);
			String valDoneDesc = tuple.get(doneDesc);
			System.out.println(MessageFormat.format(
					"{0}: dueDt={1}, doneFlg={2}, baseDt={3}, doneDesc={4}",
					valId, valDueDt, valDoneFlg, valBaseDt, valDoneDesc));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.posted_by,
	 *     COUNT(a.id)      AS cnt,
	 *     MIN(a.posted_at) AS min_posted_at,
	 *     MAX(a.posted_at) AS max_posted_at
	 * FROM
	 *     todo AS a
	 * GROUP BY
	 *     a.posted_by
	 * ORDER BY
	 *     a.posted_by ASC
	 * </pre>
	 */
	@Test
	public void 集計関数を指定する() {

		QTodo a = new QTodo("a");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.groupBy(a.postedBy);
		query.orderBy(a.postedBy.asc());

		Expression<Long> cnt = a.id.count().as("cnt");
		Expression<LocalDateTime> minPostedAt = a.postedAt.min().as(
				"min_posted_at");
		Expression<LocalDateTime> maxPostedAt = a.postedAt.max().as(
				"max_posted_at");
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(
				a.postedBy, cnt, minPostedAt, maxPostedAt));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			String valPostedBy = tuple.get(a.postedBy);
			Long valCnt = tuple.get(cnt);
			LocalDateTime valMinPostedAt = tuple.get(minPostedAt);
			LocalDateTime valMaxPostedAt = tuple.get(maxPostedAt);
			System.out.println(MessageFormat.format(
					"{0}: cnt={1}, minPostedAt={2}, maxPostedAt={3}",
					valPostedBy, valCnt, valMinPostedAt, valMaxPostedAt));
		}
	}

	/**
	 * <pre>
	 * SELECT
	 *     a.id,
	 *     a.posted_by,
	 *     (
	 *         SELECT b.name FROM account AS b
	 *         WHERE
	 *             b.login_id = a.posted_by
	 *             AND
	 *             b.deleted_flg = 0
	 *     ) AS poster_name
	 * FROM
	 *     todo AS a
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void スカラサブクエリを指定する() {

		QTodo a = new QTodo("a");
		QAccount b = new QAccount("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());

		Expression<String> posterName = new SQLSubQuery()
				.from(b)
				.where(b.loginId.eq(a.postedBy),
						b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.unique(b.name).as("poster_name");
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, posterName));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(posterName);
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
	public void 結合した列を指定する() {

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
	 *     a.due_dt,
	 *     '2015-02-01',
	 *     a.due_dt < '2015-02-01' AS due
	 * FROM
	 *     todo AS a
	 * ORDER BY
	 *     a.id ASC
	 * </pre>
	 */
	@Test
	public void 定数を列として指定する() {

		QTodo a = new QTodo("a");

		Expression<LocalDate> dt = constant(new LocalDate(2015, 2, 1));
		Expression<LocalDate> baseDt = as(dt, "base_dt");
		Expression<Boolean> due = a.dueDt.lt(dt).as("due");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.dueDt, baseDt, due));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDate valDueDt = tuple.get(a.dueDt);
			LocalDate valBaseDt = tuple.get(baseDt);
			Boolean valDue = tuple.get(due);
			System.out.println(MessageFormat.format(
					"{0}: dueDt={1}, baseDt={2}, due={3}", valId, valDueDt,
					valBaseDt, valDue));
		}
	}

}
