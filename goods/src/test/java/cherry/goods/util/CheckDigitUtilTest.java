/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.goods.util;

import static cherry.goods.util.CheckDigitUtil.modulus10weight3;
import static java.math.BigInteger.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CheckDigitUtilTest {

	@Test
	public void testModulus10weight3() {
		// [ISBN]
		// 達人に学ぶ SQL徹底指南書
		assertEquals(0, modulus10weight3(valueOf(978479811516L)));
		// コンピュータアーキテクチャ技術入門
		assertEquals(7, modulus10weight3(valueOf(978477416426L)));
		// Chef実践入門
		assertEquals(4, modulus10weight3(valueOf(978477416500L)));
		// チーム開発実践入門
		assertEquals(1, modulus10weight3(valueOf(978477416428L)));
		// AngularJSリファレンス
		assertEquals(6, modulus10weight3(valueOf(978484433668L)));
		// 実践ハイパフォーマンスMySQL 第3版
		assertEquals(9, modulus10weight3(valueOf(978487311638L)));
		// データビジュアライゼーションのためのD3.js徹底入門
		assertEquals(4, modulus10weight3(valueOf(978479736886L)));
	}

	@Test
	public void testInstantiate() {
		try {
			assertNotNull(new CheckDigitUtil());
		} catch (Exception ex) {
			fail("Exception must be thrown");
		}
	}

}
