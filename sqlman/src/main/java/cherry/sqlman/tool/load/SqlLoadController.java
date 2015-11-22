/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.sqlman.tool.load;

import static cherry.sqlman.ParamDef.REQ_CREATE;
import static cherry.sqlman.ParamDef.REQ_REF;
import static cherry.sqlman.ParamDef.REQ_TO;
import static cherry.sqlman.PathDef.SUBURI_EXECUTE_NEW;
import static cherry.sqlman.PathDef.SUBURI_START_NEW;
import static cherry.sqlman.PathDef.URI_TOOL_LOAD;

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

import cherry.foundation.validator.groups.G1;
import cherry.foundation.validator.groups.G9;

@RequestMapping(URI_TOOL_LOAD)
@SessionAttributes(types = SqlLoadForm.class)
public interface SqlLoadController {

	@RequestMapping()
	ModelAndView init(@RequestParam(value = REQ_TO, required = false) String redirTo,
			@RequestParam(value = REQ_REF, required = false) Integer ref, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, SessionStatus status);

	@RequestMapping(value = SUBURI_START_NEW)
	ModelAndView start(@RequestParam(value = REQ_REF, required = false) Integer ref,
			@Validated(G9.class) SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_EXECUTE_NEW)
	ModelAndView execute(@Validated(G1.class) SqlLoadForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr);

	@RequestMapping(value = SUBURI_EXECUTE_NEW, params = REQ_CREATE)
	ModelAndView create(@Validated(G1.class) SqlLoadForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request);

}
