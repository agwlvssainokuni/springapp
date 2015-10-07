/*
 * Copyright 2012,2015 agwlvssainokuni
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

/**
 * 整数値をコンマ区切りで整形 (文字列化) する。
 * 
 * @param {Number} num 整形する整数値。
 * @returns {String} 数値のカンマ区切り文字列。
 */
function numfmt(num) {

	if (typeof num != "number") {
		return "NaN";
	}

	var iNum = (num >= 0 ? Math.floor(num) : Math.ceil(num));

	var iNumS = (iNum >= 0 ? iNum : -iNum).toString();
	var pos = (iNumS.length % 3 === 0 ? 3 : iNumS.length % 3);
	var val = iNumS.substring(0, pos);
	for (; pos < iNumS.length; pos += 3) {
		val = val + "," + iNumS.substring(pos, pos + 3);
	}

	return (iNum >= 0 ? val : "-" + val);
}
