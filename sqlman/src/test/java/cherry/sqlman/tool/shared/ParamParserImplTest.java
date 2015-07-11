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

package cherry.sqlman.tool.shared;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:config/applicationContext-sqlman-test.xml")
@Transactional
public class ParamParserImplTest {

	@Autowired
	private ParamParser paramParser;

	@Test
	public void testParseEmpty() {
		assertEquals(new LinkedHashMap<>(), paramParser.parseMap(null));
		assertEquals(new LinkedHashMap<>(), paramParser.parseMap(""));
	}

	@Test
	public void testParseStringValue() {
		Map<String, Object> expected = new LinkedHashMap<>();
		expected.put("name1", "value1");
		assertEquals(expected, paramParser.parseMap("name1: value1"));
	}

	@Test
	public void testParseIntegerValue() {
		Map<String, Object> expected = new LinkedHashMap<>();
		expected.put("name2", 1234);
		assertEquals(expected, paramParser.parseMap("name2: 1234"));
	}

	@Test
	public void testParseMixedValue() {
		Map<String, Object> expected = new LinkedHashMap<>();
		expected.put("name1", "value1");
		expected.put("name2", 1234);
		assertEquals(expected, paramParser.parseMap("name1: value1\nname2: 1234"));
	}

	@Test
	public void testParseOrder() {
		Map<String, ?> result1 = paramParser.parseMap("name1: value1\nname2: 1234");
		assertEquals("[name1, name2]", result1.keySet().toString());
		Map<String, ?> result2 = paramParser.parseMap("name2: 1234\nname1: value1");
		assertEquals("[name2, name1]", result2.keySet().toString());
	}

	@Test
	public void testIOException() throws Exception {
		ObjectMapper mapper = mock(ObjectMapper.class);
		when(mapper.readValue(anyString(), (JavaType) any())).thenThrow(new IOException());
		ParamParserImpl parser = new ParamParserImpl();
		ReflectionTestUtils.setField(parser, "objectMapper", mapper);

		Map<String, Object> expected = new LinkedHashMap<>();
		Map<String, ?> result = parser.parseMap("name1: value1");
		assertEquals(expected, result);
	}

}
