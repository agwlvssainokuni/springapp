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

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.querytutorial.db.gen.query.QAuthor;
import cherry.querytutorial.db.gen.query.QTodo;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.QTuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class WhereClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Test
	public void sec040101_抽出条件の記述方法_単一条件() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).where(a.deletedFlg.eq(0));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedAt, a.dueDt, a.doneFlg, a.doneAt));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDateTime valPostedAt = tuple.get(a.postedAt);
			LocalDate valDueDt = tuple.get(a.dueDt);
			Integer valDoneFlg = tuple.get(a.doneFlg);
			LocalDateTime valDoneAt = tuple.get(a.doneAt);
			out.println(format(
					"{0}: postedAt={1}, dueDt={2}, doneFlg={3}, doneAt={4}",
					valId, valPostedAt, valDueDt, valDoneFlg, valDoneAt));
		}
	}

	@Test
	public void sec040102_抽出条件の記述方法_複合条件() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).where(a.deletedFlg.eq(0)).where(a.doneFlg.eq(1));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedAt, a.dueDt, a.doneFlg, a.doneAt));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDateTime valPostedAt = tuple.get(a.postedAt);
			LocalDate valDueDt = tuple.get(a.dueDt);
			Integer valDoneFlg = tuple.get(a.doneFlg);
			LocalDateTime valDoneAt = tuple.get(a.doneAt);
			out.println(format(
					"{0}: postedAt={1}, dueDt={2}, doneFlg={3}, doneAt={4}",
					valId, valPostedAt, valDueDt, valDoneFlg, valDoneAt));
		}
	}

	@Test
	public void sec040103_抽出条件の記述方法_条件の組合せ_1() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).where(
				a.doneFlg.eq(1).or(a.dueDt.goe(new LocalDate(2015, 2, 1)))
						.and(a.doneAt.isNull()));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedAt, a.dueDt, a.doneFlg, a.doneAt));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDateTime valPostedAt = tuple.get(a.postedAt);
			LocalDate valDueDt = tuple.get(a.dueDt);
			Integer valDoneFlg = tuple.get(a.doneFlg);
			LocalDateTime valDoneAt = tuple.get(a.doneAt);
			out.println(format(
					"{0}: postedAt={1}, dueDt={2}, doneFlg={3}, doneAt={4}",
					valId, valPostedAt, valDueDt, valDoneFlg, valDoneAt));
		}
	}

	@Test
	public void sec040103_抽出条件の記述方法_条件の組合せ_2() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).where(
				a.doneFlg.eq(1).or(
						a.dueDt.goe(new LocalDate(2015, 2, 1)).and(
								a.doneAt.isNull())));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedAt, a.dueDt, a.doneFlg, a.doneAt));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDateTime valPostedAt = tuple.get(a.postedAt);
			LocalDate valDueDt = tuple.get(a.dueDt);
			Integer valDoneFlg = tuple.get(a.doneFlg);
			LocalDateTime valDoneAt = tuple.get(a.doneAt);
			out.println(format(
					"{0}: postedAt={1}, dueDt={2}, doneFlg={3}, doneAt={4}",
					valId, valPostedAt, valDueDt, valDoneFlg, valDoneAt));
		}
	}

	@Test
	public void sec040205_条件式_IN() {

		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		QTodo b = new QTodo("b");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).where(
				a.loginId.in(new SQLSubQuery().from(b).where(b.doneFlg.eq(1))
						.list(b.postedBy)));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.loginId));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valLoginId = tuple.get(a.loginId);
			out.println(format("{0}: loginId={1}", valId, valLoginId));
		}
	}

	@Test
	public void sec040206_条件式_EXISTS() {

		/* 抽出条件を組み立てる。 */
		QAuthor a = new QAuthor("a");
		QTodo b = new QTodo("b");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).where(
				new SQLSubQuery().from(b).where(b.doneFlg.eq(1))
						.where(b.postedBy.eq(a.loginId)).exists());

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.loginId));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valLoginId = tuple.get(a.loginId);
			out.println(format("{0}: loginId={1}", valId, valLoginId));
		}
	}

}
