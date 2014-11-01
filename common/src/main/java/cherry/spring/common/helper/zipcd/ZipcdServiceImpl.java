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

package cherry.spring.common.helper.zipcd;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.db.gen.query.QZipcdMaster;
import cherry.spring.fwcore.type.DeletedFlag;
import cherry.spring.fwcore.type.jdbc.RowMapperCreator;

import com.mysema.query.sql.SQLQuery;

public class ZipcdServiceImpl implements ZipcdService, InitializingBean {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	private RowMapper<ZipcdAddress> rowMapper;

	@Override
	public void afterPropertiesSet() {
		rowMapper = rowMapperCreator.create(ZipcdAddress.class);
	}

	@Transactional(readOnly = true)
	@Cacheable
	@Override
	public List<ZipcdAddress> search(String zipcd) {
		QZipcdMaster z = new QZipcdMaster("z");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(z).where(z.zipcd.eq(zipcd))
				.where(z.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()))
				.orderBy(z.id.asc());
		return queryDslJdbcOperations.query(query, rowMapper, z.cityCd,
				z.zipcd, z.pref, z.city, z.addr, z.prefKana, z.cityKana,
				z.addrKana);
	}

}
