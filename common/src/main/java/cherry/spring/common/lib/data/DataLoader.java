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

package cherry.spring.common.lib.data;

import java.io.IOException;

import cherry.spring.common.lib.data.limiter.Limiter;
import cherry.spring.common.lib.data.limiter.LimiterException;

/**
 * データ取込み機能.
 */
public interface DataLoader {

	/**
	 * データを取込む.
	 * 
	 * @param provider
	 *            データの取得元
	 * @return 取込みの結果
	 * @throws IOException
	 *             データ取得でエラー
	 */
	LoadResult load(DataProvider provider) throws IOException;

	/**
	 * データを取込む.
	 * 
	 * @param provider
	 *            データの取得元
	 * @param limit
	 *            データ取込み制限
	 * @return 取込みの結果
	 * @throws LimiterException
	 *             データ取込み制限超過
	 * @throws IOException
	 *             データ取得でエラー
	 */
	LoadResult load(DataProvider provider, Limiter limiter)
			throws LimiterException, IOException;

}
