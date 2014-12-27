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

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.DeletedFlag;
import cherry.querytutorial.db.gen.query.QPriorityMaster;
import cherry.querytutorial.db.gen.query.QTodo;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.expr.CaseBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class SelectClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

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

	@Test
	public void CASE式を指定する() {

		QTodo a = new QTodo("a");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());

		LocalDate baseDt = new LocalDate(2015, 2, 1);
		Expression<String> doneDesc = new CaseBuilder().when(a.doneFlg.eq(1))
				.then("実施済").when(a.dueDt.before(baseDt)).then("未実施(期限内)")
				.otherwise("未実施(期限切)");

		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.dueDt, a.doneFlg, doneDesc));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			LocalDate valDueDt = tuple.get(a.dueDt);
			Integer valDoneFlg = tuple.get(a.doneFlg);
			String valDoneDesc = tuple.get(doneDesc);
			System.out.println(MessageFormat.format(
					"{0}: dueDt={1}, doneFlg={2}, doneDesc={3}", valId,
					valDueDt, valDoneFlg, valDoneDesc));
		}
	}

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

	@Test
	public void スカラサブクエリを指定する() {

		QTodo a = new QTodo("a");
		QPriorityMaster b = new QPriorityMaster("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.orderBy(a.id.asc());

		Expression<String> priorityLabel = new SQLSubQuery()
				.from(b)
				.where(b.priorityCd.eq(a.priorityCd),
						b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.unique(b.priorityLabel).as("priority_label");
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.priorityCd, priorityLabel));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			Integer valPriorityCd = tuple.get(a.priorityCd);
			String valPriorityLabel = tuple.get(priorityLabel);
			System.out.println(MessageFormat.format(
					"{0}: priorityCd={1}, priorityLabel={2}", valId,
					valPriorityCd, valPriorityLabel));
		}
	}

	@Test
	public void 結合した列を指定する() {

		QTodo a = new QTodo("a");
		QPriorityMaster b = new QPriorityMaster("b");

		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a)
				.join(b)
				.on(b.priorityCd.eq(a.priorityCd),
						b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(a.id,
				a.priorityCd, b.priorityLabel));

		assertThat(list, is(not(empty())));
		for (Tuple tuple : list) {
			Long valId = tuple.get(a.id);
			Integer valPriorityCd = tuple.get(a.priorityCd);
			String valPriorityLabel = tuple.get(b.priorityLabel);
			System.out.println(MessageFormat.format(
					"{0}: priorityCd={1}, priorityLabel={2}", valId,
					valPriorityCd, valPriorityLabel));
		}
	}

}
