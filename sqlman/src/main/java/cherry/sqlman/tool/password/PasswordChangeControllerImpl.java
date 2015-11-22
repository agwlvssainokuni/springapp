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

import static cherry.sqlman.ParamDef.FLASH_UPDATED;
import static cherry.sqlman.util.ModelAndViewBuilder.redirect;
import static cherry.sqlman.util.ModelAndViewBuilder.withViewname;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;
import cherry.sqlman.LogicError;
import cherry.sqlman.util.ViewNameUtil;

@Controller
public class PasswordChangeControllerImpl implements PasswordChangeController {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private PasswordChangeService passwordChangeService;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(PasswordChangeController.class).start(null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo)).build();
	}

	@Override
	public ModelAndView start(PasswordChangeForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		initializeForm(form, auth);
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView update(PasswordChangeForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return withViewname(viewnameOfStart).build();
		}

		if (!passwordChangeService.updatePassword(auth.getName(), form.getPassword(), form.getLockVersion())) {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			return withViewname(viewnameOfStart).build();
		}

		redirAttr.addFlashAttribute(FLASH_UPDATED, Boolean.TRUE);

		return redirect(redirectOnUpdate()).build();
	}

	private UriComponents redirectOnInit(String redirTo) {
		if (StringUtils.isNotEmpty(redirTo)) {
			return UriComponentsBuilder.fromPath(redirTo).build();
		} else {
			return fromMethodCall(on(PasswordChangeController.class).start(null, null, null, null, null, null)).build();
		}
	}

	private UriComponents redirectOnUpdate() {
		return fromMethodCall(on(PasswordChangeController.class).start(null, null, null, null, null, null)).build();
	}

	private void initializeForm(PasswordChangeForm form, Authentication auth) {
		form.setLockVersion(passwordChangeService.getLockVersion(auth.getName()));
	}

	private boolean hasErrors(PasswordChangeForm form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (!StringUtils.equals(form.getPassword(), form.getPasswordConf())) {
			LogicalErrorUtil.rejectValue(binding, PasswordChangeFormBase.Prop.PasswordConf.getName(),
					LogicError.PasswordConfUnmatch);
			return true;
		}

		// 整合性チェック

		return false;
	}

}
