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

import static cherry.sqlman.ParamDef.REQ_ID;
import static cherry.sqlman.ParamDef.REQ_REF;
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
import cherry.sqlman.tool.shared.DataSourceDef;
import cherry.sqlman.tool.shared.ResultSet;
import cherry.sqlman.tool.util.Util;
import cherry.sqlman.util.ViewNameUtil;

@Controller
public class SqlClauseControllerImpl extends SqlClauseSupport implements SqlClauseController {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private ClauseService clauseService;

	@Autowired
	private DataSourceDef dataSourceDef;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(SqlClauseController.class).start(null, null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Integer ref, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo, ref)).build();
	}

	@Override
	public ModelAndView start(Integer ref, SqlClauseForm form, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, NativeWebRequest request) {
		initializeForm(form, ref, auth);
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		try {
			Pair<PageSet, ResultSet> pair = search(form);
			return withViewname(viewnameOfStart).addObject(pair.getLeft()).addObject(pair.getRight()).build();
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			return withViewname(viewnameOfStart).build();
		}
	}

	@Override
	public ModelAndView download(SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, HttpServletResponse response) {

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
	public ModelAndView create(SqlClauseForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		int id = clauseService.create(form, auth.getName());
		return redirect(redirectOnCreate(id)).build();
	}

	private UriComponents redirectOnInit(String redirTo, Integer ref) {
		if (StringUtils.isNotEmpty(redirTo)) {
			return UriComponentsBuilder.fromPath(redirTo).build();
		} else {
			UriComponentsBuilder ucb = fromMethodCall(on(SqlClauseController.class).start(null, null, null, null, null,
					null, null));
			if (ref == null) {
				return ucb.replaceQuery(StringUtils.EMPTY).build();
			} else {
				return ucb.replaceQueryParam(REQ_REF, ref).build();
			}
		}
	}

	private UriComponents redirectOnCreate(int id) {
		return fromMethodCall(on(SqlClauseIdController.class).init(null, 0, null, null, null, null)).replaceQueryParam(
				REQ_ID, id).build();
	}

	private void initializeForm(SqlClauseForm form, Integer ref, Authentication auth) {
		if (ref != null) {
			SqlMetadataForm mdForm = metadataService.findById(ref, auth.getName());
			if (mdForm != null) {
				SqlClauseForm f = clauseService.findById(ref);
				if (f != null) {
					form.setDatabaseName(f.getDatabaseName());
					form.setSelect(f.getSelect());
					form.setFrom(f.getFrom());
					form.setWhere(f.getWhere());
					form.setGroupBy(f.getGroupBy());
					form.setHaving(f.getHaving());
					form.setOrderBy(f.getOrderBy());
					form.setParamMap(f.getParamMap());
					form.setLockVersion(f.getLockVersion());
					return;
				}
			}
		}
		form.setDatabaseName(dataSourceDef.getDefaultName());
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

}
