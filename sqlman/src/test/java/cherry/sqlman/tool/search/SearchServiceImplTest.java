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

import static com.ibm.icu.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.goods.paginate.PagedList;
import cherry.sqlman.Published;
import cherry.sqlman.SqlType;
import cherry.sqlman.tool.clause.ClauseService;
import cherry.sqlman.tool.clause.SqlClauseForm;
import cherry.sqlman.tool.load.LoadService;
import cherry.sqlman.tool.load.SqlLoadForm;
import cherry.sqlman.tool.metadata.MetadataService;
import cherry.sqlman.tool.metadata.SqlMetadataForm;
import cherry.sqlman.tool.statement.SqlStatementForm;
import cherry.sqlman.tool.statement.StatementService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class SearchServiceImplTest {

	@Autowired
	private SearchService searchService;

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

	private String owner = "owner";
	private String other = "other";

	@Before
	public void before() {
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
	public void testSearch_NOCOND() {

		SqlSearchForm form = new SqlSearchForm();
		form.setPublished(new ArrayList<Published>());
		form.setSqlType(new ArrayList<SqlType>());
		form.setPageNo(0L);
		form.setPageSz(10L);

		PagedList<Map<String, ?>> result = searchService.search(form, owner);
		assertEquals(9, result.getPageSet().getTotalCount());
		for (Map<String, ?> record : result.getList()) {
			assertThat((String) record.get("sqlType"),
					either(is(SqlType.CLAUSE.code())).or(is(SqlType.STATEMENT.code())).or(is(SqlType.LOAD.code())));
			if (record.get("publishedFlg").equals(Published.PRIVATE.code())) {
				assertEquals(owner, record.get("ownedBy"));
			} else {
				assertThat((String) record.get("ownedBy"), either(is(owner)).or(is(other)));
			}
		}
	}

	@Test
	public void testSearch_FULLCOND() {

		LocalDateTime now = bizDateTime.now();
		LocalDateTime fromDtm = now.minusDays(2);
		LocalDateTime toDtm = now.plusDays(2);

		SqlSearchForm form = new SqlSearchForm();
		form.setName("name");
		form.setPublished(asList(Published.PRIVATE, Published.PUBLIC));
		form.setSqlType(asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setRegisteredFromDt(fromDtm.toLocalDate());
		form.setRegisteredFromTm(fromDtm.toLocalTime());
		form.setRegisteredToDt(toDtm.toLocalDate());
		form.setRegisteredToTm(toDtm.toLocalTime());
		form.setPageNo(0L);
		form.setPageSz(10L);

		PagedList<Map<String, ?>> result = searchService.search(form, owner);
		assertEquals(9, result.getPageSet().getTotalCount());
		assertEquals(9, result.getList().size());
		for (Map<String, ?> record : result.getList()) {
			assertThat((String) record.get("sqlType"),
					either(is(SqlType.CLAUSE.code())).or(is(SqlType.STATEMENT.code())).or(is(SqlType.LOAD.code())));
			if (record.get("publishedFlg").equals(Published.PRIVATE.code())) {
				assertEquals(owner, record.get("ownedBy"));
			} else {
				assertThat((String) record.get("ownedBy"), either(is(owner)).or(is(other)));
			}
		}
	}

	@Test
	public void testSearch_NAME() {

		LocalDateTime now = bizDateTime.now();
		LocalDateTime fromDtm = now.minusDays(2);
		LocalDateTime toDtm = now.plusDays(2);

		SqlSearchForm form = new SqlSearchForm();
		form.setName("name01");
		form.setPublished(asList(Published.PRIVATE, Published.PUBLIC));
		form.setSqlType(asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setRegisteredFromDt(fromDtm.toLocalDate());
		form.setRegisteredFromTm(fromDtm.toLocalTime());
		form.setRegisteredToDt(toDtm.toLocalDate());
		form.setRegisteredToTm(toDtm.toLocalTime());
		form.setPageNo(0L);
		form.setPageSz(10L);

		PagedList<Map<String, ?>> result = searchService.search(form, owner);
		assertEquals(1, result.getPageSet().getTotalCount());
		assertEquals(1, result.getList().size());
		assertEquals(SqlType.CLAUSE.code(), result.getList().get(0).get("sqlType"));
		assertEquals(Published.PRIVATE.code(), result.getList().get(0).get("publishedFlg"));
		assertEquals(owner, result.getList().get(0).get("ownedBy"));
	}

	@Test
	public void testSearch_PUBLISHED() {

		LocalDateTime now = bizDateTime.now();
		LocalDateTime fromDtm = now.minusDays(2);
		LocalDateTime toDtm = now.plusDays(2);

		SqlSearchForm form = new SqlSearchForm();
		form.setName("name");
		form.setPublished(asList(Published.PRIVATE));
		form.setSqlType(asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setRegisteredFromDt(fromDtm.toLocalDate());
		form.setRegisteredFromTm(fromDtm.toLocalTime());
		form.setRegisteredToDt(toDtm.toLocalDate());
		form.setRegisteredToTm(toDtm.toLocalTime());
		form.setPageNo(0L);
		form.setPageSz(10L);

		PagedList<Map<String, ?>> result = searchService.search(form, owner);
		assertEquals(3, result.getPageSet().getTotalCount());
		assertEquals(3, result.getList().size());
		for (Map<String, ?> record : result.getList()) {
			assertThat((String) record.get("sqlType"),
					either(is(SqlType.CLAUSE.code())).or(is(SqlType.STATEMENT.code())).or(is(SqlType.LOAD.code())));
			assertEquals(Published.PRIVATE.code(), record.get("publishedFlg"));
			assertEquals(owner, record.get("ownedBy"));
		}
	}

	@Test
	public void testSearch_SQLTYPE() {

		LocalDateTime now = bizDateTime.now();
		LocalDateTime fromDtm = now.minusDays(2);
		LocalDateTime toDtm = now.plusDays(2);

		SqlSearchForm form = new SqlSearchForm();
		form.setName("name");
		form.setPublished(asList(Published.PRIVATE, Published.PUBLIC));
		form.setSqlType(asList(SqlType.CLAUSE));
		form.setRegisteredFromDt(fromDtm.toLocalDate());
		form.setRegisteredFromTm(fromDtm.toLocalTime());
		form.setRegisteredToDt(toDtm.toLocalDate());
		form.setRegisteredToTm(toDtm.toLocalTime());
		form.setPageNo(0L);
		form.setPageSz(10L);

		PagedList<Map<String, ?>> result = searchService.search(form, owner);
		assertEquals(3, result.getPageSet().getTotalCount());
		assertEquals(3, result.getList().size());
		for (Map<String, ?> record : result.getList()) {
			assertEquals(SqlType.CLAUSE.code(), record.get("sqlType"));
			if (record.get("publishedFlg").equals(Published.PRIVATE.code())) {
				assertEquals(owner, record.get("ownedBy"));
			} else {
				assertThat((String) record.get("ownedBy"), either(is(owner)).or(is(other)));
			}
		}
	}

	@Test
	public void testSearch_FROMDTM() {

		LocalDateTime now = bizDateTime.now();
		LocalDateTime fromDtm = now.minusDays(2);
		LocalDateTime toDtm = now.minusDays(1);

		SqlSearchForm form = new SqlSearchForm();
		form.setName("name");
		form.setPublished(asList(Published.PRIVATE, Published.PUBLIC));
		form.setSqlType(asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setRegisteredFromDt(fromDtm.toLocalDate());
		form.setRegisteredFromTm(fromDtm.toLocalTime());
		form.setRegisteredToDt(toDtm.toLocalDate());
		form.setRegisteredToTm(toDtm.toLocalTime());
		form.setPageNo(0L);
		form.setPageSz(10L);

		PagedList<Map<String, ?>> result = searchService.search(form, owner);
		assertEquals(0, result.getPageSet().getTotalCount());
		assertEquals(0, result.getList().size());
	}

	@Test
	public void testSearch_TODTM() {

		LocalDateTime now = bizDateTime.now();
		LocalDateTime fromDtm = now.plusDays(1);
		LocalDateTime toDtm = now.plusDays(1);

		SqlSearchForm form = new SqlSearchForm();
		form.setName("name");
		form.setPublished(asList(Published.PRIVATE, Published.PUBLIC));
		form.setSqlType(asList(SqlType.CLAUSE, SqlType.STATEMENT, SqlType.LOAD));
		form.setRegisteredFromDt(fromDtm.toLocalDate());
		form.setRegisteredFromTm(fromDtm.toLocalTime());
		form.setRegisteredToDt(toDtm.toLocalDate());
		form.setRegisteredToTm(toDtm.toLocalTime());
		form.setPageNo(0L);
		form.setPageSz(10L);

		PagedList<Map<String, ?>> result = searchService.search(form, owner);
		assertEquals(0, result.getPageSet().getTotalCount());
		assertEquals(0, result.getList().size());
	}

}
