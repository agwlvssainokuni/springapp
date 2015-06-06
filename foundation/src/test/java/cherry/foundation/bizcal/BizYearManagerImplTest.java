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

import static org.junit.Assert.assertEquals;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Test;

import cherry.foundation.bizdtm.SimpleBizDateTime;

public class BizYearManagerImplTest {

	@Test
	public void testGetBizYear() {
		BizYearManager impl = create(0, 4, 1);
		for (int year = 1900; year < 3000; year++) {
			for (LocalDate dt = new LocalDate(year, 1, 1); dt.getYear() == year; dt = dt.plusDays(1)) {
				if (dt.getMonthOfYear() < 4) {
					assertEquals(year - 1, impl.getBizYear(dt));
				} else {
					assertEquals(year, impl.getBizYear(dt));
				}
			}
		}
	}

	@Test
	public void testGetFirstOfBizYear() {
		BizYearManager impl = create(0, 4, 1);
		for (int year = 1900; year < 3000; year++) {
			assertEquals(new LocalDate(year, 4, 1), impl.getFirstOfBizYear(year));
			for (LocalDate dt = new LocalDate(year, 1, 1); dt.getYear() == year; dt = dt.plusDays(1)) {
				if (dt.getMonthOfYear() < 4) {
					assertEquals(new LocalDate(year - 1, 4, 1), impl.getFirstOfBizYear(dt));
				} else {
					assertEquals(new LocalDate(year, 4, 1), impl.getFirstOfBizYear(dt));
				}
			}
		}
	}

	@Test
	public void testGetLastOfBizYear() {
		BizYearManager impl = create(0, 4, 1);
		for (int year = 1900; year < 3000; year++) {
			assertEquals(new LocalDate(year + 1, 3, 31), impl.getLastOfBizYear(year));
			for (LocalDate dt = new LocalDate(year, 1, 1); dt.getYear() == year; dt = dt.plusDays(1)) {
				if (dt.getMonthOfYear() < 4) {
					assertEquals(new LocalDate(year, 3, 31), impl.getLastOfBizYear(dt));
				} else {
					assertEquals(new LocalDate(year + 1, 3, 31), impl.getLastOfBizYear(dt));
				}
			}
		}
	}

	@Test
	public void testGetNthDayOfBizYear() {
		BizYearManager impl = create(0, 4, 1);
		for (int year = 1900; year < 3000; year++) {
			assertEquals(1, impl.getNthDayOfBizYear(new LocalDate(year, 4, 1)));
			assertEquals(numberOfDays(year + 1), impl.getNthDayOfBizYear(new LocalDate(year + 1, 3, 31)));
		}
	}

	@Test
	public void testGetNumberOfDaysOfBizYear() {
		BizYearManager impl = create(0, 4, 1);
		for (int year = 1900; year < 3000; year++) {
			assertEquals(numberOfDays(year + 1), impl.getNumberOfDaysOfBizYear(year));
			for (LocalDate dt = new LocalDate(year, 1, 1); dt.getYear() == year; dt = dt.plusDays(1)) {
				if (dt.getMonthOfYear() < 4) {
					assertEquals(numberOfDays(year), impl.getNumberOfDaysOfBizYear(dt));
				} else {
					assertEquals(numberOfDays(year + 1), impl.getNumberOfDaysOfBizYear(dt));
				}
			}
		}
	}

	@Test
	public void test0901() {
		BizYearManager impl = create(-1, 9, 1);
		for (int year = 1900; year < 3000; year++) {
			for (LocalDate dt = new LocalDate(year, 1, 1); dt.getYear() == year; dt = dt.plusDays(1)) {
				if (dt.getMonthOfYear() < 9) {
					assertEquals(year, impl.getBizYear(dt));
					assertEquals(new LocalDate(year - 1, 9, 1), impl.getFirstOfBizYear(dt));
					assertEquals(new LocalDate(year, 8, 31), impl.getLastOfBizYear(dt));
				} else {
					assertEquals(year + 1, impl.getBizYear(dt));
					assertEquals(new LocalDate(year, 9, 1), impl.getFirstOfBizYear(dt));
					assertEquals(new LocalDate(year + 1, 8, 31), impl.getLastOfBizYear(dt));
				}
			}
		}
	}

	@Test
	public void testForToday() {
		BizYearManager impl = create(0, 4, 1);
		LocalDate today = LocalDate.now();
		if (today.getMonthOfYear() < 4) {
			assertEquals(today.getYear() - 1, impl.getBizYear());
			assertEquals(new LocalDate(today.getYear() - 1, 4, 1), impl.getFirstOfBizYear());
			assertEquals(new LocalDate(today.getYear(), 3, 31), impl.getLastOfBizYear());
			assertEquals(numberOfDays(today.getYear()), impl.getNumberOfDaysOfBizYear());
		} else {
			assertEquals(today.getYear(), impl.getBizYear());
			assertEquals(new LocalDate(today.getYear(), 4, 1), impl.getFirstOfBizYear());
			assertEquals(new LocalDate(today.getYear() + 1, 3, 31), impl.getLastOfBizYear());
			assertEquals(numberOfDays(today.getYear() + 1), impl.getNumberOfDaysOfBizYear());
		}
		assertEquals(Days.daysBetween(impl.getFirstOfBizYear(), today).getDays() + 1, impl.getNthDayOfBizYear());
	}

	private int numberOfDays(int year) {
		return year % 400 == 0 ? 366 : year % 100 == 0 ? 365 : year % 4 == 0 ? 366 : 365;
	}

	private BizYearManager create(int yearOffset, int month, int day) {
		SimpleBizYearStrategy strategy = new SimpleBizYearStrategy();
		strategy.setYearOfFirstOffset(yearOffset);
		strategy.setMonthOfFirst(month);
		strategy.setDayOfFirst(day);
		SimpleBizDateTime bizdtm = new SimpleBizDateTime();
		BizYearManagerImpl impl = new BizYearManagerImpl();
		impl.setBizDateTime(bizdtm);
		impl.setBizYearStrategy(strategy);
		return impl;
	}

}
