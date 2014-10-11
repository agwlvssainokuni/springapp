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

package cherry.spring.admin.service.secure.userman;

import java.io.Writer;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import cherry.spring.admin.controller.secure.userman.UsermanSearchForm;
import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.lib.paginate.PageSet;

public interface UsermanSearchService {

	Result searchUsers(UsermanSearchForm form, int pageNo, int pageSz);

	long exportUsers(Writer writer, UsermanSearchForm form);

	@Getter
	@Setter
	@EqualsAndHashCode
	@ToString
	public static class Result {

		private PageSet pageSet;

		private List<User> usersList;
	}

}
