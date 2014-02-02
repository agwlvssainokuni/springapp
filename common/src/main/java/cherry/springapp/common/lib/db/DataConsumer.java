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

/**
 * データ抽出機能におけるデータ格納先.
 */
public interface DataConsumer {

	/**
	 * データの格納を開始する.
	 * 
	 * @param col
	 *            カラムの情報
	 * @throws IOException
	 *             データ格納エラー
	 */
	void begin(Column[] col) throws IOException;

	/**
	 * 1レコードのデータを格納する.
	 * 
	 * @param record
	 *            1レコードのデータ
	 * @throws IOException
	 *             データ格納エラー
	 */
	void consume(Object[] record) throws IOException;

	/**
	 * データの格納を終了する.
	 * 
	 * @throws IOException
	 *             データ格納エラー
	 */
	void end() throws IOException;

	/**
	 * カラムの情報.
	 */
	public static class Column {

		/** カラムの型. */
		private int type;

		/** カラムの表記名. */
		private String label;

		/**
		 * カラムの型 を取得する.
		 * 
		 * @return カラムの型
		 */
		public int getType() {
			return type;
		}

		/**
		 * カラムの型 を設定する.
		 * 
		 * @param type
		 *            カラムの型
		 */
		public void setType(int type) {
			this.type = type;
		}

		/**
		 * カラムの表記名 を取得する.
		 * 
		 * @return カラムの表記名
		 */
		public String getLabel() {
			return label;
		}

		/**
		 * カラムの表記名 を設定する.
		 * 
		 * @param label
		 *            カラムの表記名
		 */
		public void setLabel(String label) {
			this.label = label;
		}
	}
}
