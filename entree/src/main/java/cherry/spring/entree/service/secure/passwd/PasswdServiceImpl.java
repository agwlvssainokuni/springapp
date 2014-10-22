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

package cherry.spring.entree.service.secure.passwd;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.db.gen.query.QUser;
import cherry.spring.foundation.type.DeletedFlag;

import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.expr.DateTimeExpression;

@Service
public class PasswdServiceImpl implements PasswdService {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Transactional
	@Override
	public boolean changePassword(final String loginId, final String password) {

		final QUser u = new QUser("u");
		long count = queryDslJdbcOperations.update(u, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {

				update.set(u.password, password);
				update.set(u.lockVersion, u.lockVersion.add(1));
				update.set(u.updatedAt, DateTimeExpression
						.currentTimestamp(LocalDateTime.class));

				update.where(u.loginId.eq(loginId));
				update.where(u.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

				return update.execute();
			}
		});

		return count == 1L;
	}

}
