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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.mysema.query.Tuple;
import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.SimplePath;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.path.TimePath;

public class PathExpressionsTest {

	@Test
	public void simplePath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		assertEquals(Tuple.class, aaa.getType());
		assertEquals("aaa", aaa.getMetadata().getName());
	}

	@Test
	public void booleanPath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		BooleanPath bbb = PathExpressions.booleanPath(aaa, "bbb");
		assertEquals(Boolean.class, bbb.getType());
		assertEquals("bbb", bbb.getMetadata().getName());
		assertEquals(aaa, bbb.getMetadata().getParent());
	}

	@Test
	public void intPath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		NumberPath<Integer> bbb = PathExpressions.intPath(aaa, "bbb");
		assertEquals(Integer.class, bbb.getType());
		assertEquals("bbb", bbb.getMetadata().getName());
		assertEquals(aaa, bbb.getMetadata().getParent());
	}

	@Test
	public void longPath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		NumberPath<Long> bbb = PathExpressions.longPath(aaa, "bbb");
		assertEquals(Long.class, bbb.getType());
		assertEquals("bbb", bbb.getMetadata().getName());
		assertEquals(aaa, bbb.getMetadata().getParent());
	}

	@Test
	public void decimalPath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		NumberPath<BigDecimal> bbb = PathExpressions.decimalPath(aaa, "bbb");
		assertEquals(BigDecimal.class, bbb.getType());
		assertEquals("bbb", bbb.getMetadata().getName());
		assertEquals(aaa, bbb.getMetadata().getParent());
	}

	@Test
	public void stringPath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		StringPath bbb = PathExpressions.stringPath(aaa, "bbb");
		assertEquals(String.class, bbb.getType());
		assertEquals("bbb", bbb.getMetadata().getName());
		assertEquals(aaa, bbb.getMetadata().getParent());
	}

	@Test
	public void datePath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		DatePath<LocalDate> bbb = PathExpressions.datePath(aaa, "bbb");
		assertEquals(LocalDate.class, bbb.getType());
		assertEquals("bbb", bbb.getMetadata().getName());
		assertEquals(aaa, bbb.getMetadata().getParent());
	}

	@Test
	public void timePath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		TimePath<LocalTime> bbb = PathExpressions.timePath(aaa, "bbb");
		assertEquals(LocalTime.class, bbb.getType());
		assertEquals("bbb", bbb.getMetadata().getName());
		assertEquals(aaa, bbb.getMetadata().getParent());
	}

	@Test
	public void dateTimePath() {
		SimplePath<Tuple> aaa = PathExpressions.simplePath("aaa");
		DateTimePath<LocalDateTime> bbb = PathExpressions.dateTimePath(aaa, "bbb");
		assertEquals(LocalDateTime.class, bbb.getType());
		assertEquals("bbb", bbb.getMetadata().getName());
		assertEquals(aaa, bbb.getMetadata().getParent());
	}

	@Test
	public void misc() {
		assertNotNull(new PathExpressions());
	}

}
