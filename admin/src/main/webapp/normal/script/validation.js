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
	return this >= 0x0000 && this <= 0x007F;
};

Number.prototype.isNumeric = function() {
	return (this >= 0x0030 && this <= 0x0039); // '0'-'9'
};

Number.prototype.isUpper = function() {
	return (this >= 0x0041 && this <= 0x005A); // 'A'-'Z'
};

Number.prototype.isLower = function() {
	return (this >= 0x0061 && this <= 0x007A); // 'a'-'z'
};

Number.prototype.isAlpha = function() {
	return this.isUpper() || this.isLower();
};

Number.prototype.isHalfKatakana = function() {
	return (this >= 0xFF61 && this <= 0xFF9F);

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
