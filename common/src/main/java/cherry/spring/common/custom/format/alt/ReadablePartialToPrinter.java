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

import java.util.Locale;

import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.Printer;
import org.springframework.format.datetime.joda.ReadablePartialPrinter;

public class ReadablePartialToPrinter implements
		Printer<ReadablePartialTo<ReadablePartial>> {

	private ReadablePartialPrinter printer;

	public ReadablePartialToPrinter(DateTimeFormatter formatter) {
		printer = new ReadablePartialPrinter(formatter);
	}

	@Override
	public String print(ReadablePartialTo<ReadablePartial> object, Locale locale) {
		return printer.print(object.getAdjusted(), locale);
	}

}
