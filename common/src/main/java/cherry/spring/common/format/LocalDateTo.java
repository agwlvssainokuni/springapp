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

package cherry.spring.common.format;

import org.joda.time.LocalDate;
import org.joda.time.ReadablePeriod;

public class LocalDateTo extends ReadablePartialTo<LocalDate> {

	public LocalDateTo(LocalDate original, ReadablePeriod offset) {
		super(original, offset);
	}

	@Override
	public LocalDate getAdjusted() {
		return getOriginal().plus(getOffset());
	}

}
