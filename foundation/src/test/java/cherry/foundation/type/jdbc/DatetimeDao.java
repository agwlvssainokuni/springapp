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

package cherry.foundation.type.jdbc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import cherry.foundation.db.gen.dto.VerifyDatetime;
import cherry.foundation.sql.SqlLoader;
import cherry.goods.masker.Masker;
import cherry.goods.masker.SqlDateMasker;
import cherry.goods.masker.SqlTimeMasker;
import cherry.goods.masker.SqlTimestampMasker;

@Component
public class DatetimeDao implements InitializingBean {

	@Autowired
	private NamedParameterJdbcOperations namedParameterJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Autowired
	private SqlParameterSourceCreator sqlParameterSourceCreator;

	@Autowired
	private SqlLoader sqlLoader;

	private String selectAllSql;

	private String insertSql;

	@Override
	public void afterPropertiesSet() throws IOException {
		Map<String, String> sqlmap = sqlLoader.load(getClass());
		selectAllSql = sqlmap.get("selectAll");
		insertSql = sqlmap.get("insert");
	}

	public List<VerifyDatetime> selectAll() {
		return namedParameterJdbcOperations.query(selectAllSql, rowMapperCreator.create(VerifyDatetime.class));
	}

	public List<VerifyDatetime> selectAllWithMask() {
		Map<String, Masker<?>> maskerMap = new HashMap<>();
		maskerMap.put("dt", SqlDateMasker.newMasker(new LocalDate(2000, 1, 1), true, true, true));
		maskerMap.put("tm", SqlTimeMasker.newMasker(new LocalTime(0, 0, 0), true, true, true));
		maskerMap.put("dtm", SqlTimestampMasker.newMasker(new LocalDateTime(2000, 1, 1, 0, 0, 0), true, true, true,
				true, true, true));
		return namedParameterJdbcOperations.query(selectAllSql,
				rowMapperCreator.create(VerifyDatetime.class, maskerMap));
	}

	public int insert(VerifyDatetime record, KeyHolder keyHolder) {
		return namedParameterJdbcOperations.update(insertSql, sqlParameterSourceCreator.create(record), keyHolder);
	}

}
