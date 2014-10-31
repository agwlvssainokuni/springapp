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

package cherry.spring.common.helper.zipcd;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import cherry.spring.common.api.ApiResponse;

@Controller
public class ZipcdControllerImpl implements ZipcdController {

	@Override
	public ApiResponse<List<ZipcdAddress>> execute(String zipcd,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		return executeImpl(zipcd);
	}

	@Override
	public ApiResponse<List<ZipcdAddress>> executeByPath(String zipcd,
			Authentication auth, Locale locale, SitePreference sitePref,
			HttpServletRequest request) {
		return executeImpl(zipcd);
	}

	private ApiResponse<List<ZipcdAddress>> executeImpl(String zipcd) {
		// TODO 実装する

		ZipcdAddress addr1 = new ZipcdAddress();
		addr1.setZipcd(zipcd);
		addr1.setCityCd("CITY CODE 1");
		addr1.setPref("PREF 1");
		addr1.setCity("CITY 1");
		addr1.setAddr("ADDR 1");
		addr1.setPrefKana("PREF KANA 1");
		addr1.setCityKana("CITY KANA 1");
		addr1.setAddrKana("ADDR KANA 1");

		ZipcdAddress addr2 = new ZipcdAddress();
		addr2.setZipcd(zipcd);
		addr2.setCityCd("CITY CODE 2");
		addr2.setPref("PREF 2");
		addr2.setCity("CITY 2");
		addr2.setAddr("ADDR 2");
		addr2.setPrefKana("PREF KANA 2");
		addr2.setCityKana("CITY KANA 2");
		addr2.setAddrKana("ADDR KANA 2");

		ApiResponse<List<ZipcdAddress>> response = new ApiResponse<>();
		response.setCode(0);
		response.setDescription("OK");
		response.setResult(Arrays.asList(addr1, addr2));
		return response;
	}

}
