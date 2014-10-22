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

package cherry.spring.admin.service.secure.asyncproc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.goods.paginate.PagedList;
import cherry.spring.common.db.gen.dto.AsyncProc;
import cherry.spring.common.db.gen.query.QAsyncProc;
import cherry.spring.common.helper.querydsl.QueryConfigurer;
import cherry.spring.common.helper.querydsl.SQLQueryHelper;
import cherry.spring.common.type.DeletedFlag;
import cherry.spring.common.type.jdbc.RowMapperCreator;

import com.mysema.query.sql.SQLQuery;

@Service
public class AsyncProcServiceImpl implements AsyncProcService {

	@Autowired
	private SQLQueryHelper sqlQueryHelper;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Transactional
	@Override
	public PagedList<AsyncProc> searchAsyncProc(String loginId, long pageNo,
			long pageSz) {
		QAsyncProc a = new QAsyncProc("a");
		return sqlQueryHelper.search(commonClause(a, loginId),
				orderByClause(a, loginId), pageNo, pageSz,
				rowMapperCreator.create(AsyncProc.class), a.id, a.launcherId,
				a.name, a.status, a.registeredAt, a.invokedAt, a.startedAt,
				a.finishedAt, a.result, a.updatedAt, a.createdAt,
				a.lockVersion, a.deletedFlg);
	}

	private QueryConfigurer commonClause(final QAsyncProc a,
			final String loginId) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.from(a);
				query.where(a.launcherId.eq(loginId));
				query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return query;
			}
		};
	}

	private QueryConfigurer orderByClause(final QAsyncProc a,
			final String loginId) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.orderBy(a.id.desc());
				return query;
			}
		};
	}

}
