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

/**
 * Basic Latinのコード値であるか検証する。
 * 
 * @returns {Boolean} Basic Latinのコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isBasicLatin = function() {
	// Basic Latin (Unicode block)
	return this >= 0x0000 && this <= 0x007F;
};

/**
 * 半角数字のコード値であるか検証する。
 * 
 * @returns {Boolean} 半角数字のコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isNumeric = function() {
	// Basic Latin (Unicode block)
	// U+003x 0 1 2 3 4 5 6 7 8 9 : ; < = > ?
	return (this >= 0x0030 && this <= 0x0039); // '0'-'9'
};

/**
 * 半角英字(大文字)のコード値であるか検証する。
 * 
 * @returns {Boolean} 半角英字(大文字)のコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isUpper = function() {
	// Basic Latin (Unicode block)
	// U+004x @ A B C D E F G H I J K L M N O
	// U+005x P Q R S T U V W X Y Z [ \ ] ^ _
	return (this >= 0x0041 && this <= 0x005A); // 'A'-'Z'
};

/**
 * 半角英字(小文字)のコード値であるか検証する。
 * 
 * @returns {Boolean} 半角英字(小文字)のコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isLower = function() {
	// Basic Latin (Unicode block)
	// U+006x ` a b c d e f g h i j k l m n o
	// U+007x p q r s t u v w x y z { | } ~ DEL
	return (this >= 0x0061 && this <= 0x007A); // 'a'-'z'
};

/**
 * 半角英字のコード値であるか検証する。
 * 
 * @returns {Boolean} 半角英字のコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isAlpha = function() {
	return this.isUpper() || this.isLower();
};

/**
 * 半角カタカナのコード値であるか検証する。
 * 
 * @returns {Boolean} 半角カタカナのコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isHalfKatakana = function() {
	// Halfwidth and Fullwidth Forms (Unicode block)
	// U+FF6x ｠ ｡ ｢ ｣ ､ ･ ｦ ｧ ｨ ｩ ｪ ｫ ｬ ｭ ｮ ｯ
	// U+FF7x ｰ ｱ ｲ ｳ ｴ ｵ ｶ ｷ ｸ ｹ ｺ ｻ ｼ ｽ ｾ ｿ
	// U+FF8x ﾀ ﾁ ﾂ ﾃ ﾄ ﾅ ﾆ ﾇ ﾈ ﾉ ﾊ ﾋ ﾌ ﾍ ﾎ ﾏ
	// U+FF9x ﾐ ﾑ ﾒ ﾓ ﾔ ﾕ ﾖ ﾗ ﾘ ﾙ ﾚ ﾛ ﾜ ﾝ ﾞ ﾟ
	return (this >= 0xFF61 && this <= 0xFF9F);
};

/**
 * 全角数字のコード値であるか検証する。
 * 
 * @returns {Boolean} 全角数字のコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isFullNumeric = function() {
	// Halfwidth and Fullwidth Forms (Unicode block)
	// U+FF1x ０ １ ２ ３ ４ ５ ６ ７ ８ ９ ： ； ＜ ＝ ＞ ？
	return (this >= 0xFF10 && this <= 0xFF19);
};

/**
 * 全角英字(大文字)のコード値であるか検証する。
 * 
 * @returns {Boolean} 全角英字(大文字)のコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isFullUpper = function() {
	// Halfwidth and Fullwidth Forms (Unicode block)
	// U+FF2x ＠ Ａ Ｂ Ｃ Ｄ Ｅ Ｆ Ｇ Ｈ Ｉ Ｊ Ｋ Ｌ Ｍ Ｎ Ｏ
	// U+FF3x Ｐ Ｑ Ｒ Ｓ Ｔ Ｕ Ｖ Ｗ Ｘ Ｙ Ｚ ［ ＼ ］ ＾ ＿
	return (this >= 0xFF21 && this <= 0xFF3A);
};

/**
 * 全角英字(小文字)のコード値であるか検証する。
 * 
 * @returns {Boolean} 全角英字(小文字)のコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isFullLower = function() {
	// Halfwidth and Fullwidth Forms (Unicode block)
	// U+FF4x ｀ ａ ｂ ｃ ｄ ｅ ｆ ｇ ｈ ｉ ｊ ｋ ｌ ｍ ｎ ｏ
	// U+FF5x ｐ ｑ ｒ ｓ ｔ ｕ ｖ ｗ ｘ ｙ ｚ ｛ ｜ ｝ ～ ｟
	return (this >= 0xFF41 && this <= 0xFF5A);
};

/**
 * 全角英字のコード値であるか検証する。
 * 
 * @returns {Boolean} 全角英字のコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isFullAlpha = function() {
	return this.isFullUpper() || this.isFullLower();
};

/**
 * 全角ひらがなのコード値であるか検証する。
 * 
 * @returns {Boolean} 全角ひらがなのコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isFullHiragana = function() {
	// Hiragana (Unicode block)
	// U+304x ぁ あ ぃ い ぅ う ぇ え ぉ お か が き ぎ く
	// U+305x ぐ け げ こ ご さ ざ し じ す ず せ ぜ そ ぞ た
	// U+306x だ ち ぢ っ つ づ て で と ど な に ぬ ね の は
	// U+307x ば ぱ ひ び ぴ ふ ぶ ぷ へ べ ぺ ほ ぼ ぽ ま み
	// U+308x む め も ゃ や ゅ ゆ ょ よ ら り る れ ろ ゎ わ
	// U+309x ゐ ゑ を ん ゔ ゕ ゖ ゙ ゚ ゛ ゜ ゝ ゞ ゟ
	return (this >= 0x3040 && this <= 0x309F) // Hiragana (Unicode block)
			// import from KATAKANA (\u30A0 - \u30FF)
			|| this == 0x30A0 // '゠' from KATAKANA (not in Win31J)
			|| this == 0x30FB // '・' from KATAKANA
			|| this == 0x30FC // 'ー' from KATAKANA
			// \u30FD 'ヽ' and \u30FE 'ヾ' if iteration mark for KATAKANA
			|| this == 0x30FF // 'ヿ' from KATAKANA (not in Win31J)
			|| this == 0x3001 // '、'
			|| this == 0x3002 // '。'
			|| this == 0x300C // '「'
			|| this == 0x300D // '」'
			|| this == 0x300E // '『'
			|| this == 0x300F; // '』'
};

/**
 * 全角カタカナのコード値であるか検証する。
 * 
 * @returns {Boolean} 全角カタカナのコード値ならばtrue、さもなくばfalse。
 */
