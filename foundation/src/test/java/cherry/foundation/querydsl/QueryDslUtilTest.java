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

package cherry.foundation.querydsl;

import static com.mysema.query.support.Expressions.as;
import static com.mysema.query.support.Expressions.constant;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.db.query.QConversionTest;
import cherry.goods.paginate.PageSet;
import cherry.goods.paginate.PagedList;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.types.Expression;
import com.mysema.query.types.expr.DateExpression;
import com.mysema.query.types.expr.DateTimeExpression;
import com.mysema.query.types.expr.TimeExpression;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
@Transactional
public class QueryDslUtilTest {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Test
	public void currentDate() {
		DateExpression<LocalDate> expr = QueryDslUtil.currentDate();
		String query = queryFactory.query().getSQL(expr).getSQL();
		assertEquals("select current_date\nfrom dual", query);
	}

	@Test
	public void currentTime() {
		TimeExpression<LocalTime> expr = QueryDslUtil.currentTime();
		String query = queryFactory.query().getSQL(expr).getSQL();
		assertEquals("select current_time\nfrom dual", query);
	}

	@Test
	public void currentDateTime() {
		DateTimeExpression<LocalDateTime> expr = QueryDslUtil.currentTimestamp();
		String query = queryFactory.query().getSQL(expr).getSQL();
		assertEquals("select current_timestamp\nfrom dual", query);
	}

	@Test
	public void adjustSize() {
		assertNull(QueryDslUtil.adjustSize(null, QConversionTest.conversionTest.secInt));
		assertEquals("", QueryDslUtil.adjustSize("", QConversionTest.conversionTest.secInt));
		assertEquals("1234567890123456789012345678901234567890", QueryDslUtil.adjustSize(
				"1234567890123456789012345678901234567890", QConversionTest.conversionTest.secInt));
		assertEquals("1234567890123456789012345678901234567890", QueryDslUtil.adjustSize(
				"12345678901234567890123456789012345678901", QConversionTest.conversionTest.secInt));
	}

	@Test
	public void getExpressionLabel() {
		assertEquals("SEC_INT", QueryDslUtil.getExpressionLabel(QConversionTest.conversionTest.secInt));
		assertEquals("aaa", QueryDslUtil.getExpressionLabel(QConversionTest.conversionTest.secInt.as("aaa")));
		assertNull(QueryDslUtil.getExpressionLabel(QueryDslUtil.currentDate()));
	}

	@Test
	public void tupleToMap() {
		Expression<String> string = constant("string");
		Expression<String> stringWithName = as(string, "aaa");
		List<Tuple> list = queryFactory.query().list(string, stringWithName);

		PagedList<Tuple> pagedList = new PagedList<>();
		pagedList.setPageSet(new PageSet());
		pagedList.setList(list);

		PagedList<Map<String, ?>> plist = QueryDslUtil.tupleToMap(pagedList, string, stringWithName);
		assertEquals(pagedList.getPageSet(), plist.getPageSet());
		assertEquals(1, plist.getList().size());
		assertEquals(1, plist.getList().get(0).entrySet().size());
		assertEquals("string", plist.getList().get(0).get("aaa"));
	}

	@Test
	public void misc() {
		assertNotNull(new QueryDslUtil());
	}

}
