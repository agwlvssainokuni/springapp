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

package cherry.spring.admin.service.secure.asyncproc;

import cherry.spring.common.db.gen.dto.AsyncProc;
import cherry.spring.common.lib.paginate.PagedList;

public interface AsyncProcService {

	PagedList<AsyncProc> searchAsyncProc(String loginId, long pageNo,
			long pageSz);

}
