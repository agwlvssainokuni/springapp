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

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class ParamParserImpl implements ParamParser {

	@Autowired
	@Qualifier("yamlObjectMapper")
	private ObjectMapper objectMapper;

	@Override
	public Map<String, ?> parseMap(String pmap) {
		if (StringUtils.isBlank(pmap)) {
			return new LinkedHashMap<>();
		}
		try {
			JavaType type = TypeFactory.defaultInstance().constructMapType(LinkedHashMap.class, String.class,
					Object.class);
			return objectMapper.readValue(pmap, type);
		} catch (IOException ex) {
			return new LinkedHashMap<>();
		}
	}

}
