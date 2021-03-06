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

package cherry.admin.secure.gallery.zipcd;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import cherry.common.api.Response;
import cherry.common.api.ResponseHelper;
import cherry.common.api.StatusCode;

@Controller
public class ZipcdControllerImpl implements ZipcdController {

	@Autowired
	private ZipcdService zipcdService;

	@Autowired
	private ResponseHelper responseHelper;

	@Override
	public Response<List<ZipcdAddress>> execute(String zipcd, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		return executeImpl(zipcd);
	}

	@Override
	public Response<List<ZipcdAddress>> executeByPath(String zipcd, Authentication auth, Locale locale,
			SitePreference sitePref, HttpServletRequest request) {
		return executeImpl(zipcd);
	}

	private Response<List<ZipcdAddress>> executeImpl(String zipcd) {

		List<ZipcdAddress> result = zipcdService.search(zipcd);
		StatusCode statusCode = result.isEmpty() ? StatusCode.WARN : StatusCode.OK;

		return responseHelper.createResponse(statusCode, result);
	}

}
