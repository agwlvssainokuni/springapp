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

package cherry.spring.common.format;

import java.text.ParseException;
import java.util.Locale;

import org.joda.time.Period;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.format.Parser;
import org.springframework.format.datetime.joda.LocalTimeParser;

public class LocalTimeToParser implements Parser<LocalTimeTo> {

	private final LocalTimeParser parserHm;

	private final LocalTimeParser parserHms;

	public LocalTimeToParser(DateTimeFormatter formatterHm,
			DateTimeFormatter formatterHms) {
		parserHm = new LocalTimeParser(formatterHm);
		parserHms = new LocalTimeParser(formatterHms);
	}

	@Override
	public LocalTimeTo parse(String text, Locale locale) throws ParseException {
		try {
			return new LocalTimeTo(parserHms.parse(text, locale), Period.ZERO);
		} catch (ParseException | ConversionFailedException ex) {
			return new LocalTimeTo(parserHm.parse(text, locale),
					Period.seconds(59));
		}
	}

}
