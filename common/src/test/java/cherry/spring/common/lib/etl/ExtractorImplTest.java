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

package cherry.spring.common.lib.etl;

import static cherry.spring.common.lib.etl.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ExtractorImplTest {

	@Test
	public void testWithHeaderWithRecord() throws IOException {
		StringWriter writer = new StringWriter();
		CsvConsumer consumer = new CsvConsumer(writer, true);
		try {
			createData(3);
			ExtractorImpl impl = new ExtractorImpl();
			Map<String, ?> paramMap = new HashMap<>();
			int count = impl.extract(getBean(DataSource.class),
					"SELECT name, address FROM member ORDER BY id", paramMap,
					consumer, new NoneLimiter());

			assertEquals(3, count);

		} finally {
			cleanupData();
		}

		assertEquals("\"NAME\",\"ADDRESS\"\r\n"
				+ "\"name_0\",\"address_0\"\r\n"
				+ "\"name_1\",\"address_1\"\r\n"
				+ "\"name_2\",\"address_2\"\r\n", writer.toString());
	}

	@Test
	public void testNoHeaderWithRecord() throws IOException {
		StringWriter writer = new StringWriter();
		CsvConsumer consumer = new CsvConsumer(writer, false);
		try {
			createData(3);
			ExtractorImpl impl = new ExtractorImpl();
			Map<String, ?> paramMap = new HashMap<>();
			int count = impl.extract(getBean(DataSource.class),
					"SELECT name, address FROM member ORDER BY id", paramMap,
					consumer, new NoneLimiter());

			assertEquals(3, count);

		} finally {
			cleanupData();
		}

		assertEquals("\"name_0\",\"address_0\"\r\n"
				+ "\"name_1\",\"address_1\"\r\n"
				+ "\"name_2\",\"address_2\"\r\n", writer.toString());
	}

	@Test
	public void testWithHeaderNoRecord() throws IOException {
		StringWriter writer = new StringWriter();
		CsvConsumer consumer = new CsvConsumer(writer, true);
		try {
			ExtractorImpl impl = new ExtractorImpl();
			Map<String, ?> paramMap = new HashMap<>();
			int count = impl.extract(getBean(DataSource.class),
					"SELECT name, address FROM member ORDER BY id", paramMap,
					consumer, new NoneLimiter());

			assertEquals(0, count);

		} finally {
			cleanupData();
		}

		assertEquals("\"NAME\",\"ADDRESS\"\r\n", writer.toString());
	}

	@Test
	public void testIOException() {
		Consumer consumer = mock(Consumer.class);
		IOException exception = new IOException();
		try {
			doThrow(exception).when(consumer).begin((Column[]) any());
			ExtractorImpl impl = new ExtractorImpl();
			Map<String, ?> paramMap = new HashMap<>();
			impl.extract(getBean(DataSource.class),
					"SELECT name, address FROM member ORDER BY id", paramMap,
					consumer, new NoneLimiter());
			fail("Exception must be thrown");
		} catch (IOException ex) {
			assertEquals(exception, ex);
		}
	}

	private void createData(int num) {
		NamedParameterJdbcTemplate template = getTemplate();
		for (int i = 0; i < num; i++) {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("name", "name_" + i);
			paramMap.put("address", "address_" + i);
			template.update(
					"INSERT INTO member (name, address) VALUES (:name, :address)",
					paramMap);
		}
	}

	private void cleanupData() {
		NamedParameterJdbcTemplate template = getTemplate();
		Map<String, String> paramMap = new HashMap<>();
		template.update("DELETE FROM member", paramMap);
	}

	private NamedParameterJdbcTemplate getTemplate() {
		DataSource dataSource = getBean(DataSource.class);
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
