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

package cherry.sqlman.tool.statement;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cherry.foundation.validator.groups.G1;
import cherry.sqlman.ParamDef;
import cherry.sqlman.PathDef;

@RequestMapping(PathDef.URI_TOOL_STATEMENT)
public interface SqlStatementController {

	@ModelAttribute()
	SqlStatementForm getForm(@RequestParam(value = ParamDef.REQ_REF, required = false) Integer ref, Authentication auth);

	@RequestMapping()
	ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(PathDef.SUBURI_EXECUTE)
	ModelAndView execute(@Validated(G1.class) SqlStatementForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(value = PathDef.SUBURI_EXECUTE, params = ParamDef.REQ_DOWNLOAD)
	ModelAndView download(@Validated(G1.class) SqlStatementForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request, HttpServletResponse response);

	@RequestMapping(value = PathDef.SUBURI_EXECUTE, params = ParamDef.REQ_CREATE)
	ModelAndView create(@Validated(G1.class) SqlStatementForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request);

}
