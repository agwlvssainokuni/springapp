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
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.admin.app.controller.secure.userman.UsermanSearchForm;
import cherry.spring.common.custom.DeletedFlag;
import cherry.spring.common.custom.jdbc.RowMapperCreator;
import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.db.gen.query.QUser;
import cherry.spring.common.helper.querydsl.SQLQueryConfigurer;
import cherry.spring.common.helper.querydsl.SQLQueryHelper;
import cherry.spring.common.helper.querydsl.SQLQueryResult;
import cherry.spring.common.lib.etl.CsvConsumer;
import cherry.spring.common.lib.etl.NoneLimiter;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Predicate;

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
				List<Predicate> where = new LinkedList<>();
				if (StringUtils.isNoneBlank(form.getLoginId())) {
					where.add(u.loginId.startsWith(form.getLoginId()));
				}
				if (form.getRegisteredFrom() != null) {
					where.add(u.registeredAt.goe(form.getRegisteredFrom()));
				}
				if (form.getRegisteredTo() != null) {
					where.add(u.registeredAt.lt(form.getRegisteredTo()
							.plusSeconds(1)));
				}
				if (StringUtils.isNotBlank(form.getFirstName())) {
					where.add(u.firstName.startsWith(form.getFirstName()));
				}
				if (StringUtils.isNotBlank(form.getLastName())) {
					where.add(u.lastName.startsWith(form.getLastName()));
				}
				where.add(u.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));

				return query.from(u).where(
						where.toArray(new Predicate[where.size()]));
			}

			@Override
			public SQLQuery orderBy(SQLQuery query) {
				return query.orderBy(u.id.asc());
			}
		};
	}

}
