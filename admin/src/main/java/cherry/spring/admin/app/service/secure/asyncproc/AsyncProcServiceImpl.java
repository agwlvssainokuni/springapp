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

package cherry.spring.admin.app.service.secure.asyncproc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.db.gen.dto.AsyncProc;
import cherry.spring.common.db.gen.query.QAsyncProc;
import cherry.spring.common.helper.querydsl.SQLQueryConfigurer;
import cherry.spring.common.helper.querydsl.SQLQueryHelper;
import cherry.spring.common.helper.querydsl.SQLQueryResult;
import cherry.spring.common.type.DeletedFlag;
import cherry.spring.common.type.jdbc.RowMapperCreator;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.sql.SQLQuery;

@Component
public class AsyncProcServiceImpl implements AsyncProcService {

	@Autowired
	private SQLQueryHelper sqlQueryHelper;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Transactional
	@Override
	public Result searchAsyncProc(String loginId, int pageNo, int pageSz) {

		QAsyncProc a = new QAsyncProc("a");
		SQLQueryResult<AsyncProc> r = sqlQueryHelper.search(
				createConfigurer(a, loginId), pageNo, pageSz,
				rowMapperCreator.create(AsyncProc.class), a.id, a.launcherId,
				a.name, a.status, a.registeredAt, a.invokedAt, a.startedAt,
				a.finishedAt, a.result, a.updatedAt, a.createdAt,
				a.lockVersion, a.deletedFlg);

		Result result = new Result();
		result.setPageSet(r.getPageSet());
		result.setAsyncProcList(r.getResultList());
		return result;
	}

	private SQLQueryConfigurer createConfigurer(final QAsyncProc a,
			final String loginId) {
		return new SQLQueryConfigurer() {

			@Override
			public SQLQuery configure(SQLQuery query) {

				BooleanBuilder where = new BooleanBuilder();
				where.and(a.launcherId.eq(loginId));
				where.and(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

				query.from(a).where(where);
				return query;
			}

			@Override
			public SQLQuery orderBy(SQLQuery query) {
				query.orderBy(a.id.desc());
				return query;
			}
		};
	}

}
