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

package cherry.spring.admin.app.controller.secure.upload;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cherry.spring.common.lib.csv.CsvParser;

@Controller
@RequestMapping(UploadController.URI_PATH)
public class UploadControllerImpl implements UploadController {

	public static final String VIEW_PATH = "secure/upload/index";

	public static final String VIEW_PATH_FIN = "secure/upload/finish";

	@Value("${admin.app.upload.charset}")
	private Charset charset;

	@RequestMapping()
	@Override
	public ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(new UploadForm());
		return mav;
	}

	@RequestMapping(URI_PATH_REQ)
	@Override
	public ModelAndView request(@Valid UploadForm uploadForm,
			BindingResult binding, RedirectAttributes redirectAttributes,
			Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(uploadForm);
			return mav;
		}

		UploadInfo uploadInfo = receiveFile(uploadForm.getFile());
		if (uploadInfo != null) {
			redirectAttributes.addFlashAttribute(uploadInfo);
		}

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

	private UploadInfo receiveFile(MultipartFile file) {
		try (InputStream in = file.getInputStream()) {
			@SuppressWarnings("resource")
			CsvParser csv = new CsvParser(new InputStreamReader(in, charset));

			String[] header = csv.read();
			if (header == null || header.length < 2) {
				UploadInfo uploadInfo = new UploadInfo();
				uploadInfo.setRowCount(0);
				return uploadInfo;
			}
			String columnName = header[1];

			int rowCount = 0;
			while (csv.read() != null) {
				rowCount++;
			}

			UploadInfo uploadInfo = new UploadInfo();
			uploadInfo.setColumnName(columnName);
			uploadInfo.setRowCount(rowCount);
			return uploadInfo;

		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
