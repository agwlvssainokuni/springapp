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

package cherry.spring.entree.app.controller.secure.passwd;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;
import cherry.spring.entree.LogicError;
import cherry.spring.entree.app.service.secure.passwd.PasswdService;

@Controller
public class PasswdControllerImpl implements PasswdController {

	public static final String VIEW_PATH = "secure/passwd/index";

	public static final String VIEW_PATH_FIN = "secure/passwd/finish";

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private PasswdService passwdService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("passwdFormValidator")
	private Validator passwdFormValidator;

	@Override
	public PasswdForm getForm() {
		return new PasswdForm();
	}

	@Override
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(passwdFormValidator);
	}

	@Override
	public ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView request(PasswdForm form, BindingResult binding,
			RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		if (!authentication.getName().equals(form.getLoginId())) {
			rejectOnCurAuthFailed(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				authentication.getName(), form.getPassword());
		try {
			Authentication auth = authenticationManager.authenticate(token);
			assert auth != null;
		} catch (AuthenticationException ex) {
			rejectOnCurAuthFailed(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		String password = passwordEncoder.encode(form.getNewPassword());
		if (!passwdService.changePassword(authentication.getName(), password)) {
			if (log.isDebugEnabled()) {
				log.debug(
						"Password has not been updated: loginId={0}, password={1}",
						authentication.getName(), password);
			}
			throw new IllegalStateException("");
		}

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_FIN, true));
		return mav;
	}

	@Override
	public ModelAndView finish(RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		return mav;
	}

	private void rejectOnCurAuthFailed(BindingResult binding) {
		binding.reject(LogicError.CurAuthFailed.name(), new Object[] {
				new DefaultMessageSourceResolvable("passwdForm.loginId"),
				new DefaultMessageSourceResolvable("passwdForm.password") },
				LogicError.CurAuthFailed.name());
	}

}
