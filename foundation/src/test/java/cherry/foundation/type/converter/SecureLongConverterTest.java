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

package cherry.foundation.type.converter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.security.SecureRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.type.SecureLong;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class SecureLongConverterTest {

	@Autowired
	private ConversionService cs;

	private SecureRandom random = new SecureRandom();

	@Test
	public void testCanConvert() {
		assertThat(cs.canConvert(String.class, SecureLong.class), is(true));
	}

	@Test
	public void testConvert() {
		for (int i = 0; i < 100; i++) {
			Long plain = random.nextLong();
			String crypto = SecureLong.plainValueOf(plain).crypto();
			assertThat(cs.convert(crypto, SecureLong.class).plain(), is(plain));
		}
	}

}
