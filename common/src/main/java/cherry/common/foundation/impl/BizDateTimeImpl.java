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

package cherry.common.foundation.impl;

import static com.mysema.query.types.expr.DateTimeExpression.currentTimestamp;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cherry.common.db.gen.query.QBizdatetimeMaster;
import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.type.DeletedFlag;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.types.expr.DateTimeExpression;

public class BizDateTimeImpl implements BizDateTime {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QBizdatetimeMaster bm = new QBizdatetimeMaster("bm");

	@Transactional
	@Override
	public LocalDate today() {
		SQLQuery query = createSqlQuery(bm);
		LocalDate ldt = query.uniqueResult(bm.bizdate);
		if (ldt == null) {
			return LocalDate.now();
		}
		return ldt;
	}

	@Transactional
	@Override
	public LocalDateTime now() {
		DateTimeExpression<LocalDateTime> curDtm = currentTimestamp(LocalDateTime.class);
		SQLQuery query = createSqlQuery(bm);
		Tuple tuple = query.uniqueResult(curDtm, bm.offsetDay, bm.offsetHour, bm.offsetMinute, bm.offsetSecond);
		if (tuple == null) {
			return LocalDateTime.now();
		}
		return tuple.get(curDtm).plusDays(tuple.get(bm.offsetDay)).plusHours(tuple.get(bm.offsetHour))
				.plusMinutes(tuple.get(bm.offsetMinute)).plusSeconds(tuple.get(bm.offsetSecond));
	}

	private SQLQuery createSqlQuery(QBizdatetimeMaster bm) {
		return queryFactory.from(bm).where(bm.deletedFlg.eq(DeletedFlag.NOT_DELETED.code())).orderBy(bm.id.desc());
	}

}
