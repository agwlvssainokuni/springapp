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

package cherry.sqlman.tool.search;

import static cherry.sqlman.util.ModelAndViewBuilder.redirect;
import static cherry.sqlman.util.ModelAndViewBuilder.withViewname;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
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

import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.paginate.PagedList;
import cherry.goods.util.LocalDateTimeUtil;
import cherry.sqlman.Config;
import cherry.sqlman.Published;
import cherry.sqlman.SqlType;
import cherry.sqlman.util.ViewNameUtil;

@Controller
public class SqlSearchControllerImpl implements SqlSearchController {

	@Autowired
	private Config config;

	@Autowired
	private SearchService searchService;

	@Autowired
	private BizDateTime bizDateTime;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(SqlSearchController.class).start(null, null,
			null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {
		status.setComplete();
		return redirect(redirectOnInit(redirTo)).build();
	}

	@Override
	public ModelAndView start(SqlSearchForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		initializeForm(form);
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(SqlSearchForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		if (form.getPageNo() <= 0L) {
			form.setPageNo(0L);
		}
		if (form.getPageSz() <= 0L) {
			form.setPageSz(config.getPaginatorDefaultPageSize());
		}

		PagedList<Map<String, ?>> result = searchService.search(form, auth.getName());

		return withViewname(viewnameOfStart).addObject(result).build();
	}

	private UriComponents redirectOnInit(String redirTo) {
		if (StringUtils.isNotEmpty(redirTo)) {
			return UriComponentsBuilder.fromPath(redirTo).build();
		} else {
			return fromMethodCall(on(SqlSearchController.class).start(null, null, null, null, null, null)).build();
		}
	}

	private void initializeForm(SqlSearchForm form) {

		LocalDate today = bizDateTime.today();

		LocalDateTime from = LocalDateTimeUtil.rangeFrom(today.minusDays(config.getSearchDefaultFromDays()));
		form.setRegisteredFromDt(from.toLocalDate());
		form.setRegisteredFromTm(from.toLocalTime());

		LocalDateTime to = LocalDateTimeUtil.rangeTo(today).minusSeconds(1);
		form.setRegisteredToDt(to.toLocalDate());
		form.setRegisteredToTm(to.toLocalTime());

		form.setSqlType(Arrays.asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setPublished(Arrays.asList(Published.PUBLIC, Published.PRIVATE));
	}

	private boolean hasErrors(SqlSearchForm form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

}
