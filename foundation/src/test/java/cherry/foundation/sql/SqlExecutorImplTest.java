/*
 * Copyright 2012,2015 agwlvssainokuni
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

package cherry.foundation.sql;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link SqlExecutorImpl} のテスト.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class SqlExecutorImplTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlExecutor executor;

	@Autowired
	private JdbcOperations operations;

	@Test
	public void DDLをファイルから読込んで実行する() throws IOException {
		Resource resource = new ClassPathResource("SqlExecutorImplTest.sql", getClass());
		executor.execute(dataSource, resource, null, false);

		assertEquals(Integer.valueOf(3), operations.queryForObject("SELECT COUNT(*) FROM sql_execution", Integer.class));
		for (int i = 1; i <= 3; i++) {
			assertEquals("user" + i,
					operations.queryForObject("SELECT name FROM sql_execution WHERE login=?", String.class, "user" + i));
		}
	}

	@Test
	public void 不正なSQLを実行する_エラー無視() throws IOException {
		try (Reader reader = new StringReader("DROP TABLE no_table")) {
			executor.execute(dataSource, reader, null, true);
		}
	}

	@Test(expected = BadSqlGrammarException.class)
	public void 不正なSQLを実行する_エラー通知() throws IOException {
		try (Reader reader = new StringReader("DROP TABLE no_table")) {
			executor.execute(dataSource, reader, null, false);
		}
	}

}
