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

package cherry.sqlman.tool.statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class SqlStatementFormTest {

	@Test
	public void testEqualsAndHashCode() {
		SqlStatementForm form0 = new SqlStatementForm();
		form0.setDatabaseName("databaseName");
		form0.setSql("sql");
		form0.setParamMap("paramMap");
		form0.setLockVersion(1);

		// 同じ
		SqlStatementForm form1 = new SqlStatementForm();
		form1.setDatabaseName("databaseName");
		form1.setSql("sql");
		form1.setParamMap("paramMap");
		form1.setLockVersion(1);
		assertEquals(form0, form1);
		assertEquals(form0.hashCode(), form1.hashCode());

		// 異なる
		SqlStatementForm form2 = new SqlStatementForm();
		form2.setDatabaseName("databaseName2");
		form2.setSql("sql");
		form2.setParamMap("paramMap");
		form2.setLockVersion(1);
		assertNotEquals(form0, form2);
		assertNotEquals(form0.hashCode(), form2.hashCode());
	}

	@Test
	public void testToString() {
		SqlStatementForm form = new SqlStatementForm();
		form.setDatabaseName("databaseName");
		form.setSql("sql");
		form.setParamMap("paramMap");
		form.setLockVersion(1);
		assertEquals(
				"SqlStatementForm(super=SqlStatementFormBase(databaseName=databaseName, sql=sql, paramMap=paramMap, lockVersion=1))",
				form.toString());
	}

}
