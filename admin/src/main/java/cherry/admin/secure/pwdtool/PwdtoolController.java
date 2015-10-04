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

package cherry.admin.secure.pwdtool;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cherry.admin.PathDef;

/**
 * パスワードエンコーダツール。
 */
@RequestMapping(PathDef.URI_PWDTOOL)
public interface PwdtoolController {

	/**
	 * ツールの画面を表示する。
	 */
	@RequestMapping()
	ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request);

	/**
	 * 平文をエンコードした文字列を返却する。
	 */
	@RequestMapping(PathDef.SUBURI_ENCODE)
	@ResponseBody
	String encode(@RequestParam(PathDef.PARAM_PLAIN_TEXT) String plainText);

	/**
	 * 平文とエンコードした文字列を照合する。
	 */
	@RequestMapping(PathDef.SUBURI_MATCH)
	@ResponseBody
	boolean match(@RequestParam(PathDef.PARAM_PLAIN_TEXT) String plainText,
			@RequestParam(PathDef.PARAM_ENCODED_TEXT) String encodedText);

}
