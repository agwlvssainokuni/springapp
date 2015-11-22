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

package cherry.sqlman.tool.search;

import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
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

import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.paginate.PagedList;
import cherry.goods.util.LocalDateTimeUtil;
import cherry.sqlman.Published;
import cherry.sqlman.SqlType;
import cherry.sqlman.tool.clause.ClauseService;
import cherry.sqlman.tool.clause.SqlClauseForm;
import cherry.sqlman.tool.load.LoadService;
import cherry.sqlman.tool.load.SqlLoadForm;
import cherry.sqlman.tool.metadata.MetadataService;
import cherry.sqlman.tool.metadata.SqlMetadataForm;
import cherry.sqlman.tool.search.SqlSearchFormBase.Prop;
import cherry.sqlman.tool.statement.SqlStatementForm;
import cherry.sqlman.tool.statement.StatementService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class SqlSearchControllerImplTest {

	@Autowired
	private WebApplicationContext appCtx;

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private ClauseService clauseService;

	@Autowired
	private StatementService statementService;

	@Autowired
	private LoadService loadService;

	@Autowired
	private BizDateTime bizDateTime;

	private MockMvc mockMvc;

	private String owner = "owner";
	private String other = "other";

	@Before
	public void before() {

		mockMvc = MockMvcBuilders.webAppContextSetup(appCtx).build();

		int num = 1;
		for (Published p : asList(Published.PRIVATE, Published.PUBLIC)) {
			for (String o : asList(owner, other)) {

				SqlClauseForm form1 = new SqlClauseForm();
				form1.setDatabaseName("db" + num);
				form1.setSelect("select" + num);
				form1.setFrom("from" + num);
				int id1 = clauseService.create(form1, o);
				SqlMetadataForm form11 = metadataService.findById(id1, o);
				form11.setName(format("name{0,number,00}", num));
				form11.setPublishedFlg(p.isPublished());
				metadataService.update(id1, form11);
				num += 1;

				SqlStatementForm form2 = new SqlStatementForm();
				form2.setDatabaseName("db" + num);
				form2.setSql("sql" + num);
				int id2 = statementService.create(form2, o);
				SqlMetadataForm form22 = metadataService.findById(id2, o);
				form22.setName(format("name{0,number,00}", num));
				form22.setPublishedFlg(p.isPublished());
				metadataService.update(id2, form22);
				num += 1;

				SqlLoadForm form3 = new SqlLoadForm();
				form3.setDatabaseName("db" + 1);
				form3.setSql("sql" + 1);
				int id3 = loadService.create(form3, o);
				SqlMetadataForm form33 = metadataService.findById(id3, o);
				form33.setName(format("name{0,number,00}", num));
				form33.setPublishedFlg(p.isPublished());
				metadataService.update(id3, form33);
				num += 1;
			}
		}
	}

	@Test
	public void testInit() throws Exception {
		MvcResult result = mockMvc
				.perform(
						get("/tool/search").principal(createPrincipal()).sessionAttr("sqlSearchForm",
								new SqlSearchForm())).andExpect(status().isFound()).andExpect(model().size(1))
				.andExpect(model().attributeExists("sqlSearchForm")).andExpect(view().name((String) null))
				.andExpect(redirectedUrl("http://localhost/tool/search/start")).andReturn();
		assertNull(result.getRequest().getSession().getAttribute("sqlSearchForm"));
	}

	@Test
	public void testStart() throws Exception {

		LocalDate today = bizDateTime.today();
		LocalDateTime fromDtm = LocalDateTimeUtil.rangeFrom(today.minusDays(7));
		LocalDateTime toDtm = LocalDateTimeUtil.rangeTo(today).minusSeconds(1);
		SqlSearchForm form = new SqlSearchForm();
		form.setPublished(asList(Published.PUBLIC, Published.PRIVATE));
		form.setSqlType(asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setRegisteredFromDt(fromDtm.toLocalDate());
		form.setRegisteredFromTm(fromDtm.toLocalTime());
		form.setRegisteredToDt(toDtm.toLocalDate());
		form.setRegisteredToTm(toDtm.toLocalTime());

		mockMvc.perform(get("/tool/search/start").principal(createPrincipal())).andExpect(status().isOk())
				.andExpect(model().size(1)).andExpect(model().attribute("sqlSearchForm", form))
				.andExpect(view().name("tool/search/start"));
	}

	@Test
	public void testExecute_1() throws Exception {

		SqlSearchForm form = new SqlSearchForm();
		form.setName("");
		form.setSqlType(new ArrayList<SqlType>());
		form.setPublished(new ArrayList<Published>());
		form.setPageNo(0L);
		form.setPageSz(20L);

		MvcResult result = mockMvc
				.perform(
						post("/tool/search/execute").principal(createPrincipal()).param(Prop.Name.getName(), "")
								.param(Prop.RegisteredFromDt.getName(), "").param(Prop.RegisteredFromTm.getName(), "")
								.param(Prop.RegisteredToDt.getName(), "").param(Prop.RegisteredToTm.getName(), "")
								.param(Prop.SqlType.getName(), "").param(Prop.Published.getName(), "")
								.param(Prop.PageNo.getName(), "0").param(Prop.PageSz.getName(), "0"))
				.andExpect(status().isOk()).andExpect(model().size(2))
				.andExpect(model().attribute("sqlSearchForm", form)).andExpect(view().name("tool/search/start"))
				.andReturn();

		assertTrue(result.getRequest().getAttribute("pagedList") instanceof PagedList);
		@SuppressWarnings("unchecked")
		PagedList<Map<String, ?>> pagedList = (PagedList<Map<String, ?>>) result.getRequest().getAttribute("pagedList");
		assertEquals(9, pagedList.getPageSet().getTotalCount());
		assertEquals(0, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(9, pagedList.getPageSet().getCurrent().getCount());
	}

	@Test
	public void testExecute_2() throws Exception {

		SqlSearchForm form = new SqlSearchForm();
		form.setName("");
		form.setSqlType(new ArrayList<SqlType>());
		form.setPublished(asList(Published.PUBLIC));
		form.setPageNo(1L);
		form.setPageSz(4L);

		MvcResult result = mockMvc
				.perform(
						post("/tool/search/execute").principal(createPrincipal()).param(Prop.Name.getName(), "")
								.param(Prop.RegisteredFromDt.getName(), "").param(Prop.RegisteredFromTm.getName(), "")
								.param(Prop.RegisteredToDt.getName(), "").param(Prop.RegisteredToTm.getName(), "")
								.param(Prop.SqlType.getName(), "").param(Prop.Published.getName(), "PUBLIC")
								.param(Prop.PageNo.getName(), "1").param(Prop.PageSz.getName(), "4"))
				.andExpect(status().isOk()).andExpect(model().size(2))
				.andExpect(model().attribute("sqlSearchForm", form)).andExpect(view().name("tool/search/start"))
				.andReturn();

		assertTrue(result.getRequest().getAttribute("pagedList") instanceof PagedList);
		@SuppressWarnings("unchecked")
		PagedList<Map<String, ?>> pagedList = (PagedList<Map<String, ?>>) result.getRequest().getAttribute("pagedList");
		assertEquals(6, pagedList.getPageSet().getTotalCount());
		assertEquals(1, pagedList.getPageSet().getCurrent().getNo());
		assertEquals(2, pagedList.getPageSet().getCurrent().getCount());
	}

	@Test
	public void testExecute_3_VALIDATIONERROR() throws Exception {

		SqlSearchForm form = new SqlSearchForm();
		form.setName("123456789012345678901234567890123456789012345678901");
		form.setSqlType(new ArrayList<SqlType>());
		form.setPublished(new ArrayList<Published>());

		mockMvc.perform(
				post("/tool/search/execute").principal(createPrincipal())
						.param(Prop.Name.getName(), "123456789012345678901234567890123456789012345678901")
						.param(Prop.RegisteredFromDt.getName(), "").param(Prop.RegisteredFromTm.getName(), "")
						.param(Prop.RegisteredToDt.getName(), "").param(Prop.RegisteredToTm.getName(), "")
						.param(Prop.SqlType.getName(), "").param(Prop.Published.getName(), "")
						.param(Prop.PageNo.getName(), "0").param(Prop.PageSz.getName(), "0"))
				.andExpect(status().isOk()).andExpect(model().size(1))
				.andExpect(model().attribute("sqlSearchForm", form)).andExpect(view().name("tool/search/start"));
	}

	private Principal createPrincipal() {
		return new UsernamePasswordAuthenticationToken(owner, "N/A");
	}

}
