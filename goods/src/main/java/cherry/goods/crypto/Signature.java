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

/**
 * 電子署名の機能のインタフェースを規定する。
 */
public interface Signature {

	/**
	 * 署名する。
	 * 
	 * @param in 原文。
	 * @return 電子署名。
	 */
	byte[] sign(byte[] in);

	/**
	 * 署名を検証する。
	 * 
	 * @param in 原文。
	 * @param signed 電子。
	 * @return 平文。
	 */
	boolean verify(byte[] in, byte[] signed);

}
