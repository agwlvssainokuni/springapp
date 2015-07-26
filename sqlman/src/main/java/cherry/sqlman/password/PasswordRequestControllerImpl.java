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

package cherry.sqlman.password;

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
import cherry.sqlman.LogicError;
import cherry.sqlman.PathDef;
import cherry.sqlman.password.PasswordRequestService.UriComponentsSource;

@Controller
public class PasswordRequestControllerImpl implements PasswordRequestController {

	@Autowired
	private PasswordRequestService passwordRequestService;

	@Override
	public PasswordRequestForm getForm() {
		return new PasswordRequestForm();
	}

	@Override
	public ModelAndView start(Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_PASSWORD_START);
		return mav;
	}

	@Override
	public ModelAndView execute(PasswordRequestForm form, BindingResult binding, final Locale locale,
			final SitePreference sitePref, final HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_PASSWORD_START);
			return mav;
		}

		UriComponentsSource source = new UriComponentsSource() {
			@Override
			public UriComponents buildUriComponents(UUID token) {
				return fromMethodCall(
						on(PasswordRequestController.class).edit(token.toString(), locale, sitePref, request))
						.replaceQueryParam(PathDef.PARAM_TOKEN, token.toString()).build();
			}
		};

		if (!passwordRequestService.createRequest(form.getMailAddr(), locale, source)) {
			LogicalErrorUtil.reject(binding, LogicError.TooManyPasswordRequest);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_PASSWORD_START);
			return mav;
		}

		redirAttr.addFlashAttribute(PathDef.SUBURI_EXECUTE, Boolean.TRUE);

		UriComponents uc = fromMethodCall(on(PasswordRequestController.class).start(locale, sitePref, request)).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView edit(String token, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_PASSWORD_EDIT);
		return mav;
	}

	@Override
	public ModelAndView update(String token, PasswordRequestForm form, BindingResult binding, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_PASSWORD_EDIT);
			return mav;
		}

		if (!form.getPassword().equals(form.getPasswordConf())) {
			LogicalErrorUtil.reject(binding, LogicError.PasswordConfUnmatch);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_PASSWORD_EDIT);
			return mav;
		}

		if (!passwordRequestService.updatePassword(token, form.getMailAddr(), form.getPassword(), locale)) {
			LogicalErrorUtil.reject(binding, LogicError.PasswordRequestUnmatch);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_PASSWORD_EDIT);
			return mav;
		}

		redirAttr.addFlashAttribute(PathDef.SUBURI_EXECUTE, Boolean.TRUE);

		UriComponents uc = fromMethodCall(on(PasswordRequestController.class).edit(token, locale, sitePref, request))
				.replaceQueryParam(PathDef.PARAM_TOKEN, token).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
