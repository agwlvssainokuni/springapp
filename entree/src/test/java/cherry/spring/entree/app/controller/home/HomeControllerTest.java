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

package cherry.spring.entree.app.controller.home;

import static cherry.spring.entree.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import cherry.spring.entree.app.controller.secure.home.HomeController;
import cherry.spring.entree.app.controller.secure.home.HomeControllerImpl;

public class HomeControllerTest {

	private HomeController homeController = getBean(HomeController.class);

	@Test
	public void index000() {
		ModelAndView mav = homeController.index(null, null, null, null);
		assertNotNull(mav);
		assertEquals(HomeControllerImpl.VIEW_PATH, mav.getViewName());
		assertNull(mav.getView());
		assertTrue(mav.getModelMap().isEmpty());
	}

}
