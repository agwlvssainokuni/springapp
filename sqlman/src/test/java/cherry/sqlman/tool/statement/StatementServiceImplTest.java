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
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.db.gen.query.BSqlStatement;
import cherry.sqlman.db.gen.query.QSqlMetadata;
import cherry.sqlman.db.gen.query.QSqlStatement;

import com.mysema.query.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class StatementServiceImplTest {

	@Autowired
	private StatementService statementService;

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private BizDateTime bizDateTime;

	private final QSqlStatement s = new QSqlStatement("s");

	private final QSqlMetadata m = new QSqlMetadata("m");

	@Test
	public void testFindById_ID() {
		SqlStatementForm form0 = new SqlStatementForm();
		form0.setDatabaseName("databaseName");
		form0.setSql("sql");
		form0.setParamMap("paramMap");
		int id = statementService.create(form0, "owner");

		SqlStatementForm form1 = statementService.findById(id);
		assertNotNull(form1);
		assertEquals(form0.getDatabaseName(), form1.getDatabaseName());
		assertEquals(form0.getSql(), form1.getSql());
		assertEquals(form0.getParamMap(), form1.getParamMap());
		assertEquals(1, form1.getLockVersion().intValue());

		SqlStatementForm form2 = statementService.findById(id + 1);
		assertNull(form2);
	}

	@Test
	public void testCreate() {

		SqlStatementForm form = new SqlStatementForm();
		form.setDatabaseName("databaseName");
		form.setSql("sql");
		form.setParamMap("paramMap");

		LocalDateTime now1 = bizDateTime.now();
		int id = statementService.create(form, "owner");
		LocalDateTime now2 = bizDateTime.now();

		BSqlStatement record = findById(id);
		assertNotNull(record);
		assertEquals(id, record.getId().intValue());
		assertEquals("sql", record.getQuery());
		assertEquals("paramMap", record.getParamMap());
		assertEquals(1, record.getLockVersion().intValue());

		BSqlMetadata metadata = findMetadataById(id);
		assertNotNull(metadata);
		assertEquals(id, metadata.getId().intValue());
		assertEquals(SqlType.STATEMENT.code(), metadata.getSqlType());
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
		SqlStatementForm form0 = new SqlStatementForm();
		form0.setDatabaseName("databaseName");
		form0.setSql("sql");
		form0.setParamMap("paramMap");
		int id = statementService.create(form0, "owner");
		BSqlStatement record0 = findById(id);

		SqlStatementForm form1 = new SqlStatementForm();
		form1.setDatabaseName("databaseName1");
		form1.setSql("sql1");
		form1.setParamMap("paramMap1");
		form1.setLockVersion(record0.getLockVersion());
		boolean updated1 = statementService.update(id, form1);
		assertTrue(updated1);
		BSqlStatement record1 = findById(id);
		assertEquals(form1.getDatabaseName(), record1.getDatabaseName());
		assertEquals(form1.getSql(), record1.getQuery());
		assertEquals(form1.getParamMap(), record1.getParamMap());
		assertEquals(record0.getLockVersion().intValue() + 1, record1.getLockVersion().intValue());

		// id違い
		SqlStatementForm form2 = new SqlStatementForm();
		form2.setDatabaseName("databaseName2");
		form2.setSql("sql2");
		form2.setParamMap("paramMap2");
		form2.setLockVersion(record1.getLockVersion());
		boolean updated2 = statementService.update(id + 1, form2);
		assertFalse(updated2);
		BSqlStatement record2 = findById(id);
		assertEquals(record1.toString(), record2.toString());

		// id違い
		SqlStatementForm form3 = new SqlStatementForm();
		form3.setDatabaseName("databaseName3");
		form3.setSql("sql3");
		form3.setParamMap("paramMap3");
		form3.setLockVersion(record1.getLockVersion().intValue() + 1);
		boolean updated3 = statementService.update(id, form3);
		assertFalse(updated3);
		BSqlStatement record3 = findById(id);
		assertEquals(record1.toString(), record3.toString());
	}

	@Test
	public void testDelete_ID_LOCKVERSION() {
		SqlStatementForm form1 = new SqlStatementForm();
		form1.setDatabaseName("databaseName");
		form1.setSql("sql");
		form1.setParamMap("paramMap");
		int id1 = statementService.create(form1, "owner");
		form1.setLockVersion(findById(id1).getLockVersion());
		assertTrue(statementService.update(id1, form1));
		BSqlMetadata metadata1 = findMetadataById(id1);
		boolean deleted1 = statementService.delete(id1, metadata1.getLockVersion());
		assertTrue(deleted1);
		assertNull(findById(id1));
		assertNull(findMetadataById(id1));

		// id違い
		SqlStatementForm form2 = new SqlStatementForm();
		form2.setDatabaseName("databaseName");
		form2.setSql("sql2");
		form2.setParamMap("paramMap");
		int id2 = statementService.create(form2, "owner");
		form2.setLockVersion(findById(id2).getLockVersion());
		assertTrue(statementService.update(id2, form2));
		BSqlMetadata metadata2 = findMetadataById(id2);
		boolean deleted2 = statementService.delete(id2 + 1, metadata2.getLockVersion());
		assertFalse(deleted2);
		assertNotNull(findById(id2));
		assertNotNull(findMetadataById(id2));

		// lockVersion違い
		SqlStatementForm form3 = new SqlStatementForm();
		form3.setDatabaseName("databaseName");
		form3.setSql("sql3");
		form3.setParamMap("paramMap");
		int id3 = statementService.create(form3, "owner");
		form3.setLockVersion(findById(id3).getLockVersion());
		assertTrue(statementService.update(id3, form3));
		BSqlMetadata metadata3 = findMetadataById(id3);
		boolean deleted3 = statementService.delete(id3, metadata3.getLockVersion().intValue() + 1);
		assertFalse(deleted3);
		assertNotNull(findById(id3));
		assertNotNull(findMetadataById(id3));
	}

	private BSqlStatement findById(int id) {
		return queryFactory.from(s).where(s.id.eq(id)).uniqueResult(s);
	}

	private BSqlMetadata findMetadataById(int id) {
		return queryFactory.from(m).where(m.id.eq(id)).uniqueResult(m);
	}

}
