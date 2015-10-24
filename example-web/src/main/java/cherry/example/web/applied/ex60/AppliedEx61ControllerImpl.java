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

import static cherry.example.web.PathDef.VIEW_APPLIED_EX61_START;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.web.util.ModelAndViewBuilder;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

@Controller
public class AppliedEx61ControllerImpl implements AppliedEx61Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private AppliedEx61Service service;

	@Override
	public ModelAndView init(String redir, AppliedEx60to61Form form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request, SessionStatus status) {
		if (StringUtils.isNotEmpty(redir)) {
			status.setComplete();
			return ModelAndViewBuilder.redirect(redirectTo(redir)).build();
		}
		if (binding.hasErrors()) {
			status.setComplete();
			return ModelAndViewBuilder.redirect(redirectToSearchResult()).build();
		}

		AppliedEx61inForm f = createSessionForm(form);
		return ModelAndViewBuilder.redirect(redirectToStart()).addObject(f).build();
	}

	@Override
	public ModelAndView start(AppliedEx61inForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		if (binding.hasErrors()) {
			return ModelAndViewBuilder.redirect(redirectToSearchResult()).build();
		}
		List<Long> id = getId(form);
		if (id.isEmpty()) {
			return ModelAndViewBuilder.redirect(redirectToSearchResult()).build();
		}
		AppliedEx61Form f = createForm(id);
		if (f.getItem().isEmpty()) {
			return ModelAndViewBuilder.redirect(redirectToSearchResult()).build();
		}
		return renderStartView().addObject(f).build();
	}

	@Override
	public ModelAndView update(AppliedEx61Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return renderStartView().build();
	}

	@Override
	public ModelAndView confirm(AppliedEx61Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return renderStartView().build();
		}

		return renderWithoutView().build();
	}

	@Override
	public ModelAndView back(AppliedEx61Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return renderStartView().build();
	}

	@Override
	public ModelAndView execute(AppliedEx61Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(form, binding)) {
			return renderStartView().build();
		}

		if (!oneTimeTokenValidator.isValid(request.getNativeRequest(HttpServletRequest.class))) {
			LogicalErrorUtil.rejectOnOneTimeTokenError(binding);
			return renderStartView().build();
		}

		long count = service.update(form);
		if (count != form.getItem().size()) {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			return renderStartView().build();
		}

		redirAttr.addFlashAttribute("updated", true);
		return ModelAndViewBuilder.redirect(redirectToStart()).build();
	}

	private ModelAndViewBuilder renderStartView() {
		return ModelAndViewBuilder.withViewname(VIEW_APPLIED_EX61_START);
	}

	private ModelAndViewBuilder renderWithoutView() {
		return ModelAndViewBuilder.withoutView();
	}

	private UriComponents redirectTo(String redir) {
		return UriComponentsBuilder.fromPath(redir).build();
	}

	private UriComponents redirectToSearchResult() {
		return fromMethodCall(on(AppliedEx60Controller.class).execute(null, null, null, null, null, null)).build();
	}

	private UriComponents redirectToStart() {
		return fromMethodCall(on(AppliedEx61Controller.class).start(null, null, null, null, null, null)).build();
	}

	private boolean hasErrors(AppliedEx61Form form, BindingResult binding) {

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

	private AppliedEx61inForm createSessionForm(AppliedEx60to61Form form) {
		List<AppliedEx61inSubForm> l = new ArrayList<>(form.getItem().size());
		for (AppliedEx60to61SubForm subform : form.getItem()) {
			if (subform.getChecked().booleanValue()) {
				AppliedEx61inSubForm sf = new AppliedEx61inSubForm();
				sf.setId(subform.getId());
				l.add(sf);
			}
		}
		AppliedEx61inForm f = new AppliedEx61inForm();
		f.setItem(l);
		return f;
	}

	private List<Long> getId(AppliedEx61inForm form) {
		List<Long> l = new ArrayList<>(form.getItem().size());
		for (AppliedEx61inSubForm subform : form.getItem()) {
			l.add(subform.getId());
		}
		return l;
	}

	private AppliedEx61Form createForm(List<Long> id) {
		AppliedEx61Form f = new AppliedEx61Form();
		f.setItem(service.search(id));
		return f;
	}

}
