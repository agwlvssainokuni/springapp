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

package cherry.sqlman.tool.statement;

import static cherry.foundation.querydsl.QueryDslUtil.currentTimestamp;
import static com.google.common.base.Preconditions.checkState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlStatement;
import cherry.sqlman.db.gen.query.QSqlStatement;
import cherry.sqlman.tool.metadata.MetadataService;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQueryFactory;

@Component
public class StatementServiceImpl implements StatementService {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private MetadataService metadataService;

	private final QSqlStatement s = new QSqlStatement("s");

	@Transactional(readOnly = true)
	@Override
	public SqlStatementForm findById(int id) {

		Tuple tuple = queryFactory.from(s).where(s.id.eq(id))
				.uniqueResult(s.databaseName, s.query, s.paramMap, s.lockVersion);
		if (tuple == null) {
			return null;
		}

		SqlStatementForm form = new SqlStatementForm();
		form.setDatabaseName(tuple.get(s.databaseName));
		form.setSql(tuple.get(s.query));
		form.setParamMap(tuple.get(s.paramMap));
		form.setLockVersion(tuple.get(s.lockVersion));
		return form;
	}

	@Transactional
	@Override
	public int create(SqlStatementForm form, String ownedBy) {

		int id = metadataService.create(SqlType.STATEMENT, ownedBy);

		BSqlStatement record = new BSqlStatement();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setParamMap(form.getParamMap());
		long count = queryFactory.insert(s).populate(record).execute();
		checkState(count == 1L, "failed to create %s: %s", s.getTableName(), record);

		return id;
	}

	@Transactional
	@Override
	public boolean update(int id, SqlStatementForm form) {
		BSqlStatement record = new BSqlStatement();
		record.setDatabaseName(form.getDatabaseName());
		record.setQuery(form.getSql());
		record.setParamMap(form.getParamMap());
		long count = queryFactory.update(s).populate(record).set(s.lockVersion, s.lockVersion.add(1))
				.set(s.updatedAt, currentTimestamp()).where(s.id.eq(id), s.lockVersion.eq(form.getLockVersion()))
				.execute();
		return count == 1L;
	}

	@Transactional
	@Override
	public boolean delete(int id, int lockVersion) {
		if (!metadataService.delete(id, lockVersion)) {
			return false;
		}
		long count = queryFactory.delete(s).where(s.id.eq(id)).execute();
		return count == 1L;
	}

}
