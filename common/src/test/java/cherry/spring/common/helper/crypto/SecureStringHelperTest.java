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

import java.nio.charset.Charset;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.spring.common.custom.SecureString;
import cherry.spring.common.lib.util.RandomUtil;

public class SecureStringHelperTest {

	private RandomUtil randomUtil = new RandomUtil();

	@Test
	public void testEncodeDecode() throws Exception {
		SecureStringHelper helper = createSecureStringHelper();
		for (int i = 0; i < 1000; i++) {
			String plain = randomUtil.randomString(1024);
			String crypto = helper.encode(plain);
			assertThat(crypto, is(not(plain)));
			assertThat(helper.decode(crypto), is(plain));
		}
	}

	@Test
	public void testSecureString() throws Exception {
		SecureString.setEncoder(createSecureStringHelper());
		for (int i = 0; i < 1000; i++) {

			String plain = randomUtil.randomString(1024);
			SecureString ss0 = SecureString.plainValueOf(plain);
			assertThat(ss0.plain(), is(plain));
			assertThat(ss0.crypto(), is(not(plain)));

			SecureString ss1 = SecureString.cryptoValueOf(ss0.crypto());
			assertThat(ss1.plain(), is(plain));
			assertThat(ss1.crypto(), is(ss0.crypto()));

			SecureString ss2 = SecureString.plainValueOf(ss1.plain());
			assertThat(ss2.plain(), is(plain));
			assertThat(ss2.crypto(), is(ss0.crypto()));

			SecureString ss3 = SecureString.cryptoValueOf(ss2.crypto());
			assertThat(ss3.plain(), is(plain));
			assertThat(ss3.crypto(), is(ss0.crypto()));
		}
	}

	private SecureStringHelper createSecureStringHelper() throws Exception {
		AESCipherHelper helper = new AESCipherHelper();
		helper.setSecretKey(new InMemoryResource(randomUtil.randomBytes(16)));
		helper.setInitVector(new InMemoryResource(randomUtil.randomBytes(16)));
		helper.afterPropertiesSet();
		SecureStringHelper sshelper = new SecureStringHelper();
		sshelper.setCharset(Charset.forName("UTF-8"));
		sshelper.setCipherHelper(helper);
		return sshelper;
	}

}