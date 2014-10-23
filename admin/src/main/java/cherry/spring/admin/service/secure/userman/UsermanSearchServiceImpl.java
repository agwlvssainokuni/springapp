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

package cherry.spring.admin.service.secure.userman;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.goods.paginate.PagedList;
import cherry.goods.util.LocalDateTimeUtil;
import cherry.spring.admin.controller.secure.userman.UsermanSearchForm;
import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.db.gen.query.QUser;
import cherry.spring.fwcore.etl.CsvConsumer;
import cherry.spring.fwcore.etl.NoneLimiter;
import cherry.spring.fwcore.querydsl.QueryConfigurer;
import cherry.spring.fwcore.querydsl.SQLQueryHelper;
import cherry.spring.fwcore.type.DeletedFlag;
import cherry.spring.fwcore.type.jdbc.RowMapperCreator;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;

@Service
public class UsermanSearchServiceImpl implements UsermanSearchService {

	@Autowired
	private SQLQueryHelper sqlQueryHelper;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Transactional
	@Override
	public PagedList<User> searchUsers(UsermanSearchForm form, long pageNo,
			long pageSz) {
		QUser u = new QUser("u");
		return sqlQueryHelper.search(commonClause(u, form),
				orderByClause(u, form), pageNo, pageSz,
				rowMapperCreator.create(User.class), getColumns(u));
	}

	@Transactional
	@Override
	public long exportUsers(Writer writer, UsermanSearchForm form) {
		try {
			QUser u = new QUser("u");
			return sqlQueryHelper.download(commonClause(u, form),
					orderByClause(u, form), new CsvConsumer(writer, true),
					new NoneLimiter(), getColumns(u));
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private Expression<?>[] getColumns(QUser u) {
		return new Expression<?>[] { u.id, u.loginId, u.registeredAt,
				u.firstName, u.lastName, u.updatedAt, u.createdAt,
				u.lockVersion, u.deletedFlg };
	}

	private QueryConfigurer commonClause(final QUser u,
			final UsermanSearchForm form) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.from(u);

				if (StringUtils.isNoneBlank(form.getLoginId())) {
					query.where(u.loginId.startsWith(form.getLoginId()));
				}
				if (form.getRegisteredFrom() != null) {
					query.where(u.registeredAt.goe(LocalDateTimeUtil
							.rangeFrom(form.getRegisteredFrom())));
				}
				if (form.getRegisteredTo() != null) {
					query.where(u.registeredAt.lt(LocalDateTimeUtil
							.rangeTo(form.getRegisteredTo())));
				}
				if (StringUtils.isNotBlank(form.getFirstName())) {
					query.where(u.firstName.startsWith(form.getFirstName()));
				}
				if (StringUtils.isNotBlank(form.getLastName())) {
					query.where(u.lastName.startsWith(form.getLastName()));
				}

				query.where(u.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return query;
			}
		};
	}

	private QueryConfigurer orderByClause(final QUser u,
			final UsermanSearchForm form) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				return query.orderBy(u.id.asc());
			}
		};
	}

}
