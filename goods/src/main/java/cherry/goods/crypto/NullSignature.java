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

import java.util.Arrays;

/**
 * 電子署名のインタフェースで、実際は署名しない機能を提供する。
 */
public class NullSignature implements Signature {

	@Override
	public byte[] sign(byte[] in) {
		return in;
	}

	@Override
	public boolean verify(byte[] in, byte[] signed) {
		return Arrays.equals(in, signed);
	}

}
