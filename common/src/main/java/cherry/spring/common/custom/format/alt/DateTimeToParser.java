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

package cherry.spring.common.custom.format.alt;

import java.text.ParseException;
import java.util.Locale;

import org.joda.time.Period;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.Parser;
import org.springframework.format.datetime.joda.DateTimeParser;

public class DateTimeToParser implements Parser<DateTimeTo> {

	private final DateTimeParser parserYmd;

	private final DateTimeParser parserYmdHm;

	private final DateTimeParser parserYmdHms;

	public DateTimeToParser(DateTimeFormatter formatterYmd,
			DateTimeFormatter formatterYmdHm, DateTimeFormatter formatterYmdHms) {
		parserYmd = new DateTimeParser(formatterYmd);
		parserYmdHm = new DateTimeParser(formatterYmdHm);
		parserYmdHms = new DateTimeParser(formatterYmdHms);
	}

	@Override
	public DateTimeTo parse(String text, Locale locale) throws ParseException {
		try {
			return new DateTimeTo(parserYmdHms.parse(text, locale), Period.ZERO);
		} catch (IllegalArgumentException ex) {
			try {
				return new DateTimeTo(parserYmdHm.parse(text, locale), Period
						.minutes(1).withSeconds(-1));
			} catch (IllegalArgumentException ex2) {
				return new DateTimeTo(parserYmd.parse(text, locale), Period
						.days(1).withSeconds(-1));
			}
		}
	}

}
