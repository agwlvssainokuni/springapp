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

package cherry.sqlman.secure;

import static cherry.foundation.querydsl.QueryDslUtil.currentTimestamp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.querydsl.QueryConfigurer;
import cherry.foundation.querydsl.QueryDslSupport;
import cherry.foundation.type.FlagCode;
import cherry.goods.paginate.PagedList;
import cherry.sqlman.Published;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.db.gen.query.QSqlMetadata;

import com.mysema.query.BooleanBuilder;
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
	public BSqlMetadata findById(int id, String loginId) {
		return queryFactory.from(m)
				.where(m.id.eq(id), m.ownedBy.eq(loginId).or(m.publishedFlg.ne(Published.PRIVATE.code())))
				.uniqueResult(m);
	}

	@Transactional
	@Override
	public int create(BSqlMetadata record) {
		BSqlMetadata r = new BSqlMetadata();
		r.setSqlType(record.getSqlType());
		r.setName(record.getName());
		r.setDescription(record.getDescription());
		r.setOwnedBy(record.getOwnedBy());
		return queryFactory.insert(m).populate(r).executeWithKey(m.id);
	}

	@Transactional
	@Override
	public boolean update(BSqlMetadata record) {
		BSqlMetadata r = new BSqlMetadata();
		r.setName(record.getName());
		r.setDescription(record.getDescription());
		r.setPublishedFlg(record.getPublishedFlg());
		r.setChangedAt(bizDateTime.now());
		long count = queryFactory.update(m).populate(r).set(m.lockVersion, m.lockVersion.add(1))
				.set(m.updatedAt, currentTimestamp())
				.where(m.id.eq(record.getId()), m.lockVersion.eq(record.getLockVersion())).execute();
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
				if (cond.getPublishedFlg().isEmpty() || cond.getPublishedFlg().contains(FlagCode.TRUE)) {
					bb.or(m.publishedFlg.ne(Published.PRIVATE.code()));
				}
				if (cond.getPublishedFlg().isEmpty() || cond.getPublishedFlg().contains(FlagCode.FALSE)) {
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
