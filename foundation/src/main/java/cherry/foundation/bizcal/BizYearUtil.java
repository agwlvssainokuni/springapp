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

import java.util.Comparator;

import org.apache.commons.lang3.Range;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

public class BizYearUtil {

	public static Range<LocalDate> between(LocalDate from, LocalDate to) {
		return Range.between(from, to, new Comparator<LocalDate>() {
			@Override
			public int compare(LocalDate o1, LocalDate o2) {
				return o1.compareTo(o2);
			}
		});
	}

	public static int numberOfDays(LocalDate from, LocalDate to) {
		Interval interval = new Interval(from.toDateTimeAtStartOfDay(), to.plusDays(1).toDateTimeAtCurrentTime());
		return (int) interval.toDuration().getStandardDays();
	}

}
