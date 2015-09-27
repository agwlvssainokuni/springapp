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

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class LoaderImplTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private NamedParameterJdbcOperations operations;

	@Test
	public void testWithHeaderWithRecord() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n" + "\"name_0\",\"address_0\"\r\n"
				+ "\"name_1\",\"address_1\"\r\n" + "\"name_2\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);

		LoaderImpl impl = new LoaderImpl();
		impl.setBatchCount(0);
		impl.setAllowedFailCount(0);
		LoadResult loadResult = impl.load(dataSource,
				"INSERT INTO etl_extr_ldr (name, address) VALUES (:name, :address)", provider, new NoneLimiter());

		assertEquals(3, loadResult.getTotalCount());
		assertEquals(3, loadResult.getSuccessCount());
		assertEquals(0, loadResult.getFailedCount());

		assertEquals("address_0", getAddressByName("name_0"));
		assertEquals("address_1", getAddressByName("name_1"));
		assertEquals("address_2", getAddressByName("name_2"));
	}

	@Test
	public void testWithHeaderWithRecordBatch3() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n" + "\"name_0\",\"address_0\"\r\n"
				+ "\"name_1\",\"address_1\"\r\n" + "\"name_2\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);

		LoaderImpl impl = new LoaderImpl();
		impl.setBatchCount(3);
		impl.setAllowedFailCount(0);
		LoadResult loadResult = impl.load(dataSource,
				"INSERT INTO etl_extr_ldr (name, address) VALUES (:name, :address)", provider, new NoneLimiter());

		assertEquals(3, loadResult.getTotalCount());
		assertEquals(3, loadResult.getSuccessCount());
		assertEquals(0, loadResult.getFailedCount());

		assertEquals("address_0", getAddressByName("name_0"));
		assertEquals("address_1", getAddressByName("name_1"));
		assertEquals("address_2", getAddressByName("name_2"));
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void testWithHeaderWithRecordBatch1() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n" + "\"name_0\",\"address_0\"\r\n"
				+ "\"name_1\",\"address_1\"\r\n" + "\"name_2\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);

		LoaderImpl impl = new LoaderImpl();
		impl.setBatchCount(1);
		impl.setAllowedFailCount(0);
		LoadResult loadResult = impl.load(dataSource,
				"INSERT INTO etl_extr_ldr (name, address) VALUES (:name, :address)", provider, new NoneLimiter());

		assertEquals(1, loadResult.getTotalCount());
		assertEquals(1, loadResult.getSuccessCount());
		assertEquals(0, loadResult.getFailedCount());

		assertEquals("address_0", getAddressByName("name_0"));
		getAddressByName("name_1");
	}

	@Test
	public void testWithHeaderWithRecordFailCount3() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n"
				+ "\"name_0_01234567890123456789012345\",\"address_0\"\r\n"
				+ "\"name_1_01234567890123456789012345\",\"address_1\"\r\n"
				+ "\"name_2_01234567890123456789012345\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);

		LoaderImpl impl = new LoaderImpl();
		impl.setBatchCount(0);
		impl.setAllowedFailCount(3);
		LoadResult loadResult = impl.load(dataSource,
				"INSERT INTO etl_extr_ldr (name, address) VALUES (:name, :address)", provider, new NoneLimiter());

		assertEquals(3, loadResult.getTotalCount());
		assertEquals(0, loadResult.getSuccessCount());
		assertEquals(3, loadResult.getFailedCount());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testWithHeaderWithRecordFailCount1() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n"
				+ "\"name_0_01234567890123456789012345\",\"address_0\"\r\n"
				+ "\"name_1_01234567890123456789012345\",\"address_1\"\r\n"
				+ "\"name_2_01234567890123456789012345\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);

		LoaderImpl impl = new LoaderImpl();
		impl.setBatchCount(0);
		impl.setAllowedFailCount(1);
		impl.load(dataSource, "INSERT INTO etl_extr_ldr (name, address) VALUES (:name, :address)", provider,
				new NoneLimiter());
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void testWithHeaderWithRecordFailCount0() throws IOException {
		StringReader reader = new StringReader("\"NAME\",\"ADDRESS\"\r\n"
				+ "\"name_0_01234567890123456789012345\",\"address_0\"\r\n"
				+ "\"name_1_01234567890123456789012345\",\"address_1\"\r\n"
				+ "\"name_2_01234567890123456789012345\",\"address_2\"\r\n");
		CsvProvider provider = new CsvProvider(reader, true);

		LoaderImpl impl = new LoaderImpl();
		impl.setBatchCount(0);
		impl.setAllowedFailCount(0);
		impl.load(dataSource, "INSERT INTO etl_extr_ldr (name, address) VALUES (:name, :address)", provider,
				new NoneLimiter());
	}

	private String getAddressByName(String name) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("name", name);
		return operations.queryForObject("SELECT address FROM etl_extr_ldr WHERE name = :name", paramMap, String.class);
	}

}
