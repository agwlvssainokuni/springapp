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

package cherry.example.web;

import cherry.foundation.type.Code;

public enum SortBy implements Code<String> {
	/** 並べ替えキー: ID */
	ID("00"),
	/** 並べ替えキー: 文字列【10】 */
	TEXT10("01"),
	/** 並べ替えキー: 整数【64bit】 */
	INT64("02"),
	/** 並べ替えキー: 小数【1桁】 */
	DECIMAL1("03"),
	/** 並べ替えキー: 小数【3桁】 */
	DECIMAL3("04"),
	/** 並べ替えキー: 日付 */
	DT("05"),
	/** 並べ替えキー: 時刻 */
	TM("06"),
	/** 並べ替えキー: 日時 */
	DTM("07");

	private final String c;

	private SortBy(String c) {
		this.c = c;
	}

	@Override
	public String code() {
		return c;
	}

}
