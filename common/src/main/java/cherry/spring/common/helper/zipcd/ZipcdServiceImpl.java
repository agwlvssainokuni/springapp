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

import static cherry.foundation.type.DeletedFlag.NOT_DELETED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.db.gen.query.QZipcdMaster;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.QBean;

@Service
public class ZipcdServiceImpl implements ZipcdService {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Transactional(readOnly = true)
	@Cacheable("zipcd")
	@Override
	public List<ZipcdAddress> search(String zipcd) {
		QZipcdMaster a = new QZipcdMaster("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a)
				.where(a.zipcd.eq(zipcd), a.deletedFlg.eq(NOT_DELETED.code()))
				.orderBy(a.id.asc());
		return queryDslJdbcOperations.query(query, new QBean<ZipcdAddress>(
				ZipcdAddress.class, a.cityCd, a.zipcd, a.pref, a.city, a.addr,
				a.prefKana, a.cityKana, a.addrKana));
	}

}
