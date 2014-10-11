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

package cherry.spring.admin.controller.secure.asyncproc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cherry.spring.admin.controller.PathDef;

@RequestMapping(PathDef.URI_ASYNCPROC)
public interface AsyncProcController {

	@RequestMapping()
	ModelAndView init(
			@RequestParam(value = PathDef.PARAM_NO, defaultValue = "0") long pageNo,
			@RequestParam(value = PathDef.PARAM_SZ, defaultValue = "0") long pageSz,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request);

}
