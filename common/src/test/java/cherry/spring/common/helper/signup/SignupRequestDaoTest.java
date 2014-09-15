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

import static org.joda.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.spring.common.db.gen.dto.SignupRequest;
import cherry.spring.common.db.gen.mapper.SignupRequestMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class SignupRequestDaoTest {

	@Autowired
	private SignupRequestDao dao;

	@Autowired
	private SignupRequestMapper mapper;

	@Test
	public void testValidateMailAddr00() {
		String mailAddr = "addr00@example.com";
		assertTrue(dao.validateMailAddr(mailAddr, now(), now().minusSeconds(5),
				5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertTrue(dao.validateMailAddr(mailAddr, now(), now().minusSeconds(5),
				5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertTrue(dao.validateMailAddr(mailAddr, now(), now().minusSeconds(5),
				5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertTrue(dao.validateMailAddr(mailAddr, now(), now().minusSeconds(5),
				5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertTrue(dao.validateMailAddr(mailAddr, now(), now().minusSeconds(5),
				5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertFalse(dao.validateMailAddr(mailAddr, now(),
				now().minusSeconds(5), 5));
	}

	@Test
	public void testValidateMailAddr01() {
		String mailAddr = "addr01@example.com";
		assertTrue(dao.validateMailAddr(mailAddr, now(), now().minusSeconds(1),
				1));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertFalse(dao.validateMailAddr(mailAddr, now(),
				now().minusSeconds(1), 1));
	}

	@Test
	public void testValidateMailAddr10() {
		String mailAddr = "addr10@example.com";
		assertTrue(dao.validateMailAddr(mailAddr, now().minusSeconds(1), now()
				.minusSeconds(5), 5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertFalse(dao.validateMailAddr(mailAddr, now().minusSeconds(1), now()
				.minusSeconds(5), 5));
	}

	@Test
	public void testValidateToken00() {
		String mailAddr = "token00@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(entity));
		assertTrue(dao.validateToken(mailAddr, entity.getToken(), now()
				.minusSeconds(5)));
	}

	@Test
	public void testValidateToken01() {
		String mailAddr = "token00@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(entity));
		assertTrue(dao.validateToken(mailAddr, entity.getToken(), now()
				.minusSeconds(5)));
		assertFalse(dao.validateToken(mailAddr, entity.getToken(), now()));
	}

	@Test
	public void testValidateToken02() {
		String mailAddr = "token02@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(entity));
		assertTrue(dao.validateToken(mailAddr, entity.getToken(), now()
				.minusSeconds(5)));
		assertFalse(dao.validateToken(mailAddr, UUID.randomUUID().toString(),
				now().minusSeconds(5)));
	}

	@Test
	public void testValidateToken10() {
		String mailAddr = "token10@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertEquals(1, mapper.insertSelective(entity));
		assertTrue(dao.validateToken(mailAddr, entity.getToken(), now()
				.minusSeconds(5)));
	}

	@Test
	public void testValidateToken11() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime valid = now.minusSeconds(5);
		String mailAddr = "token11@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertEquals(1, mapper.insertSelective(entity));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertFalse(dao.validateToken(mailAddr, entity.getToken(), valid));
	}

	@Test
	public void testValidateToken12() {
		String mailAddr = "token12@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(entity));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertFalse(dao.validateToken(mailAddr, entity.getToken(), now()
				.minusSeconds(5)));
	}

	private SignupRequest newRequest(String mailAddr) {
		SignupRequest entity = new SignupRequest();
		entity.setMailAddr(mailAddr);
		entity.setToken(UUID.randomUUID().toString());
		return entity;
	}

}
