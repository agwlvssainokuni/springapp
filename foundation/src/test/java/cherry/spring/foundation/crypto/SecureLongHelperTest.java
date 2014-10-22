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

package cherry.spring.foundation.crypto;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.security.SecureRandom;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.goods.util.RandomUtil;
import cherry.spring.foundation.type.SecureLong;

public class SecureLongHelperTest {

	private SecureRandom random = new SecureRandom();

	@Test
	public void testEncodeDecode() throws Exception {
		SecureLongHelper helper = createSecureLongHelper();
		for (int i = 0; i < 1000; i++) {
			Long plain = Long.valueOf(random.nextInt());
			String crypto = helper.encode(plain);
			assertThat(crypto, is(not(plain.toString())));
			assertThat(helper.decode(crypto), is(plain));
		}
	}

	@Test
	public void testSecureLong() throws Exception {
		SecureLong.setEncoder(createSecureLongHelper());
		for (int i = 0; i < 1000; i++) {

			Long plain = Long.valueOf(random.nextInt());
			SecureLong ss0 = SecureLong.plainValueOf(plain);
			assertThat(ss0.plain(), is(plain));
			assertThat(ss0.crypto(), is(not(plain.toString())));

			SecureLong ss1 = SecureLong.cryptoValueOf(ss0.crypto());
			assertThat(ss1.plain(), is(plain));
			assertThat(ss1.crypto(), is(ss0.crypto()));

			SecureLong ss2 = SecureLong.plainValueOf(ss1.plain());
			assertThat(ss2.plain(), is(plain));
			assertThat(ss2.crypto(), is(ss0.crypto()));

			SecureLong ss3 = SecureLong.cryptoValueOf(ss2.crypto());
			assertThat(ss3.plain(), is(plain));
			assertThat(ss3.crypto(), is(ss0.crypto()));
		}
	}

	private SecureLongHelper createSecureLongHelper() throws Exception {
		AESCipherHelper helper = new AESCipherHelper();
		helper.setSecretKey(new InMemoryResource(RandomUtil.randomBytes(16)));
		helper.setInitVector(new InMemoryResource(RandomUtil.randomBytes(16)));
		helper.afterPropertiesSet();
		SecureLongHelper sshelper = new SecureLongHelper();
		sshelper.setCipherHelper(helper);
		return sshelper;
	}

}
