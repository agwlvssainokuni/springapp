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

package cherry.spring.site.app.controller.passwd;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;
import cherry.spring.site.LogicError;
import cherry.spring.site.app.service.passwd.PasswdService;

@Component
@RequestMapping(PasswdController.URI_PATH)
public class PasswdControllerImpl implements PasswdController {

	public static final String VIEW_PATH = "passwd/index";

	public static final String VIEW_PATH_FIN = "passwd/finish";

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private PasswdService passwdService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping()
	@Override
	public ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(new PasswdForm());
		return mav;
	}

	@RequestMapping(URI_PATH_REQ)
	@Override
	public ModelAndView request(@Valid PasswdForm passwdForm,
			BindingResult binding, RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(passwdForm);
			return mav;
		}

		if (!passwdForm.getNewPassword()
				.equals(passwdForm.getNewPasswordConf())) {
			rejectOnNewPasswordUnmatch(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(passwdForm);
			return mav;
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				authentication.getName(), passwdForm.getPassword());
		try {
			Authentication auth = authenticationManager.authenticate(token);
			assert auth != null;
		} catch (AuthenticationException ex) {
			rejectOnCurPasswordInvalid(binding);
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(passwdForm);
			return mav;
		}

		String password = passwordEncoder.encode(passwdForm.getNewPassword());
		if (!passwdService.updatePassword(authentication.getName(), password)) {
			if (log.isDebugEnabled()) {
				log.debug(
						"Password has not been updated: mailAddr={0}, password={1}",
						authentication.getName(), password);
			}
			throw new IllegalStateException("");
		}

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_FIN, true));
		return mav;
	}

	@RequestMapping(URI_PATH_FIN)
	@Override
	public ModelAndView finish(RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		return mav;
	}

	private void rejectOnNewPasswordUnmatch(BindingResult binding) {
		binding.reject(LogicError.NewPasswordUnmatch.name(),
				new Object[] { new DefaultMessageSourceResolvable(
						PasswdForm.PREFIX + PasswdForm.NEW_PASSWORD) },
				LogicError.NewPasswordUnmatch.name());
	}

	private void rejectOnCurPasswordInvalid(BindingResult binding) {
		binding.reject(LogicError.CurPasswordInvalid.name(),
				new Object[] { new DefaultMessageSourceResolvable(
						PasswdForm.PREFIX + PasswdForm.PASSWORD) },
				LogicError.CurPasswordInvalid.name());
	}

}
