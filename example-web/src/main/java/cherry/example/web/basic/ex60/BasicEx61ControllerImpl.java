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

package cherry.example.web.basic.ex60;

import static cherry.example.web.ParamDef.FLASH_UPDATED;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.web.util.ViewNameUtil;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.onetimetoken.OneTimeTokenValidator;

@Controller
public class BasicEx61ControllerImpl implements BasicEx61Controller {

	@Autowired
	private OneTimeTokenValidator oneTimeTokenValidator;

	@Autowired
	private BasicEx61Service service;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(BasicEx61Controller.class).start(null, null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, BasicEx60to61Form form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request, SessionStatus status) {
		if (StringUtils.isNotEmpty(redirTo)) {
			status.setComplete();
			return redirect(redirectTo(redirTo)).build();
		}
		if (binding.hasErrors()) {
			status.setComplete();
			return redirect(redirectToSearchResult()).build();
		}

		BasicEx61SessionForm f = createSessionForm(form);
		return redirect(redirectToStart()).addObject(f).build();
	}

	@Override
	public ModelAndView start(BasicEx61SessionForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, SessionStatus status) {
		if (binding.hasErrors()) {
			status.setComplete();
			return redirect(redirectToSearchResult()).build();
		}
		BasicEx61Form f = createForm(form);
		if (f.getItem().isEmpty()) {
			status.setComplete();
			return redirect(redirectToSearchResult()).build();
		}
		return withViewname(viewnameOfStart).addObject(f).build();
	}

	@Override
	public ModelAndView confirm(BasicEx61Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(BasicEx61Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(BasicEx61Form form, BindingResult binding, BasicEx61SessionForm sessionForm,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr) {

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

		redirAttr.addFlashAttribute(FLASH_UPDATED, Boolean.TRUE);

		BasicEx61SessionForm sf = createSessionForm(form);
		sessionForm.setId(sf.getId());

		return redirect(redirectToStart()).build();
	}

	private UriComponents redirectTo(String redir) {
		return UriComponentsBuilder.fromPath(redir).build();
	}

	private UriComponents redirectToSearchResult() {
		return fromMethodCall(on(BasicEx60Controller.class).execute(null, null, null, null, null, null)).build();
	}

	private UriComponents redirectToStart() {
		return fromMethodCall(on(BasicEx61Controller.class).start(null, null, null, null, null, null, null)).build();
	}

	private boolean hasErrors(BasicEx61Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

	private BasicEx61SessionForm createSessionForm(BasicEx60to61Form form) {
		List<Long> l = new ArrayList<>(form.getItem().size());
		for (BasicEx60to61SubForm subform : form.getItem()) {
			if (subform.getChecked().booleanValue()) {
				l.add(subform.getId());
			}
		}
		BasicEx61SessionForm f = new BasicEx61SessionForm();
		f.setId(l);
		return f;
	}

	private BasicEx61SessionForm createSessionForm(BasicEx61Form form) {
		List<Long> l = new ArrayList<>(form.getItem().size());
		for (BasicEx61SubForm subform : form.getItem()) {
			l.add(subform.getId());
		}
		BasicEx61SessionForm f = new BasicEx61SessionForm();
		f.setId(l);
		return f;
	}

	private BasicEx61Form createForm(BasicEx61SessionForm form) {
		BasicEx61Form f = new BasicEx61Form();
		f.setItem(service.search(form.getId()));
		return f;
	}

}
