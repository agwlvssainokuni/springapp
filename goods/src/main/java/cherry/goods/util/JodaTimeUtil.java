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

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

/**
 * Joda-Time API操作ユーティリティ。
 */
public class JodaTimeUtil {

	/**
	 * @param ts 変換元の日時を表す{@link Timestamp}。
	 * @return 変換元と同じ日時を表す{@link DateTime}(タイムゾーンはデフォルト)。変換元をデフォルトタイムゾーンで評価した時の年月日時分秒ミリ秒の値が同じ。
	 */
	public static DateTime getDateTime(Timestamp ts) {
		Calendar cal = getCalendar(ts);
		return new DateTime(cal.get(YEAR), cal.get(MONTH) + 1, cal.get(DAY_OF_MONTH), cal.get(HOUR_OF_DAY),
				cal.get(MINUTE), cal.get(SECOND), cal.get(MILLISECOND));
	}

	/**
	 * @param dtm 変換元の日時を表す{@link DateTime}。
	 * @return 変換元と同じ日時を表す{@link Calendar}(タイムゾーンは{@link DateTime}を引継ぐ)。変換元と同じタイムゾーンで評価した時の年月日時分秒ミリ秒の値が同じ。
	 */
	public static Calendar getCalendar(DateTime dtm) {
		Calendar cal = Calendar.getInstance(dtm.getZone().toTimeZone());
		cal.set(dtm.getYear(), dtm.getMonthOfYear() - 1, dtm.getDayOfMonth(), dtm.getHourOfDay(),
				dtm.getMinuteOfHour(), dtm.getSecondOfMinute());
		cal.set(MILLISECOND, dtm.getMillisOfSecond());
		return cal;
	}

	/**
	 * @param ts 変換元の日時を表す{@link Timestamp}。
	 * @return 変換元と同じ日時を表す{@link LocalDateTime}。変換元をデフォルトタイムゾーンで評価した時の年月日時分秒ミリ秒の値が同じ。
	 */
	public static LocalDateTime getLocalDateTime(Timestamp ts) {
		Calendar cal = getCalendar(ts);
		return new LocalDateTime(cal.get(YEAR), cal.get(MONTH) + 1, cal.get(DAY_OF_MONTH), cal.get(HOUR_OF_DAY),
				cal.get(MINUTE), cal.get(SECOND), cal.get(MILLISECOND));
	}

	/**
	 * @param ldtm 変換元のローカル日時を表す{@link LocalDateTime}。
	 * @return 変換元と同じ日時を表す{@link Calendar}(タイムゾーンはデフォルト)。デフォルトタイムゾーンで評価した時の年月日時分秒ミリ秒の値が同じ。
	 */
	public static Calendar getCalendar(LocalDateTime ldtm) {
		Calendar cal = Calendar.getInstance();
		cal.set(ldtm.getYear(), ldtm.getMonthOfYear() - 1, ldtm.getDayOfMonth(), ldtm.getHourOfDay(),
				ldtm.getMinuteOfHour(), ldtm.getSecondOfMinute());
		cal.set(MILLISECOND, ldtm.getMillisOfSecond());
		return cal;
	}

	/**
	 * @param ldtm 変換元のローカル日時を表す{@link LocalDateTime}。
	 * @return 変換元と同じ日時を表す{@link Timestamp}。デフォルトタイムゾーンで評価した時の年月日時分秒ミリ秒の値が同じ。
	 */
	public static Timestamp getSqlTimestamp(LocalDateTime ldtm) {
		return new Timestamp(getCalendar(ldtm).getTimeInMillis());
	}

	/**
	 * @param date 変換元の日付を表す{@link Date}。
	 * @return 変換元と同じ日付を表す{@link LocalDateTime}。変換元をデフォルトタイムゾーンで評価した時の年月日の値が同じ。
	 */
	public static LocalDate getLocalDate(Date date) {
		Calendar cal = getCalendar(date);
		return new LocalDate(cal.get(YEAR), cal.get(MONTH) + 1, cal.get(DAY_OF_MONTH));
	}

	/**
	 * @param ldt 変換元の日付を表す{@link LocalDate}。
	 * @return 変換元と同じ日付を表す{@link Calendar}(タイムゾーンはデフォルト)。デフォルトタイムゾーンで評価した時の年月日の値が同じ。(時分秒ミリ秒は00:00:00.000)
	 */
	public static Calendar getCalendar(LocalDate ldt) {
		Calendar cal = Calendar.getInstance();
		cal.set(ldt.getYear(), ldt.getMonthOfYear() - 1, ldt.getDayOfMonth(), 0, 0, 0);
		cal.set(MILLISECOND, 0);
		return cal;
	}

	/**
	 * @param ldt 変換元の日付を表す{@link LocalDate}。
	 * @return 変換元と同じ日付を表す{@link Date}。デフォルトタイムゾーンで評価した時の年月日の値が同じ。(時分秒ミリ秒は00:00:00.000)
	 */
	public static Date getSqlDate(LocalDate ldt) {
		return new Date(getCalendar(ldt).getTimeInMillis());
	}

	/**
	 * @param time 変換元の時刻を表す{@link Time}。
	 * @return 変換元と同じ時刻を表す{@link LocalTime}。変換元をデフォルトタイムゾーンで評価した時の時分秒ミリ秒の値が同じ。
	 */
	public static LocalTime getLocalTime(Time time) {
		Calendar cal = getCalendar(time);
		return new LocalTime(cal.get(HOUR_OF_DAY), cal.get(MINUTE), cal.get(SECOND), cal.get(MILLISECOND));
	}

	/**
	 * @param ltm 変換元の時刻を表す{@link LocalTime}。
	 * @return 変換元と同じ時刻を表す{@link Calendar}(タイムゾーンはデフォルト)。デフォルトタイムゾーンで評価した時の時分秒ミリ秒の値が同じ。(年月日時分秒は1970-01-01)
	 */
	public static Calendar getCalendar(LocalTime ltm) {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 0, 1, ltm.getHourOfDay(), ltm.getMinuteOfHour(), ltm.getSecondOfMinute());
		cal.set(MILLISECOND, ltm.getMillisOfSecond());
		return cal;
	}

	/**
	 * @param ltm 変換元の時刻を表す{@link LocalTime}。
	 * @return 変換元と同じ時刻を表す{@link Time}。デフォルトタイムゾーンで評価した時の時分秒ミリ秒の値が同じ。(年月日時分秒は1970-01-01)
	 */
	public static Time getSqlTime(LocalTime ltm) {
		return new Time(getCalendar(ltm).getTimeInMillis());
	}

	/**
	 * @param d 変換元の年月日時分秒ミリ秒を表す{@link java.util.Date}。
	 * @return 変換元と同じ年月日時分秒ミリ秒を表す{@link Calendar}(タイムゾーンはデフォルト)。
	 */
	public static Calendar getCalendar(java.util.Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(d.getTime());
		return cal;
	}

}
