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

import java.util.UUID;

import org.junit.Test;

import cherry.spring.common.db.gen.dto.SignupRequests;

public class SignupRequestMapperTest {

	@Test
	public void testCreate00() {
		String mailAddr = "addr@example.com";
		SignupRequestMapper mapper = getBean(SignupRequestMapper.class);
		SignupRequests record = newEntity(mailAddr);
		assertNull(record.getId());
		assertEquals(1, mapper.create(record));
		assertNotNull(record.getId());
	}

	@Test
	public void testCanAccept00() {
		String mailAddr = "addr00@example.com";
		SignupRequestMapper mapper = getBean(SignupRequestMapper.class);
		assertTrue(mapper.canAccept(mailAddr, 0, 5, 5));
		assertEquals(1, mapper.create(newEntity(mailAddr)));
		assertTrue(mapper.canAccept(mailAddr, 0, 5, 5));
		assertEquals(1, mapper.create(newEntity(mailAddr)));
		assertTrue(mapper.canAccept(mailAddr, 0, 5, 5));
		assertEquals(1, mapper.create(newEntity(mailAddr)));
		assertTrue(mapper.canAccept(mailAddr, 0, 5, 5));
		assertEquals(1, mapper.create(newEntity(mailAddr)));
		assertTrue(mapper.canAccept(mailAddr, 0, 5, 5));
		assertEquals(1, mapper.create(newEntity(mailAddr)));
		assertFalse(mapper.canAccept(mailAddr, 0, 5, 5));
	}

	@Test
	public void testCanAccept01() {
		String mailAddr = "addr01@example.com";
		SignupRequestMapper mapper = getBean(SignupRequestMapper.class);
		assertTrue(mapper.canAccept(mailAddr, 0, 1, 1));
		assertEquals(1, mapper.create(newEntity(mailAddr)));
		assertFalse(mapper.canAccept(mailAddr, 0, 1, 1));
	}

	@Test
	public void testCanAccept10() {
		String mailAddr = "addr10@example.com";
		SignupRequestMapper mapper = getBean(SignupRequestMapper.class);
		assertTrue(mapper.canAccept(mailAddr, 1, 5, 5));
		assertEquals(1, mapper.create(newEntity(mailAddr)));
		assertFalse(mapper.canAccept(mailAddr, 1, 5, 5));
	}

	private SignupRequests newEntity(String mailAddr) {
		SignupRequests entity = new SignupRequests();
		entity.setMailAddr(mailAddr);
		entity.setToken(UUID.randomUUID().toString());
		return entity;
	}

}
