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

package cherry.example.web.applied.ex10;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.example.db.gen.query.QExTbl1;
import cherry.example.db.gen.query.QExTbl2;

import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.QBean;

@Service
public class AppliedEx10ServiceImpl implements AppliedEx10Service {

	@Autowired
	private SQLQueryFactory qf;

	private final QExTbl1 et1 = new QExTbl1("et1");
	private final QExTbl2 et2 = new QExTbl2("et2");

	@Transactional
	@Override
	public boolean exists(String text10) {
		return qf.from(et1).where(et1.text10.eq(text10)).exists();
	}

	@Transactional
	@Override
	public Long create(AppliedEx10Form form) {

		SQLInsertClause insert = qf.insert(et1);
		if (StringUtils.isNotEmpty(form.getText10())) {
			insert.set(et1.text10, form.getText10());
		}
		if (StringUtils.isNotEmpty(form.getText100())) {
			insert.set(et1.text100, form.getText100());
		}
		insert.set(et1.int64, form.getInt64());
		insert.set(et1.decimal1, form.getDecimal1());
		insert.set(et1.decimal3, form.getDecimal3());
		insert.set(et1.dt, form.getDt());
		insert.set(et1.tm, form.getTm());
		insert.set(et1.dtm, form.getDtm());
		Long id = insert.executeWithKey(et1.id);

		SQLInsertClause insertSub = qf.insert(et2);
		for (AppliedEx10SubForm sf : form.getItem()) {
			insertSub.set(et2.parentId, id);
			if (StringUtils.isNotEmpty(sf.getText10())) {
				insertSub.set(et2.text10, sf.getText10());
			}
			if (StringUtils.isNotEmpty(sf.getText100())) {
				insertSub.set(et2.text100, sf.getText100());
			}
			insertSub.set(et2.int64, sf.getInt64());
			insertSub.set(et2.decimal1, sf.getDecimal1());
			insertSub.set(et2.decimal3, sf.getDecimal3());
			insertSub.set(et2.dt, sf.getDt());
			insertSub.set(et2.tm, sf.getTm());
			insertSub.set(et2.dtm, sf.getDtm());
			insertSub.addBatch();
		}
		long count = insertSub.execute();
		if (count != form.getItem().size()) {
			throw new IllegalStateException();
		}

		return id;
	}

	@Transactional
	@Override
	public AppliedEx10Form findById(long id) {
		QBean<AppliedEx10Form> qb = new QBean<>(AppliedEx10Form.class, et1.text10, et1.text100, et1.int64,
				et1.decimal1, et1.decimal3, et1.dt, et1.tm, et1.dtm, et1.lockVersion);
		QBean<AppliedEx10SubForm> qbSub = new QBean<>(AppliedEx10SubForm.class, et2.text10, et2.text100, et2.int64,
				et2.decimal1, et2.decimal3, et2.dt, et2.tm, et2.dtm, et2.lockVersion);
		AppliedEx10Form form = qf.from(et1).where(et1.id.eq(id)).singleResult(qb);
		form.setItem(qf.from(et2).where(et2.parentId.eq(id)).list(qbSub));
		return form;
	}

	@Transactional
	@Override
	public boolean exists(long id, String text10) {
		return qf.from(et1).where(et1.id.ne(id), et1.text10.eq(text10)).exists();
	}

	@Transactional
	@Override
	public long update(long id, AppliedEx10Form form) {

		SQLUpdateClause update = qf.update(et1).where(et1.id.eq(id));
		update.where(et1.lockVersion.eq(form.getLockVersion())).set(et1.lockVersion, et1.lockVersion.add(1));
		if (StringUtils.isNotEmpty(form.getText10())) {
			update.set(et1.text10, form.getText10());
		}
		if (StringUtils.isNotEmpty(form.getText100())) {
			update.set(et1.text100, form.getText100());
		}
		update.set(et1.int64, form.getInt64());
		update.set(et1.decimal1, form.getDecimal1());
		update.set(et1.decimal3, form.getDecimal3());
		update.set(et1.dt, form.getDt());
		update.set(et1.tm, form.getTm());
		update.set(et1.dtm, form.getDtm());
		long count = update.execute();

		List<Long> subId = qf.from(et2).where(et2.parentId.eq(id)).orderBy(et2.id.asc()).list(et2.id);

		SQLUpdateClause updateSub = qf.update(et2);
		for (int rownum = 0; rownum < subId.size() && rownum < form.getItem().size(); rownum++) {
			AppliedEx10SubForm sf = form.getItem().get(rownum);
			updateSub.where(et2.id.eq(subId.get(rownum)));
			updateSub.where(et2.lockVersion.eq(sf.getLockVersion())).set(et2.lockVersion, et2.lockVersion.add(1));
			if (StringUtils.isNotEmpty(sf.getText10())) {
				updateSub.set(et2.text10, sf.getText10());
			}
			if (StringUtils.isNotEmpty(sf.getText100())) {
				updateSub.set(et2.text100, sf.getText100());
			}
			updateSub.set(et2.int64, sf.getInt64());
			updateSub.set(et2.decimal1, sf.getDecimal1());
			updateSub.set(et2.decimal3, sf.getDecimal3());
			updateSub.set(et2.dt, sf.getDt());
			updateSub.set(et2.tm, sf.getTm());
			updateSub.set(et2.dtm, sf.getDtm());
			updateSub.addBatch();
		}
		if (!subId.isEmpty() && !form.getItem().isEmpty()) {
			long cnt = updateSub.execute();
			if (cnt != Math.min(subId.size(), form.getItem().size())) {
				throw new IllegalStateException();
			}
		}

		SQLInsertClause insertSub = qf.insert(et2);
		for (int rownum = subId.size(); rownum < form.getItem().size(); rownum++) {
			AppliedEx10SubForm sf = form.getItem().get(rownum);
			insertSub.set(et2.parentId, id);
			if (StringUtils.isNotEmpty(sf.getText10())) {
				insertSub.set(et2.text10, sf.getText10());
			}
			if (StringUtils.isNotEmpty(sf.getText100())) {
				insertSub.set(et2.text100, sf.getText100());
			}
			insertSub.set(et2.int64, sf.getInt64());
			insertSub.set(et2.decimal1, sf.getDecimal1());
			insertSub.set(et2.decimal3, sf.getDecimal3());
			insertSub.set(et2.dt, sf.getDt());
			insertSub.set(et2.tm, sf.getTm());
			insertSub.set(et2.dtm, sf.getDtm());
			insertSub.addBatch();
		}
		if (form.getItem().size() > subId.size()) {
			long cnt = insertSub.execute();
			if (cnt != form.getItem().size() - subId.size()) {
				throw new IllegalStateException();
			}
		}

		SQLDeleteClause deleteSub = qf.delete(et2);
		for (int rownum = form.getItem().size(); rownum < subId.size(); rownum++) {
			deleteSub.where(et2.id.eq(subId.get(rownum)));
			deleteSub.addBatch();
		}
		if (subId.size() > form.getItem().size()) {
			long cnt = deleteSub.execute();
			if (cnt != subId.size() - form.getItem().size()) {
				throw new IllegalStateException();
			}
		}

		return count;
	}
}
