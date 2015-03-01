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

import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;

import java.math.BigInteger;

public class CheckDigitUtil {

	public static int modulus10weight3(BigInteger value) {
		return modulus(value, 10, 3, 1);
	}

	public static int modulus(BigInteger value, int modulo, int... weight) {
		BigInteger[] temp = new BigInteger[] { value, ZERO };
		int result = 0;
		for (int i = 0; temp[0].compareTo(ZERO) != 0; i++) {
			temp = temp[0].divideAndRemainder(valueOf(modulo));
			result = result + temp[1].multiply(valueOf(weight[i % weight.length])).intValue();
		}
		result = modulo - result % modulo;
		return (result >= modulo ? 0 : result);
	}

}
