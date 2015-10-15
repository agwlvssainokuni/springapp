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

package cherry.example.web.simple.ex50;

import static cherry.example.web.PathDef.VIEW_SIMPLE_EX41_START;
import static cherry.example.web.PathDef.VIEW_SIMPLE_EX51_START;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.ArrayList;
import java.util.List;
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

import cherry.example.web.util.ModelAndViewBuilder;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

@Controller
public class Ex51ControllerImpl implements Ex51Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Override
	public ModelAndView init(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return ModelAndViewBuilder.redirect(redirectOnInit(redir)).build();
	}

	@Override
	public ModelAndView start(Ex50to51Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		Ex51Form f = createForm(form);
		if (f.getItem().isEmpty()) {
			return ModelAndViewBuilder.redirect(redirectOnStart()).build();
		}
		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX51_START).addObject(f).build();
	}

	@Override
	public ModelAndView confirm(Ex51Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX51_START).build();
		}

		return ModelAndViewBuilder.withoutView().build();
	}

	@Override
	public ModelAndView back(Ex51Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX51_START).build();
	}

	@Override
	public ModelAndView execute(Ex51Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX51_START).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX41_START).build();
		}

		return ModelAndViewBuilder.withoutView().build();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(Ex51Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private UriComponents redirectOnStart() {
		return fromMethodCall(on(Ex50Controller.class).execute(null, null, null, null, null, null)).build();
	}

	private boolean hasErrors(Ex51Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック

		return false;
	}

	private Ex51Form createForm(Ex50to51Form form) {
		List<Ex51SubForm> l = new ArrayList<>();
		for (Ex50to51SubForm subform : form.getItem()) {
			if (subform.getChecked().booleanValue()) {
				Ex51SubForm sf = new Ex51SubForm();
				sf.setId(subform.getId());
				l.add(sf);
			}
		}
		Ex51Form f = new Ex51Form();
		f.setItem(l);
		return f;
	}

}
