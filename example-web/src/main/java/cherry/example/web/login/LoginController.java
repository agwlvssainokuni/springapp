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

package cherry.example.web.login;

import static cherry.example.web.PathDef.SUBURI_START;
import static cherry.example.web.PathDef.URI_LOGIN;

import java.util.Locale;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cherry.foundation.validator.groups.G9;

@RequestMapping(URI_LOGIN)
public interface LoginController {

	@RequestMapping(value = SUBURI_START)
	ModelAndView start(@Validated(G9.class) LoginForm form, BindingResult binding, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_START, params = "loginFailed")
	ModelAndView loginFailed(Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr);

	@RequestMapping(value = SUBURI_START, params = "loggedOut")
	ModelAndView loggedOut(Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr);

}
