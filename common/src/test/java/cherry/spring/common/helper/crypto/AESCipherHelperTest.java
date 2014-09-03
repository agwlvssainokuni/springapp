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
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.spring.common.lib.util.RandomUtil;

public class AESCipherHelperTest {

	private RandomUtil randomUtil = new RandomUtil();

	@Test
	public void testDefault() throws Exception {

		AESCipherHelper helper = new AESCipherHelper();
		helper.setSecretKey(new InMemoryResource(randomUtil.randomBytes(16)));
		helper.setInitVector(new InMemoryResource(randomUtil.randomBytes(16)));
		helper.afterPropertiesSet();

		for (int i = 0; i < 1000; i++) {
			byte[] plain = randomUtil.randomBytes(1024);
			byte[] enc = helper.encrypt(plain);
			byte[] dec = helper.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testCBC() throws Exception {

		AESCipherHelper helper = new AESCipherHelper();
		helper.setAlgorithm("AES/CBC/PKCS5Padding");
		helper.setSecretKey(new InMemoryResource(randomUtil.randomBytes(16)));
		helper.setInitVector(new InMemoryResource(randomUtil.randomBytes(16)));
		helper.afterPropertiesSet();

		for (int i = 0; i < 1000; i++) {
			byte[] plain = randomUtil.randomBytes(1024);
			byte[] enc = helper.encrypt(plain);
			byte[] dec = helper.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

	@Test
	public void testECB() throws Exception {

		AESCipherHelper helper = new AESCipherHelper();
		helper.setAlgorithm("AES/ECB/PKCS5Padding");
		helper.setSecretKey(new InMemoryResource(randomUtil.randomBytes(16)));
		helper.afterPropertiesSet();

		for (int i = 0; i < 1000; i++) {
			byte[] plain = randomUtil.randomBytes(1024);
			byte[] enc = helper.encrypt(plain);
			byte[] dec = helper.decrypt(enc);
			assertThat(dec, is(plain));
		}
	}

}
