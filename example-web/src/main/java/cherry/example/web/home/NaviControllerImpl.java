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

package cherry.example.web.home;

import static cherry.example.web.util.ModelAndViewBuilder.redirect;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.LinkedList;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;

@Controller
public class NaviControllerImpl implements NaviController {

	@Override
	public ModelAndView back(NaviForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		if (CollectionUtils.isEmpty(form.getHistory())) {
			return redirect(redirectToHome()).build();
		}
		String uri = form.getHistory().remove(form.getHistory().size() - 1);
		return redirect(uri).build();
	}

	@Override
	public ModelAndView save1(String to, String fm, NaviForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {
		saveHistory(fm, form);
		return redirect(to).build();
	}

	@Override
	public ModelAndView save2(String to, String referer, NaviForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {
		saveHistory(referer, form);
		return redirect(to).build();
	}

	@Override
	public ModelAndView clear(NaviForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, SessionStatus status) {
		status.setComplete();
		return redirect(redirectToHome()).build();
	}

	private UriComponents redirectToHome() {
		return fromMethodCall(on(HomeController.class).start(null, null, null, null)).build();
	}

	private void saveHistory(String path, NaviForm form) {
		if (form.getHistory() == null) {
			form.setHistory(new LinkedList<String>());
		}
		form.getHistory().add(path);
	}

}
