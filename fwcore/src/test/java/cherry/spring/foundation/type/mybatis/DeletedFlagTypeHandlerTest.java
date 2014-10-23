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

package cherry.spring.foundation.type.mybatis;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.spring.foundation.type.db.dto.ConversionTest;
import cherry.spring.foundation.type.db.mapper.ConversionTestMapper;
import cherry.spring.fwcore.type.DeletedFlag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class DeletedFlagTypeHandlerTest {

	@Autowired
	private ConversionTestMapper mapper;

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@After
	public void after() {
		namedParameterJdbcOperations.update("DELETE FROM conversion_test",
				new HashMap<String, Object>());
	}

	@Test
	public void testSaveAndLoad_NOT_DELETED() {
		ConversionTest record = new ConversionTest();
		record.setDeletedFlg(DeletedFlag.NOT_DELETED);

		int count = mapper.insert(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0)));

		List<ConversionTest> list = mapper.selectAll();
		assertThat(list.isEmpty(), is(false));
		ConversionTest r = list.get(0);
		assertThat(r.getDeletedFlg(), is(DeletedFlag.NOT_DELETED));
	}

	@Test
	public void testSaveAndLoad_1() {
		ConversionTest record = new ConversionTest();
		record.setDeletedFlg(new DeletedFlag(1));

		int count = mapper.insert(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0)));

		List<ConversionTest> list = mapper.selectAll();
		assertThat(list.isEmpty(), is(false));
		ConversionTest r = list.get(0);
		assertThat(r.getDeletedFlg(), is(new DeletedFlag(1)));
	}

	@Test
	public void testSaveAndLoad_100() {
		ConversionTest record = new ConversionTest();
		record.setDeletedFlg(new DeletedFlag(100));

		int count = mapper.insert(record);
		assertThat(count, is(1));
		assertThat(record.getId(), is(not(0)));

		List<ConversionTest> list = mapper.selectAll();
		assertThat(list.isEmpty(), is(false));
		ConversionTest r = list.get(0);
		assertThat(r.getDeletedFlg(), is(new DeletedFlag(100)));
	}

}
