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

package cherry.sqlman.secure.statement;

import static cherry.foundation.querydsl.QueryDslUtil.currentTimestamp;
import static com.google.common.base.Preconditions.checkState;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.db.gen.query.BSqlStatement;
import cherry.sqlman.db.gen.query.QSqlStatement;
import cherry.sqlman.secure.MetadataService;

import com.mysema.query.sql.SQLQueryFactory;

@Component
public class StatementServiceImpl implements StatementService {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private BizDateTime bizDateTime;

	private final QSqlStatement s = new QSqlStatement("s");

	@Transactional(readOnly = true)
	@Override
	public BSqlStatement findById(int id) {
		return queryFactory.from(s).where(s.id.eq(id)).uniqueResult(s);
	}

	@Transactional
	@Override
	public int create(BSqlStatement record, String ownedBy) {

		LocalDateTime now = bizDateTime.now();
		BSqlMetadata md = new BSqlMetadata();
		md.setSqlType(SqlType.STATEMENT.code());
		md.setName(now.toString());
		md.setDescription(now.toString());
		md.setOwnedBy(ownedBy);
		int id = metadataService.create(md);

		BSqlStatement r = new BSqlStatement();
		r.setId(id);
		r.setDatabaseName(record.getDatabaseName());
		r.setQuery(record.getQuery());
		r.setParamMap(record.getParamMap());
		long count = queryFactory.insert(s).populate(r).execute();
		checkState(count == 1L, "failed to create %s: %s", s.getTableName(), r);

		return id;
	}

	@Transactional
	@Override
	public boolean update(BSqlStatement record) {
		BSqlStatement r = new BSqlStatement();
		r.setDatabaseName(record.getDatabaseName());
		r.setQuery(record.getQuery());
		r.setParamMap(record.getParamMap());
		long count = queryFactory.update(s).populate(r).set(s.lockVersion, s.lockVersion.add(1))
				.set(s.updatedAt, currentTimestamp())
				.where(s.id.eq(record.getId()), s.lockVersion.eq(record.getLockVersion())).execute();
		return count == 1L;
	}

	@Transactional
	@Override
	public boolean delete(int id, int lockVersion) {
		long count = queryFactory.delete(s).where(s.id.eq(id), s.lockVersion.eq(lockVersion)).execute();
		return count == 1L;
	}

}
