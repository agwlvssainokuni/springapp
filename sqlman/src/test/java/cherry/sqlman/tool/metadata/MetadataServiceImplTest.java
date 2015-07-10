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

package cherry.sqlman.tool.metadata;

import static java.util.Arrays.asList;
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
import cherry.sqlman.db.gen.query.QSqlMetadata;

import com.mysema.query.sql.SQLQueryFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class MetadataServiceImplTest {

	@Autowired
	private MetadataService metadataService;

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private BizDateTime bizDateTime;

	@Test
	public void testFindById_ID_PUBPRV_OWNER() {
		String ownerId = "owner";
		String otherId = "other";
		for (String loginId : asList(ownerId, otherId)) {
			for (Published pubprv : asList(Published.PRIVATE, Published.PUBLIC)) {

				LocalDateTime now = bizDateTime.now();
				int id = create("name", "description", pubprv, SqlType.CLAUSE, now, ownerId);
				SqlMetadataForm form1 = metadataService.findById(id, loginId);
				if (ownerId.equals(loginId) || pubprv == Published.PUBLIC) {

					assertNotNull(form1);
					assertEquals("name", form1.getName());
					assertEquals("description", form1.getDescription());
					if (pubprv.isPublished()) {
						assertTrue(form1.isPublishedFlg());
					} else {
						assertFalse(form1.isPublishedFlg());
					}
					assertEquals(ownerId, form1.getOwnedBy());
					assertEquals(1, form1.getLockVersion().intValue());
				} else {

					assertNull(form1);
				}

				SqlMetadataForm form2 = metadataService.findById(id + 1, loginId);
				assertNull(form2);
			}
		}
	}

	@Test
	public void testCreate() {

		LocalDateTime now1 = bizDateTime.now();
		int id = metadataService.create(SqlType.CLAUSE, "owner");
		LocalDateTime now2 = bizDateTime.now();

		BSqlMetadata record = findById(id);
		assertNotNull(record);
		assertEquals(id, record.getId().intValue());
		assertEquals(SqlType.CLAUSE.code(), record.getSqlType());
		assertEquals(Published.PRIVATE.code(), record.getPublishedFlg());
		assertEquals("owner", record.getOwnedBy());
		assertThat(record.getName(), greaterThanOrEqualTo(now1.toString()));
		assertThat(record.getName(), lessThanOrEqualTo(now2.toString()));
		assertThat(record.getDescription(), greaterThanOrEqualTo(now1.toString()));
		assertThat(record.getDescription(), lessThanOrEqualTo(now2.toString()));
		assertThat(record.getRegisteredAt(), greaterThanOrEqualTo((ReadablePartial) now1));
		assertThat(record.getRegisteredAt(), lessThanOrEqualTo((ReadablePartial) now2));
		assertThat(record.getChangedAt(), greaterThanOrEqualTo((ReadablePartial) now1));
		assertThat(record.getChangedAt(), lessThanOrEqualTo((ReadablePartial) now2));
		assertEquals(1, record.getLockVersion().intValue());
	}

	@Test
	public void testUpdate_ID_LOCKVERSION() {

		int id = metadataService.create(SqlType.CLAUSE, "owner");
		BSqlMetadata record0 = findById(id);

		SqlMetadataForm form1 = new SqlMetadataForm();
		form1.setName("name1");
		form1.setDescription("description1");
		form1.setPublishedFlg(Published.PUBLIC.isPublished());
		form1.setLockVersion(record0.getLockVersion());

		LocalDateTime now1 = bizDateTime.now();
		boolean updated1 = metadataService.update(id, form1);
		LocalDateTime now2 = bizDateTime.now();
		assertTrue(updated1);
		BSqlMetadata record1 = findById(id);
		assertEquals("name1", record1.getName());
		assertEquals("description1", record1.getDescription());
		assertEquals(Published.PUBLIC.code(), record1.getPublishedFlg());
		assertEquals(record0.getLockVersion().intValue() + 1, record1.getLockVersion().intValue());
		assertThat(record1.getChangedAt(), greaterThanOrEqualTo((ReadablePartial) now1));
		assertThat(record1.getChangedAt(), lessThanOrEqualTo((ReadablePartial) now2));

		// id違い
		SqlMetadataForm form2 = new SqlMetadataForm();
		form2.setName("name2");
		form2.setDescription("description2");
		form2.setPublishedFlg(Published.PRIVATE.isPublished());
		form2.setLockVersion(1);
		boolean updated2 = metadataService.update(id + 1, form1);
		assertFalse(updated2);
		BSqlMetadata record2 = findById(id);
		assertEquals(record1.toString(), record2.toString());

		// lockVersion違い
		SqlMetadataForm form3 = new SqlMetadataForm();
		form3.setName("name3");
		form3.setDescription("description3");
		form3.setPublishedFlg(Published.PRIVATE.isPublished());
		form3.setLockVersion(2);
		boolean updated3 = metadataService.update(id, form1);
		assertFalse(updated3);
		BSqlMetadata record3 = findById(id);
		assertEquals(record1.toString(), record3.toString());
	}

	@Test
	public void testDelete_ID_LOCKVERSION() {
		int id1 = metadataService.create(SqlType.CLAUSE, "owner");
		BSqlMetadata record1 = findById(id1);
		boolean result1 = metadataService.delete(id1, record1.getLockVersion().intValue());
		assertTrue(result1);
		assertNull(findById(id1));

		// id違い
		int id2 = metadataService.create(SqlType.CLAUSE, "owner");
		BSqlMetadata record2 = findById(id2);
		boolean result2 = metadataService.delete(id2 + 1, record2.getLockVersion().intValue());
		assertFalse(result2);
		assertNotNull(findById(id2));

		// id違い
		int id3 = metadataService.create(SqlType.CLAUSE, "owner");
		BSqlMetadata record3 = findById(id2);
		boolean result3 = metadataService.delete(id3, record3.getLockVersion().intValue() + 1);
		assertFalse(result3);
		assertNotNull(findById(id3));
	}

	private BSqlMetadata findById(int id) {
		QSqlMetadata m = new QSqlMetadata("m");
		return queryFactory.from(m).where(m.id.eq(id)).uniqueResult(m);
	}

	private int create(String name, String desc, Published pubprv, SqlType sqlType, LocalDateTime now, String owner) {
		QSqlMetadata m = new QSqlMetadata("m");
		BSqlMetadata record = new BSqlMetadata();
		record.setName(name);
		record.setDescription(desc);
		record.setPublishedFlg(pubprv.code());
		record.setSqlType(sqlType.code());
		record.setRegisteredAt(now);
		record.setChangedAt(now);
		record.setOwnedBy(owner);
		return queryFactory.insert(m).populate(record).executeWithKey(m.id);
	}

}
