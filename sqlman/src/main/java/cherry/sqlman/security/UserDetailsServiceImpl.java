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

package cherry.sqlman.security;

import static java.util.Arrays.asList;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import cherry.sqlman.db.gen.query.QUserAccount;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQueryFactory;

public class UserDetailsServiceImpl implements UserDetailsService {

	private SQLQueryFactory queryFactory;

	private String role;

	private final QUserAccount ua = new QUserAccount("ua");

	public void setQueryFactory(SQLQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Tuple user = queryFactory.from(ua).where(ua.mailAddr.eq(username)).uniqueResult(ua.mailAddr, ua.password);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(user.get(ua.mailAddr), user.get(ua.password), asList(new SimpleGrantedAuthority(role)));
	}

}
