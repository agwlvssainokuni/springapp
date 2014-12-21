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

package cherry.foundation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class ConversionServiceTagTest {

	@Test
	public void testGetConversionService() {
		ConversionService service = ConversionServiceTag.getConversionService();
		assertNotNull(service);
	}

	@Test
	public void testConvert() {
		assertEquals("2014/01/23",
				ConversionServiceTag.convert(new LocalDate(2014, 1, 23)));
		assertEquals("1,234,567", ConversionServiceTag.convert(1234567));
	}

	@Test
	public void testInstantiate() {
		try {
			new ConversionServiceTag();
		} catch (Exception ex) {
			fail("Exception must not be thrown");
		}
	}

}
