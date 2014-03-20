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

package cherry.spring.admin.app.service.secure.userman;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cherry.spring.admin.app.controller.secure.userman.UsermanSearchForm;
import cherry.spring.common.db.BaseDto;
import cherry.spring.common.db.gen.dto.Users;
import cherry.spring.common.lib.pager.PageSet;

public interface UsermanSearchService {

	@Transactional
	Result searchUsers(UsermanSearchForm form, int pageNo, int pageSz);

	public static class Result extends BaseDto {

		private static final long serialVersionUID = 1L;

		private PageSet pageSet;

		private List<Users> usersList;

		public PageSet getPageSet() {
			return pageSet;
		}

		public void setPageSet(PageSet pageSet) {
			this.pageSet = pageSet;
		}

		public List<Users> getUsersList() {
			return usersList;
		}

		public void setUsersList(List<Users> usersList) {
			this.usersList = usersList;
		}
	}

}
