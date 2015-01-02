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

import java.text.MessageFormat;
import java.util.List;

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
import com.mysema.query.support.Expressions;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.query.ListSubQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class FromClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Test
	public void sec0301_単一表を指定する() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			out.println(format("{0}: postedBy={1}", valId, valPostedBy));
		}
	}

	@Test
	public void sec030201_複数表を指定して結合する_内部結合() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		QAuthor b = new QAuthor("b");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).join(b).on(b.loginId.eq(a.postedBy), b.deletedFlg.eq(0));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, b.name));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(b.name);
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, posterName={2}", valId, valPostedBy,
					valPosterName));
		}
	}

	@Test
	public void sec030202_複数表を指定して結合する_左外部結合() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		QAuthor b = new QAuthor("b");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).leftJoin(b)
				.on(b.loginId.eq(a.postedBy), b.deletedFlg.eq(0));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, b.name));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(b.name);
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, posterName={2}", valId, valPostedBy,
					valPosterName));
		}
	}

	@Test
	public void sec030203_複数表を指定して結合する_右外部結合() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		QAuthor b = new QAuthor("b");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).rightJoin(b)
				.on(b.loginId.eq(a.postedBy), b.deletedFlg.eq(0));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, b.name));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(b.name);
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, posterName={2}", valId, valPostedBy,
					valPosterName));
		}
	}

	// @Test
	public void sec030204_複数表を指定して結合する_全外部結合() {

		/* 抽出条件を組み立てる。 */
		QTodo a = new QTodo("a");
		QAuthor b = new QAuthor("b");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a).fullJoin(b)
				.on(b.loginId.eq(a.postedBy), b.deletedFlg.eq(0));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.postedBy, b.name));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			String valPostedBy = tuple.get(a.postedBy);
			String valPosterName = tuple.get(b.name);
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, posterName={2}", valId, valPostedBy,
					valPosterName));
		}
	}

	@Test
	public void sec0303_SELECT文をFROM句に指定する() {

		/* FROM句に指定するSELECT文を組み立てる。 */
		QTodo x = new QTodo("x");
		ListSubQuery<Tuple> internalQuery = new SQLSubQuery()
				.from(x)
				.where(x.deletedFlg.eq(0))
				.list(x.id.as("a_id"), x.postedBy.as("a_posted_by"),
						x.postedAt.as("a_posted_at"),
						x.doneFlg.as("a_done_flg"), x.doneAt.as("a_done_at"));

		/* 外側のSELECT文で取り出すカラムを指定するためのパス(メタデータ)を組み立てる。 */
		SimplePath<Tuple> a = Expressions.path(Tuple.class, "a");
		NumberPath<Long> aId = Expressions.numberPath(Long.class, a, "a_id");
		StringPath aPostedBy = Expressions.stringPath(a, "a_posted_by");
		DateTimePath<LocalDateTime> aPostedAt = Expressions.dateTimePath(
				LocalDateTime.class, a, "a_posted_at");
		NumberPath<Integer> aDoneFlg = Expressions.numberPath(Integer.class, a,
				"a_done_flg");
		DateTimePath<LocalDateTime> aDoneAt = Expressions.dateTimePath(
				LocalDateTime.class, a, "a_done_at");

		/* 外側のSELECT文の抽出条件を組み立てる。 */
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(internalQuery, a);
		query.where(aDoneFlg.eq(1));

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(aId,
				aPostedBy, aPostedAt, aDoneAt));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(aId);
			String valPostedBy = tuple.get(aPostedBy);
			LocalDateTime valPostedAt = tuple.get(aPostedAt);
			LocalDateTime valDoneAt = tuple.get(aDoneAt);
			System.out.println(MessageFormat.format(
					"{0}: postedBy={1}, postedAt={2}, doneAt={3}", valId,
					valPostedBy, valPostedAt, valDoneAt));
		}
	}

}
