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

package cherry.spring.common.foundation.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.mail.SimpleMailMessage;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.mail.MessageStore;
import cherry.foundation.type.FlagCode;
import cherry.foundation.type.jdbc.RowMapperCreator;
import cherry.spring.common.db.gen.query.QMailLog;
import cherry.spring.common.db.gen.query.QMailQueue;
import cherry.spring.common.db.gen.query.QMailRcpt;

import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

public class MessageStoreImpl implements MessageStore {

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private RowMapperCreator rowMapperCreator;

	@Override
	public long createMessage(String launcherId, String messageName,
			LocalDateTime scheduledAt, String from, List<String> to,
			List<String> cc, List<String> bcc, String subject, String body) {
		long mailId = createMailLog(launcherId, bizDateTime.now(), messageName,
				scheduledAt, from, subject, body);
		createMailRcpt(mailId, "to", to);
		createMailRcpt(mailId, "cc", cc);
		createMailRcpt(mailId, "bcc", bcc);
		createMailQueue(mailId, scheduledAt);
		return mailId;
	}

	@Override
	public List<Long> listMessage(LocalDateTime dtm) {
		QMailQueue a = new QMailQueue("a");
		SQLQuery query = queryDslJdbcOperations.newSqlQuery();
		query.from(a);
		query.where(a.scheduledAt.goe(dtm));
		return queryDslJdbcOperations.query(query,
				new SingleColumnRowMapper<Long>(Long.class), a.mailId);
	}

	@Override
	public SimpleMailMessage getMessage(long messageId) {

		QMailLog a = new QMailLog("a");
		SQLQuery querya = queryDslJdbcOperations.newSqlQuery();
		querya.from(a).where(a.id.eq(messageId)).forUpdate();
		RowMapper<MailLog> rowMappera = rowMapperCreator.create(MailLog.class);
		MailLog maillog = queryDslJdbcOperations.queryForObject(querya,
				rowMappera, a.fromAddr, a.subject, a.body);
		if (maillog == null) {
			return null;
		}

		QMailRcpt b = new QMailRcpt("b");
		SQLQuery queryb = queryDslJdbcOperations.newSqlQuery();
		queryb.from(b).where(b.mailId.eq(messageId)).orderBy(b.id.asc());
		RowMapper<MailRcpt> rowMapperb = rowMapperCreator
				.create(MailRcpt.class);
		List<MailRcpt> mailrcpt = queryDslJdbcOperations.query(queryb,
				rowMapperb, b.rcptType, b.rcptAddr);
		if (mailrcpt.isEmpty()) {
			return null;
		}

		List<String> to = new ArrayList<>();
		List<String> cc = new ArrayList<>();
		List<String> bcc = new ArrayList<>();
		for (MailRcpt rcpt : mailrcpt) {
			switch (rcpt.getRcptType()) {
			case "to":
				to.add(rcpt.getRcptAddr());
				break;
			case "cc":
				cc.add(rcpt.getRcptAddr());
				break;
			case "bcc":
				bcc.add(rcpt.getRcptAddr());
				break;
			default:
				break;
			}
		}

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to.toArray(new String[to.size()]));
		msg.setCc(cc.toArray(new String[cc.size()]));
		msg.setBcc(bcc.toArray(new String[bcc.size()]));
		msg.setFrom(maillog.getFromAddr());
		msg.setSubject(maillog.getSubject());
		msg.setText(maillog.getBody());
		return msg;
	}

	@Override
	public void finishMessage(final long messageId) {

		final QMailLog a = new QMailLog("a");
		queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.mailStatus, FlagCode.TRUE.code());
				update.where(a.id.eq(messageId));
				return update.execute();
			}
		});

		final QMailQueue b = new QMailQueue("b");
		queryDslJdbcOperations.delete(b, new SqlDeleteCallback() {
			@Override
			public long doInSqlDeleteClause(SQLDeleteClause delete) {
				delete.where(b.mailId.eq(messageId));
				return delete.execute();
			}
		});
	}

	private long createMailLog(final String launcherId,
			final LocalDateTime launchedAt, final String messageName,
			final LocalDateTime scheduledAt, final String from,
			final String subject, final String body) {
		final QMailLog a = new QMailLog("a");
		return queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.launchedBy, launcherId);
				insert.set(a.launchedAt, launchedAt);
				insert.set(a.mailStatus, FlagCode.FALSE.code());
				insert.set(a.messageName, messageName);
				insert.set(a.scheduledAt, scheduledAt);
				insert.set(a.fromAddr, from);
				insert.set(a.subject, subject);
				insert.set(a.body, body);
				Long id = insert.executeWithKey(Long.class);
				return id.longValue();
			}
		});
	}

	private void createMailRcpt(final long mailId, final String rcptType,
			List<String> rcptAddr) {
		if (rcptAddr == null || rcptAddr.isEmpty()) {
			return;
		}
		final QMailRcpt a = new QMailRcpt("a");
		for (final String addr : rcptAddr) {
			queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
				@Override
				public long doInSqlInsertClause(SQLInsertClause insert) {
					insert.set(a.mailId, mailId);
					insert.set(a.rcptType, rcptType);
					insert.set(a.rcptAddr, addr);
					Long id = insert.executeWithKey(Long.class);
					return id.longValue();
				}
			});
		}
	}

	private void createMailQueue(final long mailId,
			final LocalDateTime scheduledAt) {
		final QMailQueue a = new QMailQueue("a");
		queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.mailId, mailId);
				insert.set(a.scheduledAt, scheduledAt);
				Long id = insert.executeWithKey(Long.class);
				return id.longValue();
			}
		});
	}

	@Setter
	@Getter
	@EqualsAndHashCode
	@ToString
	public static class MailLog {
		private String fromAddr;
		private String subject;
		private String body;
	}

	@Setter
	@Getter
	@EqualsAndHashCode
	@ToString
	private static class MailRcpt {
		private String rcptType;
		private String rcptAddr;
	}

}
