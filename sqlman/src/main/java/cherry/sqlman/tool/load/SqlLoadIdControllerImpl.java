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

package cherry.sqlman.tool.load;

import static cherry.foundation.springmvc.Contract.shouldExist;
import static cherry.sqlman.ParamDef.REQ_ID;
import static cherry.sqlman.util.ModelAndViewBuilder.redirect;
import static cherry.sqlman.util.ModelAndViewBuilder.withViewname;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.foundation.async.FileProcessResult;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.sqlman.LogicError;
import cherry.sqlman.tool.metadata.MetadataService;
import cherry.sqlman.tool.metadata.SqlMetadataForm;
import cherry.sqlman.tool.shared.DataSourceDef;
import cherry.sqlman.tool.util.Util;
import cherry.sqlman.util.ViewNameUtil;

@Controller
public class SqlLoadIdControllerImpl extends SqlLoadSupport implements SqlLoadIdController {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private LoadService loadService;

	@Autowired
	private DataSourceDef dataSourceDef;

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(SqlLoadIdController.class).start(0, null,
			null, null, null, null, null));

	private final String viewnameOfEdit = ViewNameUtil.fromMethodCall(on(SqlLoadIdController.class).edit(0, null, null,
			null, null, null, null));

	@Override
	public SqlMetadataForm getMetadata(int id, Authentication auth) {
		SqlMetadataForm mdForm = metadataService.findById(id, auth.getName());
		shouldExist(mdForm, SqlMetadataForm.class, id, auth.getName());
		return mdForm;
	}

	@Override
	public ModelAndView init(String redirTo, int id, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request, SessionStatus status) {
		status.setComplete();
		return redirect(redirectOnInit(redirTo, id)).build();
	}

	@Override
	public ModelAndView start(int id, SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		initializeForm(form, id, auth);
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView execute(int id, SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		try {

			FileProcessResult result = handleFile(form.getFile(), form.getDatabaseName(), form.getSql());
			redirAttr.addFlashAttribute(result);
			form.setFile(null);

			return redirect(redirectOnExecute(id)).build();
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			return withViewname(viewnameOfStart).build();
		}
	}

	@Override
	public ModelAndView edit(int id, SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		initializeForm(form, id, auth);
		return withViewname(viewnameOfEdit).build();
	}

	@Override
	public ModelAndView update(int id, SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfEdit).build();
		}

		form.setFile(null);

		if (loadService.update(id, form)) {
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
			SqlLoadForm form = new SqlLoadForm();
			initializeForm(form, id, auth);
			return withViewname(viewnameOfEdit).addObject(form).build();
		}

		if (metadataService.update(id, mdForm)) {
			return redirect(redirectOnUpdate(id)).build();
		} else {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			SqlLoadForm form = new SqlLoadForm();
			initializeForm(form, id, auth);
			return withViewname(viewnameOfEdit).addObject(form).build();
		}
	}

	private UriComponents redirectOnInit(String redirTo, int id) {
		if (StringUtils.isNotEmpty(redirTo)) {
			return UriComponentsBuilder.fromPath(redirTo).build();
		}
		return fromMethodCall(on(SqlLoadIdController.class).start(0, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private UriComponents redirectOnExecute(int id) {
		return fromMethodCall(on(SqlLoadIdController.class).start(0, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private UriComponents redirectOnUpdate(int id) {
		return fromMethodCall(on(SqlLoadIdController.class).edit(0, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private void initializeForm(SqlLoadForm form, int id, Authentication auth) {
		SqlLoadForm f = loadService.findById(id);
		shouldExist(f, SqlLoadForm.class, id);
		form.setDatabaseName(f.getDatabaseName());
		form.setSql(f.getSql());
		form.setLockVersion(f.getLockVersion());
	}

	private boolean hasErrors(SqlLoadForm form, BindingResult binding) {

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
