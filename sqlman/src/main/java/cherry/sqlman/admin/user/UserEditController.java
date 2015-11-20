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

package cherry.sqlman.admin.user;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cherry.sqlman.ParamDef;
import cherry.sqlman.PathDef;

@RequestMapping(PathDef.URI_ADMIN_USER)
public interface UserEditController {

	@RequestMapping(PathDef.SUBURI_EDIT)
	ModelAndView add(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(PathDef.SUBURI_UPDATE)
	ModelAndView create(@Validated UserEditForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr);

	@RequestMapping(value = PathDef.SUBURI_EDIT, params = ParamDef.REQ_ID)
	ModelAndView edit(@RequestParam(ParamDef.REQ_ID) Integer id, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(value = PathDef.SUBURI_UPDATE, params = ParamDef.REQ_ID)
	ModelAndView update(@RequestParam(ParamDef.REQ_ID) Integer id, @Validated UserEditForm form,
			BindingResult binding, Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request, RedirectAttributes redirAttr);

}
