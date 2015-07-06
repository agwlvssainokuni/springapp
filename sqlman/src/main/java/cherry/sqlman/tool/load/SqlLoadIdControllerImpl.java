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
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.foundation.async.FileProcessResult;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.sqlman.LogicError;
import cherry.sqlman.PathDef;
import cherry.sqlman.tool.metadata.MetadataService;
import cherry.sqlman.tool.metadata.SqlMetadataForm;
import cherry.sqlman.tool.shared.DataSourceDef;
import cherry.sqlman.tool.util.Util;

@Controller
public class SqlLoadIdControllerImpl extends SqlLoadSupport implements SqlLoadIdController {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private LoadService loadService;

	@Autowired
	private DataSourceDef dataSourceDef;

	@Override
	public SqlMetadataForm getMetadata(int id, Authentication auth) {
		SqlMetadataForm mdForm = metadataService.findById(id, auth.getName());
		shouldExist(mdForm, SqlMetadataForm.class, id, auth.getName());
		return mdForm;
	}

	@Override
	public SqlLoadForm getForm(int id) {
		SqlLoadForm form = loadService.findById(id);
		shouldExist(form, SqlLoadForm.class, id);
		return form;
	}

	@Override
	public ModelAndView init(int id, Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD_ID);
		return mav;
	}

	@Override
	public ModelAndView start(int id, Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request, SessionStatus status) {

		status.setComplete();

		UriComponents uc = fromMethodCall(on(SqlLoadIdController.class).init(id, auth, locale, sitePref, request))
				.build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView execute(int id, SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD_ID);
			return mav;
		}

		try {

			FileProcessResult result = handleFile(form.getFile(), form.getDatabaseName(), form.getSql());

			redirAttr.addFlashAttribute(result);

			UriComponents uc = fromMethodCall(on(SqlLoadIdController.class).init(id, auth, locale, sitePref, request))
					.build();

			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(uc.toUriString(), true));
			return mav;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD_ID);
			return mav;
		}
	}

	@Override
	public ModelAndView update(int id, SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD_ID);
			return mav;
		}

		if (loadService.update(id, form)) {
			UriComponents uc = fromMethodCall(on(SqlLoadIdController.class).init(id, auth, locale, sitePref, request))
					.build();
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(uc.toUriString(), true));
			return mav;
		} else {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD_ID);
			mav.addObject(PathDef.PATHVAR_ID, id);
			return mav;
		}
	}

	@Override
	public ModelAndView metadata(int id, SqlMetadataForm mdForm, BindingResult binding, Authentication auth,
			Locale locale, SitePreference sitePref, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD_ID);
			mav.addObject(PathDef.PATHVAR_ID, id);
			return mav;
		}

		if (metadataService.update(id, mdForm)) {
			UriComponents uc = fromMethodCall(on(SqlLoadIdController.class).init(id, auth, locale, sitePref, request))
					.build();
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(uc.toUriString(), true));
			return mav;
		} else {
			LogicalErrorUtil.rejectOnOptimisticLockError(binding);
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD_ID);
			mav.addObject(PathDef.PATHVAR_ID, id);
			return mav;
		}
	}

}
