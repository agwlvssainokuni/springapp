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

package cherry.sqlman.tool.clause;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class SqlClauseFormTest {

	@Test
	public void testEqualsAndHashCode() {
		SqlClauseForm form0 = new SqlClauseForm();
		form0.setDatabaseName("databaseName");
		form0.setSelect("select");
		form0.setFrom("from");
		form0.setWhere("where");
		form0.setGroupBy("groupBy");
		form0.setHaving("having");
		form0.setOrderBy("orderBy");
		form0.setParamMap("paramMap");
		form0.setLockVersion(1);
		form0.setPno(1);
		form0.setPsz(10);

		// 同じ
		SqlClauseForm form1 = new SqlClauseForm();
		form1.setDatabaseName("databaseName");
		form1.setSelect("select");
		form1.setFrom("from");
		form1.setWhere("where");
		form1.setGroupBy("groupBy");
		form1.setHaving("having");
		form1.setOrderBy("orderBy");
		form1.setParamMap("paramMap");
		form1.setLockVersion(1);
		form1.setPno(1);
		form1.setPsz(10);
		assertEquals(form0, form1);
		assertEquals(form0.hashCode(), form1.hashCode());

		// 異なる
		SqlClauseForm form2 = new SqlClauseForm();
		form2.setDatabaseName("databaseName2");
		form2.setSelect("select");
		form2.setFrom("from");
		form2.setWhere("where");
		form2.setGroupBy("groupBy");
		form2.setHaving("having");
		form2.setOrderBy("orderBy");
		form2.setParamMap("paramMap");
		form2.setLockVersion(1);
		form2.setPno(1);
		form2.setPsz(10);
		assertNotEquals(form0, form2);
		assertNotEquals(form0.hashCode(), form2.hashCode());
	}

	@Test
	public void testToString() {
		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("databaseName");
		form.setSelect("select");
		form.setFrom("from");
		form.setWhere("where");
		form.setGroupBy("groupBy");
		form.setHaving("having");
		form.setOrderBy("orderBy");
		form.setParamMap("paramMap");
		form.setLockVersion(1);
		form.setPno(1);
		form.setPsz(10);
		assertEquals(
				"SqlClauseForm(super=SqlClauseFormBase(databaseName=databaseName, select=select, from=from, where=where, groupBy=groupBy, having=having, orderBy=orderBy, paramMap=paramMap, lockVersion=1, pno=1, psz=10))",
				form.toString());
	}

}
