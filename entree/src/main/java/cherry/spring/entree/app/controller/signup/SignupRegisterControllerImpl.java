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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cherry.spring.entree.LogicError;
import cherry.spring.entree.app.service.signup.SignupRegisterService;

@Controller
public class SignupRegisterControllerImpl implements SignupRegisterController {

	public static final String VIEW_PATH = "signup/register/index";

	public static final String VIEW_PATH_FIN = "signup/register/finish";

	@Autowired
	private SignupRegisterService signupRegisterService;

	@Override
	public SignupRegisterForm getForm() {
		return new SignupRegisterForm();
	}

	@Override
	public ModelAndView index(String token, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(PATH_VAR, token);
		return mav;
	}

	@Override
	public ModelAndView request(String token, SignupRegisterForm form,
			BindingResult binding, RedirectAttributes redirectAttributes,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, token);
			return mav;
		}

		if (!signupRegisterService.createUser(form.getEmail(), token,
				form.getFirstName(), form.getLastName(), locale)) {
			rejectOnSignupEntryUnmatch(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(PATH_VAR, token);
			return mav;
		}

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_FIN, true));
		return mav;
	}

	@Override
	public ModelAndView finish(String token,
			RedirectAttributes redirectAttributes, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		mav.addObject(PATH_VAR, token);
		return mav;
	}

	private void rejectOnSignupEntryUnmatch(BindingResult binding) {
		binding.reject(LogicError.SignupEntryUnmatch.name(), new Object[] {},
				LogicError.SignupEntryUnmatch.name());
	}

}
