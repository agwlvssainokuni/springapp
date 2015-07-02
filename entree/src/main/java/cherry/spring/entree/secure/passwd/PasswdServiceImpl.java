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

package cherry.spring.entree.secure.passwd;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.DeletedFlag;
import cherry.spring.common.db.gen.query.QUser;

import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.expr.DateTimeExpression;

@Service
public class PasswdServiceImpl implements PasswdService {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QUser u = new QUser("u");

	@Transactional
	@Override
	public boolean changePassword(String loginId, String password) {
		SQLUpdateClause update = queryFactory.update(u);
		update.set(u.password, password);
		update.set(u.lockVersion, u.lockVersion.add(1));
		update.set(u.updatedAt, DateTimeExpression.currentTimestamp(LocalDateTime.class));
		update.where(u.loginId.eq(loginId), u.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		return update.execute() == 1L;
	}

}