Number.prototype.isFullKatakana = function() {
	// Katakana (Unicode block)
	// U+30Ax ゠ ァ ア ィ イ ゥ ウ ェ エ ォ オ カ ガ キ ギ ク
	// U+30Bx グ ケ ゲ コ ゴ サ ザ シ ジ ス ズ セ ゼ ソ ゾ タ
	// U+30Cx ダ チ ヂ ッ ツ ヅ テ デ ト ド ナ ニ ヌ ネ ノ ハ
	// U+30Dx バ パ ヒ ビ ピ フ ブ プ ヘ ベ ペ ホ ボ ポ マ ミ
	// U+30Ex ム メ モ ャ ヤ ュ ユ ョ ヨ ラ リ ル レ ロ ヮ ワ
	// U+30Fx ヰ ヱ ヲ ン ヴ ヵ ヶ ヷ ヸ ヹ ヺ ・ ー ヽ ヾ ヿ
	// Katakana Phonetic Extensions (Unicode block)
	// U+31Fx ㇰ ㇱ ㇲ ㇳ ㇴ ㇵ ㇶ ㇷ ㇸ ㇹ ㇺ ㇻ ㇼ ㇽ ㇾ ㇿ
	return (this >= 0x30A0 && this <= 0x30FF) // Katakana (Unicode block)
			|| (this >= 0x31F0 && this <= 0x31FF) // Phonetic Extensions
			// import from HIRAGANA (\u3040 - \u309F)
			// \u3040, \u3097, \u3098 is reserved
			|| this == 0x3099 // MARK from HIRAGANA (not in Win31J)
			|| this == 0x309A // MARK from HIRAGANA (not in Win31J)
			|| this == 0x309B // '゛' from HIRAGANA
			|| this == 0x309C // '゜' from HIRAGANA
			// \u309D 'ゝ' and \u309E 'ゞ' is iteration mark for HIRAGANA
			|| this == 0x309F // 'ゟ' from HIRAGANA (not in Win31J)
			|| this == 0x3001 // '、'
			|| this == 0x3002 // '。'
			|| this == 0x300C // '「'
			|| this == 0x300D // '」'
			|| this == 0x300E // '『'
			|| this == 0x300F; // '』'
};

/**
 * 閏年であるか検証する。
 * 
 * @returns {Boolean} 閏年ならばtrue、さもなくばfalse。
 */
Number.prototype.isLeapYear = function() {
	if (this % 400 == 0) {
		return true;
	} else if (this % 100 == 0) {
		return false;
	} else if (this % 4 == 0) {
		return true;
	} else {
		return false;
	}
};

/**
 * 当月の日数を取得する。
 * 
 * @param {Number} year 年。
 * @returns {Number} 当月の日数。
 */
