/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.goods.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

public class LocalDateUtilTest {

	@Test
	public void testRangeFromLocalDate() {
		LocalDate now = LocalDate.now();
		assertThat(LocalDateUtil.rangeFrom((LocalDate) null), is(nullValue()));
		assertThat(LocalDateUtil.rangeFrom(now), is(new LocalDate(
				now.getYear(), now.getMonthOfYear(), now.getDayOfMonth())));
	}

	@Test
	public void testRangeFromLocalDateTime() {
		LocalDateTime now = LocalDateTime.now();
		assertThat(LocalDateUtil.rangeFrom((LocalDateTime) null),
				is(nullValue()));
		assertThat(LocalDateUtil.rangeFrom(now), is(new LocalDate(
				now.getYear(), now.getMonthOfYear(), now.getDayOfMonth())));
	}

	@Test
	public void testRangeToLocalDate() {
		LocalDate now = LocalDate.now();
		assertThat(LocalDateUtil.rangeTo((LocalDate) null), is(nullValue()));
		assertThat(LocalDateUtil.rangeTo(now), is(new LocalDate(now.getYear(),
				now.getMonthOfYear(), now.getDayOfMonth()).plusDays(1)));
	}

	@Test
	public void testRangeToLocalDateTime() {
		LocalDateTime now = LocalDateTime.now();
		assertThat(LocalDateUtil.rangeTo((LocalDateTime) null), is(nullValue()));
		assertThat(LocalDateUtil.rangeTo(now), is(new LocalDate(now.getYear(),
				now.getMonthOfYear(), now.getDayOfMonth()).plusDays(1)));
	}

	@Test
	public void testMisc() {
		assertThat(new LocalDateUtil(), is(notNullValue()));
	}

}
