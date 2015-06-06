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

package cherry.foundation.bizcal;

import org.joda.time.LocalDate;

/**
 * 営業年度管理機能。<br />
 */
public interface BizYearManager {

	/**
	 * @param dt 対象日付。
	 * @return 対象日付の営業年度。
	 */
	int getBizYear(LocalDate dt);

	/**
	 * @return 当日(業務日付)の営業年度。
	 */
	int getBizYear();

	/**
	 * @param bizYear 対象営業年度。
	 * @return 対象営業年度の初日。
	 */
	LocalDate getFirstOfBizYear(int bizYear);

	/**
	 * @param dt 対象日付。
	 * @return 対象日付の営業年度の初日。
	 */
	LocalDate getFirstOfBizYear(LocalDate dt);

	/**
	 * @return 当日(業務日付)の営業年度の初日。
	 */
	LocalDate getFirstOfBizYear();

	/**
	 * @param bizYear 対象営業年度。
	 * @return 対象営業年度の末日。
	 */
	LocalDate getLastOfBizYear(int bizYear);

	/**
	 * @param dt 対象日付。
	 * @return 対象日付の営業年度の末日。
	 */
	LocalDate getLastOfBizYear(LocalDate dt);

	/**
	 * @return 当日(業務日付)の営業年度の末日。
	 */
	LocalDate getLastOfBizYear();

	/**
	 * @param dt 対象日付。
	 * @return 対象日付が営業年度の何日目か。
	 */
	int getNthDayOfBizYear(LocalDate dt);

	/**
	 * @return 当日(業務日付)が営業年度の何日目か。
	 */
	int getNthDayOfBizYear();

	/**
	 * @param bizYear 対象営業年度。
	 * @return 対象営業年度の日数。
	 */
	int getNumberOfDaysOfBizYear(int bizYear);

	/**
	 * @param dt 対象日付。
	 * @return 対象日付の営業年度の日数。
	 */
	int getNumberOfDaysOfBizYear(LocalDate dt);

	/**
	 * @return 当日(業務日付)の営業年度の日数。
	 */
	int getNumberOfDaysOfBizYear();

}
