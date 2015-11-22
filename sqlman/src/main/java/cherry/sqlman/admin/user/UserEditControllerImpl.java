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

package cherry.sqlman.admin.user;

import static cherry.sqlman.ParamDef.FLASH_CREATED;
import static cherry.sqlman.ParamDef.FLASH_UPDATED;
import static cherry.sqlman.ParamDef.REQ_ID;
import static cherry.sqlman.util.ModelAndViewBuilder.redirect;
import static cherry.sqlman.util.ModelAndViewBuilder.withViewname;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.Locale;

import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;

import cherry.sqlman.util.ViewNameUtil;

@Controller
public class UserEditControllerImpl implements UserEditController {

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(UserEditController.class).start(null, null,
			null, null, null, null));

	private final String viewnameOfEdit = ViewNameUtil.fromMethodCall(on(UserEditController.class).edit(0, null, null,
			null, null, null, null));

	@Override
	public ModelAndView start(UserEditForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	@Override
	public ModelAndView create(UserEditForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		// TODO
		Integer id = 0;

		redirAttr.addFlashAttribute(FLASH_CREATED, Boolean.TRUE);

		return redirect(redirectToEdit(id)).build();
	}

	@Override
	public ModelAndView edit(int id, UserEditForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		initializeForm(form, id);
		return withViewname(viewnameOfEdit).build();
	}

	@Override
	public ModelAndView update(int id, UserEditForm form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request, RedirectAttributes redirAttr) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfEdit).build();
		}

		// TODO

		redirAttr.addFlashAttribute(FLASH_UPDATED, Boolean.TRUE);

		return redirect(redirectToEdit(id)).build();
	}

	private UriComponents redirectToEdit(int id) {
		return fromMethodCall(on(UserEditController.class).edit(0, null, null, null, null, null, null))
				.replaceQueryParam(REQ_ID, id).build();
	}

	private void initializeForm(UserEditForm form, int id) {
	}

	private boolean hasErrors(UserEditForm form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

}
