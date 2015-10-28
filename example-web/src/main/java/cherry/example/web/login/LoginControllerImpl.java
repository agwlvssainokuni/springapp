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

package cherry.example.web.login;

import static cherry.example.web.util.ModelAndViewBuilder.redirect;
import static cherry.example.web.util.ModelAndViewBuilder.withoutView;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;

@Controller
public class LoginControllerImpl implements LoginController {

	@Override
	public ModelAndView start(LoginForm form, BindingResult binding, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		form.setPassword(null);
		return withoutView().build();
	}

	@Override
	public ModelAndView loginFailed(Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr) {
		redirAttr.addFlashAttribute("loginFailed", true);
		return redirect(redirectToStart()).build();
	}

	@Override
	public ModelAndView loggedOut(Locale locale, SitePreference sitePref, NativeWebRequest request,
			RedirectAttributes redirAttr) {
		redirAttr.addFlashAttribute("loggedOut", true);
		return redirect(redirectToStart()).build();
	}

	private UriComponents redirectToStart() {
		return fromMethodCall(on(LoginController.class).start(null, null, null, null, null)).build();
	}

}
