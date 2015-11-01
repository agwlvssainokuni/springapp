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

import cherry.example.web.Config;

@Controller
public class NaviControllerImpl implements NaviController {

	@Autowired
	private Config config;

	@Override
	public ModelAndView back(NaviForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		String uri = restoreHistory(form);
		if (StringUtils.isBlank(uri)) {
			return redirect(redirectToHome()).build();
		}
		return redirect(uri).build();
	}

	@Override
	public ModelAndView save1(String to, String fm, NaviForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {
		saveHistory(form, fm);
		return redirect(to).build();
	}

	@Override
	public ModelAndView save2(String to, String referer, NaviForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {
		saveHistory(form, referer);
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

	private void saveHistory(NaviForm form, String uri) {
		if (form.getHistory() == null) {
			form.setHistory(new LinkedList<String>());
		}
		form.getHistory().add(uri);
		while (form.getHistory().size() > config.getHistorySize()) {
			form.getHistory().remove(0);
		}
	}

	private String restoreHistory(NaviForm form) {
		if (CollectionUtils.isEmpty(form.getHistory())) {
			return null;
		}
		return form.getHistory().remove(form.getHistory().size() - 1);
	}

}
