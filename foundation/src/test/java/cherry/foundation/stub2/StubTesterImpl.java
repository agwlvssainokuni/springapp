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

package cherry.foundation.stub2;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

@Component
public class StubTesterImpl implements StubTester {

	@Override
	public Long method(Long p1, Long p2) {
		return Long.valueOf(p1.longValue() + p2.longValue());
	}

	@Override
	public BigDecimal method(BigDecimal p1, BigDecimal p2) {
		return p1.add(p2);
	}

	@Override
	public LocalDateTime method(LocalDate p1, LocalTime p2) {
		return p1.toLocalDateTime(p2);
	}

}
