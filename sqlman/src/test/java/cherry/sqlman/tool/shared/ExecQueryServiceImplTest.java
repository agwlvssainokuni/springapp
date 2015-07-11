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

package cherry.sqlman.tool.shared;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.etl.Column;
import cherry.foundation.etl.Consumer;
import cherry.goods.paginate.PageSet;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class ExecQueryServiceImplTest {

	@Autowired
	private ExecQueryService execQueryService;

	@Test
	public void testQueryWithPagination() throws IOException {

		QueryBuilder query = new QueryBuilder();
		query.setSelect("table_schema, table_name");
		query.setFrom("information_schema.tables");
		query.setWhere("table_schema=:schema AND table_name LIKE 'SQL%'");
		query.setOrderBy("table_name ASC");
		Map<String, ?> paramMap = singletonMap("schema", "PUBLIC");

		ResultSet resultSet = new ResultSet();
		PageSet pageSet1 = execQueryService.query("system", query, paramMap, 1L, 2L, resultSet);

		assertEquals(4L, pageSet1.getTotalCount());
		assertEquals(1L, pageSet1.getCurrent().getNo());
		assertEquals(2L, pageSet1.getCurrent().getCount());
		assertEquals(2, resultSet.getHeader().length);
		int i = 0;
		for (String h : asList("TABLE_SCHEMA", "TABLE_NAME")) {
			assertEquals(h, resultSet.getHeader()[i].getLabel());
			i += 1;
		}
		assertEquals(2, resultSet.getRecordSet().size());
		i = 0;
		for (String n : asList("SQL_METADATA", "SQL_STATEMENT")) {
			Object[] r = resultSet.getRecordSet().get(i);
			assertEquals("PUBLIC", r[0]);
			assertEquals(n, r[1]);
			i += 1;
		}
	}

	@Test(expected = IllegalStateException.class)
	public void testQueryWithPagination_IOEXCEPTION() throws IOException {

		QueryBuilder query = new QueryBuilder();
		query.setSelect("table_schema, table_name");
		query.setFrom("information_schema.tables");
		query.setWhere("table_schema=:schema AND table_name LIKE 'SQL%'");
		query.setOrderBy("table_name ASC");
		Map<String, ?> paramMap = singletonMap("schema", "PUBLIC");

		Consumer consumer = mock(Consumer.class);
		doThrow(new IOException()).when(consumer).begin((Column[]) any());
		execQueryService.query("system", query, paramMap, 1L, 2L, consumer);
	}

	@Test(expected = IllegalStateException.class)
	public void testQueryWithPagination_COUNT_UNMATCH() throws IOException {

		QueryBuilder query = new QueryBuilder() {
			@Override
			public String buildCount() {
				return "SELECT 3 FROM dual";
			}
		};
		query.setSelect("table_schema, table_name");
		query.setFrom("information_schema.tables");
		query.setWhere("table_schema=:schema AND table_name LIKE 'SQL%'");
		query.setOrderBy("table_name ASC");
		Map<String, ?> paramMap = singletonMap("schema", "PUBLIC");

		ResultSet resultSet = new ResultSet();
		execQueryService.query("system", query, paramMap, 1L, 2L, resultSet);
	}

	@Test
	public void testQueryWithoutPagination() throws IOException {

		QueryBuilder query = new QueryBuilder();
		query.setSelect("table_schema, table_name");
		query.setFrom("information_schema.tables");
		query.setWhere("table_schema=:schema AND table_name LIKE 'SQL%'");
		query.setOrderBy("table_name ASC");
		Map<String, ?> paramMap = singletonMap("schema", "PUBLIC");

		ResultSet resultSet = new ResultSet();
		PageSet pageSet = execQueryService.query("system", query, paramMap, resultSet);

		assertEquals(4L, pageSet.getTotalCount());
		assertEquals(0L, pageSet.getCurrent().getNo());
		assertEquals(4L, pageSet.getCurrent().getCount());
		assertEquals(2, resultSet.getHeader().length);
		int i = 0;
		for (String h : asList("TABLE_SCHEMA", "TABLE_NAME")) {
			assertEquals(h, resultSet.getHeader()[i].getLabel());
			i += 1;
		}
		assertEquals(4, resultSet.getRecordSet().size());
		i = 0;
		for (String n : asList("SQL_CLAUSE", "SQL_LOAD", "SQL_METADATA", "SQL_STATEMENT")) {
			Object[] r = resultSet.getRecordSet().get(i);
			assertEquals("PUBLIC", r[0]);
			assertEquals(n, r[1]);
			i += 1;
		}
	}

	@Test
	public void testQueryWithoutPagination_NOITEMS() throws IOException {

		QueryBuilder query = new QueryBuilder();
		query.setSelect("table_schema, table_name");
		query.setFrom("information_schema.tables");
		query.setWhere("table_schema=:schema AND table_name LIKE 'NOMATCH%'");
		query.setOrderBy("table_name ASC");
		Map<String, ?> paramMap = singletonMap("schema", "PUBLIC");

		ResultSet resultSet = new ResultSet();
		PageSet pageSet = execQueryService.query("system", query, paramMap, resultSet);

		assertEquals(0L, pageSet.getTotalCount());
		assertEquals(0L, pageSet.getCurrent().getNo());
		assertEquals(0L, pageSet.getCurrent().getCount());
		assertEquals(2, resultSet.getHeader().length);
		int i = 0;
		for (String h : asList("TABLE_SCHEMA", "TABLE_NAME")) {
			assertEquals(h, resultSet.getHeader()[i].getLabel());
			i += 1;
		}
		assertEquals(0, resultSet.getRecordSet().size());
	}

	@Test(expected = IllegalStateException.class)
	public void testQueryWithoutPagination_IOEXCEPTION() throws IOException {

		QueryBuilder query = new QueryBuilder();
		query.setSelect("table_schema, table_name");
		query.setFrom("information_schema.tables");
		query.setWhere("table_schema=:schema AND table_name LIKE 'SQL%'");
		query.setOrderBy("table_name ASC");
		Map<String, ?> paramMap = singletonMap("schema", "PUBLIC");

		Consumer consumer = mock(Consumer.class);
		doThrow(new IOException()).when(consumer).begin((Column[]) any());
		execQueryService.query("system", query, paramMap, consumer);
	}

}
