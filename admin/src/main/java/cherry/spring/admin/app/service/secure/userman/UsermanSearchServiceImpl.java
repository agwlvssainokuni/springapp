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

package cherry.spring.admin.app.service.secure.userman;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.admin.app.controller.secure.userman.UsermanSearchForm;
import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.db.gen.query.QUser;
import cherry.spring.common.helper.querydsl.SQLQueryConfigurer;
import cherry.spring.common.helper.querydsl.SQLQueryHelper;
import cherry.spring.common.helper.querydsl.SQLQueryResult;
import cherry.spring.common.lib.etl.CsvConsumer;
import cherry.spring.common.lib.etl.NoneLimiter;
import cherry.spring.common.lib.util.LocalDateTimeUtil;
import cherry.spring.common.type.DeletedFlag;
import cherry.spring.common.type.jdbc.RowMapperCreator;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;

@Component
public class UsermanSearchServiceImpl implements UsermanSearchService {

	@Autowired
	private SQLQueryHelper sqlQueryHelper;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Transactional
	@Override
	public Result searchUsers(UsermanSearchForm form, int pageNo, int pageSz) {

		QUser u = new QUser("u");
		SQLQueryResult<User> r = sqlQueryHelper.search(getConfigurer(u, form),
				pageNo, pageSz, rowMapperCreator.create(User.class),
				getColumns(u));

		Result result = new Result();
		result.setPageSet(r.getPageSet());
		result.setUsersList(r.getResultList());
		return result;
	}

	@Transactional
	@Override
	public int exportUsers(Writer writer, UsermanSearchForm form) {
		try {

			QUser u = new QUser("u");
			return sqlQueryHelper.download(getConfigurer(u, form),
					new CsvConsumer(writer, true), new NoneLimiter(),
					getColumns(u));
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private Expression<?>[] getColumns(QUser u) {
		return new Expression<?>[] { u.id, u.loginId, u.registeredAt,
				u.firstName, u.lastName, u.updatedAt, u.createdAt,
				u.lockVersion, u.deletedFlg };
	}

	private SQLQueryConfigurer getConfigurer(final QUser u,
			final UsermanSearchForm form) {
		return new SQLQueryConfigurer() {

			@Override
			public SQLQuery configure(SQLQuery query) {

				BooleanBuilder where = new BooleanBuilder();
				if (StringUtils.isNoneBlank(form.getLoginId())) {
					where.and(u.loginId.startsWith(form.getLoginId()));
				}
				if (form.getRegisteredFrom() != null) {
					where.and(u.registeredAt.goe(LocalDateTimeUtil
							.rangeFrom(form.getRegisteredFrom())));
				}
				if (form.getRegisteredTo() != null) {
					where.and(u.registeredAt.lt(LocalDateTimeUtil.rangeTo(form
							.getRegisteredTo())));
				}
				if (StringUtils.isNotBlank(form.getFirstName())) {
					where.and(u.firstName.startsWith(form.getFirstName()));
				}
				if (StringUtils.isNotBlank(form.getLastName())) {
					where.and(u.lastName.startsWith(form.getLastName()));
				}
				where.and(u.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

				return query.from(u).where(where);
			}

			@Override
			public SQLQuery orderBy(SQLQuery query) {
				return query.orderBy(u.id.asc());
			}
		};
	}

}
