/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.etl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class ExtractorImplTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private NamedParameterJdbcOperations operations;

	@Test
	public void testWithHeaderWithRecord() throws IOException {
		StringWriter writer = new StringWriter();
		CsvConsumer consumer = new CsvConsumer(writer, true);

		createData(3);
		ExtractorImpl impl = new ExtractorImpl();
		Map<String, ?> paramMap = new HashMap<>();
		long count = impl.extract(dataSource, "SELECT name, address FROM etl_extr_ldr ORDER BY id", paramMap, consumer,
				new NoneLimiter());

		assertEquals(3L, count);
		assertEquals("\"NAME\",\"ADDRESS\"\r\n" + "\"name_0\",\"address_0\"\r\n" + "\"name_1\",\"address_1\"\r\n"
				+ "\"name_2\",\"address_2\"\r\n", writer.toString());
	}

	@Test
	public void testNoHeaderWithRecord() throws IOException {
		StringWriter writer = new StringWriter();
		CsvConsumer consumer = new CsvConsumer(writer, false);

		createData(3);
		ExtractorImpl impl = new ExtractorImpl();
		Map<String, ?> paramMap = new HashMap<>();
		long count = impl.extract(dataSource, "SELECT name, address FROM etl_extr_ldr ORDER BY id", paramMap, consumer,
				new NoneLimiter());

		assertEquals(3L, count);
		assertEquals("\"name_0\",\"address_0\"\r\n" + "\"name_1\",\"address_1\"\r\n" + "\"name_2\",\"address_2\"\r\n",
				writer.toString());
	}

	@Test
	public void testWithHeaderNoRecord() throws IOException {
		StringWriter writer = new StringWriter();
		CsvConsumer consumer = new CsvConsumer(writer, true);

		ExtractorImpl impl = new ExtractorImpl();
		Map<String, ?> paramMap = new HashMap<>();
		long count = impl.extract(dataSource, "SELECT name, address FROM etl_extr_ldr ORDER BY id", paramMap, consumer,
				new NoneLimiter());

		assertEquals(0L, count);
		assertEquals("\"NAME\",\"ADDRESS\"\r\n", writer.toString());
	}

	@Test(expected = IOException.class)
	public void testIOException() throws IOException {
		Consumer consumer = mock(Consumer.class);

		doThrow(new IOException()).when(consumer).begin((Column[]) any());
		ExtractorImpl impl = new ExtractorImpl();
		Map<String, ?> paramMap = new HashMap<>();
		impl.extract(dataSource, "SELECT name, address FROM etl_extr_ldr ORDER BY id", paramMap, consumer,
				new NoneLimiter());
	}

	private void createData(int num) {
		for (int i = 0; i < num; i++) {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("name", "name_" + i);
			paramMap.put("address", "address_" + i);
			operations.update("INSERT INTO etl_extr_ldr (name, address) VALUES (:name, :address)", paramMap);
		}
	}

}
