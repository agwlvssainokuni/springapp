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

package cherry.goods.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Hmacメッセージ認証の機能を提供する。
 */
public class HmacSignature implements Signature {

	/** Hmacメッセージ認証を使用するためのアルゴリズム名を保持する。 */
	protected String algorithm = "HmacSHA256";

	/** Hmacメッセージ認証で使用する共通鍵を復号化する機能を保持する。 */
	private Crypto keyCrypto = new NullCrypto();

	/** Hmacメッセージ認証で使用する共通鍵を保持する。 */
	private Key secretKey;

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setKeyCrypto(Crypto keyCrypto) {
		this.keyCrypto = keyCrypto;
	}

	public void setSecretKeyBytes(byte[] secretKeyBytes, String algorithm) {
		byte[] keyBin = keyCrypto.decrypt(secretKeyBytes);
		this.secretKey = new SecretKeySpec(keyBin, algorithm);
	}

	@Override
	public byte[] sign(byte[] in) {
		return encode(in);
	}

	@Override
	public boolean verify(byte[] in, byte[] signed) {
		return Arrays.equals(encode(in), signed);
	}

	private byte[] encode(byte[] in) {
		try {
			Mac mac = Mac.getInstance(algorithm);
			mac.init(secretKey);
			return mac.doFinal(in);
		} catch (NoSuchAlgorithmException | InvalidKeyException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
