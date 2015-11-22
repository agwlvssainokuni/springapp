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

package cherry.sqlman.tool.search;

import static cherry.foundation.querydsl.QueryDslUtil.tupleToMap;
import static cherry.goods.util.LocalDateTimeUtil.rangeFrom;
import static cherry.goods.util.LocalDateTimeUtil.rangeTo;
import static com.mysema.query.sql.ColumnMetadata.getColumnMetadata;
import static com.mysema.query.support.Expressions.cases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.querydsl.QueryConfigurer;
import cherry.foundation.querydsl.QueryDslSupport;
import cherry.goods.paginate.PagedList;
import cherry.sqlman.Published;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.QSqlClause;
import cherry.sqlman.db.gen.query.QSqlLoad;
import cherry.sqlman.db.gen.query.QSqlMetadata;
import cherry.sqlman.db.gen.query.QSqlStatement;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Path;
import com.mysema.query.types.QTuple;
import com.mysema.query.types.expr.StringExpression;

@Service
public class SearchServiceImpl implements SearchService, InitializingBean {

	@Autowired
	private QueryDslSupport queryDslSupport;

	private final QSqlMetadata m = new QSqlMetadata("m");

	private Expression<?>[] expressions;

	@Override
	public void afterPropertiesSet() {

		QSqlClause c = new QSqlClause("c");
		QSqlStatement s = new QSqlStatement("s");
		QSqlLoad l = new QSqlLoad("l");

		StringExpression databaseName = cases() //
				.when(m.sqlType.eq(SqlType.CLAUSE.code())) //
				.then(new SQLSubQuery().from(c).where(c.id.eq(m.id)).unique(c.databaseName)) //
				.when(m.sqlType.eq(SqlType.STATEMENT.code())) //
				.then(new SQLSubQuery().from(s).where(s.id.eq(m.id)).unique(s.databaseName)) //
				.when(m.sqlType.eq(SqlType.LOAD.code())) //
				.then(new SQLSubQuery().from(l).where(l.id.eq(m.id)).unique(l.databaseName)) //
				.otherwise(StringUtils.EMPTY).as(getColumnMetadata(c.databaseName).getName());

		List<Expression<?>> list = new ArrayList<>(m.all().length + 1);
		for (Path<?> e : m.all()) {
			list.add(e);
		}
		list.add(databaseName);

		expressions = list.toArray(new Expression<?>[list.size()]);
	}

	@Transactional
	@Override
	public PagedList<Map<String, ?>> search(SqlSearchForm form, String loginId) {
		return tupleToMap(queryDslSupport.search(commonClause(m, form, loginId), orderByClause(m, form, loginId),
				form.getPno(), form.getPsz(), new QTuple(expressions)), expressions);
	}

	private QueryConfigurer commonClause(final QSqlMetadata m, final SqlSearchForm form, final String loginId) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.from(m);

				if (StringUtils.isNotEmpty(form.getName())) {
					query.where(m.name.startsWith(form.getName()));
				}

				if (form.getRegisteredFromDt() != null) {
					query.where(m.registeredAt.goe(rangeFrom(form.getRegisteredFromDt(), form.getRegisteredFromTm())));
				}
				if (form.getRegisteredToDt() != null) {
					query.where(m.registeredAt.lt(rangeTo(form.getRegisteredToDt(), form.getRegisteredToTm())));
				}

				query.where(m.ownedBy.eq(loginId).or(m.publishedFlg.ne(Published.PRIVATE.code())));
				if (!form.getPublished().isEmpty()) {
					List<Integer> code = new ArrayList<>();
					for (Published p : form.getPublished()) {
						code.add(p.code());
					}
					query.where(m.publishedFlg.in(code));
				}

				if (!form.getSqlType().isEmpty()) {
					List<String> code = new ArrayList<>();
					for (SqlType c : form.getSqlType()) {
						code.add(c.code());
					}
					query.where(m.sqlType.in(code));
				}

				return query;
			}
		};
	}

	private QueryConfigurer orderByClause(final QSqlMetadata m, final SqlSearchForm form, final String loginId) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				return query.orderBy(m.id.asc());
			}
		};
	}

}
