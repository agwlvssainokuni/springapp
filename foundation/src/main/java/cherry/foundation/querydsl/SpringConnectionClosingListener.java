/*
 * Copyright 2015 agwlvssainokuni
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

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.mysema.commons.lang.Pair;
import com.mysema.query.QueryMetadata;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.SQLDetailedListener;
import com.mysema.query.sql.SQLListenerContext;
import com.mysema.query.sql.dml.SQLInsertBatch;
import com.mysema.query.sql.dml.SQLMergeBatch;
import com.mysema.query.sql.dml.SQLUpdateBatch;
import com.mysema.query.types.Expression;
import com.mysema.query.types.Path;
import com.mysema.query.types.SubQueryExpression;

public class SpringConnectionClosingListener implements SQLDetailedListener {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void notifyQuery(QueryMetadata md) {
		// 何もしない
	}

	@Override
	public void notifyDelete(RelationalPath<?> entity, QueryMetadata md) {
		// 何もしない
	}

	@Override
	public void notifyDeletes(RelationalPath<?> entity, List<QueryMetadata> batches) {
		// 何もしない
	}

	@Override
	public void notifyMerge(RelationalPath<?> entity, QueryMetadata md, List<Path<?>> keys, List<Path<?>> columns,
			List<Expression<?>> values, SubQueryExpression<?> subQuery) {
		// 何もしない
	}

	@Override
	public void notifyMerges(RelationalPath<?> entity, QueryMetadata md, List<SQLMergeBatch> batches) {
		// 何もしない
	}

	@Override
	public void notifyInsert(RelationalPath<?> entity, QueryMetadata md, List<Path<?>> columns,
			List<Expression<?>> values, SubQueryExpression<?> subQuery) {
		// 何もしない
	}

	@Override
	public void notifyInserts(RelationalPath<?> entity, QueryMetadata md, List<SQLInsertBatch> batches) {
		// 何もしない
	}

	@Override
	public void notifyUpdate(RelationalPath<?> entity, QueryMetadata md, List<Pair<Path<?>, Expression<?>>> updates) {
		// 何もしない
	}

	@Override
	public void notifyUpdates(RelationalPath<?> entity, List<SQLUpdateBatch> batches) {
		// 何もしない
	}

	@Override
	public void start(SQLListenerContext context) {
		// 何もしない
	}

	@Override
	public void preRender(SQLListenerContext context) {
		// 何もしない
	}

	@Override
	public void rendered(SQLListenerContext context) {
		// 何もしない
	}

	@Override
	public void prePrepare(SQLListenerContext context) {
		// 何もしない
	}

	@Override
	public void prepared(SQLListenerContext context) {
		// 何もしない
	}

	@Override
	public void preExecute(SQLListenerContext context) {
		// 何もしない
	}

	@Override
	public void executed(SQLListenerContext context) {
		// 何もしない
	}

	@Override
	public void exception(SQLListenerContext context) {
		// 何もしない
	}

	@Override
	public void end(SQLListenerContext context) {
		DataSourceUtils.releaseConnection(context.getConnection(), dataSource);
	}

}
