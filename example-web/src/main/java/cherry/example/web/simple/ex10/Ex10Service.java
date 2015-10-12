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

package cherry.example.web.simple.ex10;

import cherry.example.db.gen.query.BExTbl1;

public interface Ex10Service {

	boolean exists(String text10);

	Long create(Ex10Form form);

	BExTbl1 findById(long id);

	Ex10Form findFormById(long id);

	boolean exists(long id, String text10);

	long update(long id, Ex10Form form);

}
