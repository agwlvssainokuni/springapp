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

package cherry.spring.common.custom.format;

import static cherry.spring.common.AppCtxUtil.getBean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.DataBinder;

import cherry.spring.common.custom.format.CustomDateTimeFormat.Range;

public class LocalDateTimeFormatTest {

	@Test
	public void testNone() throws BindException {
		String name = "attrNone";
		assertEquals("2014/01/01 00:00:00",
				parseAndPrint(name, "2014/01/01 00:00:00"));
		assertEquals("2014/01/01 01:01:01",
				parseAndPrint(name, "2014/1/1 1:1:1"));
		assertEquals("2014/01/01 00:00:00",
				parseAndPrint(name, "2014/01/01 00:00"));
		assertEquals("2014/01/01 01:01:00", parseAndPrint(name, "2014/1/1 1:1"));
		assertEquals("2014/01/01 00:00:00", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01 00:00:00", parseAndPrint(name, "2014/1/1"));
	}

	@Test
	public void testNoneReq() throws BindException {
		String name = "attrNoneReq";
		assertEquals("2014/01/01 00:00:00",
				parseAndPrint(name, "2014/01/01 00:00:00"));
		assertEquals("2014/01/01 01:01:01",
				parseAndPrint(name, "2014/1/1 1:1:1"));
		try {
			parseAndPrint(name, "2014/01/01 00:00");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/1/1 1:1");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/01/01");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/1/1");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testFrom() throws BindException {
		String name = "attrFrom";
		assertEquals("2014/01/01 00:00:00",
				parseAndPrint(name, "2014/01/01 00:00:00"));
		assertEquals("2014/01/01 01:01:01",
				parseAndPrint(name, "2014/1/1 1:1:1"));
		assertEquals("2014/01/01 00:00:00",
				parseAndPrint(name, "2014/01/01 00:00"));
		assertEquals("2014/01/01 01:01:00", parseAndPrint(name, "2014/1/1 1:1"));
		assertEquals("2014/01/01 00:00:00", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01 00:00:00", parseAndPrint(name, "2014/1/1"));
	}

	@Test
	public void testFromReq() throws BindException {
		String name = "attrFromReq";
		assertEquals("2014/01/01 00:00:00",
				parseAndPrint(name, "2014/01/01 00:00:00"));
		assertEquals("2014/01/01 01:01:01",
				parseAndPrint(name, "2014/1/1 1:1:1"));
		try {
			parseAndPrint(name, "2014/01/01 00:00");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/1/1 1:1");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/01/01");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/1/1");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	@Test
	public void testTo() throws BindException {
		String name = "attrTo";
		assertEquals("2014/01/01 23:59:59",
				parseAndPrint(name, "2014/01/01 23:59:59"));
		assertEquals("2014/01/01 01:01:01",
				parseAndPrint(name, "2014/1/1 1:1:1"));
		assertEquals("2014/01/01 00:00:59",
				parseAndPrint(name, "2014/01/01 00:00"));
		assertEquals("2014/01/01 01:01:59", parseAndPrint(name, "2014/1/1 1:1"));
		assertEquals("2014/01/01 23:59:59", parseAndPrint(name, "2014/01/01"));
		assertEquals("2014/01/01 23:59:59", parseAndPrint(name, "2014/1/1"));
	}

	@Test
	public void testToReq() throws BindException {
		String name = "attrToReq";
		assertEquals("2014/01/01 23:59:59",
				parseAndPrint(name, "2014/01/01 23:59:59"));
		assertEquals("2014/01/01 01:01:01",
				parseAndPrint(name, "2014/1/1 1:1:1"));
		try {
			parseAndPrint(name, "2014/01/01 00:00");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/1/1 1:1");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/01/01");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
		try {
			parseAndPrint(name, "2014/1/1");
			fail("Exception must be thrown");
		} catch (BindException ex) {
			// OK
		}
	}

	private String parseAndPrint(String name, String value)
			throws BindException {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put(name, value);

		Form form = new Form();
		DataBinder binder = new DataBinder(form, "target");
		binder.setConversionService(getBean(ConversionService.class));
		binder.bind(new MutablePropertyValues(paramMap));

		BindingResult binding = BindingResultUtils.getBindingResult(
				binder.close(), "target");
		return (String) binding.getFieldValue(name);
	}

	public static class Form {

		@CustomDateTimeFormat(value = Range.NONE)
		private LocalDateTime attrNone;

		@CustomDateTimeFormat(value = Range.NONE, optional = false)
		private LocalDateTime attrNoneReq;

		@CustomDateTimeFormat(value = Range.FROM)
		private LocalDateTime attrFrom;

		@CustomDateTimeFormat(value = Range.FROM, optional = false)
		private LocalDateTime attrFromReq;

		@CustomDateTimeFormat(value = Range.TO)
		private LocalDateTime attrTo;

		@CustomDateTimeFormat(value = Range.TO, optional = false)
		private LocalDateTime attrToReq;

		public LocalDateTime getAttrNone() {
			return attrNone;
		}

		public void setAttrNone(LocalDateTime attrNone) {
			this.attrNone = attrNone;
		}

		public LocalDateTime getAttrNoneReq() {
			return attrNoneReq;
		}

		public void setAttrNoneReq(LocalDateTime attrNoneReq) {
			this.attrNoneReq = attrNoneReq;
		}

		public LocalDateTime getAttrFrom() {
			return attrFrom;
		}

		public void setAttrFrom(LocalDateTime attrFrom) {
			this.attrFrom = attrFrom;
		}

		public LocalDateTime getAttrFromReq() {
			return attrFromReq;
		}

		public void setAttrFromReq(LocalDateTime attrFromReq) {
			this.attrFromReq = attrFromReq;
		}

		public LocalDateTime getAttrTo() {
			return attrTo;
		}

		public void setAttrTo(LocalDateTime attrTo) {
			this.attrTo = attrTo;
		}

		public LocalDateTime getAttrToReq() {
			return attrToReq;
		}

		public void setAttrToReq(LocalDateTime attrToReq) {
			this.attrToReq = attrToReq;
		}
	}

}