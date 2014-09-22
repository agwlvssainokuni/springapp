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

package cherry.spring.admin.app.controller.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class LoginControllerTest {

	@Autowired
	private LoginController loginController;

	@Test
	public void index000() {
		RedirectAttributes redirAttr = mock(RedirectAttributes.class);
		ModelAndView mav = loginController.index(null, null, null, redirAttr);
		assertNotNull(mav);
		assertEquals(LoginControllerImpl.VIEW_PATH, mav.getViewName());
		assertNull(mav.getView());
		assertTrue(mav.getModelMap().isEmpty());
		assertNotNull(loginController.getForm());
		verify(redirAttr).getFlashAttributes();
	}

	@Test
	public void loginFailed000() {
		RedirectAttributes redirAttr = mock(RedirectAttributes.class);
		ModelAndView mav = loginController.loginFailed(null, null, null,
				redirAttr);
		assertNotNull(mav);
		assertNull(mav.getViewName());
		assertTrue(mav.getView() instanceof RedirectView);
		assertEquals(LoginController.URI_PATH,
				((RedirectView) mav.getView()).getUrl());
		verify(redirAttr).addFlashAttribute("loginFailed", true);
	}

	@Test
	public void loggedOut000() {
		RedirectAttributes redirAttr = mock(RedirectAttributes.class);
		ModelAndView mav = loginController.loggedOut(null, null, null,
				redirAttr);
		assertNotNull(mav);
		assertNull(mav.getViewName());
		assertTrue(mav.getView() instanceof RedirectView);
		assertEquals(LoginController.URI_PATH,
				((RedirectView) mav.getView()).getUrl());
		verify(redirAttr).addFlashAttribute("loggedOut", true);
	}

}
