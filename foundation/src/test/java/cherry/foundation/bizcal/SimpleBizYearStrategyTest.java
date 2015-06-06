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

import org.apache.commons.lang3.Range;
import org.joda.time.LocalDate;
import org.junit.Test;

public class SimpleBizYearStrategyTest {

	@Test
	public void test0101() {
		SimpleBizYearStrategy strategy = new SimpleBizYearStrategy();
		strategy.setYearOfFirstOffset(0);
		strategy.setMonthOfFirst(1);
		strategy.setDayOfFirst(1);
		for (int year = 1900; year < 3000; year++) {
			Range<LocalDate> range = strategy.rangeOfBizYear(year);
			assertEquals(new LocalDate(year, 1, 1), range.getMinimum());
			assertEquals(new LocalDate(year, 12, 31), range.getMaximum());
		}
	}

	@Test
	public void test0401() {
		SimpleBizYearStrategy strategy = new SimpleBizYearStrategy();
		strategy.setYearOfFirstOffset(0);
		strategy.setMonthOfFirst(4);
		strategy.setDayOfFirst(1);
		for (int year = 1900; year < 3000; year++) {
			Range<LocalDate> range = strategy.rangeOfBizYear(year);
			assertEquals(new LocalDate(year, 4, 1), range.getMinimum());
			assertEquals(new LocalDate(year + 1, 3, 31), range.getMaximum());
		}
	}

	@Test
	public void test0901() {
		SimpleBizYearStrategy strategy = new SimpleBizYearStrategy();
		strategy.setYearOfFirstOffset(-1);
		strategy.setMonthOfFirst(9);
		strategy.setDayOfFirst(1);
		for (int year = 1900; year < 3000; year++) {
			Range<LocalDate> range = strategy.rangeOfBizYear(year);
			assertEquals(new LocalDate(year - 1, 9, 1), range.getMinimum());
			assertEquals(new LocalDate(year, 8, 31), range.getMaximum());
		}
	}

}
