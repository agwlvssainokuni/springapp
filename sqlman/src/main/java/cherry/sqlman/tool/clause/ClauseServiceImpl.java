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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlClause;
import cherry.sqlman.db.gen.query.QSqlClause;
import cherry.sqlman.tool.metadata.MetadataService;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQueryFactory;

@Service
public class ClauseServiceImpl implements ClauseService {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private MetadataService metadataService;

	private final QSqlClause c = new QSqlClause("c");

	@Transactional(readOnly = true)
	@Override
	public SqlClauseForm findById(int id) {

		Tuple tuple = queryFactory
				.from(c)
				.where(c.id.eq(id))
				.uniqueResult(c.databaseName, c.selectClause, c.fromClause, c.whereClause, c.groupByClause,
						c.havingClause, c.orderByClause, c.paramMap, c.lockVersion);
		if (tuple == null) {
			return null;
		}

		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName(tuple.get(c.databaseName));
		form.setSelect(tuple.get(c.selectClause));
		form.setFrom(tuple.get(c.fromClause));
		form.setWhere(tuple.get(c.whereClause));
		form.setGroupBy(tuple.get(c.groupByClause));
		form.setHaving(tuple.get(c.havingClause));
		form.setOrderBy(tuple.get(c.orderByClause));
		form.setParamMap(tuple.get(c.paramMap));
		form.setLockVersion(tuple.get(c.lockVersion));
		return form;
	}

	@Transactional
	@Override
	public int create(SqlClauseForm form, String ownedBy) {

		int id = metadataService.create(SqlType.CLAUSE, ownedBy);

		BSqlClause record = new BSqlClause();
		record.setId(id);
		record.setDatabaseName(form.getDatabaseName());
		record.setSelectClause(form.getSelect());
		record.setFromClause(form.getFrom());
		record.setWhereClause(form.getWhere());
		record.setGroupByClause(form.getGroupBy());
		record.setHavingClause(form.getHaving());
		record.setOrderByClause(form.getOrderBy());
		record.setParamMap(form.getParamMap());
		long count = queryFactory.insert(c).populate(record).execute();
		checkState(count == 1L, "failed to create %s: %s", c.getTableName(), record);

		return id;
	}

	@Transactional
	@Override
	public boolean update(int id, SqlClauseForm form) {
		BSqlClause record = new BSqlClause();
		record.setDatabaseName(form.getDatabaseName());
		record.setSelectClause(form.getSelect());
		record.setFromClause(form.getFrom());
		record.setWhereClause(form.getWhere());
		record.setGroupByClause(form.getGroupBy());
		record.setHavingClause(form.getHaving());
		record.setOrderByClause(form.getOrderBy());
		record.setParamMap(form.getParamMap());
		long count = queryFactory.update(c).populate(record).set(c.lockVersion, c.lockVersion.add(1))
				.set(c.updatedAt, currentTimestamp()).where(c.id.eq(id), c.lockVersion.eq(form.getLockVersion()))
				.execute();
		return count == 1L;
	}

	@Transactional
	@Override
	public boolean delete(int id, int lockVersion) {
		long count = queryFactory.delete(c).where(c.id.eq(id), c.lockVersion.eq(lockVersion)).execute();
		return count == 1L;
	}

}
