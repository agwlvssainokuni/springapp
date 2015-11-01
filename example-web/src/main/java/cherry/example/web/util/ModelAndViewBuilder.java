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

package cherry.example.web.util;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;

public class ModelAndViewBuilder {

	private final ModelAndView mav;

	private ModelAndViewBuilder(ModelAndView mav) {
		this.mav = mav;
	}

	public ModelAndView build() {
		return mav;
	}

	public static ModelAndViewBuilder withoutView() {
		return new ModelAndViewBuilder(new ModelAndView());
	}

	public static ModelAndViewBuilder withViewname(String viewName) {
		return new ModelAndViewBuilder(new ModelAndView(viewName));
	}

	public static ModelAndViewBuilder redirect(UriComponents uc) {
		return redirect(uc.toUriString());
	}

	public static ModelAndViewBuilder redirect(String uri) {
		return new ModelAndViewBuilder(new ModelAndView(new RedirectView(uri, true)));
	}

	public ModelAndViewBuilder addObject(Object attributeValue) {
		mav.addObject(attributeValue);
		return this;
	}

	public ModelAndViewBuilder addObject(String attributeName, Object attributeValue) {
		mav.addObject(attributeName, attributeValue);
		return this;
	}

	public ModelAndViewBuilder addAllObject(Map<String, ?> modelMap) {
		mav.addAllObjects(modelMap);
		return this;
	}

}
