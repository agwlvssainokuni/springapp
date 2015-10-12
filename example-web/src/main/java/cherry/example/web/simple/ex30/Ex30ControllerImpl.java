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

package cherry.example.web.simple.ex30;

import static cherry.example.web.PathDef.VIEW_SIMPLE_EX30_START;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.db.gen.query.BExTbl1;
import cherry.example.web.util.ModelAndViewBuilder;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.goods.paginate.PagedList;

@Controller
public class Ex30ControllerImpl implements Ex30Controller {

	@Autowired
	private Ex30Service ex30Service;

	@Override
	public ModelAndView init(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {

		status.setComplete();

		return ModelAndViewBuilder.redirect(redirectOnInit(redir, auth, locale, sitePref, request)).build();
	}

	@Override
	public ModelAndView start(Ex30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		form.setPno(0L);

		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX30_START).build();
	}

	@Override
	public ModelAndView execute(Ex30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding, auth, locale, sitePref, request)) {
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX30_START).build();
		}

		PagedList<BExTbl1> pagedList = ex30Service.search(form);
		if (pagedList.getPageSet().getTotalCount() <= 0L) {
			LogicalErrorUtil.rejectOnSearchResultEmpty(binding);
			return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX30_START).build();
		}

		return ModelAndViewBuilder.withViewname(VIEW_SIMPLE_EX30_START).addObject(pagedList).build();
	}

	private UriComponents redirectOnInit(String redir, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return UriComponentsBuilder.fromPath(redir).build();
	}

	private boolean hasErrors(Ex30Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		if (binding.hasErrors()) {
			return true;
		}

		// 整合性チェック

		return false;
	}

}
