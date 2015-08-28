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

package cherry.foundation.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
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
public class NumberScaleValidatorTest {

	@Autowired
	private DataBinderHelper dataBinderHelper;

	@Test
	public void testOK() {

		Map<String, String> val = new HashMap<>();
		val.put("number", null);
		val.put("decimal0", "123");
		val.put("decimal1", "123.4");
		val.put("decimal3", "123.456");
		val.put("double0", "123");
		val.put("double1", "123.4");
		val.put("double3", "123.456");
		val.put("float0", "123");
		val.put("float1", "123.4");
		val.put("float3", "123.456");
		val.put("int0", "123");
		val.put("int1", "123.4");
		val.put("int3", "123.456");

		TestDto dto = new TestDto();

		BindingResult result = dataBinderHelper.bindAndValidate(dto, new MutablePropertyValues(val));
		assertEquals(0, result.getErrorCount());
		assertNull(dto.getNumber());
		assertEquals(BigDecimal.valueOf(123L, 0), dto.getDecimal0());
		assertEquals(BigDecimal.valueOf(1234L, 1), dto.getDecimal1());
		assertEquals(BigDecimal.valueOf(123456L, 3), dto.getDecimal3());
		assertEquals(Double.valueOf(123.0), dto.getDouble0());
		assertEquals(Double.valueOf(123.4), dto.getDouble1());
		assertEquals(Double.valueOf(123.456), dto.getDouble3());
		assertEquals(Float.valueOf(123.0f), dto.getFloat0());
		assertEquals(Float.valueOf(123.4f), dto.getFloat1());
		assertEquals(Float.valueOf(123.456f), dto.getFloat3());
		assertEquals(Integer.valueOf(123), dto.getInt0());
		assertEquals(Integer.valueOf(123), dto.getInt1());
		assertEquals(Integer.valueOf(123), dto.getInt3());
	}

	@Test
	public void testNG() {

		Map<String, String> val = new HashMap<>();
		val.put("decimal0", "123.4");
		val.put("decimal1", "123.45");
		val.put("decimal3", "123.4567");
		val.put("double0", "123.4");
		val.put("double1", "123.45");
		val.put("double3", "123.4567");
		val.put("float0", "123.4");
		val.put("float1", "123.45");
		val.put("float3", "123.4567");

		TestDto dto = new TestDto();

		BindingResult result = dataBinderHelper.bindAndValidate(dto, new MutablePropertyValues(val));
		assertEquals(9, result.getErrorCount());
		assertEquals(BigDecimal.valueOf(1234L, 1), dto.getDecimal0());
		assertEquals(BigDecimal.valueOf(12345L, 2), dto.getDecimal1());
		assertEquals(BigDecimal.valueOf(1234567L, 4), dto.getDecimal3());
		assertEquals(Double.valueOf(123.4), dto.getDouble0());
		assertEquals(Double.valueOf(123.45), dto.getDouble1());
		assertEquals(Double.valueOf(123.4567), dto.getDouble3());
		assertEquals(Float.valueOf(123.4f), dto.getFloat0());
		assertEquals(Float.valueOf(123.45f), dto.getFloat1());
		assertEquals(Float.valueOf(123.4567f), dto.getFloat3());
	}

	@Getter
	@Setter
	public static class TestDto {
		@NumberScale()
		private BigDecimal number;
		@NumberScale(0)
		private BigDecimal decimal0;
		@NumberScale(1)
		private BigDecimal decimal1;
		@NumberScale(3)
		private BigDecimal decimal3;
		@NumberScale(0)
		private Double double0;
		@NumberScale(1)
		private Double double1;
		@NumberScale(3)
		private Double double3;
		@NumberScale(0)
		private Float float0;
		@NumberScale(1)
		private Float float1;
		@NumberScale(3)
		private Float float3;
		@NumberScale(0)
		private Integer int0;
		@NumberScale(1)
		private Integer int1;
		@NumberScale(3)
		private Integer int3;
	}

}
