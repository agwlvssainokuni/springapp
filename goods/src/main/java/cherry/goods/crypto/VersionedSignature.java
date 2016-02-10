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

import java.util.Map;

import cherry.goods.crypto.VersionStrategy.VersionedData;

public class VersionedSignature implements Signature {

	private Integer defaultVersion;

	private Map<Integer, Signature> signatureMap;

	private VersionStrategy<byte[], Integer> versionStrategy = new DefaultVersionStrategy();

	public void setDefaultVersion(Integer defaultVersion) {
		this.defaultVersion = defaultVersion;
	}

	public void setSignatureMap(Map<Integer, Signature> signatureMap) {
		this.signatureMap = signatureMap;
	}

	public void setVersionStrategy(VersionStrategy<byte[], Integer> versionStrategy) {
		this.versionStrategy = versionStrategy;
	}

	@Override
	public byte[] sign(byte[] in) {
		Signature signature = signatureMap.get(defaultVersion);
		if (signature == null) {
			throw new IllegalStateException("No matching Signature for version " + defaultVersion);
		}
		byte[] b = signature.sign(in);
		return versionStrategy.encode(b, defaultVersion);
	}

	@Override
	public boolean verify(byte[] in, byte[] signed) {
		VersionedData<byte[], Integer> vd = versionStrategy.decode(signed);
		Signature signature = signatureMap.get(vd.getVersion());
		if (signature == null) {
			throw new IllegalStateException("No matching Signature for version " + vd.getVersion());
		}
		return signature.verify(in, vd.getData());
	}

}
