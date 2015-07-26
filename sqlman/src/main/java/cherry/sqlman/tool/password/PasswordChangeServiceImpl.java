/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.sqlman.tool.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.sqlman.db.gen.query.QUserAccount;

import com.mysema.query.sql.SQLQueryFactory;

@Service
public class PasswordChangeServiceImpl implements PasswordChangeService {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final QUserAccount ua = new QUserAccount("ua");

	@Transactional
	@Override
	public Integer getLockVersion(String mailAddr) {
		return queryFactory.from(ua).where(ua.mailAddr.eq(mailAddr)).uniqueResult(ua.lockVersion);
	}

	@Transactional
	@Override
	public boolean updatePassword(String mailAddr, String password, Integer lockVersion) {
		long count = queryFactory.update(ua).set(ua.password, passwordEncoder.encode(password))
				.set(ua.lockVersion, ua.lockVersion.add(1))
				.where(ua.mailAddr.eq(mailAddr), ua.lockVersion.eq(lockVersion)).execute();
		return count == 1L;
	}

}
