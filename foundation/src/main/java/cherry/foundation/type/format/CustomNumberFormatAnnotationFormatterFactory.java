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

package cherry.foundation.type.format;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.number.NumberFormatter;

import cherry.goods.util.NumberUtil;

public class CustomNumberFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<CustomNumberFormat> {

	private List<NumberFormatter> numberFormatter;

	private final Set<Class<?>> fieldTypes = new HashSet<Class<?>>(asList(Byte.class, Short.class, Integer.class,
			Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class));

	public void setNumberFormatter(List<NumberFormatter> numberFormatter) {
		this.numberFormatter = numberFormatter;
	}

	@Override
	public Set<Class<?>> getFieldTypes() {
		return fieldTypes;
	}

	@Override
	public Printer<Number> getPrinter(CustomNumberFormat annotation, Class<?> fieldType) {
		int scale = adjust(annotation.value());
		return numberFormatter.get(scale);
	}

	@Override
	public Parser<Number> getParser(CustomNumberFormat annotation, Class<?> fieldType) {
		final int scale = adjust(annotation.value());
		return new Parser<Number>() {
			@Override
			public Number parse(String text, Locale locale) throws ParseException {
				return NumberUtil.setScale(numberFormatter.get(scale).parse(text, locale), scale, RoundingMode.DOWN);
			}
		};
	}

	private int adjust(int scale) {
		if (scale < 0) {
			return scale;
		} else if (scale >= numberFormatter.size()) {
			return numberFormatter.size() - 1;
		} else {
			return scale;
		}
	}

}
