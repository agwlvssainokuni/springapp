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

package cherry.sqlman.tool.clause;

import static cherry.foundation.querydsl.QueryDslUtil.currentTimestamp;
import static com.google.common.base.Preconditions.checkState;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlClause;
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.db.gen.query.QSqlClause;
import cherry.sqlman.tool.shared.MetadataService;

import com.mysema.query.sql.SQLQueryFactory;

@Service
public class ClauseServiceImpl implements ClauseService {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private BizDateTime bizDateTime;

	private final QSqlClause c = new QSqlClause("c");

	@Transactional(readOnly = true)
	@Override
	public BSqlClause findById(int id) {
		return queryFactory.from(c).where(c.id.eq(id)).uniqueResult(c);
	}

	@Transactional
	@Override
	public int create(BSqlClause record, String ownedBy) {

		LocalDateTime now = bizDateTime.now();

		BSqlMetadata md = new BSqlMetadata();
		md.setSqlType(SqlType.CLAUSE.code());
		md.setName(now.toString());
		md.setDescription(now.toString());
		md.setOwnedBy(ownedBy);
		int id = metadataService.create(md);

		BSqlClause r = new BSqlClause();
		r.setId(id);
		r.setDatabaseName(record.getDatabaseName());
		r.setSelectClause(record.getSelectClause());
		r.setFromClause(record.getFromClause());
		r.setWhereClause(record.getWhereClause());
		r.setGroupByClause(record.getGroupByClause());
		r.setHavingClause(record.getHavingClause());
		r.setOrderByClause(record.getOrderByClause());
		r.setParamMap(record.getParamMap());
		long count = queryFactory.insert(c).populate(r).execute();
		checkState(count == 1L, "failed to create %s: %s", c.getTableName(), r);

		return id;
	}

	@Transactional
	@Override
	public boolean update(BSqlClause record) {
		BSqlClause r = new BSqlClause();
		r.setDatabaseName(record.getDatabaseName());
		r.setSelectClause(record.getSelectClause());
		r.setFromClause(record.getFromClause());
		r.setWhereClause(record.getWhereClause());
		r.setGroupByClause(record.getGroupByClause());
		r.setHavingClause(record.getHavingClause());
		r.setOrderByClause(record.getOrderByClause());
		r.setParamMap(record.getParamMap());
		long count = queryFactory.update(c).populate(r).set(c.lockVersion, c.lockVersion.add(1))
				.set(c.updatedAt, currentTimestamp())
				.where(c.id.eq(record.getId()), c.lockVersion.eq(record.getLockVersion())).execute();
		return count == 1L;
	}

	@Transactional
	@Override
	public boolean delete(int id, int lockVersion) {
		long count = queryFactory.delete(c).where(c.id.eq(id), c.lockVersion.eq(lockVersion)).execute();
		return count == 1L;
	}

}
