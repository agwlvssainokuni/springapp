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
public class SqlLoadControllerImpl extends SqlLoadSupport implements SqlLoadController {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private LoadService loadService;

	@Autowired
	private DataSourceDef dataSourceDef;

	@Override
	public SqlLoadForm getForm(Integer ref, Authentication auth) {
		if (ref != null) {
			SqlMetadataForm mdForm = metadataService.findById(ref, auth.getName());
			if (mdForm != null) {
				SqlLoadForm form = loadService.findById(ref);
				if (form != null) {
					return form;
				}
			}
		}
		SqlLoadForm form = new SqlLoadForm();
		form.setDatabaseName(dataSourceDef.getDefaultName());
		return form;
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD);
		return mav;
	}

	@Override
	public ModelAndView start(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request,
			SessionStatus status) {

		status.setComplete();

		UriComponents uc = fromMethodCall(on(SqlLoadController.class).init(auth, locale, sitePref, request)).build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView execute(SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD);
			return mav;
		}

		try {

			FileProcessResult result = handleFile(form.getFile(), form.getDatabaseName(), form.getSql());
			redirAttr.addFlashAttribute(result);

			UriComponents uc = fromMethodCall(on(SqlLoadController.class).init(auth, locale, sitePref, request))
					.build();
			ModelAndView mav = new ModelAndView();
			mav.setView(new RedirectView(uc.toUriString(), true));
			return mav;
		} catch (BadSqlGrammarException ex) {
			LogicalErrorUtil.reject(binding, LogicError.BadSqlGrammer, Util.getRootCause(ex).getMessage());
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD);
			return mav;
		}
	}

	@Override
	public ModelAndView create(SqlLoadForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, SessionStatus status) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_TOOL_LOAD);
			return mav;
		}

		int id = loadService.create(form, auth.getName());
		status.setComplete();

		UriComponents uc = fromMethodCall(on(SqlLoadIdController.class).init(id, auth, locale, sitePref, request))
				.build();
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

}
