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

function paginator(strategy) {

	function getPageCount(itemCount, pageSize) {
		if (itemCount % pageSize == 0) {
			return itemCount / pageSize;
		} else {
			return itemCount / pageSize + 1;
		}
	}

	function adjustPageNo(pageNo, pageCount) {
		var adjusted = pageNo;
		if (adjusted >= pageCount) {
			adjusted = pageCount - 1;
		}
		if (adjusted < 0) {
			adjusted = 0;
		}
		return adjusted;
	}

	function createPage(pageNo, pageCount, itemCount, pageSize) {
		var count;
		if (itemCount <= 0) {
			count = 0;
		} else if (pageNo < pageCount - 1) {
			count = pageSize;
		} else if (itemCount % pageSize == 0) {
			count = pageSize;
		} else {
			count = itemCount % pageSize;
		}
		return {
			no : pageNo,
			count : count,
			from : pageSize * pageNo,
			to : (pageSize * pageNo) + (count - 1),
		};
	}

	return function(pageNo, itemCount, pageSize) {

		var pageCount = getPageCount(itemCount, pageSize);
		var curNo = adjustPageNo(pageNo, pageCount);
		var prevNo = adjustPageNo(curNo - 1, pageCount);
		var nextNo = adjustPageNo(curNo + 1, pageCount);
		var firstNo = adjustPageNo(0, pageCount);
		var lastNo = adjustPageNo(pageCount - 1, pageCount);

		var pageSet = {};
		pageSet.range = [];
		var nos = strategy(curNo, pageCount);
		for (var i = 0; i < nos.length; i++) {
			var no = nos[i];
			var page = createPage(no, pageCount, itemCount, pageSize);
			pageSet.range.push(page);

			if (no == curNo) {
				pageSet.current = page;
			}
			if (no == prevNo) {
				pageSet.prev = page;
			}
			if (no == nextNo) {
				pageSet.next = page;
			}
			if (no == firstNo) {
				pageSet.first = page;
			}
			if (no == lastNo) {
				pageSet.last = page;
			}
		}

		if (pageSet.current == undefined) {
			pageSet.current = createPage(curNo, pageCount, itemCount, pageSize);
		}
		if (pageSet.prev == undefined) {
			pageSet.prev = createPage(prevNo, pageCount, itemCount, pageSize);
		}
		if (pageSet.next == undefined) {
			pageSet.next = createPage(nextNo, pageCount, itemCount, pageSize);
		}
		if (pageSet.first == undefined) {
			pageSet.first = createPage(firstNo, pageCount, itemCount, pageSize);
		}
		if (pageSet.last == undefined) {
			pageSet.last = createPage(lastNo, pageCount, itemCount, pageSize);
		}

		return pageSet;
	};
}

function TotalCountStrategy(totalCount, lowerSideHint, lowerTrim, upperTrim) {
	return function(pageNo, pageCount) {
		var from = pageNo - lowerSideHint;
		if (from <= lowerTrim) {
			from = lowerTrim;
		}
		var to = from + (totalCount - 1);
		if (to >= (pageCount - 1) - upperTrim) {
			to = (pageCount - 1) - upperTrim;
			from = to - (totalCount - 1);
			if (from <= lowerTrim) {
				from = lowerTrim;
			}
		}
		var range = [];
		for (var no = from; no <= to; no++) {
			range.push(no);
		}
		return range;
	};
}
