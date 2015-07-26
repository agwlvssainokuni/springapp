/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.sqlman.admin.mailmgmt;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cherry.sqlman.PathDef;

@RequestMapping(PathDef.URI_ADMIN_MAILMGMT)
public interface MailmgmtController {

	@RequestMapping()
	ModelAndView start(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request);

	@RequestMapping(PathDef.SUBURI_EXECUTE)
	@ResponseBody
	SendResult execute(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request);

	@Setter
	@Getter
	@ToString
	public static class SendResult {
		private long totalCount;
		private long okCount;
		private long ngCount;
		private List<Long> okId;
		private List<Long> ngId;
	}

}
