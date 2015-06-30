/*
 * Copyright 2014,2015 agwlvssainokuni
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

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.DeletedFlag;
import cherry.spring.common.db.gen.query.QSignupRequest;

import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLInsertClause;

public class SignupRequestHelperImpl2 implements SignupRequestHelper {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QSignupRequest a = new QSignupRequest("a");
	private final QSignupRequest b = new QSignupRequest("b");

	@Transactional
	@Override
	public long createSignupRequest(final String mailAddr, final String token, final LocalDateTime appliedAt) {
		SQLInsertClause insert = queryFactory.insert(a);
		insert.set(a.mailAddr, mailAddr);
		insert.set(a.token, token);
		insert.set(a.appliedAt, appliedAt);
		Long id = insert.executeWithKey(Long.class);
		checkState(id != null, "failed to create QSignupRequest: mailAddr=%s, token=%s, appliedAt=%s", mailAddr, token,
				appliedAt);
		return id.longValue();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean validateMailAddr(String mailAddr, LocalDateTime intervalFrom, LocalDateTime rangeFrom, int numOfReq) {
		return queryFactory
				.from(a)
				.where(a.mailAddr.eq(mailAddr), a.appliedAt.goe(rangeFrom),
						a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.uniqueResult(
						cases().when(a.id.count().eq(0L)).then(true).when(a.id.count().goe(numOfReq)).then(false)
								.when(a.appliedAt.max().goe(intervalFrom)).then(false).otherwise(true));
	}

	@Transactional(readOnly = true)
	@Override
	public boolean validateToken(String mailAddr, String token, LocalDateTime validFrom) {
		return queryFactory
				.from(a)
				.where(a.mailAddr.eq(mailAddr), a.token.eq(token), a.appliedAt.goe(validFrom),
						a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.where(queryFactory
						.subQuery(b)
						.where(b.mailAddr.eq(a.mailAddr), b.appliedAt.gt(a.appliedAt),
								b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code())).notExists()).exists();
	}

}
