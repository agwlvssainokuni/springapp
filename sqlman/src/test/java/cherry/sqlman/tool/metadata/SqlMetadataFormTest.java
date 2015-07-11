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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class SqlMetadataFormTest {

	@Test
	public void testEqualsAndHashCode() {
		SqlMetadataForm form0 = new SqlMetadataForm();
		form0.setName("name");
		form0.setDescription("description");
		form0.setPublishedFlg(false);
		form0.setOwnedBy("owner");
		form0.setLockVersion(1);

		// 同じ
		SqlMetadataForm form1 = new SqlMetadataForm();
		form1.setName("name");
		form1.setDescription("description");
		form1.setPublishedFlg(false);
		form1.setOwnedBy("owner");
		form1.setLockVersion(1);
		assertEquals(form0, form1);
		assertEquals(form0.hashCode(), form1.hashCode());

		// 異なる
		SqlMetadataForm form2 = new SqlMetadataForm();
		form2.setName("name2");
		form2.setDescription("description");
		form2.setPublishedFlg(false);
		form2.setOwnedBy("owner");
		form2.setLockVersion(1);
		assertNotEquals(form0, form2);
		assertNotEquals(form0.hashCode(), form2.hashCode());
	}

	@Test
	public void testToString() {
		SqlMetadataForm form = new SqlMetadataForm();
		form.setName("name");
		form.setDescription("description");
		form.setPublishedFlg(false);
		form.setOwnedBy("owner");
		form.setLockVersion(1);
		assertEquals(
				"SqlMetadataForm(super=SqlMetadataFormBase(name=name, description=description, ownedBy=owner, publishedFlg=false, lockVersion=1))",
				form.toString());
	}

}
