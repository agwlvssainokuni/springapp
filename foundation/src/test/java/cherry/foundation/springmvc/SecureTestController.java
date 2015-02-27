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

package cherry.foundation.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/secure/test")
@Controller
public class SecureTestController {

	@RequestMapping()
	public ModelAndView index() {
		return new ModelAndView("secure/test/index");
	}

	@RequestMapping(params = "redirect")
	public ModelAndView redirect() {
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/secure/text"));
		return mav;
	}

	@RequestMapping(params = "download")
	public ModelAndView download() {
		return null;
	}

	@RequestMapping(params = "nullview")
	public ModelAndView nullview() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(null);
		return mav;
	}

	@RequestMapping(params = "exception")
	public ModelAndView exception() {
		throw new IllegalStateException("SecureTestController exception");
	}

}
