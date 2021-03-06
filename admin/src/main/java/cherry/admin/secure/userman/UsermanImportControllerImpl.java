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

package cherry.admin.secure.userman;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

import cherry.admin.PathDef;
import cherry.foundation.async.AsyncProcessFacade;

@Controller
public class UsermanImportControllerImpl implements UsermanImportController {

	public static final String ASYNC_PARAM = "asyncParam";

	@Autowired
	private AsyncProcessFacade asyncProcessFacade;

	@Override
	public UsermanImportForm getForm() {
		return new UsermanImportForm();
	}

	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_USERMAN_IMPORT_INIT);
		return mav;
	}

	@Override
	public ModelAndView execute(UsermanImportForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request, RedirectAttributes redirAttr) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(PathDef.VIEW_USERMAN_IMPORT_INIT);
			return mav;
		}

		long asyncId = asyncProcessFacade.launchFileProcess(auth.getName(), "UsermanImport", form.getFile(),
				"usermanImportFileProcessHandler");

		redirAttr.addFlashAttribute(ASYNC_PARAM, asyncId);

		UriComponents uc = fromMethodCall(on(UsermanImportController.class).finish(auth, locale, sitePref, request))
				.build();

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(uc.toUriString(), true));
		return mav;
	}

	@Override
	public ModelAndView finish(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_USERMAN_IMPORT_FINISH);
		return mav;
	}

}
