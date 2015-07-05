/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.sqlman.secure.shared;

import cherry.goods.paginate.PagedList;
import cherry.sqlman.db.gen.query.BSqlMetadata;

public interface MetadataService {

	BSqlMetadata findById(int id, String loginId);

	int create(BSqlMetadata record);

	boolean update(BSqlMetadata record);

	boolean delete(int id, int lockVersion);

	PagedList<BSqlMetadata> search(MetadataCondition cond, long pageNo, long pageSz);

}
