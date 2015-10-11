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

package cherry.example.web.simple.ex30;

import static cherry.example.web.PathDef.VIEW_SIMPLE_EX30_START;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class Ex30ControllerImpl implements Ex30Controller {

	@Override
	public ModelAndView init(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request, SessionStatus status) {

		status.setComplete();

		UriComponents uc = redirectOnInit(redir, auth, locale, sitePref, request);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView start(Ex30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		form.setPno(0L);

		ModelAndView mav = new ModelAndView(VIEW_SIMPLE_EX30_START);
		return mav;
	}

	@Override
	public ModelAndView execute(Ex30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (isValid(form, binding, auth, locale, sitePref, request)) {
			ModelAndView mav = new ModelAndView(VIEW_SIMPLE_EX30_START);
			return mav;
		}

		ModelAndView mav = new ModelAndView(VIEW_SIMPLE_EX30_START);
		return mav;
	}

	private UriComponents redirectOnInit(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		return UriComponentsBuilder.fromPath(redir).build();
	}

	private boolean isValid(Ex30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

}
