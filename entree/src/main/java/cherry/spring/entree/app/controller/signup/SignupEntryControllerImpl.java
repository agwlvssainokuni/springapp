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

package cherry.spring.entree.app.controller.signup;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.spring.entree.LogicError;
import cherry.spring.entree.app.service.signup.SignupEntryService;

@Controller
public class SignupEntryControllerImpl implements SignupEntryController {

	public static final String VIEW_PATH = "signup/entry/index";

	public static final String VIEW_PATH_FIN = "signup/entry/finish";

	@Autowired
	private SignupEntryService signupEntryService;

	@Override
	public SignupEntryForm getForm() {
		return new SignupEntryForm();
	}

	@Override
	public ModelAndView index(Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView request(SignupEntryForm form, BindingResult binding,
			Locale locale, SitePreference sitePref, HttpServletRequest request,
			RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		if (!signupEntryService.createSignupRequest(form.getEmail(), request,
				locale)) {
			rejectOnSignupTooManyRequest(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		UriComponents uc = MvcUriComponentsBuilder.fromMethodName(
				SignupEntryController.class, "finish", locale, sitePref,
				request).build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		return mav;
	}

	private void rejectOnSignupTooManyRequest(BindingResult binding) {
		binding.reject(LogicError.SignupTooManyRequest.name(), new Object[] {},
				LogicError.SignupTooManyRequest.name());
	}

}
