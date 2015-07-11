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

package cherry.sqlman.tool.search;

import static cherry.sqlman.Published.PRIVATE;
import static cherry.sqlman.Published.PUBLIC;
import static cherry.sqlman.SqlType.CLAUSE;
import static cherry.sqlman.SqlType.LOAD;
import static cherry.sqlman.SqlType.STATEMENT;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class SqlSearchFormTest {

	@Test
	public void testEqualsAndHashCode() {

		SqlSearchForm form0 = new SqlSearchForm();
		form0.setName("name");
		form0.setSqlType(asList(CLAUSE, STATEMENT, LOAD));
		form0.setPublished(asList(PRIVATE, PUBLIC));
		form0.setRegisteredFromDt(new LocalDate(2015, 1, 1));
		form0.setRegisteredFromTm(new LocalTime(0, 0, 0));
		form0.setRegisteredToDt(new LocalDate(2015, 12, 31));
		form0.setRegisteredToTm(new LocalTime(23, 59, 59));
		form0.setPageNo(1);
		form0.setPageSz(10);

		// 同じ
		SqlSearchForm form1 = new SqlSearchForm();
		form1.setName("name");
		form1.setSqlType(asList(CLAUSE, STATEMENT, LOAD));
		form1.setPublished(asList(PRIVATE, PUBLIC));
		form1.setRegisteredFromDt(new LocalDate(2015, 1, 1));
		form1.setRegisteredFromTm(new LocalTime(0, 0, 0));
		form1.setRegisteredToDt(new LocalDate(2015, 12, 31));
		form1.setRegisteredToTm(new LocalTime(23, 59, 59));
		form1.setPageNo(1);
		form1.setPageSz(10);
		assertEquals(form0, form1);
		assertEquals(form0.hashCode(), form1.hashCode());

		// 異なる
		SqlSearchForm form2 = new SqlSearchForm();
		form2.setName("name2");
		form2.setSqlType(asList(CLAUSE, STATEMENT, LOAD));
		form2.setPublished(asList(PRIVATE, PUBLIC));
		form2.setRegisteredFromDt(new LocalDate(2015, 1, 1));
		form2.setRegisteredFromTm(new LocalTime(0, 0, 0));
		form2.setRegisteredToDt(new LocalDate(2015, 12, 31));
		form2.setRegisteredToTm(new LocalTime(23, 59, 59));
		form2.setPageNo(1);
		form2.setPageSz(10);
		assertNotEquals(form0, form2);
		assertNotEquals(form0.hashCode(), form2.hashCode());
	}

	@Test
	public void testToString() {
		SqlSearchForm form = new SqlSearchForm();
		form.setName("name");
		form.setSqlType(asList(CLAUSE, STATEMENT, LOAD));
		form.setPublished(asList(PRIVATE, PUBLIC));
		form.setRegisteredFromDt(new LocalDate(2015, 1, 1));
		form.setRegisteredFromTm(new LocalTime(0, 0, 0));
		form.setRegisteredToDt(new LocalDate(2015, 12, 31));
		form.setRegisteredToTm(new LocalTime(23, 59, 59));
		form.setPageNo(1);
		form.setPageSz(10);
		assertEquals(
				"SqlSearchForm(super=SqlSearchFormBase(name=name, registeredFromDt=2015-01-01, registeredFromTm=00:00:00.000, registeredToDt=2015-12-31, registeredToTm=23:59:59.000, sqlType=[CLAUSE, STATEMENT, LOAD], published=[PRIVATE, PUBLIC], pageNo=1, pageSz=10))",
				form.toString());
	}

}
