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

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cherry.foundation.validator.groups.G1;
import cherry.foundation.validator.groups.G2;
import cherry.sqlman.PathDef;

@RequestMapping(PathDef.URI_PASSWORD)
public interface PasswordRequestController {

	@ModelAttribute
	PasswordRequestForm getForm();

	@RequestMapping()
	ModelAndView start(Locale locale, SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(PathDef.SUBURI_EXECUTE)
	ModelAndView execute(@Validated(G1.class) PasswordRequestForm form, BindingResult binding, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr);

	@RequestMapping(params = PathDef.PARAM_TOKEN)
	ModelAndView edit(@RequestParam(PathDef.PARAM_TOKEN) String token, Locale locale, SitePreference sitePref,
			HttpServletRequest request);

	@RequestMapping(value = PathDef.SUBURI_EXECUTE, params = PathDef.PARAM_TOKEN)
	ModelAndView update(@RequestParam(PathDef.PARAM_TOKEN) String token, @Validated(G2.class) PasswordRequestForm form,
			BindingResult binding, Locale locale, SitePreference sitePref, HttpServletRequest request,
			RedirectAttributes redirAttr);

}
