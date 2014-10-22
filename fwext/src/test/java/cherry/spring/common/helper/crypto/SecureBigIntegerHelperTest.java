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

package cherry.spring.common.helper.crypto;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.spring.common.lib.util.RandomUtil;
import cherry.spring.common.type.SecureBigInteger;

public class SecureBigIntegerHelperTest {

	private SecureRandom random = new SecureRandom();

	@Test
	public void testEncodeDecode() throws Exception {
		SecureBigIntegerHelper helper = createSecureBigIntegerHelper();
		for (int i = 0; i < 1000; i++) {
			BigInteger plain = BigInteger.valueOf(random.nextLong());
			String crypto = helper.encode(plain);
			assertThat(crypto, is(not(plain.toString())));
			assertThat(helper.decode(crypto), is(plain));
		}
	}

	@Test
	public void testSecureBigInteger() throws Exception {
		SecureBigInteger.setEncoder(createSecureBigIntegerHelper());
		for (int i = 0; i < 1000; i++) {

			BigInteger plain = BigInteger.valueOf(random.nextInt());
			SecureBigInteger ss0 = SecureBigInteger.plainValueOf(plain);
			assertThat(ss0.plain(), is(plain));
			assertThat(ss0.crypto(), is(not(plain.toString())));

			SecureBigInteger ss1 = SecureBigInteger.cryptoValueOf(ss0.crypto());
			assertThat(ss1.plain(), is(plain));
			assertThat(ss1.crypto(), is(ss0.crypto()));

			SecureBigInteger ss2 = SecureBigInteger.plainValueOf(ss1.plain());
			assertThat(ss2.plain(), is(plain));
			assertThat(ss2.crypto(), is(ss0.crypto()));

			SecureBigInteger ss3 = SecureBigInteger.cryptoValueOf(ss2.crypto());
			assertThat(ss3.plain(), is(plain));
			assertThat(ss3.crypto(), is(ss0.crypto()));
		}
	}

	private SecureBigIntegerHelper createSecureBigIntegerHelper()
			throws Exception {
		AESCipherHelper helper = new AESCipherHelper();
		helper.setSecretKey(new InMemoryResource(RandomUtil.randomBytes(16)));
		helper.setInitVector(new InMemoryResource(RandomUtil.randomBytes(16)));
		helper.afterPropertiesSet();
		SecureBigIntegerHelper sshelper = new SecureBigIntegerHelper();
		sshelper.setCipherHelper(helper);
		return sshelper;
	}

}
