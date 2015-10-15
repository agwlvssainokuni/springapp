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

package cherry.example.web.simple.ex30;

import static cherry.example.web.ParamDef.REQ_ID;
import static cherry.example.web.PathDef.VIEW_SIMPLE_EX31_START;
import static cherry.foundation.springmvc.Contract.shouldExist;
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
public class Ex31ControllerImpl implements Ex31Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private Ex31Service ex31Service;

	@Override
	public ModelAndView init(String redir, long id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return ModelAndViewBuilder.redirect(redirectOnInit(redir, id)).build();
	}

	@Override
	public ModelAndView start(long id, Ex31Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		Ex31Form f = ex31Service.findFormById(id);
		shouldExist(f, Ex31Form.class, id);
		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX31_START).addObject(f).build();
	}

	@Override
	public ModelAndView confirm(long id, Ex31Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(id, form, binding)) {
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX31_START).build();
		}

		return ModelAndViewBuilder.withoutView().build();
	}

	@Override
	public ModelAndView back(long id, Ex31Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX31_START).build();
	}

	@Override
	public ModelAndView execute(long id, Ex31Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(id, form, binding)) {
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX31_START).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX31_START).build();
		}

		long count = ex31Service.update(id, form);
		checkState(count == 1L, "failed to update: id=%s, form=%s", id, form);

		return ModelAndViewBuilder.redirect(redirectOnExecute(id)).build();
	}

	@Override
	public ModelAndView completed(long id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		BExTbl1 record = ex31Service.findById(id);
		return ModelAndViewBuilder.withoutView().addObject(record).build();
	}

	private UriComponents redirectOnInit(String redir, long id) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(Ex31Controller.class).start(id, null, null, null, null, null, null))
					.replaceQueryParam(REQ_ID, id).build();
		}
	}

	private UriComponents redirectOnExecute(long id) {
		return fromMethodCall(on(Ex31Controller.class).completed(id, null, null, null, null)).replaceQueryParam(REQ_ID,
				id).build();
	}

	private boolean hasErrors(long id, Ex31Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック
		if (form.getDt() == null && form.getTm() != null) {
			LogicalErrorUtil.rejectValue(binding, "dt", LogicalError.RequiredWhen,
					LogicalErrorUtil.resolve("ex31Form.dt"), LogicalErrorUtil.resolve("ex31Form.tm"));
		}

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック
		if (ex31Service.exists(id, form.getText10())) {
			LogicalErrorUtil.rejectValue(binding, "text10", LogicalError.AlreadyExists,
					LogicalErrorUtil.resolve("ex31Form.text10"));
		}

		if (binding.hasErrors()) {
			return true;
		}

		return false;
	}

}
