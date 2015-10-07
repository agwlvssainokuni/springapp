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
 * メッセージ書式整形。
 * 
 * @param {String} template テンプレート文字列。
 * @param {Object} 埋込みパラメタ。
 * @returns {String} 書式設計したメッセージ文字列。
 */
function msgfmt(template, param) {
	return template.replace(/(\{([0-9]+)\}|\$\{([-_0-9a-zA-Z]+)\})/g, function(m, dmy0, index, name, o, t) {

		if (index !== undefined && index != "") {
			var v = param[Number(index)];
			if (v === undefined) {
				return "";
			}
			return v;
		}

		var v = param[name];
		if (v === undefined) {
			return "";
		}
		return v;
	});
}
