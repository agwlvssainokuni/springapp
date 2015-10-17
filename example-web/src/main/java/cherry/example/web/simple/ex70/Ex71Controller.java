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

package cherry.example.web.simple.ex70;

import static cherry.example.web.ParamDef.REQ_BACK;
import static cherry.example.web.ParamDef.REQ_REDIR;
import static cherry.example.web.PathDef.SUBURI_CONFIRM;
import static cherry.example.web.PathDef.SUBURI_EXECUTE;
import static cherry.example.web.PathDef.SUBURI_START;
import static cherry.example.web.PathDef.URI_SIMPLE_EX71;

import java.util.Locale;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping(URI_SIMPLE_EX71)
@SessionAttributes(types = { Ex71inForm.class })
public interface Ex71Controller {

	@RequestMapping()
	ModelAndView init(@RequestParam(value = REQ_REDIR, required = false) String redir, @Validated() Ex70to71Form form,
			BindingResult binding, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status);

	@RequestMapping(SUBURI_START)
	ModelAndView start(@Validated() Ex71inForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(SUBURI_CONFIRM)
	ModelAndView confirm(@Validated() Ex71Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_EXECUTE, params = REQ_BACK)
	ModelAndView back(@Validated() Ex71Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(SUBURI_EXECUTE)
	ModelAndView execute(@Validated() Ex71Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr);

}
