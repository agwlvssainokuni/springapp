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

package cherry.goods.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 数値操作ユーティリティ。<br />
 */
public class NumberUtil {

	/** 小数点第1位で四捨五入。 */
	public static BigDecimal halfUp1(BigDecimal number) {
		return number.setScale(0, RoundingMode.HALF_UP);
	}

	/** 小数点第2位で四捨五入。 */
	public static BigDecimal halfUp2(BigDecimal number) {
		return number.setScale(1, RoundingMode.HALF_UP);
	}

	/** 小数点第3位で四捨五入。 */
	public static BigDecimal halfUp3(BigDecimal number) {
		return number.setScale(2, RoundingMode.HALF_UP);
	}

	/** 小数点第4位で四捨五入。 */
	public static BigDecimal halfUp4(BigDecimal number) {
		return number.setScale(3, RoundingMode.HALF_UP);
	}

	/** 小数点第1位で切上げ (絶対値が大きくなる方向へ切上げ)。 */
	public static BigDecimal roundUp1(BigDecimal number) {
		return number.setScale(0, RoundingMode.UP);
	}

	/** 小数点第2位で切上げ (絶対値が大きくなる方向へ切上げ)。 */
	public static BigDecimal roundUp2(BigDecimal number) {
		return number.setScale(1, RoundingMode.UP);
	}

	/** 小数点第3位で切上げ (絶対値が大きくなる方向へ切上げ)。 */
	public static BigDecimal roundUp3(BigDecimal number) {
		return number.setScale(2, RoundingMode.UP);
	}

	/** 小数点第4位で切上げ (絶対値が大きくなる方向へ切上げ)。 */
	public static BigDecimal roundUp4(BigDecimal number) {
		return number.setScale(3, RoundingMode.UP);
	}

	/** 小数点第1位で切下げ (絶対値が小さくなる方向へ切下げ)。 */
	public static BigDecimal roundDown1(BigDecimal number) {
		return number.setScale(0, RoundingMode.DOWN);
	}

	/** 小数点第2位で切下げ (絶対値が小さくなる方向へ切下げ)。 */
	public static BigDecimal roundDown2(BigDecimal number) {
		return number.setScale(1, RoundingMode.DOWN);
	}

	/** 小数点第3位で切下げ (絶対値が小さくなる方向へ切下げ)。 */
	public static BigDecimal roundDown3(BigDecimal number) {
		return number.setScale(2, RoundingMode.DOWN);
	}

	/** 小数点第4位で切下げ (絶対値が小さくなる方向へ切下げ)。 */
	public static BigDecimal roundDown4(BigDecimal number) {
		return number.setScale(3, RoundingMode.DOWN);
	}

	/** 小数点第1位で切上げ (大きくなる方向へ切上げ)。 */
	public static BigDecimal ceiling1(BigDecimal number) {
		return number.setScale(0, RoundingMode.CEILING);
	}

	/** 小数点第2位で切上げ (大きくなる方向へ切上げ)。 */
	public static BigDecimal ceiling2(BigDecimal number) {
		return number.setScale(1, RoundingMode.CEILING);
	}

	/** 小数点第3位で切上げ (大きくなる方向へ切上げ)。 */
	public static BigDecimal ceiling3(BigDecimal number) {
		return number.setScale(2, RoundingMode.CEILING);
	}

	/** 小数点第4位で切上げ (大きくなる方向へ切上げ)。 */
	public static BigDecimal ceiling4(BigDecimal number) {
		return number.setScale(3, RoundingMode.CEILING);
	}

	/** 小数点第1位で切下げ (小さくなる方向へ切下げ)。 */
	public static BigDecimal floor1(BigDecimal number) {
		return number.setScale(0, RoundingMode.FLOOR);
	}

	/** 小数点第2位で切下げ (小さくなる方向へ切下げ)。 */
	public static BigDecimal floor2(BigDecimal number) {
		return number.setScale(1, RoundingMode.FLOOR);
	}

	/** 小数点第3位で切下げ (小さくなる方向へ切下げ)。 */
	public static BigDecimal floor3(BigDecimal number) {
		return number.setScale(2, RoundingMode.FLOOR);
	}

	/** 小数点第4位で切下げ (小さくなる方向へ切下げ)。 */
	public static BigDecimal floor4(BigDecimal number) {
		return number.setScale(3, RoundingMode.FLOOR);
	}

}
