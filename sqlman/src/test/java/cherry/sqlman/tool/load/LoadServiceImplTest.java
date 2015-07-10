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
import cherry.sqlman.db.gen.query.BSqlLoad;
import cherry.sqlman.db.gen.query.BSqlMetadata;
import cherry.sqlman.db.gen.query.QSqlLoad;
import cherry.sqlman.db.gen.query.QSqlMetadata;

import com.mysema.query.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class LoadServiceImplTest {

	@Autowired
	private LoadService LoadService;

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private BizDateTime bizDateTime;

	private final QSqlLoad l = new QSqlLoad("l");

	private final QSqlMetadata m = new QSqlMetadata("m");

	@Test
	public void testFindById_ID() {
		SqlLoadForm form0 = new SqlLoadForm();
		form0.setDatabaseName("databaseName");
		form0.setSql("sql");
		int id = LoadService.create(form0, "owner");

		SqlLoadForm form1 = LoadService.findById(id);
		assertNotNull(form1);
		assertEquals(form0.getDatabaseName(), form1.getDatabaseName());
		assertEquals(form0.getSql(), form1.getSql());
		assertEquals(1, form1.getLockVersion().intValue());

		SqlLoadForm form2 = LoadService.findById(id + 1);
		assertNull(form2);
	}

	@Test
	public void testCreate() {

		SqlLoadForm form = new SqlLoadForm();
		form.setDatabaseName("databaseName");
		form.setSql("sql");

		LocalDateTime now1 = bizDateTime.now();
		int id = LoadService.create(form, "owner");
		LocalDateTime now2 = bizDateTime.now();

		BSqlLoad record = findById(id);
		assertNotNull(record);
		assertEquals(id, record.getId().intValue());
		assertEquals("sql", record.getQuery());
		assertEquals(1, record.getLockVersion().intValue());

		BSqlMetadata metadata = findMetadataById(id);
		assertNotNull(metadata);
		assertEquals(id, metadata.getId().intValue());
		assertEquals(SqlType.LOAD.code(), metadata.getSqlType());
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
		SqlLoadForm form0 = new SqlLoadForm();
		form0.setDatabaseName("databaseName");
		form0.setSql("sql");
		int id = LoadService.create(form0, "owner");
		BSqlLoad record0 = findById(id);

		SqlLoadForm form1 = new SqlLoadForm();
		form1.setDatabaseName("databaseName1");
		form1.setSql("sql1");
		form1.setLockVersion(record0.getLockVersion());
		boolean updated1 = LoadService.update(id, form1);
		assertTrue(updated1);
		BSqlLoad record1 = findById(id);
		assertEquals(form1.getDatabaseName(), record1.getDatabaseName());
		assertEquals(form1.getSql(), record1.getQuery());
		assertEquals(record0.getLockVersion().intValue() + 1, record1.getLockVersion().intValue());

		// id違い
		SqlLoadForm form2 = new SqlLoadForm();
		form2.setDatabaseName("databaseName2");
		form2.setSql("sql2");
		form2.setLockVersion(record1.getLockVersion());
		boolean updated2 = LoadService.update(id + 1, form2);
		assertFalse(updated2);
		BSqlLoad record2 = findById(id);
		assertEquals(record1.toString(), record2.toString());

		// id違い
		SqlLoadForm form3 = new SqlLoadForm();
		form3.setDatabaseName("databaseName3");
		form3.setSql("sql3");
		form3.setLockVersion(record1.getLockVersion().intValue() + 1);
		boolean updated3 = LoadService.update(id, form3);
		assertFalse(updated3);
		BSqlLoad record3 = findById(id);
		assertEquals(record1.toString(), record3.toString());
	}

	@Test
	public void testDelete_ID_LOCKVERSION() {
		SqlLoadForm form1 = new SqlLoadForm();
		form1.setDatabaseName("databaseName");
		form1.setSql("sql");
		int id1 = LoadService.create(form1, "owner");
		form1.setLockVersion(findById(id1).getLockVersion());
		assertTrue(LoadService.update(id1, form1));
		BSqlMetadata metadata1 = findMetadataById(id1);
		boolean deleted1 = LoadService.delete(id1, metadata1.getLockVersion());
		assertTrue(deleted1);
		assertNull(findById(id1));
		assertNull(findMetadataById(id1));

		// id違い
		SqlLoadForm form2 = new SqlLoadForm();
		form2.setDatabaseName("databaseName");
		form2.setSql("sql2");
		int id2 = LoadService.create(form2, "owner");
		form2.setLockVersion(findById(id2).getLockVersion());
		assertTrue(LoadService.update(id2, form2));
		BSqlMetadata metadata2 = findMetadataById(id2);
		boolean deleted2 = LoadService.delete(id2 + 1, metadata2.getLockVersion());
		assertFalse(deleted2);
		assertNotNull(findById(id2));
		assertNotNull(findMetadataById(id2));

		// lockVersion違い
		SqlLoadForm form3 = new SqlLoadForm();
		form3.setDatabaseName("databaseName");
		form3.setSql("sql3");
		int id3 = LoadService.create(form3, "owner");
		form3.setLockVersion(findById(id3).getLockVersion());
		assertTrue(LoadService.update(id3, form3));
		BSqlMetadata metadata3 = findMetadataById(id3);
		boolean deleted3 = LoadService.delete(id3, metadata3.getLockVersion().intValue() + 1);
		assertFalse(deleted3);
		assertNotNull(findById(id3));
		assertNotNull(findMetadataById(id3));
	}

	private BSqlLoad findById(int id) {
		return queryFactory.from(l).where(l.id.eq(id)).uniqueResult(l);
	}

	private BSqlMetadata findMetadataById(int id) {
		return queryFactory.from(m).where(m.id.eq(id)).uniqueResult(m);
	}

}
