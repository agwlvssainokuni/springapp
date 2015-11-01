/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.example.web.home;

import static cherry.example.web.ParamDef.REQ_CLEAR;
import static cherry.example.web.ParamDef.REQ_FM;
import static cherry.example.web.ParamDef.REQ_TO;
import static cherry.example.web.PathDef.URI_NAVI;

import java.util.Locale;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import cherry.foundation.validator.groups.G9;

@RequestMapping(URI_NAVI)
@SessionAttributes(types = { NaviForm.class })
public interface NaviController {

	@RequestMapping()
	ModelAndView back(@Validated(G9.class) NaviForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(params = { REQ_TO, REQ_FM })
	ModelAndView save1(@RequestParam(REQ_TO) String to, @RequestParam(REQ_FM) String fm,
			@Validated(G9.class) NaviForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(params = { REQ_TO })
	ModelAndView save2(@RequestParam(REQ_TO) String to, @RequestHeader("referer") String referer,
			@Validated(G9.class) NaviForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(params = { REQ_CLEAR })
	ModelAndView clear(@Validated(G9.class) NaviForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, SessionStatus status);

}
