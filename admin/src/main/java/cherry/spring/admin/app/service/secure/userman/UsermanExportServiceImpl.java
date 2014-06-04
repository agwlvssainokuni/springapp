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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.lib.etl.Consumer;
import cherry.spring.common.lib.etl.CsvConsumer;
import cherry.spring.common.lib.etl.Extractor;
import cherry.spring.common.lib.etl.NoneLimiter;

@Component
public class UsermanExportServiceImpl implements UsermanExportService {

	public static final String REGISTERED_FROM = "registeredFrom";

	public static final String REGISTERED_TO = "registeredTo";

	@Autowired
	@Qualifier("usermanExportSql")
	private String usermanExportSql;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Extractor extractor;

	@Transactional
	@Override
	public int exportUsers(Writer writer, Date registeredFrom, Date registeredTo) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put(REGISTERED_FROM, registeredFrom);
		paramMap.put(REGISTERED_TO, registeredTo);
		try {
			Consumer consumer = new CsvConsumer(writer, "\r\n", true);
			return extractor.extract(dataSource, usermanExportSql, paramMap,
					consumer, new NoneLimiter());
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

}
