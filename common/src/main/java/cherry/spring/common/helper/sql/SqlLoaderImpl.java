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

package cherry.spring.common.helper.sql;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import cherry.spring.common.lib.sql.SimpleSqlParser;

public class SqlLoaderImpl implements SqlLoader {

	@Value("common.helper.sqlloader.namePattern")
	private Pattern namePattern;

	@Value("common.helper.sqlloader.charset")
	private Charset charset;

	@Autowired
	private SimpleSqlParser simpleSqlParser;

	@Override
	public Map<String, String> load(Resource resource) {
		try (InputStream in = resource.getInputStream()) {
			return load(in);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Override
	public Map<String, String> load(InputStream in) {
		return load(new InputStreamReader(in, charset));
	}

	@Override
	public Map<String, String> load(Reader reader) {
		try {

			Map<String, String> sqlmap = new LinkedHashMap<>();

			String name;
			while ((name = nextName(reader)) != null) {
				String statement = nextStatement(reader);
				sqlmap.put(name, statement);
			}

			return sqlmap;
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private String nextName(Reader reader) throws IOException {
		String comment;
		while ((comment = simpleSqlParser.nextComment(reader)) != null) {
			Matcher matcher = namePattern.matcher(comment);
			if (matcher.matches()) {
				return matcher.group(0);
			}
		}
		return null;
	}

	private String nextStatement(Reader reader) throws IOException {
		return simpleSqlParser.nextStatement(reader);
	}

}
