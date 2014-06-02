/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.entree.app.controller.signup;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping(SignupRegisterController.URI_PATH)
public interface SignupRegisterController {

	public static final String URI_PATH = "/signup/{token}";

	public static final String URI_PATH_REQ = "req";

	public static final String URI_PATH_FIN = "fin";

	public static final String PATH_VAR = "token";

	@ModelAttribute("signupRegisterForm")
	SignupRegisterForm getForm();

	@RequestMapping()
	ModelAndView index(@PathVariable(PATH_VAR) String token, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_REQ)
	ModelAndView request(@PathVariable(PATH_VAR) String token,
			@Validated SignupRegisterForm form, BindingResult binding,
			RedirectAttributes redirectAttributes, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);

	@RequestMapping(URI_PATH_FIN)
	ModelAndView finish(@PathVariable(PATH_VAR) String token,
			RedirectAttributes redirectAttributes, Locale locale,
			SitePreference sitePreference, HttpServletRequest request);
}
