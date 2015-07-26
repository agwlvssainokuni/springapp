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

package cherry.sqlman.tool.password;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;
import cherry.sqlman.LogicError;
import cherry.sqlman.PathDef;

@Controller
public class PasswordChangeControllerImpl implements PasswordChangeController {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private PasswordChangeService passwordChangeService;

	@ModelAttribute()
	public PasswordChangeForm getForm(Authentication auth) {
		PasswordChangeForm form = new PasswordChangeForm();
		form.setLockVersion(passwordChangeService.getLockVersion(auth.getName()));
		return form;
	}

	@Override
	public ModelAndView start(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_PASSWORD);
		return mav;
	}

	@Override
	public ModelAndView update(PasswordChangeForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_PASSWORD);
			return mav;
		}

		if (!form.getPassword().equals(form.getPasswordConf())) {
			LogicalErrorUtil.rejectValue(binding, PasswordChangeFormBase.Prop.PasswordConf.getName(),
					LogicError.PasswordConfUnmatch);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_PASSWORD);
			return mav;
		}

		if (!oneTimeTokenValidator.isValid(request)) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_PASSWORD);
			return mav;
		}

		if (!passwordChangeService.updatePassword(auth.getName(), form.getPassword(), form.getLockVersion())) {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_PASSWORD);
			return mav;
		}

		redirAttr.addFlashAttribute(PathDef.SUBURI_UPDATE, Boolean.TRUE);

		UriComponents uc = fromMethodCall(on(PasswordChangeController.class).start(auth, locale, sitePref, request))
				.build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
