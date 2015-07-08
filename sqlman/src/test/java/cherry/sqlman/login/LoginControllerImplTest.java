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

package cherry.sqlman.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
public class LoginControllerImplTest {

	@Autowired
	private WebApplicationContext appCtx;

	private MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(appCtx).build();
	}

	@Test
	public void testStart() throws Exception {
		mockMvc.perform(get("/login/start").principal(createPrincipal())).andExpect(status().isOk())
				.andExpect(model().size(0)).andExpect(view().name("login/start"));
	}

	@Test
	public void testLoggedOut() throws Exception {
		mockMvc.perform(get("/login/start").param("loggedOut", "").principal(createPrincipal()))
				.andExpect(status().isFound()).andExpect(view().name((String) null))
				.andExpect(redirectedUrl("http://localhost/login/start"))
				.andExpect(flash().attribute("loggedOut", true));
	}

	@Test
	public void testLoginFailed() throws Exception {
		mockMvc.perform(get("/login/start").param("loginFailed", "").principal(createPrincipal()))
				.andExpect(status().isFound()).andExpect(view().name((String) null))
				.andExpect(redirectedUrl("http://localhost/login/start"))
				.andExpect(flash().attribute("loginFailed", true));
	}

	private Principal createPrincipal() {
		return new UsernamePasswordAuthenticationToken("user01", "N/A");
	}

}
