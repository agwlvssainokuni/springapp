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

package cherry.foundation.testtool;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public interface ToolTester {

	void method0();

	long method1(long a, long b);

	Long method2(Long a, Long b);

	LocalDateTime method3(LocalDate dt, LocalTime tm);

	Dto1 method4(Dto1 a, Dto1 b);

	Dto2 method5(Dto2 a, Dto2 b);

	long method6(long a, long b);

	int method6(int a, int b);

	@Setter
	@Getter
	@ToString
	public static class Dto1 {
		private Long val1;
		private Long val2;
	}

	@Setter
	@Getter
	@ToString
	public static class Dto2 {
		private Dto1 val1;
		private Dto1 val2;
	}

}
