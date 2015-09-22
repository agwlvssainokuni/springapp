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

package cherry.foundation.springmvc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RefererRequestMatcher implements RequestMatcher {

	private final Pattern uriPattern = Pattern.compile("^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?");

	private boolean matchesIfEmpty = false;

	private boolean matchesIfInvalid = false;

	private Pattern referer = null;

	private Pattern scheme = null;

	private Pattern authority = null;

	private Pattern path = null;

	private Pattern query = null;

	private Pattern fragment = null;

	public void setMatchesIfEmpty(boolean matchesIfEmpty) {
		this.matchesIfEmpty = matchesIfEmpty;
	}

	public void setMatchesIfInvalid(boolean matchesIfInvalid) {
		this.matchesIfInvalid = matchesIfInvalid;
	}

	public void setReferer(Pattern referer) {
		this.referer = referer;
	}

	public void setScheme(Pattern scheme) {
		this.scheme = scheme;
	}

	public void setAuthority(Pattern authority) {
		this.authority = authority;
	}

	public void setPath(Pattern path) {
		this.path = path;
	}

	public void setQuery(Pattern query) {
		this.query = query;
	}

	public void setFragment(Pattern fragment) {
		this.fragment = fragment;
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		String ref = request.getHeader("referer");
		if (StringUtils.isEmpty(ref)) {
			return matchesIfEmpty;
		}
		Matcher m = uriPattern.matcher(ref);
		if (!m.matches()) {
			return matchesIfInvalid;
		}
		if (!verify(referer, ref)) {
			return false;
		}
		if (!verify(scheme, m.group(2))) {
			return false;
		}
		if (!verify(authority, m.group(4))) {
			return false;
		}
		if (!verify(path, m.group(5))) {
			return false;
		}
		if (!verify(query, m.group(7))) {
			return false;
		}
		if (!verify(fragment, m.group(9))) {
			return false;
		}
		return true;
	}

	private boolean verify(Pattern p, String s) {
		if (p == null) {
			return true;
		}
		Matcher m = p.matcher(s);
		return m.find();
	}

}
