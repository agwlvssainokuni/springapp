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

package cherry.spring.entree.controller.signup;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.foundation.logicalerror.LogicalErrorHelper;
import cherry.spring.entree.LogicalError;
import cherry.spring.entree.controller.PathDef;
import cherry.spring.entree.service.signup.SignupRegisterService;

@Controller
public class SignupRegisterControllerImpl implements SignupRegisterController {

	@Autowired
	private SignupRegisterService signupRegisterService;

	@Autowired
	private LogicalErrorHelper logicalErrorHelper;

	@Override
	public SignupRegisterForm getForm() {
		return new SignupRegisterForm();
	}

	@Override
	public ModelAndView init(String token, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SIGNUP_REGISTER_INIT);
		mav.addObject(PathDef.PATH_VAR_TOKEN, token);
		return mav;
	}

	@Override
	public ModelAndView execute(String token, SignupRegisterForm form,
			BindingResult binding, Locale locale, SitePreference sitePref,
			HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SIGNUP_REGISTER_INIT);
			mav.addObject(PathDef.PATH_VAR_TOKEN, token);
			return mav;
		}

		if (!signupRegisterService.createUser(form.getEmail(), token,
				form.getFirstName(), form.getLastName(), locale)) {
			rejectOnSignupEntryUnmatch(binding);
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_SIGNUP_REGISTER_INIT);
			mav.addObject(PathDef.PATH_VAR_TOKEN, token);
			return mav;
		}

		UriComponents uc = fromMethodCall(
				on(SignupRegisterController.class).finish(token, locale,
						sitePref, request)).build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(String token, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SIGNUP_REGISTER_FINISH);
		mav.addObject(PathDef.PATH_VAR_TOKEN, token);
		return mav;
	}

	private void rejectOnSignupEntryUnmatch(BindingResult binding) {
		logicalErrorHelper.reject(binding, LogicalError.SignupEntryUnmatch);
	}

}
