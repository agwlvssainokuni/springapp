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

package cherry.foundation.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class JodaTimeMinValidatorTest {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private Validator validator;

	@Test
	public void testOK() {

		Map<String, String> val = new HashMap<>();
		val.put("val0", null);
		val.put("val1", "1900/01/01");
		val.put("val2", "1900/01/02");
		val.put("val3", "1900/01/01 00:00:00");
		val.put("val4", "1900/01/01 00:00:01");
		val.put("val5", "00:00:00");

		TestDto dto = new TestDto();

		WebDataBinder binder = new WebDataBinder(dto);
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(new MutablePropertyValues(val));
		binder.validate();
		BindingResult result = binder.getBindingResult();
		assertEquals(0, result.getErrorCount());
		assertNull(dto.getVal0());
		assertEquals(new LocalDate(1900, 1, 1), dto.getVal1());
		assertEquals(new LocalDate(1900, 1, 2), dto.getVal2());
		assertEquals(new LocalDateTime(1900, 1, 1, 0, 0, 0), dto.getVal3());
		assertEquals(new LocalDateTime(1900, 1, 1, 0, 0, 1), dto.getVal4());
		assertEquals(new LocalTime(0, 0, 0), dto.getVal5());
	}

	@Test
	public void testNG() {

		Map<String, String> val = new HashMap<>();
		val.put("val1", "1899/12/31");
		val.put("val2", "1900/01/01");
		val.put("val3", "1899/12/31 23:59:59");
		val.put("val4", "1900/01/01 00:00:00");

		TestDto dto = new TestDto();

		WebDataBinder binder = new WebDataBinder(dto);
		binder.setConversionService(conversionService);
		binder.addValidators(validator);
		binder.bind(new MutablePropertyValues(val));
		binder.validate();
		BindingResult result = binder.getBindingResult();
		assertEquals(4, result.getErrorCount());
	}

	@Getter
	@Setter
	public static class TestDto {
		@JodaTimeMin(value = "1900-01-01")
		private LocalDate val0;
		@JodaTimeMin(value = "1900-01-01")
		private LocalDate val1;
		@JodaTimeMin(value = "1900-01-01", inclusive = false)
		private LocalDate val2;
		@JodaTimeMin(value = "1900-01-01T00:00:00")
		private LocalDateTime val3;
		@JodaTimeMin(value = "1900-01-01T00:00:00", inclusive = false)
		private LocalDateTime val4;
		@JodaTimeMin()
		private LocalTime val5;
	}

}
