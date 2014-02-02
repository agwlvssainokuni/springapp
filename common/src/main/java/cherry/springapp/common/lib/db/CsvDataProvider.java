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

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.Map;

import cherry.springapp.common.lib.csv.CsvParser;

/**
 * データ取込み機能でCSVファイルをデータ取得元とする.
 */
public class CsvDataProvider implements DataProvider {

	/** CSVパーサ. */
	private CsvParser parser;

	private Boolean hasNext = null;

	private String[] nextRecord = null;

	/**
	 * CSVデータ取込み機能を生成する.
	 * 
	 * @param reader
	 *            データ読取り元
	 */
	public CsvDataProvider(Reader reader) {
		parser = new CsvParser(reader);
	}

	/**
	 * 次のデータが存在するか判定する.
	 * 
	 * @return 次のデータが存在するか否か
	 * @throws IOException
	 *             データの取得でエラー
	 */
	@Override
	public boolean hasNext() throws IOException {

		if (hasNext == null) {
			nextRecord = parser.read();
			if (nextRecord == null) {
				parser.close();
			}
			hasNext = (nextRecord != null);
		}

		return hasNext;
	}

	/**
	 * データを取得する.
	 * 
	 * @return 取得したデータ
	 * @throws IOException
	 *             データの取得でエラー
	 */
	@Override
	public Map<String, ?> nextData() throws IOException {

		if (!hasNext()) {
			return null;
		}

		Map<String, String> nextData = new LinkedHashMap<String, String>(
				nextRecord.length);
		for (int i = 0; i < nextRecord.length; i++) {
			nextData.put("field" + i, nextRecord[i]);
		}

		hasNext = null;
		return nextData;
	}

}
