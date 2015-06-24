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

package cherry.foundation.validator;

import static cherry.foundation.validator.CharType.Mode.Alpha;
import static cherry.foundation.validator.CharType.Mode.FullWidth;
import static cherry.foundation.validator.CharType.Mode.Lower;
import static cherry.foundation.validator.CharType.Mode.None;
import static cherry.foundation.validator.CharType.Mode.Numeric;
import static cherry.foundation.validator.CharType.Mode.Space;
import static cherry.foundation.validator.CharType.Mode.Upper;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class CharTypeValidatorTest {

	@Autowired
	private DataBinderHelper dataBinderHelper;

	@Test
	public void testOK() {

		Map<String, String> val = new HashMap<>();
		val.put("none", "");
		val.put("space", " \t\r\n");
		val.put("numeric", "0123456789");
		val.put("alpha", "ABCabc");
		val.put("upper", "ABC");
		val.put("lower", "abc");
		val.put("surrogate", "\uD842\uDF9F");
		val.put("cp932fw", "あいうえお");

		TestDto dto = new TestDto();

		BindingResult result = dataBinderHelper.bindAndValidate(dto, new MutablePropertyValues(val));
		assertThat(result.getErrorCount(), is(0));
		assertThat(dto.getSpace(), is(" \t\r\n"));
		assertThat(dto.getNumeric(), is("0123456789"));
		assertThat(dto.getAlpha(), is("ABCabc"));
		assertThat(dto.getUpper(), is("ABC"));
		assertThat(dto.getLower(), is("abc"));
		assertThat(dto.getSurrogate(), is("\uD842\uDF9F"));
		assertThat(dto.getCp932fw(), is("あいうえお"));
	}

	@Test
	public void testNG() {

		Map<String, String> val = new HashMap<>();
		val.put("none", "0");
		val.put("space", " \t\r\n0");
		val.put("numeric", "0123456789A");
		val.put("alpha", "ABCabc0");
		val.put("upper", "ABCa");
		val.put("lower", "abcA");
		val.put("cp932fw", "あいうえお\u30A0");

		TestDto dto = new TestDto();

		BindingResult result = dataBinderHelper.bindAndValidate(dto, new MutablePropertyValues(val));
		assertThat(result.getErrorCount(), is(7));
	}

	@Test
	public void testMisc() {
		assertThat(CharType.Mode.valueOf("BasicLatin"), is(CharType.Mode.BasicLatin));
	}

	@Getter
	@Setter
	public static class TestDto {

		@CharType(None)
		private String none;

		@CharType(Space)
		private String space;

		@CharType(Numeric)
		private String numeric;

		@CharType(Alpha)
		private String alpha;

		@CharType(Upper)
		private String upper;

		@CharType(Lower)
		private String lower;

		@CharType(value = None, acceptable = "\uD842\uDF9F" /* \u20B9F */)
		private String surrogate;

		@CharTypeCp932
		@CharType(FullWidth)
		private String cp932fw;
	}

}
