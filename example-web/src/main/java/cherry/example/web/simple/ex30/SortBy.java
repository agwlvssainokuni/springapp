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

package cherry.example.web.simple.ex30;

import cherry.foundation.type.Code;

public enum SortBy implements Code<Integer> {
	/** 並べ替えキー: ID */
	ID(0),
	/** 並べ替えキー: 文字列【10】 */
	TEXT10(1),
	/** 並べ替えキー: 整数【64bit】 */
	INT64(2),
	/** 並べ替えキー: 小数【1桁】 */
	DECIMAL1(3),
	/** 並べ替えキー: 小数【3桁】 */
	DECIMAL3(4),
	/** 並べ替えキー: 日付 */
	DT(5),
	/** 並べ替えキー: 時刻 */
	TM(6),
	/** 並べ替えキー: 日時 */
	DTM(7);

	private final int c;

	private SortBy(int c) {
		this.c = c;
	}

	@Override
	public Integer code() {
		return c;
	}

}
