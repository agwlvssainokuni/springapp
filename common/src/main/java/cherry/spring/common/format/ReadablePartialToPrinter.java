package cherry.spring.common.format;

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
