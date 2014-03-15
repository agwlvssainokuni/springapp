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

package cherry.spring.admin.app.service.secure.userman;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.lib.db.CsvDataConsumer;
import cherry.spring.common.lib.db.DataConsumer;
import cherry.spring.common.lib.db.DataExtractor;

@Component
public class UsermanExportServiceImpl implements UsermanExportService {

	public static final String REGISTERED_FROM = "registeredFrom";

	public static final String REGISTERED_TO = "registeredTo";

	@Autowired
	@Qualifier("usersExtractor")
	private DataExtractor usersExtractor;

	@Transactional
	@Override
	public long exportUsers(Writer writer, Date registeredFrom,
			Date registeredTo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put(REGISTERED_FROM, registeredFrom);
		paramMap.put(REGISTERED_TO, registeredTo);
		try {
			DataConsumer consumer = new CsvDataConsumer(writer, "\r\n", true);
			return usersExtractor.extract(consumer, paramMap);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
