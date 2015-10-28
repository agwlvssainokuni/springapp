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

package cherry.example.web.applied.ex60;

import static cherry.example.web.util.ModelAndViewBuilder.redirect;
import static cherry.example.web.util.ModelAndViewBuilder.withViewname;
import static cherry.example.web.util.ModelAndViewBuilder.withoutView;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.web.LogicalError;
import cherry.example.web.applied.ex60.AppliedEx62FormBase.Prop;
import cherry.example.web.util.ViewNameUtil;
import cherry.foundation.logicalerror.LogicalErrorUtil;

@Controller
public class AppliedEx62ControllerImpl implements AppliedEx62Controller {

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(AppliedEx62Controller.class).start(null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redir)).build();
	}

	@Override
	public ModelAndView start(AppliedEx61Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		AppliedEx62Form f = new AppliedEx62Form();
		f.setDt(form.getDt());
		f.setTm(form.getTm());
		f.setDtm(form.getDtm());

		return withViewname(viewnameOfStart).addObject(f).build();
	}

	@Override
	public ModelAndView confirm(AppliedEx62Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(AppliedEx62Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(AppliedEx61Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return redirect(redirectOnExecute(form)).build();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(AppliedEx61Controller.class).update(null, null, null, null, null, null)).build();
		}
	}

	private UriComponents redirectOnExecute(AppliedEx61Form form) {
		return fromMethodCall(on(AppliedEx61Controller.class).update(null, null, null, null, null, null)).build();
	}

	private boolean hasErrors(AppliedEx62Form form, BindingResult binding) {

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

		if (binding.hasErrors()) {
			return true;
		}

		return false;
	}

}
