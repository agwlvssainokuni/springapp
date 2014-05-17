/*
 * Copyright 2004,2014 agwlvssainokuni
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

package cherry.spring.common.lib.paginate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PaginatorImplTest {

	private Paginator create() {
		PaginatorImpl p = new PaginatorImpl();
		return p;
	}

	@Test
	public void testNew() {
		assertNotNull(create());
	}

	@Test
	public void testGetPageCount() {
		Paginator paginator = create();
		assertEquals(0, paginator.getPageCount(0, 10));
		assertEquals(1, paginator.getPageCount(10, 10));
		assertEquals(2, paginator.getPageCount(11, 10));
		assertEquals(2, paginator.getPageCount(20, 10));
		assertEquals(3, paginator.getPageCount(21, 10));
	}

	@Test
	public void testGetPage() {

		Page page;
		Paginator paginator = create();

		page = paginator.getPage(0, 0, 10);
		assertEquals(0, page.getNo());
		assertEquals(0, page.getCount());
		assertEquals(0, page.getFrom());
		assertEquals(-1, page.getTo());

		page = paginator.getPage(-1, 21, 10);
		assertEquals(0, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(0, page.getFrom());
		assertEquals(9, page.getTo());

		page = paginator.getPage(0, 21, 10);
		assertEquals(0, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(0, page.getFrom());
		assertEquals(9, page.getTo());

		page = paginator.getPage(1, 21, 10);
		assertEquals(1, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(10, page.getFrom());
		assertEquals(19, page.getTo());

		page = paginator.getPage(2, 21, 10);
		assertEquals(2, page.getNo());
		assertEquals(1, page.getCount());
		assertEquals(20, page.getFrom());
		assertEquals(20, page.getTo());

		page = paginator.getPage(3, 21, 10);
		assertEquals(2, page.getNo());
		assertEquals(1, page.getCount());
		assertEquals(20, page.getFrom());
		assertEquals(20, page.getTo());

		page = paginator.getPage(3, 30, 10);
		assertEquals(2, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(20, page.getFrom());
		assertEquals(29, page.getTo());
	}

}
