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

package cherry.sqlman.tool.metadata;

import static cherry.foundation.querydsl.QueryDslUtil.currentTimestamp;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.querydsl.QueryConfigurer;
import cherry.foundation.querydsl.QueryDslSupport;
import cherry.goods.paginate.PagedList;
import cherry.sqlman.Published;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.db.gen.query.QSqlMetadata;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;

@Service
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private QueryDslSupport queryDslSupport;

	@Autowired
	private BizDateTime bizDateTime;

	private final QSqlMetadata m = new QSqlMetadata("m");

	@Transactional
	@Override
	public SqlMetadataForm findById(int id, String loginId) {

		Tuple tuple = queryFactory.from(m)
				.where(m.id.eq(id), m.ownedBy.eq(loginId).or(m.publishedFlg.ne(Published.PRIVATE.code())))
				.uniqueResult(m.name, m.description, m.publishedFlg, m.ownedBy, m.lockVersion);
		if (tuple == null) {
			return null;
		}

		SqlMetadataForm form = new SqlMetadataForm();
		form.setName(tuple.get(m.name));
		form.setDescription(tuple.get(m.description));
		form.setPublishedFlg(Published.valueOf(tuple.get(m.publishedFlg)).isPublished());
		form.setOwnedBy(loginId);
		form.setLockVersion(tuple.get(m.lockVersion));
		return form;
	}

	@Transactional
	@Override
	public int create(SqlType sqlType, String ownedBy) {
		LocalDateTime now = bizDateTime.now();
		BSqlMetadata record = new BSqlMetadata();
		record.setSqlType(sqlType.code());
		record.setName(now.toString());
		record.setDescription(now.toString());
		record.setOwnedBy(ownedBy);
		Integer id = queryFactory.insert(m).populate(record).executeWithKey(m.id);
		checkState(id != null, "failed to create %s: %s", m.getTableName(), record);
		return id.intValue();
	}

	@Transactional
	@Override
	public boolean update(int id, SqlMetadataForm form) {
		BSqlMetadata record = new BSqlMetadata();
		record.setName(form.getName());
		record.setDescription(form.getDescription());
		record.setPublishedFlg(Published.valueOf(form.isPublishedFlg()).code());
		record.setChangedAt(bizDateTime.now());
		long count = queryFactory.update(m).populate(record).set(m.lockVersion, m.lockVersion.add(1))
				.set(m.updatedAt, currentTimestamp()).where(m.id.eq(id), m.lockVersion.eq(form.getLockVersion()))
				.execute();
		return count == 1L;
	}

	@Transactional
	@Override
	public boolean delete(int id, int lockVersion) {
		long count = queryFactory.delete(m).where(m.id.eq(id), m.lockVersion.eq(lockVersion)).execute();
		return count == 1L;
	}

	@Transactional
	@Override
	public PagedList<BSqlMetadata> search(MetadataCondition cond, long pageNo, long pageSz) {
		return queryDslSupport.search(commonClause(m, cond), orderByClause(m, cond), pageNo, pageSz, m);
	}

	private QueryConfigurer commonClause(final QSqlMetadata m, final MetadataCondition cond) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.from(m);

				if (StringUtils.isNotEmpty(cond.getName())) {
					query.where(m.name.startsWith(cond.getName()));
				}

				if (cond.getRegisteredFrom() != null) {
					query.where(m.registeredAt.goe(cond.getRegisteredFrom()));
				}
				if (cond.getRegisteredTo() != null) {
					query.where(m.registeredAt.lt(cond.getRegisteredTo()));
				}

				BooleanBuilder bb = new BooleanBuilder();
				if (cond.getPublished().isEmpty() || cond.getPublished().contains(Published.PUBLIC)) {
					bb.or(m.publishedFlg.ne(Published.PRIVATE.code()));
				}
				if (cond.getPublished().isEmpty() || cond.getPublished().contains(Published.PRIVATE)) {
					bb.or(m.publishedFlg.eq(Published.PRIVATE.code()).and(m.ownedBy.eq(cond.getLoginId())));
				}
				query.where(bb);

				if (!cond.getSqlType().isEmpty()) {
					List<String> code = new ArrayList<>();
					for (SqlType c : cond.getSqlType()) {
						code.add(c.code());
					}
					query.where(m.sqlType.in(code));
				}

				return query;
			}
		};
	}

	private QueryConfigurer orderByClause(final QSqlMetadata m, final MetadataCondition cond) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				return query.orderBy(m.id.asc());
			}
		};
	}

}
