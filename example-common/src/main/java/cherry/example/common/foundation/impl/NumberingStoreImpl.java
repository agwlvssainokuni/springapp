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

package cherry.example.common.foundation.impl;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;

import cherry.example.db.gen.query.QNumberingMaster;
import cherry.foundation.numbering.NumberingDefinition;
import cherry.foundation.numbering.NumberingStore;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.QBean;

public class NumberingStoreImpl implements NumberingStore {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QNumberingMaster nm = new QNumberingMaster("nm");

	@Override
	public NumberingDefinition getDefinition(String numberName) {
		List<NumberingDefinition> list = baseQuery(numberName).list(
				new QBean<>(NumberingDefinition.class, nm.template, nm.minValue, nm.maxValue));
		return DataAccessUtils.requiredSingleResult(list);
	}

	@Override
	public long loadAndLock(String numberName) {
		List<Long> list = baseQuery(numberName).forUpdate().list(nm.currentValue);
		return DataAccessUtils.requiredSingleResult(list).longValue();
	}

	@Override
	public void saveAndUnlock(final String numberName, final long current) {
		SQLUpdateClause update = queryFactory.update(nm);
		update.set(nm.currentValue, current);
		update.set(nm.lockVersion, nm.lockVersion.add(1));
		update.where(nm.name.eq(numberName));
		long count = update.execute();
		checkState(count == 1, "Failed to update %s: name=%s, currentValue=%s, count=%s", nm.getTableName(),
				numberName, current, count);
	}

	private SQLQuery baseQuery(String numberName) {
		SQLQuery query = queryFactory.from(nm);
		query.where(nm.name.eq(numberName));
		return query;
	}

}
