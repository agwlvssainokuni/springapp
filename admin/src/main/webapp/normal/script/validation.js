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

$(function() {

	var chartype = [ {
		type : "app-check-ascii",
		validate : function(v) {
			return v.isAscii();
		},
		message : "Should be ascii"
	}, {
		type : "app-check-alpha",
		validate : function(v) {
			return v.isAlpha();
		},
		message : "Should be alphabet"
	}, {
		type : "app-check-numeric",
		validate : function(v) {
			return v.isNumeric();
		},
		message : "Should be numeric"
	}, {
		type : "app-check-alphanumeric",
		validate : function(v) {
			return v.isAlphaNumeric();
		},
		message : "Should be alphanumeric"
	}, {
		type : "app-check-halfkatakana",
		validate : function(v) {
			return v.isHalfKatakana();
		},
		message : "Should be half katakana"
	}, {
		type : "app-check-halfwidth",
		validate : function(v) {
			return v.isHalfWidth();
		},
		message : "Should be half width"
	}, {
		type : "app-check-fullalpha",
		validate : function(v) {
			return v.isFullAlpha();
		},
		message : "Should be full alpha"
	}, {
		type : "app-check-fullnumeric",
		validate : function(v) {
			return v.isFullNumeric();
		},
		message : "Should be full numeric"
	}, {
		type : "app-check-fullalphanumeric",
		validate : function(v) {
			return v.isFullAlphaNumeric();
		},
		message : "Should be full alphanumeric"
	}, {
		type : "app-check-fullhiragana",
		validate : function(v) {
			return v.isFullHiragana();
		},
		message : "Should be full hiragana"
	}, {
		type : "app-check-fullkatakana",
		validate : function(v) {
			return v.isFullKatakana();
		},
		message : "Should be full katakana"
	}, {
		type : "app-check-fullwidth",
		validate : function(v) {
			return v.isFullWidth();
		},
		message : "Should be full width"
	}, {
		type : "app-check-number",
		validate : function(v) {
			return v.isNumberFormat();
		},
		message : "Should be number"
	}, {
		type : "app-check-date",
		validate : function(v) {
			return v.isDateFormat();
		},
		message : "Should be date"
	}, {
		type : "app-check-time",
		validate : function(v) {
			return v.isTimeFormat();
		},
		message : "Should be time"
	}, {
		type : "app-check-datetime",
		validate : function(v) {
			return v.isDateTimeFormat();
		},
		message : "Should be datetime"
	} ];

	var rangecheck = [ {
		type : "minlength",
		validate : function(v, p) {
			return (v.length <= 0) || (v.length >= p);
		},
		message : "Should be at least {0} characters"
	}, {
		type : "maxlength",
		validate : function(v, p) {
			return (v.length <= 0) || (v.length <= p);
		},
		message : "Should be at most {0} characters"
	}, {
		type : "min",
		validate : function(v, p) {
			return (v.length <= 0) || !v.isNumberFormat() || (Number(v) >= p);
		},
		message : "Should be greater than or equal to {0}"
	}, {
		type : "max",
		validate : function(v, p) {
			return (v.length <= 0) || !v.isNumberFormat() || (Number(v) <= p);
		},
		message : "Should be less than or equal to {0}"
	} ];

	$("input, textarea").each(function(index) {

		var validation = false;
		for (var i = 0; i < chartype.length; i++) {
			if ($(this).hasClass(chartype[i].type)) {
				validation = true;
			}
		}
		for (var i = 0; i < rangecheck.length; i++) {
			if ($(this).data(rangecheck[i].type) != undefined) {
				validation = true;
			}
		}
		if (validation) {
			$(this).blur(function(event) {
				$(this).removeClass("ui-state-error");
			});
		}

		for (var i = 0; i < chartype.length; i++) {
			if ($(this).hasClass(chartype[i].type)) {
				$(this).blur((function(ct) {
					return function(event) {
						if (!ct.validate($(this).val())) {
							$(this).addClass("ui-state-error");
							alert(ct.message);
						}
					};
				})(chartype[i]));
			}
		}
		for (var i = 0; i < rangecheck.length; i++) {
			if ($(this).data(rangecheck[i].type) != undefined) {
				$(this).blur((function(rc, p) {
					return function(event) {
						if (!rc.validate($(this).val(), p)) {
							$(this).addClass("ui-state-error");
							alert(msgfmt(rc.message, [ p ]));
						}
					}
				})(rangecheck[i], Number($(this).data(rangecheck[i].type))));
			}
		}
	});

});