Number.prototype.getNumberOfDays = function(year) {
	if (this == 1 || this == 3 || this == 5 || this == 7 || this == 8 || this == 10 || this == 12) {
		return 31;
	} else if (this == 4 || this == 6 || this == 9 || this == 11) {
		return 30;
	} else if (this == 2) {
		if (year == undefined) {
			return 28;
		} else if (year.isLeapYear()) {
			return 29;
		} else {
			return 28;
		}
	} else {
		throw this;
	}
};

/**
 * ASCII文字列であるか検証する。
 * 
 * @returns {Boolean} ASCII文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isAscii = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isBasicLatin()) {
			return false;
		}
	}
	return true;
};

/**
 * 半角英字文字列であるか検証する。
 * 
 * @returns {Boolean} 半角英字文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isAlpha = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isAlpha()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 半角数字文字列であるか検証する。
 * 
 * @returns {Boolean} 半角数字文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isNumeric = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isNumeric()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 半角英数字文字列であるか検証する。
 * 
 * @returns {Boolean} 半角英数字文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isAlphaNumeric = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isNumeric()) {
			continue;
		}
		if (ch.isAlpha()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 半角カタカナ文字列であるか検証する。
 * 
 * @returns {Boolean} 半角カタカナ文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isHalfKatakana = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isHalfKatakana()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 半角文字列であるか検証する。
 * 
 * @returns {Boolean} 半角文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isHalfWidth = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isBasicLatin()) {
			continue;
		}
		if (ch.isHalfKatakana()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 全角英字文字列であるか検証する。
 * 
 * @returns {Boolean} 全角英字文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isFullAlpha = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isFullAlpha()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 全角数字文字列であるか検証する。
 * 
 * @returns {Boolean} 全角数字文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isFullNumeric = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isFullNumeric()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 全角英数字文字列であるか検証する。
 * 
 * @returns {Boolean} 全角英数字文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isFullAlphaNumeric = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isFullNumeric()) {
			continue;
		}
		if (ch.isFullAlpha()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 全角ひらがな文字列であるか検証する。
 * 
 * @returns {Boolean} 全角ひらがな文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isFullHiragana = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isFullHiragana()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 全角カタカナ文字列であるか検証する。
 * 
 * @returns {Boolean} 全角カタカナ文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isFullKatakana = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isFullKatakana()) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 全角文字列であるか検証する。
 * 
 * @returns {Boolean} 全角文字列ならばtrue、さもなくばfalse。
 */
String.prototype.isFullWidth = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!(ch.isBasicLatin() || ch.isHalfKatakana())) {
			continue;
		}
		return false;
	}
	return true;
};

/**
 * 数値の文字列表記形式であるか検証する。
 * 
 * @returns {Boolean} 数値の文字列表記形式ならばtrue、さもなくばfalse。
 */
String.prototype.isNumberFormat = function() {
	if (this.length <= 0) {
		return true;
	}
	var match = /^[-+]?(\d+(\.\d*)?|\.\d+)$/.exec(this);
	if (match == null) {
		return false;
	}
	return true;
};

/**
 * 日付の文字列表記形式であるか検証する。
 * 
 * @returns {Boolean} 日付の文字列表記形式ならばtrue、さもなくばfalse。
 */
String.prototype.isDateFormat = function() {
	if (this.length <= 0) {
		return true;
	}
	var match = /^(\d{4})\/(\d{2})\/(\d{2})$/.exec(this);
	if (match == null) {
		return false;
	}
	var y = Number(match[1]);
	var m = Number(match[2]);
	var d = Number(match[3]);
	return m >= 1 && m <= 12 && d >= 1 && d <= m.getNumberOfDays(y);
};

/**
 * 時刻の文字列表記形式であるか検証する。
 * 
 * @returns {Boolean} 時刻の文字列表記形式ならばtrue、さもなくばfalse。
 */
String.prototype.isTimeFormat = function() {
	if (this.length <= 0) {
		return true;
	}
	var match = /^([01][0-9]|2[0123]):([0-5][0-9]):([0-5][0-9])$/.exec(this);
	if (match == null) {
		return false;
	}
	return true;
};

/**
 * 日時の文字列表記形式であるか検証する。
 * 
 * @returns {Boolean} 日時の文字列表記形式ならばtrue、さもなくばfalse。
 */
String.prototype.isDateTimeFormat = function() {
	if (this.length <= 0) {
		return true;
	}
	var match = /^(\d{4})\/(\d{2})\/(\d{2}) ([01][0-9]|2[0123]):([0-5][0-9]):([0-5][0-9])$/.exec(this);
	if (match == null) {
		return false;
	}
	var y = Number(match[1]);
	var m = Number(match[2]);
	var d = Number(match[3]);
	return m >= 1 && m <= 12 && d >= 1 && d <= m.getNumberOfDays(y);
};
