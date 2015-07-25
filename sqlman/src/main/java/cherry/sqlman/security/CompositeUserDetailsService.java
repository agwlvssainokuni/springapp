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

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

public class CompositeUserDetailsService implements UserDetailsService {

	private final Log log = LogFactory.getLog(getClass());

	private List<UserDetailsService> delegates;

	public void setDelegates(List<UserDetailsService> delegates) {
		this.delegates = delegates;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		for (UserDetailsService service : delegates) {
			try {
				return service.loadUserByUsername(username);
			} catch (UsernameNotFoundException ex) {
				log.debug("Not found for {0}, going next: {1}", username, ex.getMessage());
			}
		}
		throw new UsernameNotFoundException("Not found for " + username);
	}

}
