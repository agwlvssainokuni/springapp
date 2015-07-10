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

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDateTime;
import org.joda.time.ReadablePartial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.sqlman.Published;
import cherry.sqlman.SqlType;
import cherry.sqlman.db.gen.query.BSqlClause;
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.db.gen.query.QSqlClause;
import cherry.sqlman.db.gen.query.QSqlMetadata;

import com.mysema.query.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class ClauseServiceImplTest {

	@Autowired
	private ClauseService clauseService;

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private BizDateTime bizDateTime;

	private final QSqlClause c = new QSqlClause("c");

	private final QSqlMetadata m = new QSqlMetadata("m");

	@Test
	public void testFindById_ID() {
		SqlClauseForm form0 = new SqlClauseForm();
		form0.setDatabaseName("databaseName");
		form0.setSelect("select");
		form0.setFrom("from");
		form0.setWhere("where");
		form0.setGroupBy("groupBy");
		form0.setHaving("having");
		form0.setOrderBy("orderBy");
		form0.setParamMap("paramMap");
		int id = clauseService.create(form0, "owner");

		SqlClauseForm form1 = clauseService.findById(id);
		assertNotNull(form1);
		assertEquals(form0.getDatabaseName(), form1.getDatabaseName());
		assertEquals(form0.getSelect(), form1.getSelect());
		assertEquals(form0.getFrom(), form1.getFrom());
		assertEquals(form0.getWhere(), form1.getWhere());
		assertEquals(form0.getGroupBy(), form1.getGroupBy());
		assertEquals(form0.getHaving(), form1.getHaving());
		assertEquals(form0.getOrderBy(), form1.getOrderBy());
		assertEquals(form0.getParamMap(), form1.getParamMap());
		assertEquals(1, form1.getLockVersion().intValue());

		SqlClauseForm form2 = clauseService.findById(id + 1);
		assertNull(form2);
	}

	@Test
	public void testCreate() {

		SqlClauseForm form = new SqlClauseForm();
		form.setDatabaseName("databaseName");
		form.setSelect("select");
		form.setFrom("from");
		form.setWhere("where");
		form.setGroupBy("groupBy");
		form.setHaving("having");
		form.setOrderBy("orderBy");
		form.setParamMap("paramMap");

		LocalDateTime now1 = bizDateTime.now();
		int id = clauseService.create(form, "owner");
		LocalDateTime now2 = bizDateTime.now();

		BSqlClause record = findById(id);
		assertNotNull(record);
		assertEquals(id, record.getId().intValue());
		assertEquals("select", record.getSelectClause());
		assertEquals("from", record.getFromClause());
		assertEquals("where", record.getWhereClause());
		assertEquals("groupBy", record.getGroupByClause());
		assertEquals("having", record.getHavingClause());
		assertEquals("orderBy", record.getOrderByClause());
		assertEquals("paramMap", record.getParamMap());
		assertEquals(1, record.getLockVersion().intValue());

		BSqlMetadata metadata = findMetadataById(id);
		assertNotNull(metadata);
		assertEquals(id, metadata.getId().intValue());
		assertEquals(SqlType.CLAUSE.code(), metadata.getSqlType());
		assertEquals(Published.PRIVATE.code(), metadata.getPublishedFlg());
		assertEquals("owner", metadata.getOwnedBy());
		assertThat(metadata.getName(), greaterThanOrEqualTo(now1.toString()));
		assertThat(metadata.getName(), lessThanOrEqualTo(now2.toString()));
		assertThat(metadata.getDescription(), greaterThanOrEqualTo(now1.toString()));
		assertThat(metadata.getDescription(), lessThanOrEqualTo(now2.toString()));
		assertThat(metadata.getRegisteredAt(), greaterThanOrEqualTo((ReadablePartial) now1));
		assertThat(metadata.getRegisteredAt(), lessThanOrEqualTo((ReadablePartial) now2));
		assertThat(metadata.getChangedAt(), greaterThanOrEqualTo((ReadablePartial) now1));
		assertThat(metadata.getChangedAt(), lessThanOrEqualTo((ReadablePartial) now2));
		assertEquals(1, metadata.getLockVersion().intValue());
	}

	@Test
	public void testUpdate_ID_LOCKVERSION() {
		SqlClauseForm form0 = new SqlClauseForm();
		form0.setDatabaseName("databaseName");
		form0.setSelect("select");
		form0.setFrom("from");
		form0.setWhere("where");
		form0.setGroupBy("groupBy");
		form0.setHaving("having");
		form0.setOrderBy("orderBy");
		form0.setParamMap("paramMap");
		int id = clauseService.create(form0, "owner");
		BSqlClause record0 = findById(id);

		SqlClauseForm form1 = new SqlClauseForm();
		form1.setDatabaseName("databaseName1");
		form1.setSelect("select1");
		form1.setFrom("from1");
		form1.setWhere("where1");
		form1.setGroupBy("groupBy1");
		form1.setHaving("having1");
		form1.setOrderBy("orderBy1");
		form1.setParamMap("paramMap1");
		form1.setLockVersion(record0.getLockVersion());
		boolean updated1 = clauseService.update(id, form1);
		assertTrue(updated1);
		BSqlClause record1 = findById(id);
		assertEquals(form1.getDatabaseName(), record1.getDatabaseName());
		assertEquals(form1.getSelect(), record1.getSelectClause());
		assertEquals(form1.getFrom(), record1.getFromClause());
		assertEquals(form1.getWhere(), record1.getWhereClause());
		assertEquals(form1.getGroupBy(), record1.getGroupByClause());
		assertEquals(form1.getHaving(), record1.getHavingClause());
		assertEquals(form1.getOrderBy(), record1.getOrderByClause());
		assertEquals(form1.getParamMap(), record1.getParamMap());
		assertEquals(record0.getLockVersion().intValue() + 1, record1.getLockVersion().intValue());

		// id違い
		SqlClauseForm form2 = new SqlClauseForm();
		form2.setDatabaseName("databaseName2");
		form2.setSelect("select2");
		form2.setFrom("from2");
		form2.setWhere("where2");
		form2.setGroupBy("groupBy2");
		form2.setHaving("having2");
		form2.setOrderBy("orderBy2");
		form2.setParamMap("paramMap2");
		form2.setLockVersion(record1.getLockVersion());
		boolean updated2 = clauseService.update(id + 1, form2);
		assertFalse(updated2);
		BSqlClause record2 = findById(id);
		assertEquals(record1.toString(), record2.toString());

		// id違い
		SqlClauseForm form3 = new SqlClauseForm();
		form3.setDatabaseName("databaseName3");
		form3.setSelect("select3");
		form3.setFrom("from3");
		form3.setWhere("where3");
		form3.setGroupBy("groupBy3");
		form3.setHaving("having3");
		form3.setOrderBy("orderBy3");
		form3.setParamMap("paramMap3");
		form3.setLockVersion(record1.getLockVersion().intValue() + 1);
		boolean updated3 = clauseService.update(id, form3);
		assertFalse(updated3);
		BSqlClause record3 = findById(id);
		assertEquals(record1.toString(), record3.toString());
	}

	@Test
	public void testDelete_ID_LOCKVERSION() {
		SqlClauseForm form1 = new SqlClauseForm();
		form1.setDatabaseName("databaseName");
		form1.setSelect("select");
		form1.setFrom("from");
		form1.setWhere("where");
		form1.setGroupBy("groupBy");
		form1.setHaving("having");
		form1.setOrderBy("orderBy");
		form1.setParamMap("paramMap");
		int id1 = clauseService.create(form1, "owner");
		form1.setLockVersion(findById(id1).getLockVersion());
		assertTrue(clauseService.update(id1, form1));
		BSqlMetadata metadata1 = findMetadataById(id1);
		boolean deleted1 = clauseService.delete(id1, metadata1.getLockVersion());
		assertTrue(deleted1);
		assertNull(findById(id1));
		assertNull(findMetadataById(id1));

		// id違い
		SqlClauseForm form2 = new SqlClauseForm();
		form2.setDatabaseName("databaseName");
		form2.setSelect("select");
		form2.setFrom("from");
		form2.setWhere("where");
		form2.setGroupBy("groupBy");
		form2.setHaving("having");
		form2.setOrderBy("orderBy");
		form2.setParamMap("paramMap");
		int id2 = clauseService.create(form2, "owner");
		form2.setLockVersion(findById(id2).getLockVersion());
		assertTrue(clauseService.update(id2, form2));
		BSqlMetadata metadata2 = findMetadataById(id2);
		boolean deleted2 = clauseService.delete(id2 + 1, metadata2.getLockVersion());
		assertFalse(deleted2);
		assertNotNull(findById(id2));
		assertNotNull(findMetadataById(id2));

		// lockVersion違い
		SqlClauseForm form3 = new SqlClauseForm();
		form3.setDatabaseName("databaseName");
		form3.setSelect("select");
		form3.setFrom("from");
		form3.setWhere("where");
		form3.setGroupBy("groupBy");
		form3.setHaving("having");
		form3.setOrderBy("orderBy");
		form3.setParamMap("paramMap");
		int id3 = clauseService.create(form3, "owner");
		form3.setLockVersion(findById(id3).getLockVersion());
		assertTrue(clauseService.update(id3, form3));
		BSqlMetadata metadata3 = findMetadataById(id3);
		boolean deleted3 = clauseService.delete(id3, metadata3.getLockVersion().intValue() + 1);
		assertFalse(deleted3);
		assertNotNull(findById(id3));
		assertNotNull(findMetadataById(id3));
	}

	private BSqlClause findById(int id) {
		return queryFactory.from(c).where(c.id.eq(id)).uniqueResult(c);
	}

	private BSqlMetadata findMetadataById(int id) {
		return queryFactory.from(m).where(m.id.eq(id)).uniqueResult(m);
	}

}
