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

package cherry.spring.fwcore.crypto;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.security.util.InMemoryResource;

import cherry.goods.util.RandomUtil;

public class VersionedCipherHelperTest {

	@Test
	public void simpleEncryptAndDecrypt() throws Exception {

		Map<Integer, CipherHelper> map = createCipherHelperMap(1);

		VersionedCipherHelper helper = new VersionedCipherHelper();
		helper.setDefaultVersion(0);
		helper.setCipherHelperMap(map);
		helper.setVersioningStrategy(new DefaultVersioningStrategy());

		for (int i = 0; i < 1000; i++) {
			byte[] plain = RandomUtil.randomBytes(1024);
			byte[] crypto = helper.encrypt(plain);
			assertThat(crypto, is(not(plain)));
			assertThat(helper.decrypt(crypto), is(plain));
		}
	}

	@Test
	public void versionedEncryptAndDecrypt() throws Exception {

		Map<Integer, CipherHelper> map = createCipherHelperMap(2);

		VersionedCipherHelper helper0 = new VersionedCipherHelper();
		helper0.setDefaultVersion(0);
		helper0.setCipherHelperMap(map);

		VersionedCipherHelper helper1 = new VersionedCipherHelper();
		helper1.setDefaultVersion(1);
		helper1.setCipherHelperMap(map);

		for (int i = 0; i < 1000; i++) {
			byte[] plain = RandomUtil.randomBytes(1024);
			byte[] crypto = helper0.encrypt(plain);
			assertThat(crypto, is(not(plain)));
			assertThat(crypto, is(not(helper1.encrypt(plain))));
			assertThat(helper0.decrypt(crypto), is(plain));
			assertThat(helper1.decrypt(crypto), is(plain));
		}
	}

	@Test
	public void errorNoMatchingCipherHelperToEncrypt() throws Exception {
		Map<Integer, CipherHelper> map = createCipherHelperMap(1);
		VersionedCipherHelper helper0 = new VersionedCipherHelper();
		helper0.setDefaultVersion(1);
		helper0.setCipherHelperMap(map);
		try {
			byte[] plain = RandomUtil.randomBytes(1024);
			helper0.encrypt(plain);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	@Test
	public void errorNoMatchingCipherHelperToDecrypt() throws Exception {

		Map<Integer, CipherHelper> map1 = createCipherHelperMap(2);
		Map<Integer, CipherHelper> map0 = new HashMap<>();
		map0.put(0, map1.get(0));

		VersionedCipherHelper helper0 = new VersionedCipherHelper();
		helper0.setDefaultVersion(0);
		helper0.setCipherHelperMap(map0);

		VersionedCipherHelper helper1 = new VersionedCipherHelper();
		helper1.setDefaultVersion(1);
		helper1.setCipherHelperMap(map1);

		try {
			byte[] plain = RandomUtil.randomBytes(1024);
			byte[] crypto = helper1.encrypt(plain);
			assertThat(helper1.decrypt(crypto), is(plain));

			helper0.decrypt(crypto);
			fail("Exception must be thrown");
		} catch (IllegalStateException ex) {
			// OK
		}
	}

	private Map<Integer, CipherHelper> createCipherHelperMap(int num)
			throws IOException {
		Map<Integer, CipherHelper> map = new HashMap<>();
		for (int i = 0; i < num; i++) {
			map.put(i, createAESCipherHelper());
		}
		return map;
	}

	private CipherHelper createAESCipherHelper() throws IOException {
		AESCipherHelper helper = new AESCipherHelper();
		helper.setSecretKey(new InMemoryResource(RandomUtil.randomBytes(16)));
		helper.setInitVector(new InMemoryResource(RandomUtil.randomBytes(16)));
		helper.afterPropertiesSet();
		return helper;
	}

}
