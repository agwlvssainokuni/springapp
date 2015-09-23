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

package cherry.foundation.springmvc;

import static java.util.regex.Pattern.compile;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class RefererRequestMatcherTest {

	@Test
	public void testMatchesIfEmpty_TRUE() {
		RefererRequestMatcher m = new RefererRequestMatcher();
		m.setMatchesIfEmpty(true);
		assertTrue(m.matches(new MockHttpServletRequest()));
		assertTrue(m.matches(createRequest("")));
	}

	@Test
	public void testMatchesIfEmpty_FALSE() {
		RefererRequestMatcher m = new RefererRequestMatcher();
		m.setMatchesIfEmpty(false);
		assertFalse(m.matches(new MockHttpServletRequest()));
		assertFalse(m.matches(createRequest("")));
	}

	@Test
	public void testReferer() {
		RefererRequestMatcher m = new RefererRequestMatcher();
		m.setReferer(compile("^(http|https)://.*"));
		assertTrue(m.matches(createRequest("http://aaa/bbb?ccc#ddd")));
		assertTrue(m.matches(createRequest("https://aaa/bbb?ccc#ddd")));
		assertFalse(m.matches(createRequest("ftp://aaa/bbb?ccc#ddd")));
	}

	@Test
	public void testScheme() {
		RefererRequestMatcher m = new RefererRequestMatcher();
		m.setScheme(compile("^(http|https)$"));
		assertTrue(m.matches(createRequest("http://aaa/bbb?ccc#ddd")));
		assertTrue(m.matches(createRequest("https://aaa/bbb?ccc#ddd")));
		assertFalse(m.matches(createRequest("ftp://aaa/bbb?ccc#ddd")));
	}

	@Test
	public void testAuthority() {
		RefererRequestMatcher m = new RefererRequestMatcher();
		m.setAuthority(compile("^(aaa|bbb)(:80)?$"));
		assertTrue(m.matches(createRequest("http://aaa:80/bbb?ccc#ddd")));
		assertTrue(m.matches(createRequest("https://aaa/bbb?ccc#ddd")));
		assertTrue(m.matches(createRequest("http://bbb:80/aaa?ccc#ddd")));
		assertTrue(m.matches(createRequest("https://bbb/aaa?ccc#ddd")));
		assertFalse(m.matches(createRequest("http://aaaaaa/bbb?ccc#ddd")));
	}

	@Test
	public void testPath() {
		RefererRequestMatcher m = new RefererRequestMatcher();
		m.setPath(compile("^/(bbb|bbb/ccc|ccc)$"));
		assertTrue(m.matches(createRequest("http://aaa/bbb?ccc#ddd")));
		assertTrue(m.matches(createRequest("http://aaa/ccc?ccc#ddd")));
		assertTrue(m.matches(createRequest("http://aaa/bbb/ccc?ccc#ddd")));
		assertFalse(m.matches(createRequest("http://aaa/ccc/bbb?ccc#ddd")));
	}

	@Test
	public void testQuery() {
		RefererRequestMatcher m = new RefererRequestMatcher();
		m.setQuery(compile("^(ccc&ddd|ddd&ccc|ccc=ddd)$"));
		assertTrue(m.matches(createRequest("http://aaa/bbb?ccc&ddd#ddd")));
		assertTrue(m.matches(createRequest("http://aaa/bbb?ddd&ccc#ddd")));
		assertTrue(m.matches(createRequest("http://aaa/bbb?ccc=ddd#ddd")));
		assertFalse(m.matches(createRequest("http://aaa/bbb?ddd=ccc#ddd")));
	}

	@Test
	public void testFragment() {
		RefererRequestMatcher m = new RefererRequestMatcher();
		m.setFragment(compile("^(ddd|eee)$"));
		assertTrue(m.matches(createRequest("http://aaa/bbb?ccc#ddd")));
		assertTrue(m.matches(createRequest("http://aaa/bbb?ccc#eee")));
		assertFalse(m.matches(createRequest("http://aaa/bbb?ccc#dddeee")));
	}

	private MockHttpServletRequest createRequest(String ref) {
		MockHttpServletRequest r = new MockHttpServletRequest();
		r.addHeader("Referer", ref);
		return r;
	}

}
