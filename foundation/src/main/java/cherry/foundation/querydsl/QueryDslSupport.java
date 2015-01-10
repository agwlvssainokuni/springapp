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

package cherry.foundation.querydsl;

import java.io.IOException;

import org.springframework.jdbc.core.RowMapper;

import cherry.foundation.etl.Consumer;
import cherry.foundation.etl.Limiter;
import cherry.foundation.etl.LimiterException;
import cherry.goods.paginate.PagedList;

import com.google.common.base.Predicate;
import com.mysema.query.types.Expression;

public interface QueryDslSupport {

	<T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo, long pageSz,
			RowMapper<T> rowMapper, Expression<?>... expressions);

	<T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo, long pageSz,
			Predicate<Long> cancelPredicate, RowMapper<T> rowMapper, Expression<?>... expressions);

	<T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo, long pageSz,
			Expression<T> expression);

	<T> PagedList<T> search(QueryConfigurer commonClause, QueryConfigurer orderByClause, long pageNo, long pageSz,
			Predicate<Long> cancelPredicate, Expression<T> expression);

	long download(QueryConfigurer commonClause, QueryConfigurer orderByClause, Consumer consumer,
			Expression<?>... expressions) throws IOException;

	long download(QueryConfigurer commonClause, QueryConfigurer orderByClause, Consumer consumer, Limiter limiter,
			Expression<?>... expressions) throws LimiterException, IOException;

}
