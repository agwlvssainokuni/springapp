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

package cherry.goods.util;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public class JodaTimeUtil {

	public static LocalDateTime getLocalDateTime(Timestamp ts) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(ts.getTime());
		return new LocalDateTime(cal.get(YEAR), cal.get(MONTH) + 1, cal.get(DAY_OF_MONTH), cal.get(HOUR_OF_DAY),
				cal.get(MINUTE), cal.get(SECOND), cal.get(MILLISECOND));
	}

	public static Timestamp getSqlTimestamp(LocalDateTime ldtm) {
		Calendar cal = Calendar.getInstance();
		cal.set(ldtm.getYear(), ldtm.getMonthOfYear() - 1, ldtm.getDayOfMonth(), ldtm.getHourOfDay(),
				ldtm.getMinuteOfHour(), ldtm.getSecondOfMinute());
		cal.set(MILLISECOND, ldtm.getMillisOfSecond());
		return new Timestamp(cal.getTimeInMillis());
	}

	public static LocalDate getLocalDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		return new LocalDate(cal.get(YEAR), cal.get(MONTH) + 1, cal.get(DAY_OF_MONTH));
	}

	public static Date getSqlDate(LocalDate ldt) {
		Calendar cal = Calendar.getInstance();
		cal.set(ldt.getYear(), ldt.getMonthOfYear() - 1, ldt.getDayOfMonth(), 0, 0, 0);
		cal.set(MILLISECOND, 0);
		return new Date(cal.getTimeInMillis());
	}

	public static LocalTime getLocalTime(Time time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		return new LocalTime(cal.get(HOUR_OF_DAY), cal.get(MINUTE), cal.get(SECOND), cal.get(MILLISECOND));
	}

	public static Time getSqlTime(LocalTime ltm) {
		Calendar cal = Calendar.getInstance();
		cal.set(0, 0, 0, ltm.getHourOfDay(), ltm.getMinuteOfHour(), ltm.getSecondOfMinute());
		cal.set(MILLISECOND, ltm.getMillisOfSecond());
		return new Time(cal.getTimeInMillis());
	}

}
