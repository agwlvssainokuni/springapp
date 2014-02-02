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

package cherry.springapp.common.lib.db;

import static java.text.MessageFormat.format;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * データ取込み機能.
 */
public class DataLoaderImpl extends NamedParameterJdbcDaoSupport implements
		DataLoader {

	/** ログ出力. */
	private final Log log = LogFactory.getLog(getClass());

	/** 取込み用SQL. */
	private String sql;

	/** 読み込み件数. */
	private int batchCount;

	/** エラーを許容する件数. */
	private int allowedFailCount;

	/**
	 * 取込み用SQL を設定する.
	 * 
	 * @param sql
	 *            取込み用SQL
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * 読込み件数 を設定する.
	 * 
	 * @param batchCount
	 *            読込み件数
	 */
	public void setBatchCount(int batchCount) {
		this.batchCount = batchCount;
	}

	/**
	 * エラーを許容する件数 を設定する.
	 * 
	 * @param allowedFailCount
	 *            エラーを許容する件数
	 */
	public void setAllowedFailCount(int allowedFailCount) {
		this.allowedFailCount = allowedFailCount;
	}

	/**
	 * データを取込む.
	 * 
	 * @param provider
	 *            データの取得元
	 * @return 取込みの結果
	 * @throws IOException
	 *             データ取得でエラー
	 */
	@Transactional(rollbackFor = { DataAccessException.class, IOException.class })
	@Override
	public Result load(DataProvider provider) throws IOException {

		int totalCount = 0;
		int successCount = 0;
		int failedCount = 0;
		while (provider.hasNext()) {

			Map<String, ?> data = provider.nextData();
			totalCount += 1;

			try {
				getNamedParameterJdbcTemplate().update(sql, data);
				successCount += 1;
			} catch (DataAccessException ex) {
				if (allowedFailCount <= 0) {
					throw ex;
				}

				failedCount += 1;
				log.warn(format("SQL failed: count={0}, message={1}",
						failedCount, ex.getMessage()));
				if (allowedFailCount < failedCount) {
					throw ex;
				}
			}

			if (batchCount > 0 && batchCount <= totalCount) {
				break;
			}
		}

		Result result = new Result();
		result.setTotalCount(totalCount);
		result.setSuccessCount(successCount);
		result.setFailedCount(failedCount);
		return result;
	}

}
