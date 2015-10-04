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

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cherry.common.db.gen.query.QMailTemplate;
import cherry.common.db.gen.query.QMailTemplateRcpt;
import cherry.foundation.mail.MailData;
import cherry.foundation.mail.TemplateStore;
import cherry.foundation.type.DeletedFlag;

import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

public class TemplateStoreImpl implements TemplateStore {

	@Autowired
	private SQLQueryFactory queryFactory;

	private final QMailTemplate mt = new QMailTemplate("mt");
	private final QMailTemplateRcpt mtr = new QMailTemplateRcpt("mtr");

	@Transactional
	@Override
	public MailData getTemplate(String templateName) {

		SQLQuery querya = queryFactory.from(mt);
		querya.where(mt.templateName.eq(templateName), mt.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		Tuple templ = querya.uniqueResult(mt.id, mt.fromAddr, mt.replyToAddr, mt.subject, mt.body);

		SQLQuery queryb = queryFactory.from(mtr);
		queryb.where(mtr.templateId.eq(templ.get(mt.id)), mtr.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		List<Tuple> templAddr = queryb.list(mtr.rcptType, mtr.rcptAddr);

		List<String> toAddr = new ArrayList<>();
		List<String> ccAddr = new ArrayList<>();
		List<String> bccAddr = new ArrayList<>();
		for (Tuple addr : templAddr) {
			RcptType type = RcptType.valueOf(addr.get(mtr.rcptType));
			if (type == RcptType.TO) {
				toAddr.add(addr.get(mtr.rcptAddr));
			}
			if (type == RcptType.CC) {
				ccAddr.add(addr.get(mtr.rcptAddr));
			}
			if (type == RcptType.BCC) {
				bccAddr.add(addr.get(mtr.rcptAddr));
			}
		}

		MailData template = new MailData();
		template.setFromAddr(templ.get(mt.fromAddr));
		template.setToAddr(toAddr);
		template.setCcAddr(ccAddr);
		template.setBccAddr(bccAddr);
		template.setReplyToAddr(templ.get(mt.replyToAddr));
		template.setSubject(templ.get(mt.subject));
		template.setBody(templ.get(mt.body));
		return template;
	}

	@Transactional
	@Override
	public void putTemplate(String templateName, MailData mailData) {

		SQLQuery querya = queryFactory.from(mt);
		querya.where(mt.templateName.eq(templateName), mt.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		querya.forUpdate();
		Long id = querya.uniqueResult(mt.id);
		if (id == null) {
			SQLInsertClause insert = queryFactory.insert(mt);
			insert.set(mt.templateName, templateName);
			insert.set(mt.fromAddr, mailData.getFromAddr());
			insert.set(mt.replyToAddr, mailData.getReplyToAddr());
			insert.set(mt.subject, mailData.getSubject());
			insert.set(mt.body, mailData.getBody());
			id = insert.executeWithKey(Long.class);
			checkState(id != 0, "Failed to create %s: %s", mt.getTableName(), mailData.toString());
		} else {
			SQLUpdateClause update = queryFactory.update(mt);
			update.set(mt.fromAddr, mailData.getFromAddr());
			update.set(mt.replyToAddr, mailData.getReplyToAddr());
			update.set(mt.subject, mailData.getSubject());
			update.set(mt.body, mailData.getBody());
			update.set(mt.lockVersion, mt.lockVersion.add(1));
			update.where(mt.id.eq(id));
			long count = update.execute();
			checkState(count == 1, "Failed to update %s: %s", mt.getTableName(), mailData.toString());
		}

		queryFactory.delete(mtr).where(mtr.templateId.eq(id)).execute();
		SQLInsertClause insert = queryFactory.insert(mtr);
		setupInsertClause(insert, mtr, id, RcptType.TO.name(), mailData.getToAddr());
		setupInsertClause(insert, mtr, id, RcptType.CC.name(), mailData.getCcAddr());
		setupInsertClause(insert, mtr, id, RcptType.BCC.name(), mailData.getBccAddr());
		long count = insert.execute();
		long numOfAddr = (mailData.getToAddr() == null ? 0L : mailData.getToAddr().size())
				+ (mailData.getCcAddr() == null ? 0L : mailData.getCcAddr().size())
				+ (mailData.getBccAddr() == null ? 0L : mailData.getBccAddr().size());
		checkState(count == numOfAddr, "Failed to create %s: %s", mtr.getTableName(), mailData.toString());
	}

	private void setupInsertClause(SQLInsertClause insert, QMailTemplateRcpt mtr, Long templateId, String type,
			List<String> list) {
		if (list != null) {
			for (String addr : list) {
				insert.set(mtr.templateId, templateId);
				insert.set(mtr.rcptType, type);
				insert.set(mtr.rcptAddr, addr);
				insert.addBatch();
			}
		}
	}

}
