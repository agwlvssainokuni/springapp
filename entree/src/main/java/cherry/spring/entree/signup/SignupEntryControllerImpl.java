/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.spring.entree.signup;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.spring.entree.LogicalError;
import cherry.spring.entree.PathDef;
import cherry.spring.entree.signup.SignupEntryService.UriComponentsSource;

@Controller
public class SignupEntryControllerImpl implements SignupEntryController {

	@Autowired
	private SignupEntryService signupEntryService;

	@Override
	public SignupEntryForm getForm() {
		return new SignupEntryForm();
	}

	@Override
	public ModelAndView init(Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SIGNUP_ENTRY_INIT);
		return mav;
	}

	@Override
	public ModelAndView execute(SignupEntryForm form, BindingResult binding, final Locale locale,
			final SitePreference sitePref, final HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_SIGNUP_ENTRY_INIT);
			return mav;
		}

		UriComponentsSource source = new UriComponentsSource() {
			@Override
			public UriComponents buildUriComponents(UUID token) {
				return fromMethodCall(
						on(SignupRegisterController.class).init(token.toString(), locale, sitePref, request)).build();
			}
		};

		if (!signupEntryService.createSignupRequest(form.getEmail(), locale, source)) {
			rejectOnSignupTooManyRequest(binding);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_SIGNUP_ENTRY_INIT);
			return mav;
		}

		UriComponents uc = fromMethodCall(on(SignupEntryController.class).finish(locale, sitePref, request)).build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_SIGNUP_ENTRY_FINISH);
		return mav;
	}

	private void rejectOnSignupTooManyRequest(BindingResult binding) {
		LogicalErrorUtil.reject(binding, LogicalError.SignupTooManyRequest);
	}

}
