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

package cherry.sqlman.tool.clause;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import cherry.goods.paginate.PageSet;
import cherry.sqlman.PathDef;
import cherry.sqlman.tool.clause.SqlClauseFormBase.Prop;
import cherry.sqlman.tool.shared.ResultSet;
import cherry.sqlman.tool.statement.SqlStatementForm;
import cherry.sqlman.tool.statement.StatementService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class SqlClauseControllerImplTest {

	@Autowired
	private WebApplicationContext appCtx;

	@Autowired
	private ClauseService clauseService;

	@Autowired
	private StatementService statementService;

	private MockMvc mockMvc;

	private String owner = "owner";

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(appCtx).build();
	}

	@Test
	public void testInit() throws Exception {
		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db1");
		mockMvc.perform(get("/tool/clause").principal(createPrincipal())).andExpect(status().isOk())
				.andExpect(model().size(1)).andExpect(model().attribute("sqlClauseForm", form))
				.andExpect(view().name("tool/clause/page")).andReturn();
	}

	@Test
	public void testInitWithExistingRef() throws Exception {

		SqlClauseForm form0 = new SqlClauseForm();
		form0.setDatabaseName("db2");
		form0.setSelect("*");
		form0.setFrom("dual");
		int id = clauseService.create(form0, owner);

		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db2");
		form.setSelect("*");
		form.setFrom("dual");
		form.setLockVersion(1);
		mockMvc.perform(get("/tool/clause").principal(createPrincipal()).param("ref", String.valueOf(id)))
				.andExpect(status().isOk()).andExpect(model().size(1))
				.andExpect(model().attribute("sqlClauseForm", form)).andExpect(view().name("tool/clause/page"))
				.andReturn();
	}

	@Test
	public void testInitWithMissingRef() throws Exception {
		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db1");
		mockMvc.perform(get("/tool/clause").principal(createPrincipal()).param("ref", String.valueOf(0)))
				.andExpect(status().isOk()).andExpect(model().size(1))
				.andExpect(model().attribute("sqlClauseForm", form)).andExpect(view().name("tool/clause/page"))
				.andReturn();
	}

	@Test
	public void testInitWithInvalidRef() throws Exception {

		SqlStatementForm form0 = new SqlStatementForm();
		form0.setDatabaseName("db2");
		form0.setSql("SELECT * FROM dual");
		int id = statementService.create(form0, owner);

		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db1");
		mockMvc.perform(get("/tool/clause").principal(createPrincipal()).param("ref", String.valueOf(id)))
				.andExpect(status().isOk()).andExpect(model().size(1))
				.andExpect(model().attribute("sqlClauseForm", form)).andExpect(view().name("tool/clause/page"))
				.andReturn();
	}

	@Test
	public void testExecute() throws Exception {

		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db2");
		form.setSelect("*");
		form.setFrom("dual");

		MvcResult result = mockMvc
				.perform(
						post("/tool/clause/execute").principal(createPrincipal())
								.param(Prop.DatabaseName.getName(), "db2").param(Prop.Select.getName(), "*")
								.param(Prop.From.getName(), "dual")).andExpect(status().isOk())
				.andExpect(model().size(3)).andExpect(model().attribute("sqlClauseForm", form))
				.andExpect(model().attributeExists("pageSet")).andExpect(model().attributeExists("resultSet"))
				.andExpect(view().name("tool/clause/page")).andReturn();
		PageSet pageSet = (PageSet) result.getRequest().getAttribute("pageSet");
		ResultSet resultSet = (ResultSet) result.getRequest().getAttribute("resultSet");

		assertEquals(1, pageSet.getTotalCount());
		assertEquals(1, resultSet.getHeader().length);
		assertEquals("X", resultSet.getHeader()[0].getLabel());
		assertEquals(1, resultSet.getRecordSet().size());
		assertEquals(Long.valueOf(1L), resultSet.getRecordSet().get(0)[0]);
	}

	@Test
	public void testExecuteWithPageSz() throws Exception {

		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db2");
		form.setSelect("*");
		form.setFrom("dual");
		form.setPageSz(10L);

		MvcResult result = mockMvc
				.perform(
						post("/tool/clause/execute").principal(createPrincipal())
								.param(Prop.DatabaseName.getName(), "db2").param(Prop.Select.getName(), "*")
								.param(Prop.From.getName(), "dual").param(Prop.PageSz.getName(), "10"))
				.andExpect(status().isOk()).andExpect(model().size(3))
				.andExpect(model().attribute("sqlClauseForm", form)).andExpect(model().attributeExists("pageSet"))
				.andExpect(model().attributeExists("resultSet")).andExpect(view().name("tool/clause/page")).andReturn();
		PageSet pageSet = (PageSet) result.getRequest().getAttribute("pageSet");
		ResultSet resultSet = (ResultSet) result.getRequest().getAttribute("resultSet");

		assertEquals(1, pageSet.getTotalCount());
		assertEquals(1, resultSet.getHeader().length);
		assertEquals("X", resultSet.getHeader()[0].getLabel());
		assertEquals(1, resultSet.getRecordSet().size());
		assertEquals(Long.valueOf(1L), resultSet.getRecordSet().get(0)[0]);
	}

	@Test
	public void testExecute_VALIDATIONERROR() throws Exception {
		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db2");
		form.setSelect("");
		form.setFrom("");
		mockMvc.perform(
				post("/tool/clause/execute").principal(createPrincipal()).param(Prop.DatabaseName.getName(), "db2")
						.param(Prop.Select.getName(), "").param(Prop.From.getName(), ""))
				.andExpect(status().isOk())
				.andExpect(model().size(1))
				.andExpect(model().attribute("sqlClauseForm", form))
				.andExpect(model().attributeHasFieldErrors("sqlClauseForm", Prop.Select.getName(), Prop.From.getName()))
				.andExpect(view().name("tool/clause/page"));
	}

	@Test
	public void testExecute_BADSQLGRAMMER() throws Exception {
		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db2");
		form.setSelect("y");
		form.setFrom("dual");
		mockMvc.perform(
				post("/tool/clause/execute").principal(createPrincipal()).param(Prop.DatabaseName.getName(), "db2")
						.param(Prop.Select.getName(), "y").param(Prop.From.getName(), "dual"))
				.andExpect(status().isOk()).andExpect(model().size(1))
				.andExpect(model().attribute("sqlClauseForm", form))
				.andExpect(model().attributeHasErrors("sqlClauseForm")).andExpect(view().name("tool/clause/page"));
	}

	@Test
	public void testDownload() throws Exception {

		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("db2");
		form.setSelect("*");
		form.setFrom("dual");

		MvcResult result = mockMvc
				.perform(
						post("/tool/clause/execute").principal(createPrincipal()).param(PathDef.METHOD_DOWNLOAD, "")
								.param(Prop.DatabaseName.getName(), "db2").param(Prop.Select.getName(), "*")
								.param(Prop.From.getName(), "dual")).andExpect(status().isOk()).andReturn();
		assertEquals("\"X\"\r\n\"1\"\r\n", result.getResponse().getContentAsString());
	}

	private Principal createPrincipal() {
		return new UsernamePasswordAuthenticationToken(owner, "N/A");
	}

}
