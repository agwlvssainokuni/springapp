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

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.spring.common.helper.logicalerror.LogicalErrorHelper;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;
import cherry.spring.entree.LogicalError;
import cherry.spring.entree.app.service.secure.passwd.PasswdService;

@Controller
public class PasswdControllerImpl implements PasswdController {

	public static final String VIEW_PATH = "secure/passwd/index";

	public static final String VIEW_PATH_FIN = "secure/passwd/finish";

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private PasswdService passwdService;

	@Autowired
	private LogicalErrorHelper logicalErrorHelper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public PasswdForm getForm() {
		return new PasswdForm();
	}

	@Override
	public ModelAndView index(Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@Override
	public ModelAndView request(PasswdForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		if (!validateForm(form, binding)) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		if (!auth.getName().equals(form.getLoginId())) {
			rejectOnCurAuthFailed(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				auth.getName(), form.getPassword());
		try {
			Authentication a = authenticationManager.authenticate(token);
			checkNotNull(a, "AuthenticationManager#authenticate(token): null");
		} catch (AuthenticationException ex) {
			rejectOnCurAuthFailed(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		String password = passwordEncoder.encode(form.getNewPassword());
		if (!passwdService.changePassword(auth.getName(), password)) {
			if (log.isDebugEnabled()) {
				log.debug(
						"Password has not been updated: loginId={0}, password={1}",
						auth.getName(), password);
			}
			throw new IllegalStateException("");
		}

		UriComponents uc = MvcUriComponentsBuilder.fromMethodName(
				PasswdController.class, "finish", auth, locale, sitePref,
				request).build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		return mav;
	}

	private boolean validateForm(PasswdForm form, BindingResult binding) {
		if (StringUtils.isEmpty(form.getNewPassword())) {
			return true;
		}
		if (form.getNewPassword().equals(form.getNewPasswordConf())) {
			return true;
		}
		logicalErrorHelper.rejectValue(binding, "newPasswordConf",
				LogicalError.PasswdConfirmFailed);
		return false;
	}

	private void rejectOnCurAuthFailed(BindingResult binding) {
		logicalErrorHelper.reject(binding, LogicalError.CurAuthFailed,
				logicalErrorHelper.resolve("passwdForm.loginId"),
				logicalErrorHelper.resolve("passwdForm.password"));
	}

}
