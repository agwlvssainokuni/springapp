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

package cherry.example.web.applied.ex10;

import static cherry.example.web.ParamDef.REQ_ID;
import static cherry.example.web.PathDef.VIEW_APPLIED_EX10_START;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.web.LogicalError;
import cherry.example.web.applied.ex10.AppliedEx10FormBase.Prop;
import cherry.example.web.util.ModelAndViewBuilder;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

@Controller
public class AppliedEx10ControllerImpl implements AppliedEx10Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private AppliedEx10Service service;

	@Override
	public ModelAndView init(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {

		status.setComplete();

		return ModelAndViewBuilder.redirect(redirectOnInit(redir)).build();
	}

	@Override
	public ModelAndView start(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return renderStartView().build();
	}

	@Override
	public ModelAndView update(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return renderStartView().build();
	}

	@Override
	public ModelAndView confirm(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return renderStartView().build();
		}

		return renderWithoutView().build();
	}

	@Override
	public ModelAndView back(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return renderStartView().build();
	}

	@Override
	public ModelAndView execute(AppliedEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return renderStartView().build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return renderStartView().build();
		}

		Long id = service.create(form);
		checkState(id != null, "failed to create: form=%s", form);

		return ModelAndViewBuilder.redirect(redirectOnExecute(id.longValue())).build();
	}

	@Override
	public ModelAndView completed(long id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {

		status.setComplete();

		AppliedEx10Form form = service.findById(id);
		return renderWithoutView().addObject(form).build();
	}

	private ModelAndViewBuilder renderStartView() {
		return ModelAndViewBuilder.withViewname(VIEW_APPLIED_EX10_START);
	}

	private ModelAndViewBuilder renderWithoutView() {
		return ModelAndViewBuilder.withoutView();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(AppliedEx10Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private UriComponents redirectOnExecute(long id) {
		return fromMethodCall(on(AppliedEx10Controller.class).completed(id, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private boolean hasErrors(AppliedEx10Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getDt() == null && form.getTm() != null) {
			LogicalErrorUtil.rejectValue(binding, Prop.Dt.getName(), LogicalError.RequiredWhen, Prop.Dt.resolve(),
					Prop.Tm.resolve());
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック
		if (service.exists(form.getText10())) {
			LogicalErrorUtil.rejectValue(binding, Prop.Text10.getName(), LogicalError.AlreadyExists,
					Prop.Text10.resolve());
		}

		if (binding.hasErrors()) {
			return true;
		}

		return false;
	}

}
