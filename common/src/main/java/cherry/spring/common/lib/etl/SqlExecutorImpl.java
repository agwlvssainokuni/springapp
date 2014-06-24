/*
 * Copyright 2012,2014 agwlvssainokuni
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

package cherry.spring.common.lib.etl;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * SQL実行機能。
 */
public class SqlExecutorImpl implements SqlExecutor {

	private SqlUtil sqlUtil = new SqlUtil();

	/**
	 * SQLを実行する。
	 * 
	 * @param dataSource
	 *            データソース。
	 * @param reader
	 *            SQL文の読込み元。
	 * @param paramMap
	 *            SQLに受渡すパラメタ。
	 * @param continueOnError
	 *            SQL実行エラーで継続するか否か。
	 * @throws IOException
	 *             SQL文の読込みでエラー。
	 */
	@Override
	public void execute(DataSource dataSource, Reader reader,
			Map<String, ?> paramMap, boolean continueOnError)
			throws IOException {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				dataSource);

		String sql;
		while ((sql = sqlUtil.nextSql(reader)) != null) {

			sql = sql.trim();
			if (sql.isEmpty()) {
				continue;
			}

			try {
				template.update(sql, paramMap);
			} catch (DataAccessException ex) {
				if (!continueOnError) {
					throw ex;
				}
			}
		}
	}

}
