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

package cherry.example.web.simple.ex20;

import static cherry.example.web.ParamDef.REQ_ID;
import static cherry.example.web.PathDef.VIEW_SIMPLE_EX20_START;
import static com.google.common.base.Preconditions.checkState;
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

import cherry.example.web.LogicalError;
import cherry.example.web.util.ModelAndViewBuilder;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

@Controller
public class Ex20ControllerImpl implements Ex20Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private Ex20Service ex20Service;

	@Override
	public ModelAndView init(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return ModelAndViewBuilder.redirect(redirectOnInit(redir)).build();
	}

	@Override
	public ModelAndView start(Ex20Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX20_START).build();
	}

	@Override
	public ModelAndView confirm(Ex20Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX20_START).build();
		}

		return ModelAndViewBuilder.withoutView().build();
	}

	@Override
	public ModelAndView back(Ex20Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX20_START).build();
	}

	@Override
	public ModelAndView execute(Ex20Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(form, binding)) {
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX20_START).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX20_START).build();
		}

		Long id = ex20Service.create(form);
		checkState(id != null, "failed to create: form=%s", form);

		redirAttr.addFlashAttribute("created", Boolean.TRUE);
		return ModelAndViewBuilder.redirect(redirectOnExecute(id.longValue())).build();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(Ex20Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private UriComponents redirectOnExecute(long id) {
		return fromMethodCall(on(Ex21Controller.class).start(id, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private boolean hasErrors(Ex20Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getDt() == null && form.getTm() != null) {
			LogicalErrorUtil.rejectValue(binding, "dt", LogicalError.RequiredWhen,
					LogicalErrorUtil.resolve("ex20Form.dt"), LogicalErrorUtil.resolve("ex20Form.tm"));
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック
		if (ex20Service.exists(form.getText10())) {
			LogicalErrorUtil.rejectValue(binding, "text10", LogicalError.AlreadyExists,
					LogicalErrorUtil.resolve("ex20Form.text10"));
		}

		if (binding.hasErrors()) {
			return true;
		}

		return false;
	}

}
