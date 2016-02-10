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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

/**
 * RSA暗号アルゴリズムによる電子署名の機能を提供する。
 */
public class RSASignature implements Signature {

	/** RSA電子署名アルゴリズムを使用するためのアルゴリズム名を保持する。 */
	private String algorithm = "SHA256withRSA";

	/** RSA電子署名アルゴリズムで使用する公開鍵を保持する。 */
	private PublicKey publicKey;

	/** RSA電子署名アルゴリズムで使用する秘密鍵を保持する。 */
	private PrivateKey privateKey;

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setPublicKeyBytes(byte[] publicKeyBytes) throws InvalidKeySpecException {
		this.publicKey = KeyUtil.createRsaPublicKey(publicKeyBytes);
	}

	public void setPrivateKeyBytes(byte[] privateKeyBytes) throws InvalidKeySpecException {
		this.privateKey = KeyUtil.createRsaPrivateKey(privateKeyBytes);
	}

	public void setPrivateKeyBytes(byte[] privateKeyBytes, char[] password) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, InvalidKeySpecException {
		this.privateKey = KeyUtil.createRsaPrivateKey(privateKeyBytes, password);
	}

	@Override
	public byte[] sign(byte[] in) {
		try {
			java.security.Signature signature = java.security.Signature.getInstance(algorithm);
			signature.initSign(privateKey);
			signature.update(in);
			return signature.sign();
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public boolean verify(byte[] in, byte[] signed) {
		try {
			java.security.Signature signature = java.security.Signature.getInstance(algorithm);
			signature.initVerify(publicKey);
			signature.update(in);
			return signature.verify(signed);
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
