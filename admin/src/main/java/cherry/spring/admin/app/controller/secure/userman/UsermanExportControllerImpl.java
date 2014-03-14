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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cherry.spring.common.lib.csv.CsvCreator;

@Controller
@RequestMapping(UsermanExportController.URI_PATH)
public class UsermanExportControllerImpl implements UsermanExportController {

	public static final String VIEW_PATH = "secure/userman/export/index";

	@Value("${admin.app.userman.export.contentType}")
	private String contentType;

	@Value("${admin.app.userman.export.charset}")
	private Charset charset;

	@Value("${admin.app.userman.export.headerName}")
	private String headerName;

	@Value("${admin.app.userman.export.headerValue}")
	private String headerValue;

	@Value("${admin.app.userman.export.filename}")
	private String filename;

	@RequestMapping()
	@Override
	public ModelAndView index(Authentication authentication, Locale locale,
			SitePreference sitePreference, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(VIEW_PATH);
		mav.addObject(new UsermanExportForm());
		return mav;
	}

	@RequestMapping(URI_PATH_REQ)
	@Override
	public ModelAndView request(@Valid UsermanExportForm usermanExportForm,
			BindingResult binding, Authentication authentication,
			Locale locale, SitePreference sitePreference,
			HttpServletRequest request, HttpServletResponse response) {

		if (binding.hasErrors()) {
			ModelAndView mav = new ModelAndView(VIEW_PATH);
			mav.addObject(usermanExportForm);
			return mav;
		}

		sendFile(usermanExportForm.getAppliedFrom(),
				usermanExportForm.getAppliedTo(), response);

		return null;
	}

	private void sendFile(String appliedFrom, String appliedTo,
			HttpServletResponse response) {

		response.setContentType(contentType);
		response.setCharacterEncoding(charset.name());
		String fname = MessageFormat.format(filename, new Date());
		response.setHeader(headerName, MessageFormat.format(headerValue, fname));

		try (OutputStream out = response.getOutputStream()) {
			@SuppressWarnings("resource")
			CsvCreator csv = new CsvCreator(
					new OutputStreamWriter(out, charset), "\r\n");
			csv.write(new String[] { "#", "DUMMY" });
			for (int row = 1; row <= 10; row++) {
				csv.write(new String[] { String.valueOf(row),
						UUID.randomUUID().toString() });
			}
			csv.flush();
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
