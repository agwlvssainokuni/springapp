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

import static cherry.foundation.springmvc.Contract.shouldExist;
import static cherry.sqlman.ParamDef.REQ_ID;
import static cherry.sqlman.util.ModelAndViewBuilder.redirect;
import static cherry.sqlman.util.ModelAndViewBuilder.withViewname;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.goods.paginate.PageSet;
import cherry.sqlman.LogicError;
import cherry.sqlman.tool.metadata.MetadataService;
import cherry.sqlman.tool.metadata.SqlMetadataForm;
import cherry.sqlman.tool.shared.ResultSet;
import cherry.sqlman.tool.util.Util;
import cherry.sqlman.util.ViewNameUtil;

@Controller
public class SqlClauseIdControllerImpl extends SqlClauseSupport implements SqlClauseIdController {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private ClauseService clauseService;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(SqlClauseIdController.class).start(0, null,
			null, null, null, null, null));

	private final String viewnameOfEdit = ViewNameUtil.fromMethodCall(on(SqlClauseIdController.class).edit(0, null,
			null, null, null, null, null));

	@Override
	public SqlMetadataForm getMetadata(int id, Authentication auth) {
		SqlMetadataForm mdForm = metadataService.findById(id, auth.getName());
		shouldExist(mdForm, SqlMetadataForm.class, id, auth.getName());
		return mdForm;
	}

	@Override
	public ModelAndView init(String redirTo, int id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo, id)).build();
	}

	@Override
	public ModelAndView start(int id, SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		initializeForm(form, id, auth, true);
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(int id, SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		try {
			initializeForm(form, id, auth, false);
			Pair<PageSet, ResultSet> pair = search(form);
			return withViewname(viewnameOfStart).addObject(pair.getLeft()).addObject(pair.getRight()).build();
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			return withViewname(viewnameOfStart).build();
		}
	}

	@Override
	public ModelAndView download(int id, final SqlClauseForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request, HttpServletResponse response) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		try {
			download(form, response);
			return null;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			return withViewname(viewnameOfStart).build();
		}
	}

	@Override
	public ModelAndView edit(int id, SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		initializeForm(form, id, auth, true);
		return withViewname(viewnameOfEdit).build();
	}

	@Override
	public ModelAndView update(int id, SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfEdit).build();
		}

		if (clauseService.update(id, form)) {
			return redirect(redirectOnUpdate(id)).build();
		} else {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			return withViewname(viewnameOfEdit).build();
		}
	}

	@Override
	public ModelAndView metadata(int id, SqlMetadataForm mdForm, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {

		if (hasMdErrors(mdForm, binding)) {
			SqlClauseForm form = new SqlClauseForm();
			initializeForm(form, id, auth, true);
			return withViewname(viewnameOfEdit).addObject(form).build();
		}

		if (metadataService.update(id, mdForm)) {
			return redirect(redirectOnUpdate(id)).build();
		} else {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			SqlClauseForm form = new SqlClauseForm();
			initializeForm(form, id, auth, true);
			return withViewname(viewnameOfEdit).addObject(form).build();
		}
	}

	private UriComponents redirectOnInit(String redirTo, int id) {
		if (StringUtils.isNotEmpty(redirTo)) {
			return UriComponentsBuilder.fromPath(redirTo).build();
		}
		return fromMethodCall(on(SqlClauseIdController.class).start(0, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private UriComponents redirectOnUpdate(int id) {
		return fromMethodCall(on(SqlClauseIdController.class).edit(0, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private void initializeForm(SqlClauseForm form, int id, Authentication auth, boolean includeParamMap) {
		SqlClauseForm f = clauseService.findById(id);
		shouldExist(f, SqlClauseForm.class, id);
		form.setDatabaseName(f.getDatabaseName());
		form.setSelect(f.getSelect());
		form.setFrom(f.getFrom());
		form.setWhere(f.getWhere());
		form.setGroupBy(f.getGroupBy());
		form.setHaving(f.getHaving());
		form.setOrderBy(f.getOrderBy());
		if (includeParamMap) {
			form.setParamMap(f.getParamMap());
		}
		form.setLockVersion(f.getLockVersion());
	}

	private boolean hasErrors(SqlClauseForm form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

	private boolean hasMdErrors(SqlMetadataForm form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

}
