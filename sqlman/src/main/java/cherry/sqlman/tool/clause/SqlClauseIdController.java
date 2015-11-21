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

package cherry.sqlman.tool.clause;

import static cherry.sqlman.ParamDef.REQ_ID;
import static cherry.sqlman.ParamDef.REQ_TO;
import static cherry.sqlman.PathDef.SUBURI_EDIT;
import static cherry.sqlman.PathDef.SUBURI_EXECUTE;
import static cherry.sqlman.PathDef.SUBURI_METADATA;
import static cherry.sqlman.PathDef.SUBURI_START;
import static cherry.sqlman.PathDef.SUBURI_UPDATE;
import static cherry.sqlman.PathDef.URI_TOOL_CLAUSE;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import cherry.foundation.validator.groups.G2;
import cherry.foundation.validator.groups.G3;
import cherry.foundation.validator.groups.G9;
import cherry.sqlman.ParamDef;
import cherry.sqlman.tool.metadata.SqlMetadataForm;

@RequestMapping(URI_TOOL_CLAUSE)
public interface SqlClauseIdController {

	@ModelAttribute()
	SqlMetadataForm getMetadata(@RequestParam(REQ_ID) int id, Authentication auth);

	@RequestMapping(params = { REQ_ID })
	ModelAndView init(@RequestParam(value = REQ_TO, required = false) String redirTo, @RequestParam(REQ_ID) int id,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_START)
	ModelAndView start(@RequestParam(REQ_ID) int id, @Validated(G9.class) SqlClauseForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_EXECUTE)
	ModelAndView execute(@RequestParam(REQ_ID) int id, @Validated(G3.class) SqlClauseForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_EXECUTE, params = ParamDef.REQ_DOWNLOAD)
	ModelAndView download(@RequestParam(REQ_ID) int id, @Validated(G3.class) SqlClauseForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request,
			HttpServletResponse response);

	@RequestMapping(value = SUBURI_EDIT)
	ModelAndView edit(@RequestParam(REQ_ID) int id, @Validated(G9.class) SqlClauseForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_UPDATE)
	ModelAndView update(@RequestParam(REQ_ID) int id, @Validated(G2.class) SqlClauseForm form, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

	@RequestMapping(value = SUBURI_METADATA)
	ModelAndView metadata(@RequestParam(REQ_ID) int id, @Validated SqlMetadataForm mdForm, BindingResult binding,
			Authentication auth, Locale locale, SitePreference sitePref, NativeWebRequest request);

}
