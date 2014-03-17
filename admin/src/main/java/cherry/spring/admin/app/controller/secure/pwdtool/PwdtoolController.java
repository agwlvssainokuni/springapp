/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.admin.app.controller.secure.pwdtool;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * パスワードエンコーダツール。
 */
@Controller
@RequestMapping(PwdtoolController.URI_PATH)
public interface PwdtoolController {

	public static final String URI_PATH = "/secure/pwdtool";

	public static final String URI_PATH_ENCODE = "encode";

	public static final String PARAM_PLAIN_TEXT = "plainText";

	/**
	 * ツールの画面を表示する。
	 */
	@RequestMapping()
	ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	/**
	 * 平文をエンコードした文字列を返却する。
	 */
	@RequestMapping(URI_PATH_ENCODE)
	@ResponseBody
	String encode(@RequestParam(PARAM_PLAIN_TEXT) String plainText);

}