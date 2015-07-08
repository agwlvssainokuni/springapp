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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDateTime;
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

	private final QSqlMetadata m = new QSqlMetadata("m");

	@Test
	public void testFindById_ID_PUBPRV_OWNER() {
		String ownerId = "owner";
		String otherId = "other";
		for (String loginId : asList(ownerId, otherId)) {
			for (Published pubprv : asList(Published.PRIVATE, Published.PUBLIC)) {

				int id = create("name", "description", pubprv, SqlType.CLAUSE, ownerId);
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

	private int create(String name, String description, Published published, SqlType sqlType, String ownedBy) {
		LocalDateTime now = bizDateTime.now();
		BSqlMetadata record = new BSqlMetadata();
		record.setName(name);
		record.setDescription(description);
		record.setPublishedFlg(published.code());
		record.setSqlType(sqlType.code());
		record.setOwnedBy(ownedBy);
		record.setRegisteredAt(now);
		record.setChangedAt(now);
		return queryFactory.insert(m).populate(record).executeWithKey(m.id);
	}

}
