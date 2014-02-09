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

package cherry.spring.admin.app.controller.pwdtool;

import static cherry.spring.admin.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

public class PwdtoolControllerTest {

	private PwdtoolController pwdtoolController = getBean(PwdtoolController.class);

	private PasswordEncoder passwordEncoder = getBean(PasswordEncoder.class);

	@Test
	public void index000() {
		ModelAndView mav = pwdtoolController.index(null, null, null, null);
		assertNotNull(mav);
		assertEquals(PwdtoolControllerImpl.VIEW_PATH, mav.getViewName());
		assertNull(mav.getView());
		assertTrue(mav.getModelMap().isEmpty());
	}

	@Test
	public void encode000() {
		final int length = 16;
		final String domain = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final int count = 10;
		for (int i = 0; i < count; i++) {
			String plain = RandomStringUtils.random(length, domain);
			String encoded = pwdtoolController.encode(plain);
			assertNotNull(encoded);
			assertTrue(passwordEncoder.matches(plain, encoded));
			assertFalse(passwordEncoder.matches(plain + "2", encoded));
		}
	}

}
