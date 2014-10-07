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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import cherry.spring.admin.controller.PathDef;
import cherry.spring.admin.service.secure.asyncproc.AsyncProcService;
import cherry.spring.admin.service.secure.asyncproc.AsyncProcService.Result;

@Controller
public class AsyncProcControllerImpl implements AsyncProcController {

	@Value("${admin.app.asyncproc.pageSize}")
	private int defaultPageSize;

	@Autowired
	private AsyncProcService asyncProcService;

	@Override
	public ModelAndView init(int pageNo, int pageSz, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request) {

		Result result = asyncProcService.searchAsyncProc(auth.getName(),
				pageNo, (pageSz <= 0 ? defaultPageSize : pageSz));

		ModelAndView mav = new ModelAndView(PathDef.VIEW_ASYNCPROC_INIT);
		mav.addObject(result);
		return mav;
	}

}
