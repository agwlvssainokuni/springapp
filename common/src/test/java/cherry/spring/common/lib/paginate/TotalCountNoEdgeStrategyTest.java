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

import org.junit.Test;

public class TotalCountNoEdgeStrategyTest {

	private Paginator create(int totalCount) {
		TotalCountNoEdgeStrategy strategy = new TotalCountNoEdgeStrategy();
		strategy.setTotalCount(totalCount);
		PaginatorImpl p = new PaginatorImpl();
		p.setPaginateStrategy(strategy);
		return p;
	}

	@Test
	public void testPaginate() {

		Page page;
		PageSet pageSet;
		Paginator paginator = create(8);

		pageSet = paginator.paginate(0, 101, 10);
		assertEquals(0, pageSet.getCurrent().getNo());
		assertEquals(10, pageSet.getCurrent().getCount());
		assertEquals(0, pageSet.getCurrent().getFrom());
		assertEquals(9, pageSet.getCurrent().getTo());
		assertEquals(1, pageSet.getNext().getNo());
		assertEquals(10, pageSet.getNext().getCount());
		assertEquals(10, pageSet.getNext().getFrom());
		assertEquals(19, pageSet.getNext().getTo());
		assertEquals(8, pageSet.getRange().size());
		assertEquals(pageSet.getNext(), pageSet.getRange().get(0));
		page = pageSet.getRange().get(7);
		assertEquals(8, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(80, page.getFrom());
		assertEquals(89, page.getTo());

		pageSet = paginator.paginate(5, 101, 10);
		assertEquals(5, pageSet.getCurrent().getNo());
		assertEquals(4, pageSet.getPrev().getNo());
		assertEquals(6, pageSet.getNext().getNo());
		assertEquals(8, pageSet.getRange().size());
		assertEquals(pageSet.getCurrent(), pageSet.getRange().get(3));
		assertEquals(pageSet.getPrev(), pageSet.getRange().get(2));
		assertEquals(pageSet.getNext(), pageSet.getRange().get(4));
		page = pageSet.getRange().get(0);
		assertEquals(2, page.getNo());
		page = pageSet.getRange().get(7);
		assertEquals(9, page.getNo());

		pageSet = paginator.paginate(10, 101, 10);
		assertEquals(10, pageSet.getCurrent().getNo());
		assertEquals(1, pageSet.getCurrent().getCount());
		assertEquals(100, pageSet.getCurrent().getFrom());
		assertEquals(100, pageSet.getCurrent().getTo());
		assertEquals(9, pageSet.getPrev().getNo());
		assertEquals(10, pageSet.getPrev().getCount());
		assertEquals(90, pageSet.getPrev().getFrom());
		assertEquals(99, pageSet.getPrev().getTo());
		assertEquals(8, pageSet.getRange().size());
		assertEquals(pageSet.getPrev(), pageSet.getRange().get(7));
		page = pageSet.getRange().get(0);
		assertEquals(2, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(20, page.getFrom());
		assertEquals(29, page.getTo());

		pageSet = paginator.paginate(9, 81, 10);
		assertEquals(8, pageSet.getCurrent().getNo());
		assertEquals(1, pageSet.getCurrent().getCount());
		assertEquals(80, pageSet.getCurrent().getFrom());
		assertEquals(80, pageSet.getCurrent().getTo());
		assertEquals(7, pageSet.getPrev().getNo());
		assertEquals(10, pageSet.getPrev().getCount());
		assertEquals(70, pageSet.getPrev().getFrom());
		assertEquals(79, pageSet.getPrev().getTo());
		assertEquals(7, pageSet.getRange().size());
		assertEquals(pageSet.getPrev(), pageSet.getRange().get(6));
		page = pageSet.getRange().get(0);
		assertEquals(1, page.getNo());
		assertEquals(10, page.getCount());
		assertEquals(10, page.getFrom());
		assertEquals(19, page.getTo());
	}

}
