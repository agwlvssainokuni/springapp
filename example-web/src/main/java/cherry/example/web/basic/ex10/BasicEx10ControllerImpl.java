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

package cherry.example.web.basic.ex10;

import static cherry.example.web.ParamDef.REQ_ID;
import static cherry.example.web.util.ModelAndViewBuilder.redirect;
import static cherry.example.web.util.ModelAndViewBuilder.withViewname;
import static cherry.example.web.util.ModelAndViewBuilder.withoutView;
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

import cherry.example.web.LogicalError;
import cherry.example.web.basic.ex10.BasicEx10FormBase.Prop;
import cherry.example.web.util.ViewNameUtil;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

@Controller
public class BasicEx10ControllerImpl implements BasicEx10Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private BasicEx10Service service;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(BasicEx10Controller.class).start(null, null,
			null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo)).build();
	}

	@Override
	public ModelAndView start(BasicEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView confirm(BasicEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(BasicEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(BasicEx10Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return withViewname(viewnameOfStart).build();
		}

		Long id = service.create(form);
		checkState(id != null, "failed to create: form=%s", form);

		return redirect(redirectOnExecute(id.longValue())).build();
	}

	@Override
	public ModelAndView completed(long id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		BasicEx10Form f = service.findById(id);
		shouldExist(f, BasicEx10Form.class, id);
		return withoutView().addObject(f).build();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(BasicEx10Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private UriComponents redirectOnExecute(long id) {
		return fromMethodCall(on(BasicEx10Controller.class).completed(id, null, null, null, null)).replaceQueryParam(
				REQ_ID, id).build();
	}

	private boolean hasErrors(BasicEx10Form form, BindingResult binding) {

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
