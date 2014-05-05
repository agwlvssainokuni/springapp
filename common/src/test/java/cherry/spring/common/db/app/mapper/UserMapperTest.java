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

import static cherry.spring.common.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import cherry.spring.common.db.gen.dto.Users;
import cherry.spring.common.db.gen.mapper.UsersMapper;

public class UserMapperTest {

	@Test
	public void testCreateUser00() {
		List<Integer> ids = new ArrayList<>();
		try {

			String loginId = "user00@example.com";
			UserMapper mapper = getBean(UserMapper.class);
			Users entity = newUser(loginId, encode("password"), "firstName",
					"lastName");
			assertNull(entity.getId());
			assertEquals(1, mapper.createUser(entity));
			assertNotNull(entity.getId());
			ids.add(entity.getId());

			assertNotNull(getBean(UsersMapper.class).selectByPrimaryKey(
					entity.getId()));

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testChangePassword00() {
		List<Integer> ids = new ArrayList<>();
		try {

			String loginId = "user00@example.com";
			UserMapper mapper = getBean(UserMapper.class);

			assertEquals(0,
					mapper.changePassword(loginId, encode("newPassword")));

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testChangePassword01() {
		List<Integer> ids = new ArrayList<>();
		try {

			String loginId = "user00@example.com";
			UserMapper mapper = getBean(UserMapper.class);
			Users entity = newUser(loginId, encode("oldPasswor"), "firstName",
					"lastName");
			assertEquals(1, mapper.createUser(entity));
			ids.add(entity.getId());

			assertEquals(1,
					mapper.changePassword(loginId, encode("newPassword")));

			Users user = getBean(UsersMapper.class).selectByPrimaryKey(
					entity.getId());
			assertTrue(getBean(PasswordEncoder.class).matches("newPassword",
					user.getPassword()));

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers00() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			UserCondition cond = new UserCondition();
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertTrue(list.isEmpty());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers01() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(10, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers02() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setLoginId("%");
			cond.setRegisteredFrom(LocalDateTime.now().minusMinutes(1));
			cond.setRegisteredTo(LocalDateTime.now());
			cond.setFirstName("%");
			cond.setLastName("%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(10, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers10() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setLoginId("user%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(10, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers11() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setLoginId("user1%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(1, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers12() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setLoginId("users%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertTrue(list.isEmpty());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers20() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setRegisteredFrom(LocalDateTime.now().minusMinutes(1));
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(10, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers21() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setRegisteredFrom(LocalDateTime.now().plusMinutes(1));
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertTrue(list.isEmpty());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers30() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setRegisteredTo(LocalDateTime.now().plusMinutes(1));
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(10, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers31() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setRegisteredTo(LocalDateTime.now().minusMinutes(1));
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertTrue(list.isEmpty());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers40() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setFirstName("first%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(10, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers41() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setFirstName("first1%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(1, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers42() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setFirstName("firsts%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertTrue(list.isEmpty());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers50() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setLastName("last%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(10, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers51() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setLastName("last1%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertFalse(list.isEmpty());
			assertEquals(1, list.size());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	@Test
	public void testSearchUsers52() {
		List<Integer> ids = new ArrayList<>();
		try {

			UserMapper mapper = getBean(UserMapper.class);
			for (int i = 0; i < 10; i++) {
				Users entity = newUser("user" + i + "@example.com", "password",
						"first" + i + "name", "last" + i + "name");
				mapper.createUser(entity);
				ids.add(entity.getId());
			}

			UserCondition cond = new UserCondition();
			cond.setLastName("lasts%");
			List<Users> list = mapper.searchUsers(cond, 100, 0);
			assertNotNull(list);
			assertTrue(list.isEmpty());

		} finally {
			for (Integer id : ids) {
				getBean(UsersMapper.class).deleteByPrimaryKey(id);
			}
		}
	}

	private String encode(String rawPassword) {
		return getBean(PasswordEncoder.class).encode(rawPassword);
	}

	private Users newUser(String loginId, String password, String firstName,
			String lastName) {
		Users entity = new Users();
		entity.setLoginId(loginId);
		entity.setPassword(password);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		return entity;
	}

}
