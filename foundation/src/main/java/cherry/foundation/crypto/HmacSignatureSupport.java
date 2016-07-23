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

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import cherry.goods.crypto.HmacSignature;

import com.google.common.io.ByteStreams;

/**
 * Hmacメッセージ認証の機能を提供する。
 */
public class HmacSignatureSupport extends HmacSignature implements InitializingBean {

	/** Hmacメッセージ認証で使用する共通鍵が定義されたファイルのパスをを保持する。 */
	private Resource secretKeyResource;

	public void setSecretKeyResource(Resource secretKeyResource) {
		this.secretKeyResource = secretKeyResource;
	}

	/**
	 * Hmacメッセージ認証による電子署名の機能を初期化する。<br />
	 * 具体的には、ファイルのパスとして指定された共通鍵を、ファイルから読込み、復号化し、JCE APIで使用する形式で保持する。
	 */
	@Override
	public void afterPropertiesSet() throws IOException {
		try (InputStream in = secretKeyResource.getInputStream()) {
			setSecretKeyBytes(ByteStreams.toByteArray(in), algorithm);
		}
	}

}
