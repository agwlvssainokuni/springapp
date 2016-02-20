/*
 * Copyright 2014,2015 agwlvssainokuni
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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * AES暗号アルゴリズムによる暗号化/復号化の機能を提供する。
 */
public class AESCrypto implements Crypto {

	/** AES暗号アルゴリズムを使用するためのアルゴリズム名を保持する。 */
	private String algorithm = "AES/CBC/PKCS5Padding";

	/** AES暗号アルゴリズムで使用する共通鍵、初期化ベクタを復号化する機能を保持する。 */
	private Crypto keyCrypto = new NullCrypto();

	/** AES暗号アルゴリズムで使用する共通鍵を保持する。 */
	private Key secretKey;

	/** AES暗号アルゴリズムで使用する初期化ベクタを生成する。 */
	private Random random = new SecureRandom();

	/** AES暗号アルゴリズムで使用する初期化ベクタの長さを保持する。 */
	private final int initVectorLength = 128 / 8;

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setKeyCrypto(Crypto keyCrypto) {
		this.keyCrypto = keyCrypto;
	}

	public void setSecretKeyBytes(byte[] secretKeyBytes) {
		byte[] keyBin = keyCrypto.decrypt(secretKeyBytes);
		this.secretKey = KeyUtil.createAesSecretKey(keyBin);
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	@Override
	public byte[] encrypt(byte[] in) {
		try {
			byte[] iv = new byte[initVectorLength];
			random.nextBytes(iv);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, KeyUtil.createAesInitVector(iv));
			byte[] crypto = cipher.doFinal(in);
			byte[] out = new byte[iv.length + crypto.length];
			System.arraycopy(iv, 0, out, 0, iv.length);
			System.arraycopy(crypto, 0, out, iv.length, crypto.length);
			return out;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
				| InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public byte[] decrypt(byte[] in) {
		try {
			byte[] iv = new byte[initVectorLength];
			byte[] crypto = new byte[in.length - initVectorLength];
			System.arraycopy(in, 0, iv, 0, iv.length);
			System.arraycopy(in, iv.length, crypto, 0, crypto.length);
			Cipher cipher = Cipher.getInstance(algorithm);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, KeyUtil.createAesInitVector(iv));
			return cipher.doFinal(crypto);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
				| InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
