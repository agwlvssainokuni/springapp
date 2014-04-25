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

package cherry.spring.common.service;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.db.BaseDto;
import cherry.spring.common.db.gen.dto.AsyncProcs;
import cherry.spring.common.lib.pager.PageSet;

@Component
public interface AsyncProcService {

	@Transactional(propagation = REQUIRES_NEW)
	int createAsyncProc(String name);

	@Transactional(propagation = REQUIRES_NEW)
	void invokeAsyncProc(int id);

	@Transactional(propagation = REQUIRES_NEW)
	void startAsyncProc(int id);

	@Transactional(propagation = REQUIRES_NEW)
	void successAsyncProc(int id, String json);

	@Transactional(propagation = REQUIRES_NEW)
	void errorAsyncProc(int id, String json);

	@Transactional
	Result searchAsyncProc(int pageNo, int pageSz);

	public static class Result extends BaseDto {

		private static final long serialVersionUID = 1L;

		private PageSet pageSet;

		private List<AsyncProcs> asyncProcList;

		public PageSet getPageSet() {
			return pageSet;
		}

		public void setPageSet(PageSet pageSet) {
			this.pageSet = pageSet;
		}

		public List<AsyncProcs> getAsyncProcList() {
			return asyncProcList;
		}

		public void setAsyncProcList(List<AsyncProcs> asyncProcList) {
			this.asyncProcList = asyncProcList;
		}
	}

}
