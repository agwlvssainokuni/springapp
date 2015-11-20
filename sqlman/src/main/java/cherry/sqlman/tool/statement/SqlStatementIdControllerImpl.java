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

package cherry.sqlman.tool.statement;

import static cherry.foundation.springmvc.Contract.shouldExist;
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
import cherry.sqlman.ParamDef;
import cherry.sqlman.PathDef;
import cherry.sqlman.tool.metadata.MetadataService;
import cherry.sqlman.tool.metadata.SqlMetadataForm;
import cherry.sqlman.tool.shared.ResultSet;
import cherry.sqlman.tool.util.Util;

@Controller
public class SqlStatementIdControllerImpl extends SqlStatementSupport implements SqlStatementIdController {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private StatementService statementService;

	@Override
	public SqlMetadataForm getMetadata(int id, Authentication auth) {
		SqlMetadataForm mdForm = metadataService.findById(id, auth.getName());
		shouldExist(mdForm, SqlMetadataForm.class, id, auth.getName());
		return mdForm;
	}

	@Override
	public SqlStatementForm getForm(int id) {
		SqlStatementForm form = statementService.findById(id);
		shouldExist(form, SqlStatementForm.class, id);
		return form;
	}

	@Override
	public ModelAndView init(int id, Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID);
		mav.addObject(ParamDef.PATHVAR_ID, id);
		return mav;
	}

	@Override
	public ModelAndView execute(int id, SqlStatementForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			return mav;
		}

		try {

			Pair<PageSet, ResultSet> pair = search(form);

			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			mav.addObject(pair.getLeft());
			mav.addObject(pair.getRight());
			return mav;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			return mav;
		}
	}

	@Override
	public ModelAndView download(int id, SqlStatementForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request, HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			return mav;
		}

		try {
			download(form, response);
			return null;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			return mav;
		}
	}

	@Override
	public ModelAndView edit(int id, Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID_EDIT);
		mav.addObject(ParamDef.PATHVAR_ID, id);
		return mav;
	}

	@Override
	public ModelAndView update(int id, SqlStatementForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID_EDIT);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			return mav;
		}

		if (statementService.update(id, form)) {
			UriComponents uc = fromMethodCall(
					on(SqlStatementIdController.class).edit(id, auth, locale, sitePref, request)).build();
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(uc.toUriString(), true));
			return mav;
		} else {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID_EDIT);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			return mav;
		}
	}

	@Override
	public ModelAndView metadata(int id, SqlMetadataForm mdForm, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID_EDIT);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			return mav;
		}

		if (metadataService.update(id, mdForm)) {
			UriComponents uc = fromMethodCall(
					on(SqlStatementIdController.class).edit(id, auth, locale, sitePref, request)).build();
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(uc.toUriString(), true));
			return mav;
		} else {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_STATEMENT_ID_EDIT);
			mav.addObject(ParamDef.PATHVAR_ID, id);
			return mav;
		}
	}

}
