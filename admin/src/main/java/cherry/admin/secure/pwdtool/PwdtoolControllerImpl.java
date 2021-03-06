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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import cherry.admin.PathDef;

/**
 * パスワードエンコーダツール。
 */
@Controller
public class PwdtoolControllerImpl implements PwdtoolController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ツールの画面を表示する。
	 */
	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_PWDTOOL_INIT);
		return mav;
	}

	/**
	 * 平文をエンコードした文字列を返却する。
	 */
	@Override
	public String encode(String plainText) {
		return passwordEncoder.encode(plainText);
	}

	/**
	 * 平文とエンコードした文字列を照合する。
	 */
	@Override
	public boolean match(String plainText, String encodedText) {
		return passwordEncoder.matches(plainText, encodedText);
	}

}
