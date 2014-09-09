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

package cherry.spring.common.custom;

import static cherry.spring.common.custom.SecureBigDecimal.cryptoValueOf;
import static cherry.spring.common.custom.SecureBigDecimal.plainValueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Before;
import org.junit.Test;

import cherry.spring.common.AppCtxUtil;
import cherry.spring.common.lib.util.RandomUtil;

public class SecureBigDecimalTest {

	private SecureRandom random = new SecureRandom();

	private RandomUtil randomUtil = new RandomUtil();

	private int loopCount = 1000;

	private int size = 1024;

	@Before
	public void setup() {
		AppCtxUtil.getAppCtx();
	}

	@Test
	public void testRandomTest() {
		for (int i = 0; i < loopCount; i++) {
			BigInteger bi = new BigInteger(randomUtil.randomBytes(size));
			int scale = random.nextInt();
			BigDecimal plain = new BigDecimal(bi, scale);
			SecureBigDecimal sec0 = plainValueOf(plain);
			SecureBigDecimal sec1 = cryptoValueOf(sec0.crypto());
			assertThat(sec1.plain(), is(plain));
		}
	}

}