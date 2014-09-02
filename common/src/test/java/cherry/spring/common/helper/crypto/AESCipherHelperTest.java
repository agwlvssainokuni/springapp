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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.security.SecureRandom;
import java.util.UUID;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

public class AESCipherHelperTest {

	private SecureRandom random = new SecureRandom();

	@Test
	public void testDefault() throws Exception {

		AESCipherHelper helper = new AESCipherHelper();
		helper.setSecretKey(new InMemoryResource(randomBytes(16)));
		helper.setInitVector(new InMemoryResource(randomBytes(16)));
		helper.afterPropertiesSet();

		for (int i = 0; i < 1000; i++) {
			UUID plain = UUID.randomUUID();
			byte[] enc = helper.encrypt(plain.toString().getBytes());
			byte[] dec = helper.decrypt(enc);
			assertThat(dec, is(plain.toString().getBytes()));
		}
	}

	@Test
	public void testCBC() throws Exception {

		AESCipherHelper helper = new AESCipherHelper();
		helper.setAlgorithm("AES/CBC/PKCS5Padding");
		helper.setSecretKey(new InMemoryResource(randomBytes(16)));
		helper.setInitVector(new InMemoryResource(randomBytes(16)));
		helper.afterPropertiesSet();

		for (int i = 0; i < 1000; i++) {
			UUID plain = UUID.randomUUID();
			byte[] enc = helper.encrypt(plain.toString().getBytes());
			byte[] dec = helper.decrypt(enc);
			assertThat(dec, is(plain.toString().getBytes()));
		}
	}

	@Test
	public void testECB() throws Exception {

		AESCipherHelper helper = new AESCipherHelper();
		helper.setAlgorithm("AES/ECB/PKCS5Padding");
		helper.setSecretKey(new InMemoryResource(randomBytes(16)));
		helper.afterPropertiesSet();

		for (int i = 0; i < 1000; i++) {
			UUID plain = UUID.randomUUID();
			byte[] enc = helper.encrypt(plain.toString().getBytes());
			byte[] dec = helper.decrypt(enc);
			assertThat(dec, is(plain.toString().getBytes()));
		}
	}

	private byte[] randomBytes(int size) {
		byte[] buff = new byte[size];
		random.nextBytes(buff);
		return buff;
	}

}
