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

package cherry.example.web.applied.ex50;

import static cherry.example.web.util.ModelAndViewBuilder.redirect;
import static cherry.example.web.util.ModelAndViewBuilder.withViewname;
import static cherry.example.web.util.ModelAndViewBuilder.withoutView;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.web.util.ViewNameUtil;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

@Controller
public class AppliedEx51ControllerImpl implements AppliedEx51Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private AppliedEx51Service service;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(AppliedEx51Controller.class).start(null,
			null, null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, AppliedEx50to51Form form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request, SessionStatus status) {
		if (StringUtils.isNotEmpty(redirTo)) {
			status.setComplete();
			return redirect(redirectTo(redirTo)).build();
		}
		if (binding.hasErrors()) {
			status.setComplete();
			return redirect(redirectToSearchResult()).build();
		}

		AppliedEx51SessionForm f = createSessionForm(form);
		return redirect(redirectToStart()).addObject(f).build();
	}

	@Override
	public ModelAndView start(AppliedEx51SessionForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, SessionStatus status) {
		if (binding.hasErrors()) {
			status.setComplete();
			return redirect(redirectToSearchResult()).build();
		}
		AppliedEx51Form f = createForm(form);
		if (f.getItem().isEmpty()) {
			status.setComplete();
			return redirect(redirectToSearchResult()).build();
		}
		return withViewname(viewnameOfStart).addObject(f).build();
	}

	@Override
	public ModelAndView update(AppliedEx51Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView confirm(AppliedEx51Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(AppliedEx51Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(AppliedEx51Form form, BindingResult binding, AppliedEx51SessionForm sessionForm,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return withViewname(viewnameOfStart).build();
		}

		long count = service.update(form);
		if (count != form.getItem().size()) {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			return withViewname(viewnameOfStart).build();
		}

		AppliedEx51SessionForm sf = createSessionForm(form);
		sessionForm.setId(sf.getId());

		AppliedEx51Form f = createForm(sf);
		return withoutView().addObject(f).build();
	}

	private UriComponents redirectTo(String redir) {
		return UriComponentsBuilder.fromPath(redir).build();
	}

	private UriComponents redirectToSearchResult() {
		return fromMethodCall(on(AppliedEx50Controller.class).execute(null, null, null, null, null, null)).build();
	}

	private UriComponents redirectToStart() {
		return fromMethodCall(on(AppliedEx51Controller.class).start(null, null, null, null, null, null, null)).build();
	}

	private boolean hasErrors(AppliedEx51Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

	private AppliedEx51SessionForm createSessionForm(AppliedEx50to51Form form) {
		List<Long> l = new ArrayList<>(form.getItem().size());
		for (AppliedEx50to51SubForm subform : form.getItem()) {
			if (subform.getChecked().booleanValue()) {
				l.add(subform.getId());
			}
		}
		AppliedEx51SessionForm f = new AppliedEx51SessionForm();
		f.setId(l);
		return f;
	}

	private AppliedEx51SessionForm createSessionForm(AppliedEx51Form form) {
		List<Long> l = new ArrayList<>(form.getItem().size());
		for (AppliedEx51SubForm subform : form.getItem()) {
			l.add(subform.getId());
		}
		AppliedEx51SessionForm f = new AppliedEx51SessionForm();
		f.setId(l);
		return f;
	}

	private AppliedEx51Form createForm(AppliedEx51SessionForm form) {
		AppliedEx51Form f = new AppliedEx51Form();
		f.setItem(service.search(form.getId()));
		return f;
	}

}
