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

package cherry.sqlman.tool.load;

import static cherry.foundation.querydsl.QueryDslUtil.currentTimestamp;
import static com.google.common.base.Preconditions.checkState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlLoad;
import cherry.sqlman.db.gen.query.QSqlLoad;
import cherry.sqlman.tool.metadata.MetadataService;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQueryFactory;

@Component
public class LoadServiceImpl implements LoadService {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private BizDateTime bizDateTime;

	private final QSqlLoad l = new QSqlLoad("l");

	@Transactional(readOnly = true)
	@Override
	public SqlLoadForm findById(int id) {

		Tuple tuple = queryFactory.from(l).where(l.id.eq(id)).uniqueResult(l.databaseName, l.query, l.lockVersion);
		if (tuple == null) {
			return null;
		}

		SqlLoadForm form = new SqlLoadForm();
		form.setDatabaseName(tuple.get(l.databaseName));
		form.setSql(tuple.get(l.query));
		form.setLockVersion(tuple.get(l.lockVersion));
		return form;
	}

	@Transactional
	@Override
	public int create(SqlLoadForm form, String ownedBy) {

		int id = metadataService.create(SqlType.LOAD, ownedBy);

		BSqlLoad record = new BSqlLoad();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		long count = queryFactory.insert(l).populate(record).execute();
		checkState(count == 1L, "failed to create %s: %s", l.getTableName(), record);

		return id;
	}

	@Transactional
	@Override
	public boolean update(int id, SqlLoadForm form) {
		BSqlLoad record = new BSqlLoad();
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		long count = queryFactory.update(l).populate(record).set(l.lockVersion, l.lockVersion.add(1))
				.set(l.updatedAt, currentTimestamp()).where(l.id.eq(id), l.lockVersion.eq(form.getLockVersion()))
				.execute();
		return count == 1L;
	}

	@Transactional
	@Override
	public boolean delete(int id, int lockVersion) {
		long count = queryFactory.delete(l).where(l.id.eq(id), l.lockVersion.eq(lockVersion)).execute();
		return count == 1L;
	}

}
