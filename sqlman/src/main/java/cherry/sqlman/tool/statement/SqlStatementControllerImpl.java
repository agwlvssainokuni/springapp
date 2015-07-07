/*
 * Copyright 2014,2015 agwlvssainokuni
 *
 * Licensed under t
 Apache License, Version 2.0 (the "License");
 * you may not use this file except in complianc
with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
import org.springframework.stereotype.Controller;

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cherry.sqlman.tool.statement;

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
public class SqlStatementControllerImpl extends SqlStatementSupport implements SqlStatementController {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private StatementService statementService;

	@Autowired
	private DataSourceDef dataSourceDef;

	@Override
	public SqlStatementForm getForm(Integer ref, Authentication auth) {
		if (ref != null) {
			SqlMetadataForm mdForm = metadataService.findById(ref, auth.getName());
			if (mdForm != null) {
				SqlStatementForm form = statementService.findById(ref);
				if (form != null) {
					return form;
				}
			}
		}
		SqlStatementForm form = new SqlStatementForm();
		form.setDatabaseName(dataSourceDef.getDefaultName());
		return form;
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT);
		return mav;
	}

	@Override
	public ModelAndView execute(SqlStatementForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT);
			return mav;
		}

		try {

			Pair<PageSet, ResultSet> pair = search(form);

			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT);
			mav.addObject(pair.getLeft());
			mav.addObject(pair.getRight());
			return mav;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT);
			return mav;
		}
	}

	@Override
	public ModelAndView download(SqlStatementForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT);
			return mav;
		}

		try {
			download(form, response);
			return null;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT);
			return mav;
		}
	}

	@Override
	public ModelAndView create(SqlStatementForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT);
			return mav;
		}

		int id = statementService.create(form, auth.getName());

		UriComponents uc = fromMethodCall(on(SqlStatementIdController.class).init(id, auth, locale, sitePref, request))
				.build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
