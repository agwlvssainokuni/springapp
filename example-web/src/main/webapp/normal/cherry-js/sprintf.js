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
 * 文字列書式整形。
 * 
 * @param {String} template テンプレート文字列。
 * @param {Object} args... 可変長引数。テンプレートに埋込む値を指定する。
 * @returns {String} 整形した結果文字列。
 */
function sprintf(template) {

	var genpad = function(ch, len) {
		var pad = "";
		for (var i = 0; i < len; i++) {
			pad += ch;
		}
		return pad;
	};

	var index = 1;
	return template.replace(/%([\-+0 ]*)([1-9][0-9]*)?(\.([1-9][0-9]*))?([dfs%])/g, function(m, flag, widthArg, dmy0,
			precArg, type, o, t) {

		if (type == "%") {
			return "%";
		}

		var width = (widthArg === undefined ? 0 : Number(widthArg));
		var prec = (precArg === undefined ? 0 : Number(precArg));

		var src = sprintf.arguments[index++];
		if (type == "s") {

			var valS = src.toString();

			if (prec !== 0 && valS.length > prec) {
				valS = valS.substring(0, prec);
			}

			if (width === 0) {
				return valS;
			}
			if (valS.length >= width) {
				return valS;
			}

			if (flag.indexOf("-") >= 0) {
				return valS + genpad(" ", width - valS.length);
			} else if (flag.indexOf("0") >= 0) {
				return genpad("0", width - valS.length) + valS;
			} else {
				return genpad(" ", width - valS.length) + valS;
			}
		}

		if (typeof src != "number") {
			return "NaN";
		}

		var valN;
		if (type == "d") {
			valN = Math.floor(src >= 0 ? src : -src).toString();
			if (prec !== 0 && valN.length < prec) {
				valN = genpad("0", prec - valN.length) + valN;
			}
		} else {
			var absVal = (src >= 0 ? src : -src);
			var intVal = Math.floor(absVal);
			var fracVal = absVal - intVal;
			valN = intVal.toString();
			if (prec !== 0) {
				var v = Math.floor(fracVal * Math.pow(10, prec)).toString();
				valN = valN + "." + genpad("0", prec - v.length) + v;
			} else {
				var v = fracVal.toString();
				if (v == "0") {
					valN = valN + ".0";
				} else {
					valN = valN + v.substring(1, v.length);
				}
			}
		}

		if (width === 0) {
			return (src >= 0 ? valN : "-" + valN);
		}
		if (valN.length + (src >= 0 ? 0 : 1) >= width) {
			return (src >= 0 ? valN : "-" + valN);
		}

		if (src >= 0) {
			if (flag.indexOf("-") >= 0) {
				if (flag.indexOf("+") >= 0) {
					return "+" + valN + genpad(" ", width - valN.length - 1);
				} else if (flag.indexOf(" ") >= 0) {
					return " " + valN + genpad(" ", width - valN.length - 1);
				} else {
					return valN + genpad(" ", width - valN.length);
				}
			} else if (flag.indexOf("0") >= 0) {
				if (flag.indexOf("+") >= 0) {
					return "+" + genpad("0", width - valN.length - 1) + valN;
				} else if (flag.indexOf(" ") >= 0) {
					return " " + genpad("0", width - valN.length - 1) + valN;
				} else {
					return genpad("0", width - valN.length) + valN;
				}
			} else {
				if (flag.indexOf("+") >= 0) {
					return genpad(" ", width - valN.length - 1) + "+" + valN;
				} else if (flag.indexOf(" ") >= 0) {
					return genpad(" ", width - valN.length) + valN;
				} else {
					return genpad(" ", width - valN.length) + valN;
				}
			}
		} else {
			if (flag.indexOf("-") >= 0) {
				return "-" + valN + genpad(" ", width - valN.length - 1);
			} else if (flag.indexOf("0") >= 0) {
				return "-" + genpad("0", width - valN.length - 1) + valN;
			} else {
				return genpad(" ", width - valN.length - 1) + "-" + valN;
			}
		}
	});
}
