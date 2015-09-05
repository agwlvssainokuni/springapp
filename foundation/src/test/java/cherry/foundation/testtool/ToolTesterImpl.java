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

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

import cherry.goods.log.Log;
import cherry.goods.log.LogFactory;

@Component
public class ToolTesterImpl implements ToolTester {

	private final Log log = LogFactory.getLog(getClass());

	@Override
	public void toBeInvoked0() {
		log.debug("method0");
	}

	@Override
	public long toBeInvoked1(long a, long b) {
		log.debug("method1");
		return a + b;
	}

	@Override
	public Long toBeInvoked2(Long a, Long b) {
		log.debug("method2");
		return submethod(a, b);
	}

	@Override
	public LocalDateTime toBeInvoked3(LocalDate dt, LocalTime tm) {
		log.debug("method3");
		return dt.toLocalDateTime(tm);
	}

	@Override
	public Dto1 toBeInvoked4(Dto1 a, Dto1 b) {
		log.debug("method4");
		return submethod(a, b);
	}

	@Override
	public Dto2 toBeInvoked5(Dto2 a, Dto2 b) {
		log.debug("method4");
		Dto2 c = new Dto2();
		c.setVal1(submethod(a.getVal1(), b.getVal1()));
		c.setVal2(submethod(a.getVal2(), b.getVal2()));
		return c;
	}

	@Override
	public long toBeInvoked6(long a, long b) {
		return a - b;
	}

	@Override
	public int toBeInvoked6(int a, int b) {
		return b - a;
	}

	@Override
	public Long toBeStubbed(Long p1, Long p2) {
		return Long.valueOf(p1.longValue() + p2.longValue());
	}

	@Override
	public BigDecimal toBeStubbed(BigDecimal p1, BigDecimal p2) {
		return p1.add(p2);
	}

	@Override
	public LocalDateTime toBeStubbed(LocalDate p1, LocalTime p2) {
		return p1.toLocalDateTime(p2);
	}

	private Long submethod(Long a, Long b) {
		if (a == null || b == null) {
			return null;
		}
		return Long.valueOf(a.longValue() + b.longValue());
	}

	private Dto1 submethod(Dto1 a, Dto1 b) {
		Dto1 c = new Dto1();
		c.setVal1(submethod(a.getVal1(), b.getVal1()));
		c.setVal2(submethod(a.getVal2(), b.getVal2()));
		return c;
	}

}
