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

package cherry.example.web.applied.ex50;

import static com.mysema.query.support.Expressions.cases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionOperations;

import cherry.example.db.gen.query.QExTbl1;

import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.QBean;
import com.mysema.query.types.expr.CaseBuilder.Cases;
import com.mysema.query.types.expr.NumberExpression;

@Service
public class AppliedEx51ServiceImpl implements AppliedEx51Service {

	@Autowired
	private SQLQueryFactory qf;

	@Autowired
	private TransactionOperations txOps;

	private final QExTbl1 et1 = new QExTbl1("et1");

	@Transactional
	@Override
	public List<AppliedEx51SubForm> search(List<Long> id) {
		return qf
				.from(et1)
				.where(et1.id.in(id))
				.orderBy(orderBy(id))
				.list(new QBean<>(AppliedEx51SubForm.class, et1.id, et1.text10, et1.int64, et1.decimal1, et1.decimal3,
						et1.dt, et1.tm, et1.dtm, et1.lockVersion));
	}

	@Override
	public long update(final AppliedEx51Form form) {
		return txOps.execute(new TransactionCallback<Long>() {
			@Override
			public Long doInTransaction(TransactionStatus status) {
				SQLUpdateClause update = qf.update(et1);
				for (AppliedEx51SubForm sf : form.getItem()) {
					update.where(et1.id.eq(sf.getId()), et1.lockVersion.eq(sf.getLockVersion()));
					update.set(et1.lockVersion, et1.lockVersion.add(1));
					update.set(et1.int64, sf.getInt64()).set(et1.decimal1, sf.getDecimal1())
							.set(et1.decimal3, sf.getDecimal3());
					if (form.getDt() != null) {
						update.set(et1.dt, form.getDt());
					}
					if (form.getTm() != null) {
						update.set(et1.tm, form.getTm());
					}
					if (form.getDtm() != null) {
						update.set(et1.dtm, form.getDtm());
					}
					update.addBatch();
				}
				long count = update.execute();
				if (count != form.getItem().size()) {
					status.setRollbackOnly();
				}
				return count;
			}
		});
	}

	private OrderSpecifier<Integer> orderBy(List<Long> id) {
		Cases<Integer, NumberExpression<Integer>> cases = null;
		for (int i = 0; i < id.size(); i++) {
			if (cases == null) {
				cases = cases().when(et1.id.eq(id.get(i))).then(i);
			} else {
				cases = cases.when(et1.id.eq(id.get(i))).then(i);
			}
		}
		return cases.otherwise(id.size()).asc();
	}

}
