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

package cherry.sqlman.secure.load;

import static cherry.foundation.querydsl.QueryDslUtil.currentTimestamp;
import static com.google.common.base.Preconditions.checkState;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlLoad;
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.db.gen.query.QSqlLoad;
import cherry.sqlman.secure.shared.MetadataService;

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
	public BSqlLoad findById(int id) {
		return queryFactory.from(l).where(l.id.eq(id)).uniqueResult(l);
	}

	@Transactional
	@Override
	public int create(BSqlLoad record, String ownedBy) {

		LocalDateTime now = bizDateTime.now();
		BSqlMetadata md = new BSqlMetadata();
		md.setSqlType(SqlType.LOAD.code());
		md.setName(now.toString());
		md.setDescription(md.toString());
		md.setOwnedBy(ownedBy);
		int id = metadataService.create(md);

		BSqlLoad r = new BSqlLoad();
		r.setId(id);
		r.setDatabaseName(record.getDatabaseName());
		r.setQuery(record.getQuery());
		long count = queryFactory.update(l).populate(r).execute();
		checkState(count == 1L, "failed to create %s: %s", l.getTableName(), r);

		return id;
	}

	@Transactional
	@Override
	public boolean update(BSqlLoad record) {
		BSqlLoad r = new BSqlLoad();
		r.setDatabaseName(record.getDatabaseName());
		r.setQuery(record.getQuery());
		long count = queryFactory.update(l).populate(r).set(l.lockVersion, l.lockVersion.add(1))
				.set(l.updatedAt, currentTimestamp())
				.where(l.id.eq(record.getId()), l.lockVersion.eq(record.getLockVersion())).execute();
		return count == 1L;
	}

	@Transactional
	@Override
	public boolean delete(int id, int lockVersion) {
		long count = queryFactory.delete(l).where(l.id.eq(id), l.lockVersion.eq(lockVersion)).execute();
		return count == 1L;
	}

}
