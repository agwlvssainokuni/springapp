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

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cherry.foundation.validator.groups.G1;
import cherry.sqlman.ParamDef;
import cherry.sqlman.PathDef;

@RequestMapping(PathDef.URI_TOOL_LOAD)
@SessionAttributes(types = SqlLoadForm.class)
public interface SqlLoadController {

	@ModelAttribute()
	SqlLoadForm getForm(@RequestParam(value = ParamDef.REQ_REF, required = false) Integer ref, Authentication auth);

	@RequestMapping()
	ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request,
			SessionStatus status);

	@RequestMapping(PathDef.SUBURI_START)
	ModelAndView start(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(PathDef.SUBURI_EXECUTE)
	ModelAndView execute(@Validated(G1.class) SqlLoadForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr);

	@RequestMapping(value = PathDef.SUBURI_EXECUTE, params = ParamDef.REQ_CREATE)
	ModelAndView create(@Validated(G1.class) SqlLoadForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request, SessionStatus status);

}
