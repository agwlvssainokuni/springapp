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

package cherry.spring.common.helper;

import static org.joda.time.format.DateTimeFormat.forPattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DateTimeHelperImpl implements DateTimeHelper, InitializingBean {

	@Value("${common.dateTimeHelper.datePattern}")
	private String datePattern;

	@Value("${common.dateTimeHelper.timePattern}")
	private String timePattern;

	private DateTimeFormatter dateTimeFormatter;

	private DateTimeFormatter dateFormatter;

	@Override
	public void afterPropertiesSet() {
		dateFormatter = forPattern(datePattern);
		dateTimeFormatter = new DateTimeFormatterBuilder()
				.appendPattern(datePattern).appendPattern(timePattern)
				.toFormatter();
	}

	@Override
	public DateTime parseFrom(String str) {
		try {
			return DateTime.parse(str, dateTimeFormatter);
		} catch (IllegalArgumentException ex) {
			return DateTime.parse(str, dateFormatter);
		}
	}

	@Override
	public DateTime parseTo(String str) {
		try {
			return DateTime.parse(str, dateTimeFormatter).plusSeconds(1);
		} catch (IllegalArgumentException ex) {
			return DateTime.parse(str, dateFormatter).plusDays(1);
		}
	}

}
