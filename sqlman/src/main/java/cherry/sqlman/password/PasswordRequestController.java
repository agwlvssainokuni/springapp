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

package cherry.sqlman.password;

import static cherry.sqlman.ParamDef.REQ_TO;
import static cherry.sqlman.ParamDef.REQ_TOKEN;
import static cherry.sqlman.PathDef.SUBURI_CREATE;
import static cherry.sqlman.PathDef.SUBURI_EDIT;
import static cherry.sqlman.PathDef.SUBURI_START;
import static cherry.sqlman.PathDef.SUBURI_UPDATE;
import static cherry.sqlman.PathDef.URI_PASSWORD;

import java.util.Locale;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cherry.foundation.validator.groups.G1;
import cherry.foundation.validator.groups.G2;
import cherry.foundation.validator.groups.G9;

@RequestMapping(URI_PASSWORD)
public interface PasswordRequestController {

	@RequestMapping()
	ModelAndView init(@RequestParam(value = REQ_TO, required = false) String redirTo,
			@RequestParam(value = REQ_TOKEN, required = false) String token, Locale locale, SitePreference sitePref,
			NativeWebRequest request);

	@RequestMapping(value = SUBURI_START)
	ModelAndView start(@Validated(G9.class) PasswordRequestForm form, BindingResult binding, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_CREATE)
	ModelAndView create(@Validated(G1.class) PasswordRequestForm form, BindingResult binding, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr);

	@RequestMapping(value = SUBURI_EDIT, params = { REQ_TOKEN })
	ModelAndView edit(@RequestParam(REQ_TOKEN) String token, @Validated(G9.class) PasswordRequestForm form,
			BindingResult binding, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_UPDATE, params = { REQ_TOKEN })
	ModelAndView update(@RequestParam(REQ_TOKEN) String token, @Validated(G2.class) PasswordRequestForm form,
			BindingResult binding, Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr);

}
