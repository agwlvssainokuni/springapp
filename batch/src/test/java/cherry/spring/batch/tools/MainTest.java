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

package cherry.spring.batch.tools;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cherry.foundation.batch.ExitStatus;

public class MainTest {

	@Before
	public void before() {
		System.setProperty("batch.appCtx", "classpath:config/applicationContext-test.xml");
	}

	@Test
	public void testNormal() {
		assertEquals(ExitStatus.NORMAL, Main.doMain("test03Batch", "NORMAL"));
	}

	@Test
	public void testNormalWithJobId() {
		assertEquals(ExitStatus.NORMAL, Main.doMain("-j", "JOB01", "test03Batch", "NORMAL"));
	}

	@Test
	public void testNormalWithHelp() {
		assertEquals(ExitStatus.NORMAL, Main.doMain("-h"));
	}

	@Test
	public void testFatalWithoutArgs() {
		assertEquals(ExitStatus.FATAL, Main.doMain());
	}

	@Test
	public void testFatalWithInvalidArgs() {
		assertEquals(ExitStatus.FATAL, Main.doMain("--invalid"));
	}

}
