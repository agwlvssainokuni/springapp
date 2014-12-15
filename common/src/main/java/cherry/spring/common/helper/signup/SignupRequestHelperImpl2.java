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

package cherry.spring.common.helper.signup;

import static com.google.common.base.Preconditions.checkState;

import java.sql.SQLException;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.DeletedFlag;
import cherry.spring.common.db.gen.query.QSignupRequest;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.expr.CaseBuilder;

public class SignupRequestHelperImpl2 implements SignupRequestHelper {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Transactional
	@Override
	public int createSignupRequest(final String mailAddr, final String token,
			final LocalDateTime appliedAt) {
		final QSignupRequest a = new QSignupRequest("a");
		SqlInsertWithKeyCallback<Integer> callback = new SqlInsertWithKeyCallback<Integer>() {
			@Override
			public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert)
					throws SQLException {
				insert.set(a.mailAddr, mailAddr);
				insert.set(a.token, token);
				insert.set(a.appliedAt, appliedAt);
				return insert.executeWithKey(Integer.class);
			}
		};
		Integer id = queryDslJdbcOperations.insertWithKey(a, callback);
		checkState(
				id != null,
				"failed to create QSignupRequest: mailAddr={0}, token={1}, appliedAt={2}",
				mailAddr, token, appliedAt);
		return id.intValue();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean validateMailAddr(String mailAddr,
			LocalDateTime intervalFrom, LocalDateTime rangeFrom, int numOfReq) {

		QSignupRequest a = new QSignupRequest("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.mailAddr.eq(mailAddr));
		query.where(a.appliedAt.goe(rangeFrom));
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

		Expression<Boolean> cases = (new CaseBuilder())
				.when(a.id.count().eq(0L)).then(true)
				.when(a.id.count().goe(numOfReq)).then(false)
				.when(a.appliedAt.max().goe(intervalFrom)).then(false)
				.otherwise(true);

		return queryDslJdbcOperations.queryForObject(query, cases);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean validateToken(String mailAddr, String token,
			LocalDateTime validFrom) {

		QSignupRequest a = new QSignupRequest("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.mailAddr.eq(mailAddr));
		query.where(a.token.eq(token));
		query.where(a.appliedAt.goe(validFrom));
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

		QSignupRequest b = new QSignupRequest("b");
		SQLSubQuery subquery = new SQLSubQuery();
		subquery.from(b);
		subquery.where(b.mailAddr.eq(a.mailAddr));
		subquery.where(b.appliedAt.gt(a.appliedAt));
		subquery.where(b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

		query.where(subquery.notExists());

		return queryDslJdbcOperations
				.queryForObject(query, a.id.count().gt(0L));
	}

}
