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

package cherry.spring.entree.signup;

import static org.joda.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.joda.time.Interval;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import cherry.foundation.type.DeletedFlag;
import cherry.spring.common.db.gen.dto.SignupRequest;
import cherry.spring.common.db.gen.dto.SignupRequestCriteria;
import cherry.spring.common.db.gen.dto.SignupRequestCriteria.Criteria;
import cherry.spring.common.db.gen.mapper.SignupRequestMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class SignupRequestHelperTest {

	@Autowired
	private SignupRequestHelper helper;

	@Autowired
	private SignupRequestMapper mapper;

	@Test
	public void testCreateSignupRequest00() {
		String mailAddr = "req00@example.com";

		SignupRequestCriteria crit = new SignupRequestCriteria();
		Criteria c = crit.createCriteria();
		c.andMailAddrEqualTo(mailAddr);
		c.andDeletedFlgEqualTo(DeletedFlag.NOT_DELETED);
		assertEquals(mapper.selectByExample(crit).size(), 0);

		LocalDateTime before = now();
		sleep(100L);
		assertNotNull(helper.createSignupRequest(mailAddr, UUID.randomUUID().toString(), now()));
		sleep(100L);
		assertNotNull(helper.createSignupRequest(mailAddr, UUID.randomUUID().toString(), now()));
		sleep(100L);
		assertNotNull(helper.createSignupRequest(mailAddr, UUID.randomUUID().toString(), now()));
		sleep(100L);
		LocalDateTime after = now();

		Interval interval = new Interval(before.toDate().getTime(), after.toDate().getTime());
		List<SignupRequest> list = mapper.selectByExample(crit);
		assertEquals(list.size(), 3);
		for (SignupRequest r : list) {
			assertTrue(interval.contains(r.getAppliedAt().toDate().getTime()));
		}
	}

	@Test
	public void testCreateSignupRequest99() {
		String mailAddr = "req99@example.com";

		NamedParameterJdbcOperations op = mock(NamedParameterJdbcOperations.class);
		SqlParameterSource paramSource = any(SqlParameterSource.class);
		KeyHolder keyHolder = any(KeyHolder.class);
		when(op.update(anyString(), paramSource, keyHolder)).thenReturn(0);

		SignupRequestHelperImpl impl = new SignupRequestHelperImpl();
		ReflectionTestUtils.setField(impl, "namedParameterJdbcOperations", op);

		try {
			impl.createSignupRequest(mailAddr, UUID.randomUUID().toString(), now());
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	@Test
	public void testValidateMailAddr00() {
		String mailAddr = "addr00@example.com";
		assertTrue(helper.validateMailAddr(mailAddr, now(), now().minusSeconds(5), 5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		sleep(100L);
		assertTrue(helper.validateMailAddr(mailAddr, now(), now().minusSeconds(5), 5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		sleep(100L);
		assertTrue(helper.validateMailAddr(mailAddr, now(), now().minusSeconds(5), 5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		sleep(100L);
		assertTrue(helper.validateMailAddr(mailAddr, now(), now().minusSeconds(5), 5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		sleep(100L);
		assertTrue(helper.validateMailAddr(mailAddr, now(), now().minusSeconds(5), 5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		sleep(100L);
		assertFalse(helper.validateMailAddr(mailAddr, now(), now().minusSeconds(5), 5));
	}

	@Test
	public void testValidateMailAddr01() {
		String mailAddr = "addr01@example.com";
		assertTrue(helper.validateMailAddr(mailAddr, now(), now().minusSeconds(1), 1));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertFalse(helper.validateMailAddr(mailAddr, now(), now().minusSeconds(1), 1));
	}

	@Test
	public void testValidateMailAddr10() {
		String mailAddr = "addr10@example.com";
		assertTrue(helper.validateMailAddr(mailAddr, now().minusSeconds(1), now().minusSeconds(5), 5));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertFalse(helper.validateMailAddr(mailAddr, now().minusSeconds(1), now().minusSeconds(5), 5));
	}

	@Test
	public void testValidateToken00() {
		String mailAddr = "token00@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(entity));
		assertTrue(helper.validateToken(mailAddr, entity.getToken(), now().minusSeconds(5)));
	}

	@Test
	public void testValidateToken01() {
		String mailAddr = "token00@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(entity));
		assertTrue(helper.validateToken(mailAddr, entity.getToken(), now().minusSeconds(5)));
		sleep(100L);
		assertFalse(helper.validateToken(mailAddr, entity.getToken(), now()));
	}

	@Test
	public void testValidateToken02() {
		String mailAddr = "token02@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(entity));
		assertTrue(helper.validateToken(mailAddr, entity.getToken(), now().minusSeconds(5)));
		assertFalse(helper.validateToken(mailAddr, UUID.randomUUID().toString(), now().minusSeconds(5)));
	}

	@Test
	public void testValidateToken10() {
		String mailAddr = "token10@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertEquals(1, mapper.insertSelective(entity));
		assertTrue(helper.validateToken(mailAddr, entity.getToken(), now().minusSeconds(5)));
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
		assertFalse(helper.validateToken(mailAddr, entity.getToken(), valid));
	}

	@Test
	public void testValidateToken12() {
		String mailAddr = "token12@example.com";
		SignupRequest entity = newRequest(mailAddr);
		assertEquals(1, mapper.insertSelective(entity));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertEquals(1, mapper.insertSelective(newRequest(mailAddr)));
		assertFalse(helper.validateToken(mailAddr, entity.getToken(), now().minusSeconds(5)));
	}

	private SignupRequest newRequest(String mailAddr) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException ex) {
			// NOTHING
		}
		SignupRequest entity = new SignupRequest();
		entity.setMailAddr(mailAddr);
		entity.setToken(UUID.randomUUID().toString());
		return entity;
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ex) {
			// NOTHING
		}
	}

}
