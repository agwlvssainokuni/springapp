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

package cherry.spring.common.lib.crypto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAKeyLoader implements KeyLoader {

	private final KeyFactory keyFactory;

	public RSAKeyLoader() throws IllegalStateException {
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public PublicKey loadPublicKey(InputStream in) {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(loadBytes(in));
		try {
			return keyFactory.generatePublic(keySpec);
		} catch (InvalidKeySpecException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public PrivateKey loadPrivateKey(InputStream in) {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(loadBytes(in));
		try {
			return keyFactory.generatePrivate(keySpec);
		} catch (InvalidKeySpecException ex) {
			throw new IllegalStateException(ex);
		}
	}

	byte[] loadBytes(InputStream in) {
		try {
			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				byte[] buff = new byte[8192];
				int len;
				while ((len = in.read(buff)) >= 0) {
					out.write(buff, 0, len);
				}
				return out.toByteArray();
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}
}
