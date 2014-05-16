/*
 *   Copyright 2012,2014 agwlvssainokuni
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package cherry.spring.common.lib.data;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.lib.data.limiter.Limiter;
import cherry.spring.common.lib.data.limiter.LimiterException;
import cherry.spring.common.lib.data.limiter.NoneLimiter;

/**
 * データ抽出機能.
 */
public class DataExtractorImpl implements DataExtractor {

	/** SQL実行. */
	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	/** 抽出用SQL. */
	private String sql;

	/**
	 * 抽出用SQL を設定する.
	 * 
	 * @param sql
	 *            抽出用SQL
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * データを抽出する.
	 * 
	 * @param consumer
	 *            データの格納先
	 * @param paramMap
	 *            データ抽出時のパラメタ
	 * @return 格納したデータの件数
	 * @throws IOException
	 *             データ格納エラー
	 */
	@Transactional(rollbackFor = { DataAccessException.class, IOException.class })
	@Override
	public long extract(final DataConsumer consumer, Map<String, ?> paramMap)
			throws IOException {
		return extract(consumer, paramMap, new NoneLimiter());
	}

	/**
	 * データを抽出する.
	 * 
	 * @param consumer
	 *            データの格納先
	 * @param paramMap
	 *            データ抽出時のパラメタ
	 * @return 格納したデータの件数
	 * @throws LimiterException
	 *             データ抽出制限超過
	 * @throws IOException
	 *             データ格納エラー
	 */
	@Transactional(rollbackFor = { DataAccessException.class,
			LimiterException.class, IOException.class })
	@Override
	public long extract(final DataConsumer consumer, Map<String, ?> paramMap,
			final Limiter limiter) throws LimiterException, IOException {

		ResultSetExtractor<Long> extractor = new ResultSetExtractor<Long>() {
			@Override
			public Long extractData(ResultSet rs) throws SQLException {
				try {

					ResultSetMetaData metaData = rs.getMetaData();
					DataConsumer.Column[] col = new DataConsumer.Column[metaData
							.getColumnCount()];
					for (int i = 1; i <= col.length; i++) {
						col[i - 1] = new DataConsumer.Column();
						col[i - 1].setType(metaData.getColumnType(i));
						col[i - 1].setLabel(metaData.getColumnLabel(i));
					}

					consumer.begin(col);

					long count;
					for (count = 0L; rs.next(); count++) {

						Object[] record = new Object[col.length];
						for (int i = 1; i <= record.length; i++) {
							record[i - 1] = rs.getObject(i);
						}

						consumer.consume(record);
						limiter.tick();
					}

					consumer.end();
					return count;

				} catch (IOException ex) {
					throw new IllegalStateException(ex);
				}
			}
		};

		limiter.start();
		try {
			return namedParameterJdbcOperations.query(sql, paramMap, extractor);
		} catch (IllegalStateException ex) {
			throw (IOException) ex.getCause();
		} finally {
			limiter.stop();
		}
	}

}
