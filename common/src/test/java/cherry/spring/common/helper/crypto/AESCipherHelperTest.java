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

	@Test
	public void testUsingKeyCipherHelper() throws Exception {

		byte[] key = randomUtil.randomBytes(16);
		byte[] iv = randomUtil.randomBytes(16);

		AESCipherHelper helper0 = new AESCipherHelper();
		helper0.setSecretKey(new InMemoryResource(key));
		helper0.setInitVector(new InMemoryResource(iv));
		helper0.afterPropertiesSet();

		AESCipherHelper keyCipherHelper = new AESCipherHelper();
		keyCipherHelper.setSecretKey(new InMemoryResource(randomUtil
				.randomBytes(16)));
		keyCipherHelper.setInitVector(new InMemoryResource(randomUtil
				.randomBytes(16)));
		keyCipherHelper.afterPropertiesSet();

		AESCipherHelper helper1 = new AESCipherHelper();
		helper1.setSecretKey(new InMemoryResource(keyCipherHelper.encrypt(key)));
		helper1.setInitVector(new InMemoryResource(keyCipherHelper.encrypt(iv)));
		helper1.setKeyCipherHelper(keyCipherHelper);
		helper1.afterPropertiesSet();

		for (int i = 0; i < 1000; i++) {
			byte[] plain = randomUtil.randomBytes(1024);
			byte[] enc0 = helper0.encrypt(plain);
			byte[] enc1 = helper1.encrypt(plain);
			assertThat(enc1, is(enc0));
			byte[] dec0 = helper0.decrypt(enc0);
			byte[] dec1 = helper1.decrypt(enc1);
			assertThat(dec0, is(plain));
			assertThat(dec1, is(plain));
		}
	}

}
