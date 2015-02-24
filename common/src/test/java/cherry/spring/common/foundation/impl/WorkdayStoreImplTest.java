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

package cherry.spring.common.foundation.impl;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.workday.WorkdayStore;

import com.ibm.icu.text.MessageFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class WorkdayStoreImplTest {

	@Autowired
	private WorkdayStore workdayStore;

	@Test
	public void testGetNumberOfWorkday() {
		for (int i = 0; i < 50; i++) {
			LocalDate dt = LocalDate.now().plusDays(i);
			int numOfDays = workdayStore.getNumberOfWorkday("standard", LocalDate.now(), dt);
			System.out.println(MessageFormat.format("{0,date,yyyy/MM/dd} {1,number,0000}", dt.toDate(), numOfDays));
		}
	}

	@Test
	public void testGetNextWorkday() {
		for (int i = 1; i <= 50; i++) {
			LocalDate dt = workdayStore.getNextWorkday("standard", LocalDate.now(), i);
			System.out.println(MessageFormat.format("{0,number,0000} {1,date,yyyy/MM/dd}", i, dt.toDate()));
		}
	}

}
