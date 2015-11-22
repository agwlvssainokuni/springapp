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

import static cherry.sqlman.ParamDef.FLASH_CREATED;
import static cherry.sqlman.ParamDef.FLASH_UPDATED;
import static cherry.sqlman.ParamDef.REQ_TOKEN;
import static cherry.sqlman.util.ModelAndViewBuilder.redirect;
import static cherry.sqlman.util.ModelAndViewBuilder.withViewname;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
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
import cherry.sqlman.password.PasswordRequestService.UriComponentsSource;
import cherry.sqlman.util.ViewNameUtil;

@Controller
public class PasswordRequestControllerImpl implements PasswordRequestController {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private PasswordRequestService passwordRequestService;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(PasswordRequestController.class).start(null,
			null, null, null, null));

	private final String viewnameOfEdit = ViewNameUtil.fromMethodCall(on(PasswordRequestController.class).edit(null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, String token, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo, token)).build();
	}

	@Override
	public ModelAndView start(PasswordRequestForm form, BindingResult binding, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView create(PasswordRequestForm form, BindingResult binding, Locale locale, SitePreference sitePref,
			NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		UriComponentsSource source = new UriComponentsSource() {
			@Override
			public UriComponents buildUriComponents(UUID token) {
				return fromMethodCall(
						on(PasswordRequestController.class).edit(token.toString(), null, null, null, null, null))
						.replaceQueryParam(REQ_TOKEN, token.toString()).build();
			}
		};

		if (!passwordRequestService.createRequest(form.getMailAddr(), locale, source)) {
			LogicalErrorUtil.reject(binding, LogicError.TooManyPasswordRequest);
			return withViewname(viewnameOfStart).build();
		}

		redirAttr.addFlashAttribute(FLASH_CREATED, Boolean.TRUE);

		return redirect(redirectOnExecute()).build();
	}

	@Override
	public ModelAndView edit(String token, PasswordRequestForm form, BindingResult binding, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfEdit).build();
	}

	@Override
	public ModelAndView update(String token, PasswordRequestForm form, BindingResult binding, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfEdit).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return withViewname(viewnameOfEdit).build();
		}

		if (!passwordRequestService.updatePassword(token, form.getMailAddr(), form.getPassword(), locale)) {
			LogicalErrorUtil.reject(binding, LogicError.PasswordRequestUnmatch);
			return withViewname(viewnameOfEdit).build();
		}

		redirAttr.addFlashAttribute(FLASH_UPDATED, Boolean.TRUE);

		return redirect(redirectOnUpdate(token)).build();
	}

	private UriComponents redirectOnInit(String redirTo, String token) {
		if (StringUtils.isNotEmpty(redirTo)) {
			return UriComponentsBuilder.fromPath(redirTo).build();
		} else {
			if (StringUtils.isBlank(token)) {
				return fromMethodCall(on(PasswordRequestController.class).start(null, null, null, null, null)).build();
			} else {
				return fromMethodCall(on(PasswordRequestController.class).edit(null, null, null, null, null, null))
						.replaceQueryParam(REQ_TOKEN, token).build();
			}
		}
	}

	private UriComponents redirectOnExecute() {
		return fromMethodCall(on(PasswordRequestController.class).start(null, null, null, null, null)).build();
	}

	private UriComponents redirectOnUpdate(String token) {
		return fromMethodCall(on(PasswordRequestController.class).edit(null, null, null, null, null, null))
				.replaceQueryParam(REQ_TOKEN, token).build();
	}

	private boolean hasErrors(PasswordRequestForm form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (!StringUtils.equals(form.getPassword(), form.getPasswordConf())) {
			LogicalErrorUtil.rejectValue(binding, PasswordRequestFormBase.Prop.PasswordConf.getName(),
					LogicError.PasswordConfUnmatch);
			return true;
		}

		// 整合性チェック

		return false;
	}

}
