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

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.mysema.query.types.path.BooleanPath;
import com.mysema.query.types.path.DatePath;
import com.mysema.query.types.path.DateTimePath;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.path.TimePath;

public class PathBuilderUtil {

	public static <T> PathBuilder<T> pathbuilder(Class<? extends T> type,
			String variable) {
		return new PathBuilder<T>(type, variable);
	}

	public static BooleanPath booleanPath(PathBuilder<?> path, String property) {
		return path.getBoolean(property);
	}

	public static NumberPath<Integer> intPath(PathBuilder<?> path,
			String property) {
		return path.getNumber(property, Integer.class);
	}

	public static NumberPath<Long> longPath(PathBuilder<?> path, String property) {
		return path.getNumber(property, Long.class);
	}

	public static NumberPath<BigDecimal> decimalPath(PathBuilder<?> path,
			String property) {
		return path.getNumber(property, BigDecimal.class);
	}

	public static StringPath stringPath(PathBuilder<?> path, String property) {
		return path.getString(property);
	}

	public static DatePath<LocalDate> datePath(PathBuilder<?> path,
			String property) {
		return path.getDate(property, LocalDate.class);
	}

	public static TimePath<LocalTime> timePath(PathBuilder<?> path,
			String property) {
		return path.getTime(property, LocalTime.class);
	}

	public static DateTimePath<LocalDateTime> dateTimePath(PathBuilder<?> path,
			String property) {
		return path.getDateTime(property, LocalDateTime.class);
	}

}
