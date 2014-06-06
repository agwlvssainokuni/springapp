/*
 * Copyright 2012,2014 agwlvssainokuni
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

import static cherry.spring.common.AppCtxUtil.getBean;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.dao.DataAccessException;

/**
 * {@link SqlExecutorImpl} のテスト.
 */
public class SqlExecutorImplTest {

	@Test
	public void DDLをファイルから読込んで実行する() throws IOException {
		DataSource dataSource = getBean(DataSource.class);
		SqlExecutor executor = new SqlExecutorImpl();
		Reader reader = new InputStreamReader(getClass().getResourceAsStream(
				"SqlExecutorImplTest.sql"), Charset.forName("UTF-8"));
		try {
			executor.execute(dataSource, reader, null, true);
			assertTrue(true);
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		} finally {
			executor.execute(dataSource, new StringReader(
					"DROP TABLE etl_user_test"), null, true);
			reader.close();
		}
	}

	@Test
	public void 不正なSQLを実行する_エラー無視() throws IOException {
		DataSource dataSource = getBean(DataSource.class);
		SqlExecutor executor = new SqlExecutorImpl();
		Reader reader = new StringReader("DROP TABLE no_table");
		try {
			executor.execute(dataSource, reader, null, true);
			assertTrue(true);
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		} finally {
			reader.close();
		}
	}

	@Test
	public void 不正なSQLを実行する_エラー通知() throws IOException {
		DataSource dataSource = getBean(DataSource.class);
		SqlExecutor executor = new SqlExecutorImpl();
		Reader reader = new StringReader("DROP TABLE no_table");
		try {
			executor.execute(dataSource, reader, null, false);
			fail("例外が発生しないとNG");
		} catch (DataAccessException ex) {
			assertTrue(true);
		} finally {
			reader.close();
		}
	}

}
