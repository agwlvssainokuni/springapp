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

package cherry.spring.common.custom;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import cherry.spring.common.custom.CodeUtil.CodeMap;

public class CodeUtilTest {

	@Test
	public void testGetCodeMapNoDefault() {
		CodeMap<Integer, FlagCode> codeMap = CodeUtil.getCodeMap(
				FlagCode.class, null);
		for (int i = -1024; i <= 1024; i++) {
			switch (i) {
			case 0:
				assertThat(codeMap.get(i), is(FlagCode.FALSE));
				break;
			case 1:
				assertThat(codeMap.get(i), is(FlagCode.TRUE));
				break;
			default:
				try {
					codeMap.get(i);
					fail("Exception must be thrown");
				} catch (IllegalArgumentException ex) {
					// OK
				}
				break;
			}
		}
	}

	@Test
	public void testGetCodeMapDefaultFALSE() {
		CodeMap<Integer, FlagCode> codeMap = CodeUtil.getCodeMap(
				FlagCode.class, FlagCode.FALSE);
		for (int i = -1024; i <= 1024; i++) {
			switch (i) {
			case 1:
				assertThat(codeMap.get(i), is(FlagCode.TRUE));
				break;
			default:
				assertThat(codeMap.get(i), is(FlagCode.FALSE));
				break;
			}
		}
	}

	@Test
	public void testGetMapThrowsException() {
		try {
			CodeUtil.getMap(DeletedFlag.class);
			fail("Exception must be thrown");
		} catch (IllegalArgumentException ex) {
			// OK
		}
	}

}
