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

package cherry.common.testtool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cherry.foundation.testtool.stub.StubConfigurer;
import cherry.foundation.testtool.stub.StubRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.PatternFilenameFilter;

@Component
public class StubConfigLoader implements InitializingBean {

	@Value("/opt/springapp/stubdef")
	private File defDir;

	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper jsonObjectMapper;

	@Autowired
	@Qualifier("yamlObjectMapper")
	private ObjectMapper yamlObjectMapper;

	@Autowired
	private StubRepository repository;

	@Override
	public void afterPropertiesSet() throws IOException {
		File[] jsonfiles = defDir.listFiles(new PatternFilenameFilter(".*\\.json$"));
		configure(jsonfiles, jsonObjectMapper);
		File[] yamlfiles = defDir.listFiles(new PatternFilenameFilter(".*\\.yaml$"));
		configure(yamlfiles, yamlObjectMapper);
	}

	private void configure(File[] files, ObjectMapper mapper) throws IOException {
		List<Resource> list = new ArrayList<>();
		if (files != null) {
			for (File f : files) {
				list.add(new FileSystemResource(f));
			}
		}
		StubConfigurer cfg = new StubConfigurer();
		cfg.setObjectMapper(mapper);
		cfg.setResources(list);
		cfg.setRepository(repository);
		cfg.configure();
	}

}
