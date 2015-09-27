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

package cherry.foundation.type.jdbc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.db.gen.dto.VerifySecure;
import cherry.foundation.type.SecureBigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class SecureBigDecimalTest {

	@Autowired
	private SecureDao dao;

	private SecureRandom random = new SecureRandom();

	@Test
	public void testSaveAndLoad() {
		BigDecimal plain = BigDecimal.valueOf(random.nextDouble());
		VerifySecure record = new VerifySecure();
		record.setBigdec(SecureBigDecimal.plainValueOf(plain));

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = dao.insert(record, keyHolder);

		assertThat(count, is(1));
		assertThat(keyHolder.getKey().intValue(), is(not(0)));

		List<VerifySecure> list = dao.selectAll();
		assertThat(list.isEmpty(), is(false));
		VerifySecure r = list.get(0);
		assertThat(r.getBigdec().plain(), is(plain));
	}

}
