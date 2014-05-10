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

package cherry.spring.common.lib.db;

import java.io.IOException;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.lib.db.limiter.Limiter;
import cherry.spring.common.lib.db.limiter.LimiterException;

/**
 * データ抽出機能.
 */
public interface DataExtractor {

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
	long extract(DataConsumer consumer, Map<String, ?> paramMap)
			throws IOException;

	/**
	 * データを抽出する.
	 * 
	 * @param consumer
	 *            データの格納先
	 * @param paramMap
	 *            データ抽出時のパラメタ
	 * @param limiter
	 *            データ抽出制限
	 * @return 格納したデータの件数
	 * @throws LimiterException
	 *             データ抽出制限超過
	 * @throws IOException
	 *             データ格納エラー
	 */
	@Transactional(rollbackFor = { DataAccessException.class,
			LimiterException.class, IOException.class })
	long extract(DataConsumer consumer, Map<String, ?> paramMap, Limiter limiter)
			throws LimiterException, IOException;

}
