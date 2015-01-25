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

package cherry.spring.common.foundation.impl;

import static com.google.common.base.Preconditions.checkState;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.mail.MailData;
import cherry.foundation.mail.TemplateStore;
import cherry.foundation.type.DeletedFlag;
import cherry.spring.common.db.gen.query.QMailTemplate;
import cherry.spring.common.db.gen.query.QMailTemplateRcpt;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.QTuple;

public class TemplateStoreImpl implements TemplateStore {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Transactional
	@Override
	public MailData getTemplate(String templateName) {

		QMailTemplate a = new QMailTemplate("a");
		SQLQuery querya = queryDslJdbcOperations.newSqlQuery();
		querya.from(a);
		querya.where(a.templateName.eq(templateName), a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		Tuple templ = queryDslJdbcOperations.queryForObject(querya, new QTuple(a.id, a.fromAddr, a.subject, a.body));

		QMailTemplateRcpt b = new QMailTemplateRcpt("b");
		SQLQuery queryb = queryDslJdbcOperations.newSqlQuery();
		queryb.from(b);
		queryb.where(b.templateId.eq(templ.get(a.id)), b.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		List<Tuple> templAddr = queryDslJdbcOperations.query(queryb, new QTuple(b.rcptType, b.rcptAddr));

		List<String> toAddr = new ArrayList<>();
		List<String> ccAddr = new ArrayList<>();
		List<String> bccAddr = new ArrayList<>();
		for (Tuple addr : templAddr) {
			RcptType type = RcptType.valueOf(addr.get(b.rcptType));
			if (type == RcptType.TO) {
				toAddr.add(addr.get(b.rcptAddr));
			}
			if (type == RcptType.CC) {
				ccAddr.add(addr.get(b.rcptAddr));
			}
			if (type == RcptType.BCC) {
				bccAddr.add(addr.get(b.rcptAddr));
			}
		}

		MailData template = new MailData();
		template.setFromAddr(templ.get(a.fromAddr));
		template.setToAddr(toAddr);
		template.setCcAddr(bccAddr);
		template.setBccAddr(bccAddr);
		template.setSubject(templ.get(a.subject));
		template.setBody(templ.get(a.body));
		return template;
	}

	@Transactional
	@Override
	public void putTemplate(final String templateName, final MailData mailData) {

		final QMailTemplate a = new QMailTemplate("a");
		SQLQuery querya = queryDslJdbcOperations.newSqlQuery();
		querya.from(a);
		querya.where(a.templateName.eq(templateName), a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		querya.forUpdate();
		Long id = queryDslJdbcOperations.queryForObject(querya, a.id);
		if (id == null) {
			id = queryDslJdbcOperations.insertWithKey(a, new SqlInsertWithKeyCallback<Long>() {
				@Override
				public Long doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					insert.set(a.templateName, templateName);
					insert.set(a.fromAddr, mailData.getFromAddr());
					insert.set(a.subject, mailData.getSubject());
					insert.set(a.body, mailData.getBody());
					return insert.executeWithKey(Long.class);
				}
			});
			checkState(id != 0, "Failed to create %s: %s", a.getTableName(), mailData.toString());
		} else {
			final Long fId = id;
			long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
				@Override
				public long doInSqlUpdateClause(SQLUpdateClause update) {
					update.set(a.fromAddr, mailData.getFromAddr());
					update.set(a.subject, mailData.getSubject());
					update.set(a.body, mailData.getBody());
					update.set(a.lockVersion, a.lockVersion.add(1));
					update.where(a.id.eq(fId));
					return update.execute();
				}
			});
			checkState(count == 1, "Failed to update %s: %s", a.getTableName(), mailData.toString());
		}

		final Long templateId = id;
		final QMailTemplateRcpt b = new QMailTemplateRcpt("b");
		queryDslJdbcOperations.delete(b, new SqlDeleteCallback() {
			@Override
			public long doInSqlDeleteClause(SQLDeleteClause delete) {
				delete.where(b.templateId.eq(templateId));
				return delete.execute();
			}
		});
		long count = queryDslJdbcOperations.insert(b, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				setupInsertClause(insert, b, templateId, RcptType.TO.name(), mailData.getToAddr());
				setupInsertClause(insert, b, templateId, RcptType.CC.name(), mailData.getCcAddr());
				setupInsertClause(insert, b, templateId, RcptType.BCC.name(), mailData.getBccAddr());
				return insert.execute();
			}
		});
		long numOfAddr = (mailData.getToAddr() == null ? 0L : mailData.getToAddr().size())
				+ (mailData.getCcAddr() == null ? 0L : mailData.getCcAddr().size())
				+ (mailData.getBccAddr() == null ? 0L : mailData.getBccAddr().size());
		checkState(count == numOfAddr, "Failed to create %s: %s", b.getTableName(), mailData.toString());
	}

	private void setupInsertClause(SQLInsertClause insert, QMailTemplateRcpt b, Long templateId, String type,
			List<String> list) {
		if (list != null) {
			for (String addr : list) {
				insert.set(b.templateId, templateId);
				insert.set(b.rcptType, type);
				insert.set(b.rcptAddr, addr);
				insert.addBatch();
			}
		}
	}

}
