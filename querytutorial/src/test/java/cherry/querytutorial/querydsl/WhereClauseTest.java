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
import cherry.querytutorial.db.gen.query.QTodo;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.QTuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class WhereClauseTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

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

}
