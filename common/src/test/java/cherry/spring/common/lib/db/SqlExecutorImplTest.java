/*
 *   Copyright 2012,2014 agwlvssainokuni
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package cherry.spring.common.lib.db;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import cherry.spring.common.lib.db.SqlExecutor;
import cherry.spring.common.lib.db.SqlExecutorImpl;

/**
 * {@link SqlExecutorImpl} のテスト.
 */
public class SqlExecutorImplTest {

	private static ApplicationContext appCtx = null;

	private static ApplicationContext getAppCtx() {
		if (appCtx == null) {
			appCtx = new ClassPathXmlApplicationContext(
					"classpath:cherry/spring/common/lib/db/applicationContext.xml");
		}
		return appCtx;
	}

	private static <T> T getBean(Class<T> klass) {
		return getAppCtx().getBean(klass);
	}

	@Test
	public void DDLをファイルから読込んで実行する() throws IOException {
		SqlExecutor executor = getBean(SqlExecutor.class);
		Reader reader = new InputStreamReader(getClass().getResourceAsStream(
				"SqlExecutorImplTest.sql"), Charset.forName("UTF-8"));
		try {
			executor.execute(reader, null, true);
			assertTrue(true);
		} catch (DataAccessException ex) {
			ex.printStackTrace();
			fail("例外が発生するのはNG");
		} finally {
			reader.close();
		}
	}

	@Test
	public void 不正なSQLを実行する_エラー無視() throws IOException {
		SqlExecutor executor = getBean(SqlExecutor.class);
		Reader reader = new StringReader("DROP TABLE no_table");
		try {
			executor.execute(reader, null, true);
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
		SqlExecutor executor = getBean(SqlExecutor.class);
		Reader reader = new StringReader("DROP TABLE no_table");
		try {
			executor.execute(reader, null, false);
			fail("例外が発生しないとNG");
		} catch (DataAccessException ex) {
			assertTrue(true);
		} finally {
			reader.close();
		}
	}

}
