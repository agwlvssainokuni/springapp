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

package cherry.spring.admin.controller.secure.userman;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import cherry.goods.paginate.PagedList;
import cherry.spring.admin.controller.PathDef;
import cherry.spring.admin.service.secure.userman.UsermanSearchService;
import cherry.spring.common.db.gen.dto.User;
import cherry.spring.fwcore.bizdtm.BizDateTime;
import cherry.spring.fwcore.download.DownloadAction;
import cherry.spring.fwcore.download.DownloadHelper;

@Controller
public class UsermanSearchControllerImpl implements UsermanSearchController {

	@Value("${admin.app.userman.search.pageSize}")
	private int defaultPageSize;

	@Value("${admin.app.userman.export.contentType}")
	private String contentType;

	@Value("${admin.app.userman.export.filename}")
	private String filename;

	@Autowired
	private UsermanSearchService usermanSearchService;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private DownloadHelper downloadHelper;

	@Override
	public UsermanSearchForm getForm() {
		return new UsermanSearchForm();
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_USERMAN_SEARCH_INIT);
		return mav;
	}

	@Override
	public ModelAndView execute(UsermanSearchForm form, BindingResult binding,
			long pageNo, long pageSz, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_USERMAN_SEARCH_INIT);
			return mav;
		}

		PagedList<User> result = usermanSearchService.searchUsers(form, pageNo,
				(pageSz <= 0 ? defaultPageSize : pageSz));

		ModelAndView mav = new ModelAndView(PathDef.VIEW_USERMAN_SEARCH_INIT);
		mav.addObject(result);
		return mav;
	}

	@Override
	public ModelAndView download(final UsermanSearchForm form,
			BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request,
			HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					PathDef.VIEW_USERMAN_SEARCH_INIT);
			return mav;
		}

		DownloadAction action = new DownloadAction() {
			@Override
			public long doDownload(Writer writer) throws IOException {
				return usermanSearchService.exportUsers(writer, form);
			}
		};
		downloadHelper.download(response, contentType, filename,
				bizDateTime.now(), action);

		return null;
	}

}
