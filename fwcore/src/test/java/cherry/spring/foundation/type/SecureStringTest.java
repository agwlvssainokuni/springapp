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

package cherry.spring.foundation.type;

import static cherry.spring.fwcore.type.SecureString.cryptoValueOf;
import static cherry.spring.fwcore.type.SecureString.plainValueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.goods.util.RandomUtil;
import cherry.spring.fwcore.type.SecureString;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class SecureStringTest {

	private int loopCount = 1000;

	private int size = 1024;

	@Test
	public void testRandomTest() {
		for (int i = 0; i < loopCount; i++) {
			String plain = RandomUtil.randomString(size);
			SecureString sec0 = plainValueOf(plain);
			SecureString sec1 = cryptoValueOf(sec0.crypto());
			assertThat(sec1.plain(), is(plain));
		}
	}

}
