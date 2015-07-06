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

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.goods.paginate.PageSet;
import cherry.sqlman.LogicError;
import cherry.sqlman.PathDef;
import cherry.sqlman.tool.metadata.MetadataService;
import cherry.sqlman.tool.metadata.SqlMetadataForm;
import cherry.sqlman.tool.shared.DataSourceDef;
import cherry.sqlman.tool.shared.ResultSet;
import cherry.sqlman.tool.util.Util;

@Controller
public class SqlClauseControllerImpl extends SqlClauseSupport implements SqlClauseController {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private ClauseService clauseService;

	@Autowired
	private DataSourceDef dataSourceDef;

	@Override
	public SqlClauseForm getForm(Integer ref, Authentication auth) {
		if (ref != null) {
			SqlMetadataForm mdForm = metadataService.findById(ref, auth.getName());
			if (mdForm != null) {
				SqlClauseForm form = clauseService.findById(ref);
				if (form != null) {
					return form;
				}
			}
		}
		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName(dataSourceDef.getDefaultName());
		return form;
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_CLAUSE);
		return mav;
	}

	@Override
	public ModelAndView execute(SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_CLAUSE);
			return mav;
		}

		try {

			Pair<PageSet, ResultSet> pair = search(form);

			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_CLAUSE);
			mav.addObject(pair.getLeft());
			mav.addObject(pair.getRight());
			return mav;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_CLAUSE);
			return mav;
		}
	}

	@Override
	public ModelAndView download(SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_CLAUSE);
			return mav;
		}

		try {
			download(form, response);
			return null;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_CLAUSE);
			return mav;
		}
	}

	@Override
	public ModelAndView create(SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_CLAUSE);
			return mav;
		}

		int id = clauseService.create(form, auth.getName());

		UriComponents uc = fromMethodCall(on(SqlClauseIdController.class).init(id, auth, locale, sitePref, request))
				.build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
