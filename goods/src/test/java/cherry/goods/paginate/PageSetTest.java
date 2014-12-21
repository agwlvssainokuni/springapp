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

package cherry.goods.paginate;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class PageSetTest {

	@Test
	public void testGetterSetter() {

		Page p0 = createPage(0, 0, 0, 1);
		Page p1 = createPage(1, 1, 1, 1);
		Page p2 = createPage(2, 2, 2, 1);

		PageSet pageSet = new PageSet();
		pageSet.setFirst(p0);
		pageSet.setPrev(p0);
		pageSet.setCurrent(p1);
		pageSet.setNext(p2);
		pageSet.setLast(p2);
		pageSet.setPageSz(1);
		pageSet.setTotalCount(3);
		pageSet.setRange(Arrays.asList(p0, p1, p2));

		assertEquals(p0, pageSet.getFirst());
		assertEquals(p0, pageSet.getPrev());
		assertEquals(p1, pageSet.getCurrent());
		assertEquals(p2, pageSet.getNext());
		assertEquals(p2, pageSet.getLast());
		assertEquals(1L, pageSet.getPageSz());
		assertEquals(3L, pageSet.getTotalCount());
		assertEquals(3, pageSet.getRange().size());
		assertEquals(p0, pageSet.getRange().get(0));
		assertEquals(p1, pageSet.getRange().get(1));
		assertEquals(p2, pageSet.getRange().get(2));
	}

	@Test
	public void testToString() {

		Page p0 = createPage(0, 0, 0, 1);
		Page p1 = createPage(1, 1, 1, 1);
		Page p2 = createPage(2, 2, 2, 1);

		PageSet pageSet = new PageSet();
		pageSet.setFirst(p0);
		pageSet.setPrev(p0);
		pageSet.setCurrent(p1);
		pageSet.setNext(p2);
		pageSet.setLast(p2);
		pageSet.setPageSz(1);
		pageSet.setTotalCount(3);
		pageSet.setRange(Arrays.asList(p0, p1, p2));

		assertEquals(
				"PageSet[totalCount=3,pageSz=1,current=Page[no=1,count=1,from=1,to=1],prev=Page[no=0,count=1,from=0,to=0],next=Page[no=2,count=1,from=2,to=2],first=Page[no=0,count=1,from=0,to=0],last=Page[no=2,count=1,from=2,to=2],range=[Page[no=0,count=1,from=0,to=0], Page[no=1,count=1,from=1,to=1], Page[no=2,count=1,from=2,to=2]]]",
				pageSet.toString());
	}

	private Page createPage(long no, long from, long to, long count) {
		Page p = new Page();
		p.setNo(no);
		p.setFrom(from);
		p.setTo(to);
		p.setCount(count);
		return p;
	}

}
