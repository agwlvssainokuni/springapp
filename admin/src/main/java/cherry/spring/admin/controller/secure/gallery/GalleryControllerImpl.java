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

package cherry.spring.admin.controller.secure.gallery;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import cherry.spring.admin.controller.PathDef;

@Controller
public class GalleryControllerImpl implements GalleryController {

	@Override
	public ModelAndView init(Authentication auth, Locale locale, SitePreference sitePref, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(PathDef.VIEW_GALLERY_INIT);
		return mav;
	}

}
