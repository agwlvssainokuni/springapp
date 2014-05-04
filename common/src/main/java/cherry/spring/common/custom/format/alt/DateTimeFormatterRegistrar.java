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

import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.format.datetime.joda.JodaDateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.format.datetime.joda.LocalTimeParser;
import org.springframework.format.datetime.joda.ReadableInstantPrinter;
import org.springframework.format.datetime.joda.ReadablePartialPrinter;

public class DateTimeFormatterRegistrar implements FormatterRegistrar {

	@Value("${common.format.date.print}")
	private String dateToPrint;

	@Value("${common.format.date.parse}")
	private String dateToParse;

	@Value("${common.format.time.print}")
	private String timeToPrint;

	@Value("${common.format.time.parse.hm}")
	private String timeToParseHm;

	@Value("${common.format.time.parse.s}")
	private String timeToParseS;

	@Value("${common.format.delimiter.print}")
	private String delimiterToPrint;

	@Value("${common.format.delimiter.parse}")
	private String delimiterToParse;

	@Override
	public void registerFormatters(FormatterRegistry registry) {

		DateTimeFormatterBuilder datePrint = builder(dateToPrint);
		DateTimeFormatterBuilder timePrint = builder(timeToPrint);
		DateTimeFormatterBuilder dateTimePrint = builder(dateToPrint)
				.appendPattern(delimiterToPrint).appendPattern(timeToPrint);

		DateTimeFormatterBuilder dateParse = builder(dateToParse);
		DateTimeFormatterBuilder timeParse = builder(timeToParseHm)
				.appendOptional(builder(timeToParseS).toParser());
		DateTimeFormatterBuilder dateTimeParse = builder(dateToParse)
				.appendOptional(
						builder(delimiterToParse).append(
								builder(timeToParseHm).appendOptional(
										builder(timeToParseS).toParser())
										.toParser()).toParser());

		DateTimeFormatterBuilder timeHmParse = builder(timeToParseHm);
		DateTimeFormatterBuilder timeHmsParse = builder(timeToParseHm)
				.appendPattern(timeToParseS);
		DateTimeFormatterBuilder dateTimeHmParse = builder(dateToParse)
				.appendPattern(delimiterToParse).appendPattern(timeToParseHm);
		DateTimeFormatterBuilder dateTimeHmsParse = builder(dateToParse)
				.appendPattern(delimiterToParse).appendPattern(timeToParseHm)
				.appendPattern(timeToParseS);

		registry.addFormatterForFieldType(LocalDate.class,
				new ReadablePartialPrinter(datePrint.toFormatter()),
				new LocalDateParser(dateParse.toFormatter()));

		registry.addFormatterForFieldType(LocalTime.class,
				new ReadablePartialPrinter(timePrint.toFormatter()),
				new LocalTimeParser(timeParse.toFormatter()));

		registry.addFormatterForFieldType(LocalDateTime.class,
				new ReadablePartialPrinter(dateTimePrint.toFormatter()),
				new LocalDateTimeParser(dateTimeParse.toFormatter()));

		registry.addFormatterForFieldType(ReadableInstant.class,
				new ReadableInstantPrinter(dateTimePrint.toFormatter()),
				new DateTimeParser(dateTimeParse.toFormatter()));

		registry.addFormatterForFieldType(LocalDateTo.class,
				new ReadablePartialToPrinter(datePrint.toFormatter()),
				new LocalDateToParser(dateParse.toFormatter()));

		registry.addFormatterForFieldType(
				LocalTimeTo.class,
				new ReadablePartialToPrinter(timePrint.toFormatter()),
				new LocalTimeToParser(timeHmParse.toFormatter(), timeHmsParse
						.toFormatter()));

		registry.addFormatterForFieldType(
				LocalDateTimeTo.class,
				new ReadablePartialToPrinter(dateTimePrint.toFormatter()),
				new LocalDateTimeToParser(dateParse.toFormatter(),
						dateTimeHmParse.toFormatter(), dateTimeHmsParse
								.toFormatter()));

		registry.addFormatterForFieldType(
				ReadableInstantTo.class,
				new ReadablePartialToPrinter(dateTimePrint.toFormatter()),
				new DateTimeToParser(dateParse.toFormatter(), dateTimeHmParse
						.toFormatter(), dateTimeHmsParse.toFormatter()));

		registry.addFormatterForFieldType(Date.class,
				new ReadableInstantPrinter(dateTimePrint.toFormatter()),
				new DateTimeParser(dateTimeParse.toFormatter()));

		registry.addFormatterForFieldType(Calendar.class,
				new ReadableInstantPrinter(dateTimePrint.toFormatter()),
				new DateTimeParser(dateTimeParse.toFormatter()));

		registry.addFormatterForFieldAnnotation(new JodaDateTimeFormatAnnotationFormatterFactory());
	}

	private DateTimeFormatterBuilder builder(String pattern) {
		return new DateTimeFormatterBuilder().appendPattern(pattern);
	}

}
