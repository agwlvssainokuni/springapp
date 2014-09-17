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

package cherry.spring.common.helper.querydsl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import cherry.spring.common.lib.etl.Column;
import cherry.spring.common.lib.etl.Consumer;
import cherry.spring.common.lib.etl.Limiter;
import cherry.spring.common.lib.etl.LimiterException;
import cherry.spring.common.lib.paginate.PageSet;
import cherry.spring.common.lib.paginate.Paginator;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.types.Expression;

public class SQLQueryHelperImpl implements SQLQueryHelper {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private Paginator paginator;

	@Override
	public <T> SQLQueryResult<T> search(SQLQueryConfigurer configurer,
			int pageNo, int pageSz, RowMapper<T> rowMapper,
			Expression<?>... projection) {

		SQLQuery sqlQuery = queryDslJdbcOperations.newSqlQuery();
		sqlQuery = configurer.configure(sqlQuery);

		long count = queryDslJdbcOperations.count(sqlQuery);
		PageSet pageSet = paginator.paginate(pageNo, (int) count, pageSz);
		sqlQuery.limit(pageSz).offset(pageSet.getCurrent().getFrom());
		List<T> list = queryDslJdbcOperations.query(sqlQuery, rowMapper,
				projection);

		SQLQueryResult<T> result = new SQLQueryResult<>();
		result.setTotalCount((int) count);
		result.setCount(list.size());
		result.setPageSet(pageSet);
		result.setResultList(list);
		return result;
	}

	@Override
	public int download(SQLQueryConfigurer configurer, final Consumer consumer,
			final Limiter limiter, Expression<?>... projection)
			throws LimiterException, IOException {

		ResultSetExtractor<List<Integer>> extractor = new ResultSetExtractor<List<Integer>>() {
			@Override
			public List<Integer> extractData(ResultSet rs) throws SQLException {
				try {

					ResultSetMetaData metaData = rs.getMetaData();
					Column[] col = new Column[metaData.getColumnCount()];
					for (int i = 1; i <= col.length; i++) {
						col[i - 1] = new Column();
						col[i - 1].setType(metaData.getColumnType(i));
						col[i - 1].setLabel(metaData.getColumnLabel(i));
					}

					consumer.begin(col);

					int count;
					for (count = 0; rs.next(); count++) {

						Object[] record = new Object[col.length];
						for (int i = 1; i <= record.length; i++) {
							record[i - 1] = rs.getObject(i);
						}

						consumer.consume(record);
						limiter.tick();
					}

					consumer.end();
					return Arrays.asList(count);

				} catch (IOException ex) {
					throw new IllegalStateException(ex);
				}
			}
		};

		SQLQuery sqlQuery = queryDslJdbcOperations.newSqlQuery();
		sqlQuery = configurer.configure(sqlQuery);

		limiter.start();
		try {
			List<Integer> count = queryDslJdbcOperations.query(sqlQuery,
					extractor, projection);
			return count.get(0);
		} catch (IllegalStateException ex) {
			throw (IOException) ex.getCause();
		} finally {
			limiter.stop();
		}
	}

}
