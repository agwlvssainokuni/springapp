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

package cherry.sqlman.tool.load;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class SqlLoadFormTest {

	@Test
	public void testEqualsAndHashCode() {
		SqlLoadForm form0 = new SqlLoadForm();
		form0.setDatabaseName("databaseName");
		form0.setSql("sql");
		form0.setLockVersion(1);

		// 同じ
		SqlLoadForm form1 = new SqlLoadForm();
		form1.setDatabaseName("databaseName");
		form1.setSql("sql");
		form1.setLockVersion(1);
		assertEquals(form0, form1);
		assertEquals(form0.hashCode(), form1.hashCode());

		// 異なる
		SqlLoadForm form2 = new SqlLoadForm();
		form2.setDatabaseName("databaseName2");
		form2.setSql("sql");
		form2.setLockVersion(1);
		assertNotEquals(form0, form2);
		assertNotEquals(form0.hashCode(), form2.hashCode());
	}

	@Test
	public void testToString() {
		SqlLoadForm form = new SqlLoadForm();
		form.setDatabaseName("databaseName");
		form.setSql("sql");
		form.setLockVersion(1);
		assertEquals(
				"SqlLoadForm(super=SqlLoadFormBase(databaseName=databaseName, sql=sql, file=null, lockVersion=1))",
				form.toString());
	}

}
