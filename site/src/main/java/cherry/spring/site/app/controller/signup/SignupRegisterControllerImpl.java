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

package cherry.spring.site.app.controller.signup;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cherry.spring.site.app.service.signup.SignupRegisterService;

@Controller
@RequestMapping(SignupRegisterController.URI_PATH)
public class SignupRegisterControllerImpl implements SignupRegisterController {

	public static final String VIEW_PATH = "signup/register/index";

	public static final String VIEW_PATH_FIN = "signup/register/finish";

	@Autowired
	private SignupRegisterService signupRegisterService;

	@RequestMapping()
	@Override
	public ModelAndView index(@PathVariable(PATH_VAR) String token,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(new SignupRegisterForm());
		mav.addObject(PATH_VAR, token);
		return mav;
	}

	@RequestMapping(URI_PATH_REQ)
	@Override
	public ModelAndView request(@PathVariable(PATH_VAR) String token,
			SignupRegisterForm signupRegisterForm, BindingResult binding,
			RedirectAttributes redirectAttributes, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(signupRegisterForm);
			mav.addObject(PATH_VAR, token);
			return mav;
		}

		signupRegisterService.createUser(signupRegisterForm.getEmail(), token,
				signupRegisterForm.getFirstName(),
				signupRegisterForm.getLastName(), locale);

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_FIN, true));
		return mav;
	}

	@RequestMapping(URI_PATH_FIN)
	@Override
	public ModelAndView finish(@PathVariable(PATH_VAR) String token,
			RedirectAttributes redirectAttributes, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		mav.addObject(PATH_VAR, token);
		return mav;
	}

}
