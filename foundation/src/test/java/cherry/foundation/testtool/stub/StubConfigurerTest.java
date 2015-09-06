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

package cherry.foundation.testtool.stub;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.util.InMemoryResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cherry.foundation.testtool.ToolTester;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext-test.xml")
public class StubConfigurerTest {

	@Autowired
	private StubRepository repository;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;

	@Autowired
	private ToolTester toolTester;

	private Method method;

	@Before
	public void before() throws NoSuchMethodException {
		method = ToolTester.class.getDeclaredMethod("toBeStubbed", Long.class, Long.class);
	}

	@After
	public void after() {
		for (Method m : repository.getStubbedMethod()) {
			repository.clear(m);
		}
	}

	@Test
	public void testConfigure1() {

		assertEquals(Long.valueOf(0L), toolTester.toBeStubbed(Long.valueOf(0L), Long.valueOf(0L)));

		String json = "{\"cherry.foundation.testtool.ToolTester\":{\"toBeStubbed(java.lang.Long,java.lang.Long)\":{\"data\":123}}}";
		Resource res = new InMemoryResource(json.getBytes(Charset.forName("UTF-8")));
		configure(asList(res));
		assertEquals(1, repository.getStubbedMethod().size());
		assertEquals(method, repository.getStubbedMethod().get(0));

		assertEquals(Long.valueOf(123L), toolTester.toBeStubbed(Long.valueOf(0L), Long.valueOf(0L)));
	}

	@Test
	public void testConfigure1_withType() {

		assertEquals(Long.valueOf(0L), toolTester.toBeStubbed(Long.valueOf(0L), Long.valueOf(0L)));

		String json = "{\"cherry.foundation.testtool.ToolTester\":{\"toBeStubbed(java.lang.Long,java.lang.Long)\":{\"data\":123,\"type\":\"java.lang.Long\"}}}";
		Resource res = new InMemoryResource(json.getBytes(Charset.forName("UTF-8")));
		configure(asList(res));
		assertEquals(1, repository.getStubbedMethod().size());
		assertEquals(method, repository.getStubbedMethod().get(0));

		assertEquals(Long.valueOf(123L), toolTester.toBeStubbed(Long.valueOf(0L), Long.valueOf(0L)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConfigure1_withInvalidType() {
		String json = "{\"cherry.foundation.testtool.ToolTester\":{\"toBeStubbed(java.lang.Long,java.lang.Long)\":{\"data\":123,\"type\":\"INVALID_TYPE\"}}}";
		Resource res = new InMemoryResource(json.getBytes(Charset.forName("UTF-8")));
		configure(asList(res));
	}

	@Test
	public void testConfigure2() {

		assertEquals(Long.valueOf(0L), toolTester.toBeStubbed(Long.valueOf(0L), Long.valueOf(0L)));

		String json = "{\"cherry.foundation.testtool.ToolTester\":{\"toBeStubbed(Long,Long)\":{\"data\":123}}}";
		Resource res = new InMemoryResource(json.getBytes(Charset.forName("UTF-8")));
		configure(asList(res));
		assertEquals(1, repository.getStubbedMethod().size());
		assertEquals(method, repository.getStubbedMethod().get(0));

		assertEquals(Long.valueOf(123L), toolTester.toBeStubbed(Long.valueOf(0L), Long.valueOf(0L)));
	}

	@Test
	public void testConfigure3() {

		assertEquals(Long.valueOf(0L), toolTester.toBeStubbed(Long.valueOf(0L), Long.valueOf(0L)));
		assertEquals(BigDecimal.ZERO, toolTester.toBeStubbed(BigDecimal.ZERO, BigDecimal.ZERO));
		assertEquals(new LocalDateTime(2015, 1, 1, 12, 34, 56),
				toolTester.toBeStubbed(new LocalDate(2015, 1, 1), new LocalTime(12, 34, 56)));

		String json = "{\"cherry.foundation.testtool.ToolTester\":{\"toBeStubbed\":{\"data\":123}}}";
		Resource res = new InMemoryResource(json.getBytes(Charset.forName("UTF-8")));
		configure(asList(res));
		assertEquals(3, repository.getStubbedMethod().size());
		for (Method m : ToolTester.class.getDeclaredMethods()) {
			if (m.getName().equals("toBeStubbed")) {
				assertTrue(repository.getStubbedMethod().contains(m));
			}
		}

		assertEquals(Long.valueOf(123L), toolTester.toBeStubbed(Long.valueOf(0L), Long.valueOf(0L)));
		assertEquals(BigDecimal.valueOf(123L), toolTester.toBeStubbed(BigDecimal.ZERO, BigDecimal.ZERO));
		assertEquals(new DateTime(123L).toLocalDateTime(),
				toolTester.toBeStubbed(new LocalDate(2015, 1, 1), new LocalTime(12, 34, 56)));
	}

	@Test
	public void testConfigure_NOMETHOD() {
		String json = "{\"cherry.foundation.testtool.ToolTester\":{\"NOT_EXIST\":{\"data\":123}}}";
		Resource res = new InMemoryResource(json.getBytes(Charset.forName("UTF-8")));
		configure(asList(res));
		assertTrue(repository.getStubbedMethod().isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConfigure_NGJSON() {
		String json = "NGJSON";
		Resource res = new InMemoryResource(json.getBytes(Charset.forName("UTF-8")));
		configure(asList(res));
	}

	@Test
	public void testConfigure_NORESOURCE() {
		configure(new ArrayList<Resource>());
		assertTrue(repository.getStubbedMethod().isEmpty());
	}

	@Test
	public void testConfigure_RESOURCENOTEXIST() {
		Resource res = new FileSystemResource("/not/exist");
		configure(asList(res));
		assertTrue(repository.getStubbedMethod().isEmpty());
	}

	private void configure(List<Resource> resources) {
		StubConfigurer cfg = new StubConfigurer();
		cfg.setRepository(repository);
		cfg.setObjectMapper(objectMapper);
		cfg.setResources(resources);
		cfg.configure();
	}

}
