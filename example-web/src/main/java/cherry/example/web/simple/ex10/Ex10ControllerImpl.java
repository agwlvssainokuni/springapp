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

package cherry.example.web.simple.ex10;

import static cherry.example.web.ParamDef.REQ_ID;
import static cherry.example.web.PathDef.VIEW_SIMPLE_EX10_START;
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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.db.gen.query.BExTbl1;
import cherry.example.web.LogicalError;
import cherry.example.web.util.ModelAndViewBuilder;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

@Controller
public class Ex10ControllerImpl implements Ex10Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private Ex10Service ex10Service;

	@Override
	public ModelAndView init(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return ModelAndViewBuilder.redirect(redirectOnInit(redir)).build();
	}

	@Override
	public ModelAndView start(Ex10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return renderStartView().build();
	}

	@Override
	public ModelAndView confirm(Ex10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return renderStartView().build();
		}

		return renderWithoutView().build();
	}

	@Override
	public ModelAndView back(Ex10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return renderStartView().build();
	}

	@Override
	public ModelAndView execute(Ex10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return renderStartView().build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return renderStartView().build();
		}

		Long id = ex10Service.create(form);
		checkState(id != null, "failed to create: form=%s", form);

		return ModelAndViewBuilder.redirect(redirectOnExecute(id.longValue())).build();
	}

	@Override
	public ModelAndView completed(long id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		BExTbl1 record = ex10Service.findById(id);
		return renderWithoutView().addObject(record).build();
	}

	private ModelAndViewBuilder renderStartView() {
		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX10_START);
	}

	private ModelAndViewBuilder renderWithoutView() {
		return ModelAndViewBuilder.withoutView();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(Ex10Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private UriComponents redirectOnExecute(long id) {
		return fromMethodCall(on(Ex10Controller.class).completed(id, null, null, null, null)).replaceQueryParam(REQ_ID,
				id).build();
	}

	private boolean hasErrors(Ex10Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getDt() == null && form.getTm() != null) {
			LogicalErrorUtil.rejectValue(binding, "dt", LogicalError.RequiredWhen,
					LogicalErrorUtil.resolve("ex10Form.dt"), LogicalErrorUtil.resolve("ex10Form.tm"));
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック
		if (ex10Service.exists(form.getText10())) {
			LogicalErrorUtil.rejectValue(binding, "text10", LogicalError.AlreadyExists,
					LogicalErrorUtil.resolve("ex10Form.text10"));
		}

		if (binding.hasErrors()) {
			return true;
		}

		return false;
	}

}
