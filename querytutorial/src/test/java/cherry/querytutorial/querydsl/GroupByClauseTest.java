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
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.query.ListSubQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class GroupByClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Test
	public void sec0501_GROUPBY() {

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
	public void sec0502_HAVING() {

		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.groupBy(a.postedBy);
		query.having(a.id.count().gt(1),
				a.postedAt.max().lt(new LocalDateTime(2015, 2, 1, 0, 0)));
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
	public void sec0503_ORDERBY() {

		QTodo a = new QTodo("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.groupBy(a.postedBy);
		query.orderBy(a.id.count().asc());
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
	public void sec0504_UNION() {

		/* UNION句でつなげるSELECT文を組み立てる。 */
		QTodo a = new QTodo("a");
		ListSubQuery<Tuple> queryA = new SQLSubQuery().from(a).list(a.id,
				a.postedBy.as("name"));
		QAuthor b = new QAuthor("b");
		ListSubQuery<Tuple> queryB = new SQLSubQuery().from(b).list(b.id,
				b.loginId.as("name"));

		/* 外側のSELECT文で取り出すカラムを指定するためのパス(メタデータ)を組み立てる。 */
		SimplePath<Tuple> x = Expressions.path(Tuple.class, "x");
		NumberPath<Long> xId = Expressions.numberPath(Long.class, x, "id");
		StringPath xName = Expressions.stringPath(x, "name");

		/* 外側のSELECT文の抽出条件を組み立てる。 */
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.union(x, queryA, queryB);

		/* 取出すカラムとデータの取出し方を指定してクエリを発行する。 */
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(xId,
				xName));

		/* クエリの結果を表示する。 */
		for (Tuple tuple : list) {
			Long valId = tuple.get(xId);
			String valName = tuple.get(xName);
			out.println(format("{0}: name={1}", valId, valName));
		}
	}
}
