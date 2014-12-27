/*
 *   Copyright 2014 agwlvssainokuni
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package cherry.querytutorial.querydsl;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.text.MessageFormat;
import java.util.List;

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
import com.mysema.query.types.QTuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class FromClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

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

}
