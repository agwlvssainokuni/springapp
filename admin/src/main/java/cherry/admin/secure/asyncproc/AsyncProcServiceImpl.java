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

package cherry.admin.secure.asyncproc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.common.db.gen.query.QAsyncProcess;
import cherry.common.db.gen.query.QAsyncProcessFile;
import cherry.common.db.gen.query.QAsyncProcessFileResult;
import cherry.foundation.querydsl.QueryConfigurer;
import cherry.foundation.querydsl.QueryDslSupport;
import cherry.foundation.type.DeletedFlag;
import cherry.goods.paginate.PagedList;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.QTuple;

@Service
public class AsyncProcServiceImpl implements AsyncProcService {

	@Autowired
	private QueryDslSupport queryDslSupport;

	private QAsyncProcess a = new QAsyncProcess("a");
	private QAsyncProcessFile b = new QAsyncProcessFile("b");
	private QAsyncProcessFileResult c = new QAsyncProcessFileResult("c");

	@Transactional
	@Override
	public PagedList<Tuple> searchAsyncProc(String loginId, long pageNo, long pageSz) {
		return queryDslSupport.search(commonClause(loginId), orderByClause(), pageNo, pageSz, new QTuple(getColumns()));
	}

	@Override
	public Expression<?>[] getColumns() {
		return new Expression<?>[] { a.id, a.launchedBy, a.description, a.asyncType, a.asyncStatus, a.registeredAt,
				a.launchedAt, a.startedAt, a.finishedAt, b.originalFilename, b.fileSize, c.totalCount, c.okCount,
				c.ngCount };
	}

	private QueryConfigurer commonClause(final String loginId) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.from(a);
				query.leftJoin(b).on(b.asyncId.eq(a.id), b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				query.leftJoin(c).on(c.asyncId.eq(a.id), c.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				query.where(a.launchedBy.eq(loginId));
				query.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return query;
			}
		};
	}

	private QueryConfigurer orderByClause() {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.orderBy(a.id.desc());
				return query;
			}
		};
	}

}
