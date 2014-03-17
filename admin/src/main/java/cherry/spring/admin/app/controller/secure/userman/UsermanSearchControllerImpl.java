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

package cherry.spring.admin.app.controller.secure.userman;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cherry.spring.common.lib.pager.Paginator;

@Controller
@RequestMapping(UsermanSearchController.URI_PATH)
public class UsermanSearchControllerImpl implements UsermanSearchController {

	public static final String VIEW_PATH = "secure/userman/search/index";

	@Value("${admin.app.userman.search.pageSize}")
	private int defaultPageSize;

	@Autowired
	private Paginator paginator;

	@ModelAttribute(UsermanSearchForm.NAME)
	@Override
	public UsermanSearchForm getForm() {
		return new UsermanSearchForm();
	}

	@RequestMapping()
	@Override
	public ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@RequestMapping(URI_PATH_REQ)
	@Override
	public ModelAndView request(
			@Validated UsermanSearchForm form,
			BindingResult binding,
			@RequestParam(value = PARAM_NO, required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = PARAM_SZ, required = false, defaultValue = "0") int pageSz,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		int pageSize = (pageSz <= 0 ? defaultPageSize : pageSz);

		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

}
