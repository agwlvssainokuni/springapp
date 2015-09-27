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

package cherry.foundation.mybatis;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.db.gen.dto.VerifySecure;
import cherry.foundation.db.gen.mapper.VerifySecureMapper;
import cherry.foundation.type.SecureString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class SecureStringTypeHandlerTest {

	@Autowired
	private VerifySecureMapper mapper;

	@Test
	public void testSaveAndLoad() {
		String plain = RandomStringUtils.randomAlphanumeric(32);
		VerifySecure record = new VerifySecure();
		record.setStr(SecureString.plainValueOf(plain));

		int count = mapper.insertSelective(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0L)));

		List<VerifySecure> list = mapper.selectByExample(null);
		assertThat(list.isEmpty(), is(false));
		VerifySecure r = list.get(0);
		assertThat(r.getStr().plain(), is(plain));
	}

}
