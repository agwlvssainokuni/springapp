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

import static java.lang.System.out;
import static java.text.MessageFormat.format;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
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
import cherry.querytutorial.db.gen.query.QAuthor;
import cherry.querytutorial.db.gen.query.QTodo;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.support.Expressions;
import com.mysema.query.types.Expression;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.expr.StringExpressions;
import com.mysema.query.types.expr.Wildcard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class SelectClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Test
	public void sec020101_カラムを絞って照会する() {

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.loginId, a.name));

		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valLoginId = tuple.get(a.loginId);
			String valName = tuple.get(a.name);
			LocalDateTime valUpdatedAt = tuple.get(a.updatedAt);
			LocalDateTime valCreatedAt = tuple.get(a.createdAt);
			Integer valLockVersion = tuple.get(a.lockVersion);
			Integer valDeletedFlg = tuple.get(a.deletedFlg);
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}",
					valId, valLoginId, valName, valUpdatedAt, valCreatedAt,
					valLockVersion, valDeletedFlg));
		}
	}

	@Test
	public void sec020102_全てのカラムを照会する() {

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));

		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valLoginId = tuple.get(a.loginId);
			String valName = tuple.get(a.name);
			LocalDateTime valUpdatedAt = tuple.get(a.updatedAt);
			LocalDateTime valCreatedAt = tuple.get(a.createdAt);
			Integer valLockVersion = tuple.get(a.lockVersion);
			Integer valDeletedFlg = tuple.get(a.deletedFlg);
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}",
					valId, valLoginId, valName, valUpdatedAt, valCreatedAt,
					valLockVersion, valDeletedFlg));
		}
	}

	@Test
	public void sec020103_アスタリスクを指定して照会する() {

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Object[]> list = queryDslJdbcOperations.query(query, Wildcard.all);

		for (Object[] tuple : list) {
			Long valId = (Long) tuple[0];
			String valLoginId = (String) tuple[1];
			String valName = (String) tuple[2];
			Timestamp valUpdatedAt = (Timestamp) tuple[3];
			Timestamp valCreatedAt = (Timestamp) tuple[4];
			Integer valLockVersion = (Integer) tuple[5];
			Integer valDeletedFlg = (Integer) tuple[6];
			out.println(format(
					"{0}: loginId={1}, name={2}, updatedAt={3}, createdAt={4}, lockVersion={5}, deletedFlg={6}",
					valId, valLoginId, valName, valUpdatedAt, valCreatedAt,
					valLockVersion, valDeletedFlg));
		}
	}

	@Test
	public void sec020104_カラムにエイリアスを付与する() {

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(
				query,
				new QTuple(a.id.as("alias1"), a.loginId.as("alias2"), a.name
						.as("alias3")));

		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id.as("alias1"));
			String valLoginId = tuple.get(a.loginId.as("alias2"));
			String valName = tuple.get(a.name.as("alias3"));
			out.println(format("{0}: loginId={1}, name={2}", valId, valLoginId,
					valName));
			assertThat(tuple.get(a.id), is(nullValue()));
			assertThat(tuple.get(a.loginId), is(nullValue()));
			assertThat(tuple.get(a.name), is(nullValue()));
		}
	}

	@Test
	public void sec020201_定数値をカラムとして照会する() {

		Expression<Integer> const1 = Expressions.constant(1);
		Expression<String> const2 = Expressions.constant("string");

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				const1, const2));

		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			Integer valConst1 = tuple.get(const1);
			String valConst2 = tuple.get(const2);
			out.println(format("{0}: const1={1}, const2={2}", valId, valConst1,
					valConst2));
		}
	}

	@Test
	public void sec020202_定数値のカラムにエイリアスを付与する() {

		Expression<Integer> const1 = Expressions.constant(1);
		Expression<String> const2 = Expressions.constant("string");

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(
				query,
				new QTuple(a.id, Expressions.as(const1, "alias1"), Expressions
						.as(const2, "alias2")));

		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			Integer valConst1 = tuple.get(Expressions.as(const1, "alias1"));
			String valConst2 = tuple.get(Expressions.as(const2, "alias2"));
			out.println(format("{0}: const1={1}, const2={2}", valId, valConst1,
					valConst2));
			assertThat(tuple.get(const1), is(nullValue()));
			assertThat(tuple.get(const2), is(nullValue()));
		}
	}

	@Test
	public void sec020301_カラムに対する算術計算_加減乗除() {

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(
				query,
				new QTuple(a.id, a.id.add(2L), a.id.subtract(2L), a.id
						.multiply(2L), a.id.divide(2L), a.id.mod(2L)));

		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			Long valAdd = tuple.get(a.id.add(2L));
			Long valSubtract = tuple.get(a.id.subtract(2L));
			Long valMultiply = tuple.get(a.id.multiply(2L));
			Long valDivide = tuple.get(a.id.divide(2L));
			Long valMod = tuple.get(a.id.mod(2L));
			out.println(format(
					"{0}: +2={1}, -2={2}, *2={3}, /2={4}, mod 2={5}", valId,
					valAdd, valSubtract, valMultiply, valDivide, valMod));
		}
	}

	@Test
	public void sec020302_カラムに対する算術計算_計算順序() {

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.id, a.id.add(2L).multiply(2L), a.id.multiply(2L)
						.add(2L), a.id.add(2L).multiply(2L).subtract(2L)
						.divide(2L), a.id.add(2L).multiply(a.id.subtract(2L))));

		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			Long val1 = tuple.get(a.id.add(2L).multiply(2L));
			Long val2 = tuple.get(a.id.multiply(2L).add(2L));
			Long val3 = tuple.get(a.id.add(2L).multiply(2L).subtract(2L)
					.divide(2L));
			Long val4 = tuple.get(a.id.add(2L).multiply(a.id.subtract(2L)));
			out.println(format(
					"{0}: (id+2)*2={1}, id*2+2={2}, ((id+2)*2-2)/2={3}, (id+2)*(id-2)={4}",
					valId, val1, val2, val3, val4));
		}
	}

	@Test
	public void sec020401_カラムに対する関数適用() {

		QAuthor a = new QAuthor("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		List<Tuple> list = queryDslJdbcOperations.query(
				query,
				new QTuple(a.id, a.loginId, a.name, a.loginId.length(),
						a.loginId.concat(a.name), StringExpressions.lpad(
								a.loginId, 10, 'X')));

		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valLoginId = tuple.get(a.loginId);
			String valName = tuple.get(a.name);
			Integer valLength = tuple.get(a.loginId.length());
			String valConcat = tuple.get(a.loginId.concat(a.name));
			String valLpad = tuple.get(StringExpressions.lpad(a.loginId, 10,
					'X'));
			out.println(format(
					"{0}: loginId={1}, name={2}, LENGTH(loginId)={3}, CONCAT(loginId, name)={4}, LPAD(loginId, 10, X)={5}",
					valId, valLoginId, valName, valLength, valConcat, valLpad));
		}
	}

	@Test
	public void sec020408_カラムに対する関数適用_集約関数() {

		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.groupBy(a.postedBy);
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(
				a.postedBy, a.id.count(), a.id.sum(), a.postedAt.min(),
				a.postedAt.max()));

		for (Tuple tuple : list) {
			String valPostedBy = tuple.get(a.postedBy);
			Long valCount = tuple.get(a.id.count());
			Long valSum = tuple.get(a.id.sum());
			LocalDateTime valMinPostedAt = tuple.get(a.postedAt.min());
			LocalDateTime valMaxPostedAt = tuple.get(a.postedAt.max());
			out.println(format(
					"{0}: COUNT(id)={1}, SUM(id)={2}, MIN(postedAt)={3}, MAX(postedAt)={4}",
					valPostedBy, valCount, valSum, valMinPostedAt,
					valMaxPostedAt));
		}
	}

	@Test
	public void sec020501_CASE式を指定する_単純CASE式() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* CASE式を組立てる。 */
		Expression<String> doneDesc = a.doneFlg.when(0).then("未実施").when(1)
				.then("実施済").otherwise("不定");

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.doneFlg, doneDesc));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			Integer valDoneFlg = tuple.get(a.doneFlg);
			String valDoneDesc = tuple.get(doneDesc);
			out.println(format("{0}: doneFlg={1}, doneDesc(CASE)={2}", valId,
					valDoneFlg, valDoneDesc));
		}
	}

	@Test
	public void sec020502_CASE式を指定する_検索CASE式() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* CASE式を組立てる。 */
		Expression<String> doneDesc = Expressions.cases().when(a.doneFlg.eq(1))
				.then("実施済").when(a.dueDt.lt(new LocalDate(2015, 2, 1)))
				.then("未実施(期限内)").otherwise("未実施(期限切)");

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.dueDt, a.doneFlg, doneDesc));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDate valDueDt = tuple.get(a.dueDt);
			Integer valDoneFlg = tuple.get(a.doneFlg);
			String valDoneDesc = tuple.get(doneDesc);
			out.println(format(
					"{0}: dueDt={1}, doneFlg={2}, doneDesc(CASE)={3}", valId,
					valDueDt, valDoneFlg, valDoneDesc));
		}
	}

	@Test
	public void sec0206_スカラサブクエリを指定する() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* スカラサブクエリを組立てる。 */
		QAuthor b = new QAuthor("b");
		Expression<String> posterName = new SQLSubQuery().from(b)
				.where(b.loginId.eq(a.postedBy), b.deletedFlg.eq(0))
				.unique(b.name);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, posterName));

		/* クエリの結果を表示する。 */
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
	 *     LEFT JOIN author AS b
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
		QAuthor b = new QAuthor("b");

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

}
