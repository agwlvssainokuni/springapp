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

package cherry.foundation.querydsl;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.ColumnMetadata;
import com.mysema.query.types.Path;

public class QueryDslUtil {

	public static String adjustSize(String value, Path<?> path) {
		if (value == null) {
			return value;
		}
		ColumnMetadata metadata = ColumnMetadata.getColumnMetadata(path);
		if (metadata.getSize() < 0) {
			return value;
		} else if (value.length() <= metadata.getSize()) {
			return value;
		} else {
			return value.substring(0, metadata.getSize() + 1);
		}
	}

	public static Map<String, ?> tupleToMap(Tuple tuple, Path<?>... paths) {
		Map<String, Object> map = new LinkedHashMap<>();
		for (Path<?> p : paths) {
			ColumnMetadata metadata = ColumnMetadata.getColumnMetadata(p);
			String name = UPPER_UNDERSCORE.to(LOWER_CAMEL, metadata.getName());
			map.put(name, tuple.get(p));
		}
		return map;
	}

	public static List<Map<String, ?>> tupleListToMapList(
			List<Tuple> tupleList, Path<?>... paths) {
		List<Map<String, ?>> list = new ArrayList<>(tupleList.size());
		for (Tuple tuple : tupleList) {
			list.add(tupleToMap(tuple, paths));
		}
		return list;
	}

}
