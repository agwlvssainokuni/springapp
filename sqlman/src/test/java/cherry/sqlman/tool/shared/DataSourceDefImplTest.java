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

import static java.text.MessageFormat.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class DataSourceDefImplTest {

	@Autowired
	private DataSourceDef dataSourceDef;

	@Test
	public void testDefaultName() {
		assertEquals("db1", dataSourceDef.getDefaultName());
	}

	@Test
	public void testGetNames() {
		int i = 1;
		for (String name : dataSourceDef.getNames()) {
			if (i == dataSourceDef.getNames().size()) {
				assertEquals("system", name);
			} else {
				assertEquals(format("db{0}", i), name);
			}
			i += 1;
		}
	}

	@Test
	public void testGetDataSource() {
		for (String name : dataSourceDef.getNames()) {
			assertNotNull(dataSourceDef.getDataSource(name));
		}
		assertNull(dataSourceDef.getDataSource("noname"));
	}

}
