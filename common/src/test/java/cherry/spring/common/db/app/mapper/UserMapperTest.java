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

package cherry.spring.common.db.app.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.db.gen.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class UserMapperTest {

	@Autowired
	private UserMapper2 mapper;

	@Autowired
	private UserMapper mapper0;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void testChangePassword00() {
		List<Integer> ids = new ArrayList<>();
		try {

			String loginId = "user00@example.com";

			assertEquals(0,
					mapper.changePassword(loginId, encode("newPassword")));

		} finally {
			for (Integer id : ids) {
				mapper0.deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testChangePassword01() {
		List<Integer> ids = new ArrayList<>();
		try {

			String loginId = "user00@example.com";

			User entity = newUser(loginId, encode("oldPasswor"), "firstName",
					"lastName");
			assertEquals(1, mapper0.insertSelective(entity));
			ids.add(entity.getId());

			assertEquals(1,
					mapper.changePassword(loginId, encode("newPassword")));

			User user = mapper0.selectByPrimaryKey(entity.getId());
			assertTrue(passwordEncoder.matches("newPassword",
					user.getPassword()));

		} finally {
			for (Integer id : ids) {
				mapper0.deleteByPrimaryKey(id);
			}
		}
	}

	private String encode(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	private User newUser(String loginId, String password, String firstName,
			String lastName) {
		User entity = new User();
		entity.setLoginId(loginId);
		entity.setPassword(password);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		return entity;
	}

}
