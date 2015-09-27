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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.db.gen.dto.VerifyFlag;
import cherry.foundation.db.gen.mapper.VerifyFlagMapper;
import cherry.foundation.type.FlagCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class FlagCodeTypeHandlerTest {

	@Autowired
	private VerifyFlagMapper mapper;

	@Test
	public void testSaveAndLoad_FALSE() {
		VerifyFlag record = new VerifyFlag();
		record.setFlagCode(FlagCode.FALSE);

		int count = mapper.insertSelective(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0L)));

		List<VerifyFlag> list = mapper.selectByExample(null);
		assertThat(list.isEmpty(), is(false));
		VerifyFlag r = list.get(0);
		assertThat(r.getFlagCode(), is(FlagCode.FALSE));
	}

	@Test
	public void testSaveAndLoad_TRUE() {
		VerifyFlag record = new VerifyFlag();
		record.setFlagCode(FlagCode.TRUE);

		int count = mapper.insertSelective(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0L)));

		List<VerifyFlag> list = mapper.selectByExample(null);
		assertThat(list.isEmpty(), is(false));
		VerifyFlag r = list.get(0);
		assertThat(r.getFlagCode(), is(FlagCode.TRUE));
	}

}
