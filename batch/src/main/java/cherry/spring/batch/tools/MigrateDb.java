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

package cherry.spring.batch.tools;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cherry.spring.batch.ExitStatus;
import cherry.spring.batch.IBatch;

/**
 * マイグレーションツール。
 */
@Component("migrateDb")
public class MigrateDb implements IBatch {

	@Autowired
	private DataSource dataSource;

	@Value("${batch.migrateDb.locations}")
	private String[] locations;

	@Value("${batch.migrateDb.encoding}")
	private String encoding;

	@Override
	public ExitStatus execute(String... args) {
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource);
		flyway.setLocations(locations);
		flyway.setEncoding(encoding);
		flyway.migrate();
		return ExitStatus.NORMAL;
	}

}
