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
import static com.mysema.query.support.Expressions.cases;

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
import com.mysema.query.types.expr.BooleanExpression;

public class SignupRequestHelperImpl2 implements SignupRequestHelper {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Transactional
	@Override
	public long createSignupRequest(final String mailAddr, final String token,
			final LocalDateTime appliedAt) {
		final QSignupRequest a = new QSignupRequest("a");
		SqlInsertWithKeyCallback<Long> callback = new SqlInsertWithKeyCallback<Long>() {
			@Override
			public Long doInSqlInsertWithKeyClause(SQLInsertClause insert)
					throws SQLException {
				insert.set(a.mailAddr, mailAddr);
				insert.set(a.token, token);
				insert.set(a.appliedAt, appliedAt);
				return insert.executeWithKey(Long.class);
			}
		};
		Long id = queryDslJdbcOperations.insertWithKey(a, callback);
		checkState(
				id != null,
				"failed to create QSignupRequest: mailAddr={0}, token={1}, appliedAt={2}",
				mailAddr, token, appliedAt);
		return id.longValue();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean validateMailAddr(String mailAddr,
			LocalDateTime intervalFrom, LocalDateTime rangeFrom, int numOfReq) {

		QSignupRequest a = new QSignupRequest("a");

		SQLQuery query = queryDslJdbcOperations
				.newSqlQuery()
				.from(a)
				.where(a.mailAddr.eq(mailAddr), a.appliedAt.goe(rangeFrom),
						a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

		BooleanExpression cases = cases().when(a.id.count().eq(0L)).then(true)
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
		QSignupRequest b = new QSignupRequest("b");

		SQLQuery query = queryDslJdbcOperations
				.newSqlQuery()
				.from(a)
				.where(a.mailAddr.eq(mailAddr), a.token.eq(token),
						a.appliedAt.goe(validFrom),
						a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.where(new SQLSubQuery()
						.from(b)
						.where(b.mailAddr.eq(a.mailAddr),
								b.appliedAt.gt(a.appliedAt),
								b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
						.notExists());

		return queryDslJdbcOperations.exists(query);
	}

}
