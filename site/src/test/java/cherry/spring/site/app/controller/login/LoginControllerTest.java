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

package cherry.spring.site.app.controller.login;

import static cherry.spring.site.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class LoginControllerTest {

	private LoginController loginController = getBean(LoginController.class);

	@Test
	public void index000() {
		ModelAndView mav = loginController.index(null, null, null);
		assertNotNull(mav);
		assertEquals(LoginControllerImpl.VIEW_PATH, mav.getViewName());
		assertNull(mav.getView());
		assertFalse(mav.getModelMap().isEmpty());
		Object form = mav.getModelMap().get("loginForm");
		assertNotNull(form);
		assertTrue(form instanceof LoginForm);
	}

}
