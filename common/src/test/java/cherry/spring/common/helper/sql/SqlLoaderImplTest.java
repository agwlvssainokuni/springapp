/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.common.helper.sql;

import static org.junit.Assert.*;
import static cherry.spring.common.AppCtxUtil.getBean;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * {@link SqlLoaderImpl} のテスト。
 */
public class SqlLoaderImplTest {

	@Test
	public void DDLをファイルから読込む() throws IOException {
		SqlLoader sqlLoader = getBean(SqlLoader.class);
		Resource resource = new ClassPathResource("SqlLoaderImplTest.sql", getClass());
		Map<String, String> sqlmap = sqlLoader.load(resource);
		assertEquals("SELECT 1 FROM dual", sqlmap.get("TEST01"));
		assertEquals("SELECT 2 FROM dual", sqlmap.get("TEST02"));
		assertEquals("SELECT 3 FROM dual", sqlmap.get("TEST03"));
		assertEquals("SELECT 4 FROM dual", sqlmap.get("TEST04"));
		assertEquals("SELECT 5 FROM dual", sqlmap.get("TEST05"));
	}

}
