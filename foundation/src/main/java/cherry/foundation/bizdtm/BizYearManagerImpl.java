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

package cherry.foundation.bizdtm;

import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

public class BizYearManagerImpl implements BizYearManager {

	private BizDateTime bizDateTime;

	private BizYearStore bizYearStore;

	public void setBizDateTime(BizDateTime bizDateTime) {
		this.bizDateTime = bizDateTime;
	}

	public void setBizYearStore(BizYearStore bizYearStore) {
		this.bizYearStore = bizYearStore;
	}

	@Override
	public int getBizYear(LocalDate dt) {
		return getBizYearByDate(dt.getYear(), dt);
	}

	@Override
	public int getBizYear() {
		return getBizYear(bizDateTime.today());
	}

	@Override
	public LocalDate getFirstOfBizYear(int bizYear) {
		return bizYearStore.getRangeOfBizYear(bizYear).getLeft();
	}

	@Override
	public LocalDate getFirstOfBizYear(LocalDate dt) {
		return getRangeOfBizYearByDate(dt.getYear(), dt).getLeft();
	}

	@Override
	public LocalDate getFirstOfBizYear() {
		return getFirstOfBizYear(bizDateTime.today());
	}

	@Override
	public LocalDate getLastOfBizYear(int bizYear) {
		return bizYearStore.getRangeOfBizYear(bizYear).getRight();
	}

	@Override
	public LocalDate getLastOfBizYear(LocalDate dt) {
		return getRangeOfBizYearByDate(dt.getYear(), dt).getRight();
	}

	@Override
	public LocalDate getLastOfBizYear() {
		return getLastOfBizYear(bizDateTime.today());
	}

	@Override
	public int getNthDayOfBizYear(LocalDate dt) {
		Pair<LocalDate, LocalDate> pair = getRangeOfBizYearByDate(dt.getYear(), dt);
		Interval interval = new Interval(pair.getLeft().toDateTimeAtStartOfDay(), dt.plusDays(1)
				.toDateTimeAtStartOfDay());
		return (int) interval.toDuration().getStandardDays();
	}

	@Override
	public int getNthDayOfBizYear() {
		return getNthDayOfBizYear(bizDateTime.today());
	}

	@Override
	public int getNumberOfDaysOfBizYear(int bizYear) {
		Pair<LocalDate, LocalDate> pair = bizYearStore.getRangeOfBizYear(bizYear);
		Interval interval = new Interval(pair.getLeft().toDateTimeAtStartOfDay(), pair.getRight().plusDays(1)
				.toDateTimeAtStartOfDay());
		return (int) interval.toDuration().getStandardDays();
	}

	@Override
	public int getNumberOfDaysOfBizYear(LocalDate dt) {
		Pair<LocalDate, LocalDate> pair = getRangeOfBizYearByDate(dt.getYear(), dt);
		Interval interval = new Interval(pair.getLeft().toDateTimeAtStartOfDay(), pair.getRight().plusDays(1)
				.toDateTimeAtStartOfDay());
		return (int) interval.toDuration().getStandardDays();
	}

	@Override
	public int getNumberOfDaysOfBizYear() {
		return getNumberOfDaysOfBizYear(bizDateTime.today());
	}

	private int getBizYearByDate(int year, LocalDate dt) {
		Pair<LocalDate, LocalDate> pair = bizYearStore.getRangeOfBizYear(year);
		Interval interval = new Interval(pair.getLeft().toDateTimeAtStartOfDay(), pair.getRight().plusDays(1)
				.toDateTimeAtStartOfDay());
		if (interval.isAfter(dt.toDateTimeAtStartOfDay())) {
			return getBizYearByDate(year - 1, dt);
		}
		if (interval.isBefore(dt.toDateTimeAtStartOfDay())) {
			return getBizYearByDate(year + 1, dt);
		}
		return year;
	}

	private Pair<LocalDate, LocalDate> getRangeOfBizYearByDate(int year, LocalDate dt) {
		Pair<LocalDate, LocalDate> pair = bizYearStore.getRangeOfBizYear(year);
		Interval interval = new Interval(pair.getLeft().toDateTimeAtStartOfDay(), pair.getRight().plusDays(1)
				.toDateTimeAtStartOfDay());
		if (interval.isAfter(dt.toDateTimeAtStartOfDay())) {
			return getRangeOfBizYearByDate(year - 1, dt);
		}
		if (interval.isBefore(dt.toDateTimeAtStartOfDay())) {
			return getRangeOfBizYearByDate(year + 1, dt);
		}
		return pair;
	}

}
