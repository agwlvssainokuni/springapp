/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.admin.app.controller.secure.userman;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cherry.spring.admin.app.service.secure.userman.UsermanImportService;
import cherry.spring.common.lib.db.DataLoader.Result;

@Controller
@RequestMapping(UsermanImportController.URI_PATH)
public class UsermanImportControllerImpl implements UsermanImportController {

	public static final String VIEW_PATH = "secure/userman/import/index";

	public static final String VIEW_PATH_FIN = "secure/userman/import/finish";

	@Value("${admin.app.userman.import.charset}")
	private Charset charset;

	@Autowired
	private UsermanImportService usermanImportService;

	@ModelAttribute(UsermanImportForm.NAME)
	@Override
	public UsermanImportForm getForm() {
		return new UsermanImportForm();
	}

	@RequestMapping()
	@Override
	public ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		return mav;
	}

	@RequestMapping(URI_PATH_REQ)
	@Override
	public ModelAndView request(@Validated UsermanImportForm form,
			BindingResult binding, RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			return mav;
		}

		Result result = receiveFile(form.getFile());
		redirectAttributes.addFlashAttribute(result);

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView(URI_PATH_FIN, true));
		return mav;
	}

	@RequestMapping(URI_PATH_FIN)
	@Override
	public ModelAndView finish(RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH_FIN);
		mav.addAllObjects(redirectAttributes.getFlashAttributes());
		return mav;
	}

	private Result receiveFile(MultipartFile file) {
		try (InputStream in = file.getInputStream()) {
			return usermanImportService.importUsers(new InputStreamReader(in,
					charset));
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
