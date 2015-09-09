/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.goods.masker;

import java.sql.Time;
import java.util.Calendar;

import org.joda.time.LocalTime;

public abstract class SqlTimeMasker implements Masker<Time> {

	public static SqlTimeMasker newMasker(LocalTime mask, boolean hour, boolean minute, boolean second) {
		return new MaskerImpl(mask, hour, minute, second);
	}

	static class MaskerImpl extends SqlTimeMasker {

		private final LocalTimeMasker masker;

		public MaskerImpl(LocalTime mask, boolean hour, boolean minute, boolean second) {
			this.masker = LocalTimeMasker.newMasker(mask, hour, minute, second);
		}

		@Override
		public Time mask(Time value) {
			if (value == null) {
				return value;
			}
			return getSqlTime(masker.mask(getLocalTime(value)));
		}

		private Time getSqlTime(LocalTime ltm) {
			Calendar cal = Calendar.getInstance();
			cal.set(0, 0, 0, ltm.getHourOfDay(), ltm.getMinuteOfHour(), ltm.getSecondOfMinute());
			cal.set(Calendar.MILLISECOND, ltm.getMillisOfSecond());
			return new Time(cal.getTimeInMillis());
		}

		private LocalTime getLocalTime(Time time) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(time.getTime());
			return new LocalTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND),
					cal.get(Calendar.MILLISECOND));
		}
	}

}
