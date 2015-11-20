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

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.sqlman.ParamDef;
import cherry.sqlman.PathDef;

@Controller
public class UserEditControllerImpl implements UserEditController {

	@Override
	public ModelAndView add(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_ADMIN_USER_ADD);
		mav.addObject(new UserEditForm());
		return mav;
	}

	@Override
	public ModelAndView create(UserEditForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_ADMIN_USER_ADD);
			return mav;
		}

		// TODO
		Integer id = 0;

		UriComponents uc = fromMethodCall(on(UserEditController.class).edit(id, auth, locale, sitePref, request))
				.replaceQueryParam(ParamDef.REQ_ID, id).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView edit(Integer id, Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_ADMIN_USER_EDIT);
		mav.addObject(new UserEditForm());
		return mav;
	}

	@Override
	public ModelAndView update(Integer id, UserEditForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_ADMIN_USER_EDIT);
			return mav;
		}

		// TODO

		UriComponents uc = fromMethodCall(on(UserEditController.class).edit(id, auth, locale, sitePref, request))
				.replaceQueryParam(ParamDef.REQ_ID, id).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
