/*
 *   Copyright 2014 agwlvssainokuni
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package cherry.querytutorial.querydsl;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.DeletedFlag;
import cherry.foundation.type.jdbc.RowMapperCreator;
import cherry.querytutorial.db.gen.dto.PriorityMaster;
import cherry.querytutorial.db.gen.query.QPriorityMaster;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.QBean;
import com.mysema.query.types.QTuple;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class BasicUsageTest {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Test
	public void test全列取得Tuple受け() {
		QPriorityMaster a = new QPriorityMaster("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());
		List<Tuple> list = queryDslJdbcOperations.query(query,
				new QTuple(a.all()));
		assertThat(list, is(not(empty())));
		assertThat(list.size(), is(3));
		for (Tuple tuple : list) {
			assertThat(tuple.get(a.id), is(not(nullValue())));
			assertThat(tuple.get(a.priorityCd), is(not(nullValue())));
			assertThat(tuple.get(a.priorityLabel), is(not(nullValue())));
			assertThat(tuple.get(a.updatedAt), is(not(nullValue())));
			assertThat(tuple.get(a.createdAt), is(not(nullValue())));
			assertThat(tuple.get(a.lockVersion), is(not(nullValue())));
			assertThat(tuple.get(a.deletedFlg), is(not(nullValue())));
		}
	}

	@Test
	public void test列指定Tuple受け() {
		QPriorityMaster a = new QPriorityMaster("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());
		List<Tuple> list = queryDslJdbcOperations.query(query, new QTuple(
				a.priorityCd, a.priorityLabel));
		assertThat(list, is(not(empty())));
		assertThat(list.size(), is(3));
		for (Tuple tuple : list) {
			assertThat(tuple.get(a.id), is(nullValue()));
			assertThat(tuple.get(a.priorityCd), is(not(nullValue())));
			assertThat(tuple.get(a.priorityLabel), is(not(nullValue())));
			assertThat(tuple.get(a.updatedAt), is(nullValue()));
			assertThat(tuple.get(a.createdAt), is(nullValue()));
			assertThat(tuple.get(a.lockVersion), is(nullValue()));
			assertThat(tuple.get(a.deletedFlg), is(nullValue()));
		}
	}

	@Test
	public void test全列取得Bean受け_QBean() {
		QPriorityMaster a = new QPriorityMaster("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());
		List<PriorityMaster> list = queryDslJdbcOperations.query(query,
				new QBean<PriorityMaster>(PriorityMaster.class, a.all()));
		assertThat(list, is(not(empty())));
		assertThat(list.size(), is(3));
		for (PriorityMaster entity : list) {
			assertThat(entity.getId(), is(not(nullValue())));
			assertThat(entity.getPriorityCd(), is(not(nullValue())));
			assertThat(entity.getPriorityLabel(), is(not(nullValue())));
			assertThat(entity.getUpdatedAt(), is(not(nullValue())));
			assertThat(entity.getCreatedAt(), is(not(nullValue())));
			assertThat(entity.getLockVersion(), is(not(nullValue())));
			assertThat(entity.getDeletedFlg(), is(not(nullValue())));
		}
	}

	@Test
	public void test列指定Bean受け_QBean() {
		QPriorityMaster a = new QPriorityMaster("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());
		List<PriorityMaster> list = queryDslJdbcOperations.query(query,
				new QBean<PriorityMaster>(PriorityMaster.class, a.priorityCd,
						a.priorityLabel));
		assertThat(list, is(not(empty())));
		assertThat(list.size(), is(3));
		for (PriorityMaster entity : list) {
			assertThat(entity.getId(), is(nullValue()));
			assertThat(entity.getPriorityCd(), is(not(nullValue())));
			assertThat(entity.getPriorityLabel(), is(not(nullValue())));
			assertThat(entity.getUpdatedAt(), is(nullValue()));
			assertThat(entity.getCreatedAt(), is(nullValue()));
			assertThat(entity.getLockVersion(), is(nullValue()));
			assertThat(entity.getDeletedFlg(), is(nullValue()));
		}
	}

	@Test
	public void test全列取得Bean受け_RowMapper() {
		QPriorityMaster a = new QPriorityMaster("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());
		List<PriorityMaster> list = queryDslJdbcOperations.query(query,
				rowMapperCreator.create(PriorityMaster.class), a.all());
		assertThat(list, is(not(empty())));
		assertThat(list.size(), is(3));
		for (PriorityMaster entity : list) {
			assertThat(entity.getId(), is(not(nullValue())));
			assertThat(entity.getPriorityCd(), is(not(nullValue())));
			assertThat(entity.getPriorityLabel(), is(not(nullValue())));
			assertThat(entity.getUpdatedAt(), is(not(nullValue())));
			assertThat(entity.getCreatedAt(), is(not(nullValue())));
			assertThat(entity.getLockVersion(), is(not(nullValue())));
			assertThat(entity.getDeletedFlg(), is(not(nullValue())));
		}
	}

	@Test
	public void test列指定Bean受け_RowMapper() {
		QPriorityMaster a = new QPriorityMaster("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		query.orderBy(a.id.asc());
		List<PriorityMaster> list = queryDslJdbcOperations.query(query,
				rowMapperCreator.create(PriorityMaster.class), a.priorityCd,
				a.priorityLabel);
		assertThat(list, is(not(empty())));
		assertThat(list.size(), is(3));
		for (PriorityMaster entity : list) {
			assertThat(entity.getId(), is(nullValue()));
			assertThat(entity.getPriorityCd(), is(not(nullValue())));
			assertThat(entity.getPriorityLabel(), is(not(nullValue())));
			assertThat(entity.getUpdatedAt(), is(nullValue()));
			assertThat(entity.getCreatedAt(), is(nullValue()));
			assertThat(entity.getLockVersion(), is(nullValue()));
			assertThat(entity.getDeletedFlg(), is(nullValue()));
		}
	}

}
