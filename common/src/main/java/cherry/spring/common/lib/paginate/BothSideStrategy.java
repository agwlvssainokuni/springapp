/*
 * Copyright 2004,2014 agwlvssainokuni
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

package cherry.spring.common.lib.paginate;

import cherry.spring.common.lib.paginate.range.ContinuousRange;

/**
 * ページネーションリンクとして並べるページ番号の範囲を算出する。現在のページの前後それぞれにできるだけ表示範囲を広げる。
 */
public class BothSideStrategy implements RangeStrategy {

	/** 前に表示するページ数を保持する。 */
	private int smallerSide;

	/** 後に表示するページ数を保持する。 */
	private int biggerSide;

	public void setSmallerSide(int smallerSide) {
		this.smallerSide = smallerSide;
	}

	public void setBiggerSide(int biggerSide) {
		this.biggerSide = biggerSide;
	}

	/**
	 * ページネーションリンクとして並べるページ番号の範囲を算出する。現在のページの前後それぞれにできるだけ表示範囲を広げる。
	 * 
	 * @param pageNo
	 *            ページ番号。
	 * @param pageCount
	 *            ページ数。
	 * @return ページ番号の範囲。
	 */
	@Override
	public Range calcRange(int pageNo, int pageCount) {
		int from = pageNo - smallerSide;
		if (from < 0) {
			from = 0;
		}
		int to = pageNo + biggerSide;
		if (to >= pageCount) {
			to = pageCount - 1;
		}
		return new ContinuousRange(from, to);
	}

}
