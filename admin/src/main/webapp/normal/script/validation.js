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

Number.prototype.isBasicLatin = function() {
	// Basic Latin (Unicode block)
	return this >= 0x0000 && this <= 0x007F;
};

Number.prototype.isNumeric = function() {
	// Basic Latin (Unicode block)
	// U+003x 0 1 2 3 4 5 6 7 8 9 : ; < = > ?
	return (this >= 0x0030 && this <= 0x0039); // '0'-'9'
};

Number.prototype.isUpper = function() {
	// Basic Latin (Unicode block)
	// U+004x @ A B C D E F G H I J K L M N O
	// U+005x P Q R S T U V W X Y Z [ \ ] ^ _
	return (this >= 0x0041 && this <= 0x005A); // 'A'-'Z'
};

Number.prototype.isLower = function() {
	// Basic Latin (Unicode block)
	// U+006x ` a b c d e f g h i j k l m n o
	// U+007x p q r s t u v w x y z { | } ~ DEL
	return (this >= 0x0061 && this <= 0x007A); // 'a'-'z'
};

Number.prototype.isAlpha = function() {
	return this.isUpper() || this.isLower();
};

Number.prototype.isHalfKatakana = function() {
	// Halfwidth and Fullwidth Forms (Unicode block)
	// U+FF6x ｠ ｡ ｢ ｣ ､ ･ ｦ ｧ ｨ ｩ ｪ ｫ ｬ ｭ ｮ ｯ
	// U+FF7x ｰ ｱ ｲ ｳ ｴ ｵ ｶ ｷ ｸ ｹ ｺ ｻ ｼ ｽ ｾ ｿ
	// U+FF8x ﾀ ﾁ ﾂ ﾃ ﾄ ﾅ ﾆ ﾇ ﾈ ﾉ ﾊ ﾋ ﾌ ﾍ ﾎ ﾏ
	// U+FF9x ﾐ ﾑ ﾒ ﾓ ﾔ ﾕ ﾖ ﾗ ﾘ ﾙ ﾚ ﾛ ﾜ ﾝ ﾞ ﾟ
	return (this >= 0xFF61 && this <= 0xFF9F);
};

Number.prototype.isFullNumeric = function() {
	// Halfwidth and Fullwidth Forms (Unicode block)
	// U+FF1x ０ １ ２ ３ ４ ５ ６ ７ ８ ９ ： ； ＜ ＝ ＞ ？
	return (this >= 0xFF10 && this <= 0xFF19);
};

Number.prototype.isFullUpper = function() {
	// Halfwidth and Fullwidth Forms (Unicode block)
	// U+FF2x ＠ Ａ Ｂ Ｃ Ｄ Ｅ Ｆ Ｇ Ｈ Ｉ Ｊ Ｋ Ｌ Ｍ Ｎ Ｏ
	// U+FF3x Ｐ Ｑ Ｒ Ｓ Ｔ Ｕ Ｖ Ｗ Ｘ Ｙ Ｚ ［ ＼ ］ ＾ ＿
	return (this >= 0xFF21 && this <= 0xFF3A);
};

Number.prototype.isFullLower = function() {
	// Halfwidth and Fullwidth Forms (Unicode block)
	// U+FF4x ｀ ａ ｂ ｃ ｄ ｅ ｆ ｇ ｈ ｉ ｊ ｋ ｌ ｍ ｎ ｏ
	// U+FF5x ｐ ｑ ｒ ｓ ｔ ｕ ｖ ｗ ｘ ｙ ｚ ｛ ｜ ｝ ～ ｟
	return (this >= 0xFF41 && this <= 0xFF5A);
};

Number.prototype.isFullAlpha = function() {
	return this.isFullUpper() || this.isFullLower();
};

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
			|| this == 0x30FF; // 'ヿ' from KATAKANA (not in Win31J)
};

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
			|| this == 0x309F; // 'ゟ' from HIRAGANA (not in Win31J)
};

String.prototype.isAscii = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isBasicLatin()) {
			return false;
		}
	}
	return true;
};

String.prototype.isAlpha = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isAlpha()) {
			return false;
		}
	}
	return true;
};

String.prototype.isNumeric = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isNumeric()) {
			return false;
		}
	}
	return true;
};

String.prototype.isAlphaNumeric = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isNumeric()) {
			return false;
		}
		if (!ch.isAlpha()) {
			return false;
		}
	}
	return true;
};

String.prototype.isHalfKatakana = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isHalfKatakana()) {
			return false;
		}
	}
	return true;
};

String.prototype.isHalfWidth = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isBasicLatin()) {
			return false;
		}
		if (!ch.isHalfKatakana()) {
			return false;
		}
	}
	return true;
};

String.prototype.isFullAlpha = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isFullApha()) {
			return false;
		}
	}
	return true;
};

String.prototype.isFullNumeric = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isFullNumeric()) {
			return false;
		}
	}
	return true;
};

String.prototype.isFullAlphaNumeric = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isFullNumeric()) {
			return false;
		}
		if (!ch.isFullAlpha()) {
			return false;
		}
	}
	return true;
};

String.prototype.isFullHiragana = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isFullHiragana()) {
			return false;
		}
	}
	return true;
};

String.prototype.isFullKatakana = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (!ch.isFullKatakana()) {
			return false;
		}
	}
	return true;
};

String.prototype.isFullWidth = function() {
	for (var i = 0; i < this.length; i++) {
		var ch = this.charCodeAt(i);
		if (ch.isHalfWidth()) {
			return false;
		}
	}
	return true;
};
