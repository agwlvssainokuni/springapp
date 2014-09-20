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

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.spring.common.lib.util.RandomUtil;

public class RSACipherHelperTest {

	private int loopCount = 1000;

	private int size = 245;

	@Test
	public void testEncDec() throws Exception {
		RSACipherHelper helper = createCipherHelper();
		for (int i = 0; i < loopCount; i++) {
			byte[] plain = RandomUtil.randomBytes(size);
			byte[] crypto = helper.encrypt(plain);
			assertThat(crypto, is(not(plain)));
			assertThat(helper.decrypt(crypto), is(plain));
		}
	}

	private RSACipherHelper createCipherHelper() throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		keygen.initialize(2048);
		KeyPair key = keygen.generateKeyPair();
		RSACipherHelper helper = new RSACipherHelper();
		helper.setAlgorithm("RSA/ECB/PKCS1Padding");
		helper.setKeyLoader(new RSAKeyLoader());
		helper.setPublicKey(new InMemoryResource(key.getPublic().getEncoded()));
		helper.setPrivateKey(new InMemoryResource(key.getPrivate().getEncoded()));
		helper.afterPropertiesSet();
		return helper;
	}

}
