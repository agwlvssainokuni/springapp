package cherry.spring.common.format;

import java.util.Locale;

import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.Printer;
import org.springframework.format.datetime.joda.ReadableInstantPrinter;

public class ReadableInstantToPrinter implements
		Printer<ReadableInstantTo<ReadableInstant>> {

	private ReadableInstantPrinter printer;

	public ReadableInstantToPrinter(DateTimeFormatter formatter) {
		printer = new ReadableInstantPrinter(formatter);
	}

	@Override
	public String print(ReadableInstantTo<ReadableInstant> object, Locale locale) {
		return printer.print(object.getAdjusted(), locale);
	}

}
