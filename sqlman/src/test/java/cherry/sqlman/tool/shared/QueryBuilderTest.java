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

package cherry.sqlman.tool.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class QueryBuilderTest {

	@Test
	public void testBuild_FULL() {
		QueryBuilder builder = new QueryBuilder();
		builder.setSelect("a");
		builder.setFrom("b");
		builder.setWhere("c");
		builder.setGroupBy("d");
		builder.setHaving("e");
		builder.setOrderBy("f");
		assertEquals("SELECT a FROM b WHERE c GROUP BY d HAVING e ORDER BY f", builder.build());
		assertEquals("SELECT a FROM b WHERE c GROUP BY d HAVING e ORDER BY f", builder.build(null, null));
		assertEquals("SELECT a FROM b WHERE c GROUP BY d HAVING e ORDER BY f LIMIT 10 OFFSET 20",
				builder.build(10L, 20L));
		assertEquals("SELECT COUNT(*) FROM (SELECT a FROM b WHERE c GROUP BY d HAVING e) AS A", builder.buildCount());
	}

	@Test
	public void testBuild_PARTIAL() {
		QueryBuilder builder = new QueryBuilder();
		builder.setSelect("a");
		builder.setFrom("b");
		assertEquals("SELECT a FROM b", builder.build());
		assertEquals("SELECT a FROM b", builder.build(null, null));
		assertEquals("SELECT a FROM b LIMIT 10 OFFSET 20", builder.build(10L, 20L));
		assertEquals("SELECT COUNT(*) FROM (SELECT a FROM b) AS A", builder.buildCount());
	}

	@Test
	public void testEqualsAndHashCode() {
		QueryBuilder builder0 = new QueryBuilder();
		builder0.setSelect("a");
		builder0.setFrom("b");
		builder0.setWhere("c");
		builder0.setGroupBy("d");
		builder0.setHaving("e");
		builder0.setOrderBy("f");

		// 同じ
		QueryBuilder builder1 = new QueryBuilder();
		builder1.setSelect("a");
		builder1.setFrom("b");
		builder1.setWhere("c");
		builder1.setGroupBy("d");
		builder1.setHaving("e");
		builder1.setOrderBy("f");
		assertEquals(builder0, builder1);
		assertEquals(builder0.hashCode(), builder1.hashCode());

		// 異なる
		QueryBuilder builder2 = new QueryBuilder();
		builder2.setSelect("a2");
		builder2.setFrom("b");
		builder2.setWhere("c");
		builder2.setGroupBy("d");
		builder2.setHaving("e");
		builder2.setOrderBy("f");
		assertNotEquals(builder0, builder2);
		assertNotEquals(builder0.hashCode(), builder2.hashCode());
	}

	@Test
	public void testToString() {
		QueryBuilder builder = new QueryBuilder();
		builder.setSelect("a");
		builder.setFrom("b");
		builder.setWhere("c");
		builder.setGroupBy("d");
		builder.setHaving("e");
		builder.setOrderBy("f");
		assertEquals("QueryBuilder(select=a, from=b, where=c, groupBy=d, having=e, orderBy=f)", builder.toString());
	}

}
