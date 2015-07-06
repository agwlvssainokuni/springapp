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

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Arrays;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.paginate.PagedList;
import cherry.goods.util.LocalDateTimeUtil;
import cherry.sqlman.PathDef;
import cherry.sqlman.Published;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.tool.metadata.MetadataCondition;
import cherry.sqlman.tool.metadata.MetadataService;

@Controller
public class SqlSearchControllerImpl implements SqlSearchController {

	@Value("${sqlman.search.defaultFromDays}")
	private Integer defaultFromDays;

	@Value("${sqlman.paginator.pageSize}")
	private int defaultPageSize;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private BizDateTime bizDateTime;

	@Override
	public SqlSearchForm getForm() {
		LocalDate today = bizDateTime.today();
		SqlSearchForm form = new SqlSearchForm();

		LocalDateTime from = LocalDateTimeUtil.rangeFrom(today.minusDays(defaultFromDays));
		form.setRegisteredFromDt(from.toLocalDate());
		form.setRegisteredFromTm(from.toLocalTime());

		LocalDateTime to = LocalDateTimeUtil.rangeTo(today).minusSeconds(1);
		form.setRegisteredToDt(to.toLocalDate());
		form.setRegisteredToTm(to.toLocalTime());

		form.setSqlType(Arrays.asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setPublished(Arrays.asList(Published.PUBLIC, Published.PRIVATE));
		return form;
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_SEARCH);
		return mav;
	}

	@Override
	public ModelAndView start(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request,
			SessionStatus status) {
		status.setComplete();
		UriComponents uc = fromMethodCall(on(SqlSearchController.class).init(auth, locale, sitePref, request)).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView execute(SqlSearchForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_SEARCH);
			return mav;
		}

		MetadataCondition cond = createSqlCondition(form, auth.getName());
		long pageNo = form.getPageNo();
		long pageSz = (form.getPageSz() <= 0 ? defaultPageSize : form.getPageSz());

		PagedList<BSqlMetadata> result = metadataService.search(cond, pageNo, pageSz);

		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_SEARCH);
		mav.addObject(result);
		return mav;
	}

	private MetadataCondition createSqlCondition(SqlSearchForm form, String loginId) {
		MetadataCondition cond = new MetadataCondition();
		cond.setName(form.getName());
		cond.setSqlType(form.getSqlType());
		cond.setPublished(form.getPublished());
		cond.setRegisteredFrom(LocalDateTimeUtil.rangeFrom(form.getRegisteredFromDt(), form.getRegisteredFromTm()));
		cond.setRegisteredTo(LocalDateTimeUtil.rangeTo(form.getRegisteredToDt(), form.getRegisteredToTm()));
		cond.setLoginId(loginId);
		return cond;
	}

}
