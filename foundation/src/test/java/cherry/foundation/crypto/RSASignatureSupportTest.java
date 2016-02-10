/*
 * Copyright 2016 agwlvssainokuni
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

package cherry.foundation.crypto;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

public class RSASignatureSupportTest {

	@Test
	public void testSignVerify() throws Exception {
		RSASignatureSupport impl = createCrypto();
		for (int i = 0; i < 100; i++) {
			byte[] plain = RandomUtils.nextBytes(245);
			byte[] signed = impl.sign(plain);
			assertNotEquals(plain, signed);
			assertTrue(impl.verify(plain, signed));
		}
	}

	private RSASignatureSupport createCrypto() throws Exception {
		KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
		keygen.initialize(2048);
		KeyPair key = keygen.generateKeyPair();
		RSASignatureSupport crypto = new RSASignatureSupport();
		crypto.setAlgorithm("SHA256withRSA");
		crypto.setPublicKeyResource(new InMemoryResource(key.getPublic().getEncoded()));
		crypto.setPrivateKeyResource(new InMemoryResource(key.getPrivate().getEncoded()));
		crypto.afterPropertiesSet();
		return crypto;
	}

}
