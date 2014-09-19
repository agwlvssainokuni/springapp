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

package cherry.spring.common.lib.mask;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IntegerMaskerTest {

	@Test
	public void testLowerDigitMasker() {
		IntegerMasker masker = IntegerMasker.lowerDigitMasker(999999, 2);
		assertThat(masker, is(notNullValue()));
		assertThat(masker.mask(null), is(nullValue(Integer.class)));
		assertThat(masker.mask(0), is(0));
		assertThat(masker.mask(1), is(1));
		assertThat(masker.mask(12), is(12));
		assertThat(masker.mask(123), is(923));
		assertThat(masker.mask(1234), is(9934));
		assertThat(masker.mask(12345), is(99945));
		assertThat(masker.mask(123456), is(999956));
		assertThat(masker.mask(1234567), is(999967));
		assertThat(masker.mask(12345678), is(999978));
		assertThat(masker.mask(-1), is(-1));
		assertThat(masker.mask(-12), is(-12));
		assertThat(masker.mask(-123), is(-923));
		assertThat(masker.mask(-1234), is(-9934));
		assertThat(masker.mask(-12345), is(-99945));
		assertThat(masker.mask(-123456), is(-999956));
		assertThat(masker.mask(-1234567), is(-999967));
		assertThat(masker.mask(-12345678), is(-999978));
	}

}
