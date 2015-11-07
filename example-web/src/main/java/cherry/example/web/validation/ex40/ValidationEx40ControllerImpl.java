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

package cherry.example.web.validation.ex40;

import static cherry.example.web.util.ModelAndViewBuilder.redirect;
import static cherry.example.web.util.ModelAndViewBuilder.withViewname;
import static cherry.example.web.util.ModelAndViewBuilder.withoutView;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cherry.example.web.util.ViewNameUtil;
import cherry.example.web.validation.ex40.ValidationEx40Form.List2Property;
import cherry.example.web.validation.ex40.ValidationEx40Form.Map2Property;

@Controller
public class ValidationEx40ControllerImpl implements ValidationEx40Controller {

	private final String viewnameOfStart = ViewNameUtil.fromMethodCall(on(ValidationEx40Controller.class).start(null,
			null, null, null, null, null));

	@Override
	public ModelAndView init(String redirTo, Authentication auth, Locale locale, SitePreference sitePref,
			NativeWebRequest request) {
		return redirect(redirectOnInit(redirTo)).build();
	}

	@Override
	public ModelAndView start(ValidationEx40Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).addObject(createForm()).build();
	}

	@Override
	public ModelAndView confirm(ValidationEx40Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {

		if (hasErrors(form, binding)) {
			return withViewname(viewnameOfStart).build();
		}

		return withoutView().build();
	}

	@Override
	public ModelAndView back(ValidationEx40Form form, BindingResult binding, Authentication auth, Locale locale,
			SitePreference sitePref, NativeWebRequest request) {
		return withViewname(viewnameOfStart).build();
	}

	private UriComponents redirectOnInit(String redir) {
		if (StringUtils.isNotEmpty(redir)) {
			return UriComponentsBuilder.fromPath(redir).build();
		} else {
			return fromMethodCall(on(ValidationEx40Controller.class).start(null, null, null, null, null, null)).build();
		}
	}

	private boolean hasErrors(ValidationEx40Form form, BindingResult binding) {

		// 単項目チェック
		if (binding.hasErrors()) {
			return true;
		}

		// 項目間チェック

		// 整合性チェック

		return false;
	}

	private ValidationEx40Form createForm() {

		ValidationEx40Form form = new ValidationEx40Form();
		form.setList1(createSubFormList(3));

		List<List2Property> list2 = new ArrayList<>();
		list2.add(new List2Property(createSubFormList(3)));
		list2.add(new List2Property(createSubFormList(1)));
		list2.add(new List2Property(createSubFormList(2)));
		form.setList2(list2);

		form.setMap1(createSubFormMap(3));

		Map<String, Map2Property> map2 = new LinkedHashMap<>();
		map2.put("key0", new Map2Property(createSubFormMap(3)));
		map2.put("key1", new Map2Property(createSubFormMap(1)));
		map2.put("key2", new Map2Property(createSubFormMap(2)));
		form.setMap2(map2);

		return form;
	}

	private List<ValidationEx40SubForm> createSubFormList(int count) {
		List<ValidationEx40SubForm> list = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			list.add(new ValidationEx40SubForm());
		}
		return list;
	}

	private Map<String, ValidationEx40SubForm> createSubFormMap(int count) {
		Map<String, ValidationEx40SubForm> map = new LinkedHashMap<>();
		for (int i = 0; i < count; i++) {
			map.put("key" + i, new ValidationEx40SubForm());
		}
		return map;
	}

}
