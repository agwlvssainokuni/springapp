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

import static cherry.foundation.querydsl.QueryDslUtil.adjustSize;
import static com.google.common.base.Preconditions.checkState;
import static com.mysema.query.types.expr.DateTimeExpression.currentTimestamp;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.async.AsyncProcessStore;
import cherry.foundation.async.AsyncStatus;
import cherry.foundation.async.AsyncType;
import cherry.foundation.async.FileProcessResult;
import cherry.foundation.async.FileRecordInfo;
import cherry.foundation.type.DeletedFlag;
import cherry.goods.command.CommandResult;
import cherry.goods.util.ToMapUtil;
import cherry.spring.common.db.gen.query.QAsyncProcess;
import cherry.spring.common.db.gen.query.QAsyncProcessCommand;
import cherry.spring.common.db.gen.query.QAsyncProcessCommandArg;
import cherry.spring.common.db.gen.query.QAsyncProcessCommandResult;
import cherry.spring.common.db.gen.query.QAsyncProcessException;
import cherry.spring.common.db.gen.query.QAsyncProcessFile;
import cherry.spring.common.db.gen.query.QAsyncProcessFileArg;
import cherry.spring.common.db.gen.query.QAsyncProcessFileResult;
import cherry.spring.common.db.gen.query.QAsyncProcessFileResultDetail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

public class AsyncProcessStoreImpl implements AsyncProcessStore {

	@Autowired
	private SQLQueryFactory queryFactory;

	@Autowired
	private ObjectMapper objectMapper;

	private final QAsyncProcess ap = new QAsyncProcess("ap");
	private final QAsyncProcessException ape = new QAsyncProcessException("ape");
	private final QAsyncProcessFile apf = new QAsyncProcessFile("apf");
	private final QAsyncProcessFileArg apfa = new QAsyncProcessFileArg("apfa");
	private final QAsyncProcessFileResult apfr = new QAsyncProcessFileResult("apfr");
	private final QAsyncProcessFileResultDetail apfrd = new QAsyncProcessFileResultDetail("apfrd");
	private final QAsyncProcessCommand apc = new QAsyncProcessCommand("apc");
	private final QAsyncProcessCommandArg apca = new QAsyncProcessCommandArg("apca");
	private final QAsyncProcessCommandResult apcr = new QAsyncProcessCommandResult("apcr");

	@Transactional(value = "jtaTransactionManager", propagation = REQUIRES_NEW)
	@Override
	public long createFileProcess(String launcherId, LocalDateTime dtm, String description, String name,
			String originalFilename, String contentType, long size, String handlerName, String... args) {

		long asyncId = createAsyncProcess(launcherId, description, AsyncType.FILE, dtm);

		SQLInsertClause insert = queryFactory.insert(apf);
		insert.set(apf.asyncId, asyncId);
		insert.set(apf.paramName, adjustSize(name, apf.paramName));
		insert.set(apf.originalFilename, adjustSize(originalFilename, apf.originalFilename));
		insert.set(apf.contentType, adjustSize(contentType, apf.contentType));
		insert.set(apf.fileSize, size);
		insert.set(apf.handlerName, adjustSize(handlerName, apf.handlerName));
		long count = insert.execute();
		checkState(
				count == 1L,
				"failed to create QAsyncProcessFile: asyncId=%s, paramName=%s, originalFilename=%s, contentType=%s, fileSize=%s, handlerName=%s",
				asyncId, name, originalFilename, contentType, size, handlerName);

		for (String arg : args) {
			long c = queryFactory.insert(apfa).set(apfa.asyncId, asyncId)
					.set(apfa.argument, adjustSize(arg, apfa.argument)).execute();
			checkState(c == 1L, "failed to create QAsyncProcessFileArg: asyncId=%s, arg=%s", asyncId, arg);
		}

		return asyncId;
	}

	@Transactional(value = "jtaTransactionManager", propagation = REQUIRES_NEW)
	public long createCommand(String launcherId, LocalDateTime dtm, String description, String command, String... args) {

		long asyncId = createAsyncProcess(launcherId, description, AsyncType.COMMAND, dtm);

		SQLInsertClause insert = queryFactory.insert(apc);
		insert.set(apc.asyncId, asyncId);
		insert.set(apc.command, adjustSize(command, apc.command));
		long count = insert.execute();
		checkState(count == 1L, "failed to create QAsyncProcessCommand: asyncId=%s, command=%s", asyncId, command);

		for (String arg : args) {
			long c = queryFactory.insert(apca).set(apca.asyncId, asyncId)
					.set(apca.argument, adjustSize(arg, apca.argument)).execute();
			checkState(c == 1L, "failed to create QAsyncProcessCommandArg: asyncId=%s, arg=%s", asyncId, arg);
		}

		return asyncId;
	}

	@Transactional("jtaTransactionManager")
	public void updateToLaunched(long asyncId, LocalDateTime dtm) {
		SQLUpdateClause update = queryFactory.update(ap);
		update.set(ap.asyncStatus, AsyncStatus.LAUNCHED.code());
		update.set(ap.launchedAt, dtm);
		update.set(ap.updatedAt, currentTimestamp(LocalDateTime.class));
		update.set(ap.lockVersion, ap.lockVersion.add(1));
		update.where(ap.id.eq(asyncId));
		update.where(ap.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		long count = update.execute();
		checkState(count == 1L, "failed to update QAsyncProcess: id=%s, asyncStatus=%s, launchedAt=%s, count=%s",
				asyncId, AsyncStatus.LAUNCHED.code(), dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void updateToProcessing(long asyncId, LocalDateTime dtm) {
		SQLUpdateClause update = queryFactory.update(ap);
		update.set(ap.asyncStatus, AsyncStatus.PROCESSING.code());
		update.set(ap.startedAt, dtm);
		update.set(ap.updatedAt, currentTimestamp(LocalDateTime.class));
		update.set(ap.lockVersion, ap.lockVersion.add(1));
		update.where(ap.id.eq(asyncId));
		update.where(ap.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		long count = update.execute();
		checkState(count == 1L, "failed to update QAsyncProcess: id=%s, asyncStatus=%s, startedAt=%s, count=%s",
				asyncId, AsyncStatus.PROCESSING.code(), dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishFileProcess(long asyncId, LocalDateTime dtm, AsyncStatus status, FileProcessResult result) {

		finishAsyncProcess(asyncId, dtm, status);

		SQLInsertClause insert = queryFactory.insert(apfr);
		insert.set(apfr.asyncId, asyncId);
		insert.set(apfr.totalCount, result.getTotalCount());
		insert.set(apfr.okCount, result.getOkCount());
		insert.set(apfr.ngCount, result.getNgCount());
		long count = insert.execute();
		checkState(count == 1L,
				"failed to create QAsyncProcessFileResult: asyncId=%s, totalCount=%s, okCount=%s, ngCount=%s", asyncId,
				result.getTotalCount(), result.getOkCount(), result.getNgCount());

		List<FileRecordInfo> list = (result.getNgRecordInfoList() == null ? new ArrayList<FileRecordInfo>() : result
				.getNgRecordInfoList());
		for (FileRecordInfo r : list) {
			if (r.isOk()) {
				continue;
			}
			long c = queryFactory.insert(apfrd).set(apfrd.asyncId, asyncId).set(apfrd.recordNumber, r.getNumber())
					.set(apfrd.description, adjustSize(r.getDescription(), apfrd.description)).execute();
			checkState(c == 1L,
					"failed to create QAsyncProcessFileResultDetail: asyncId=%s, recordNumber=%s, description=%s",
					asyncId, r.getNumber(), r.getDescription());
		}
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishCommand(long asyncId, LocalDateTime dtm, AsyncStatus status, CommandResult result) {

		finishAsyncProcess(asyncId, dtm, status);

		SQLInsertClause insert = queryFactory.insert(apcr);
		insert.set(apcr.asyncId, asyncId);
		insert.set(apcr.exitValue, result.getExitValue());
		insert.set(apcr.stdout, adjustSize(result.getStdout(), apcr.stdout));
		insert.set(apcr.stderr, adjustSize(result.getStderr(), apcr.stderr));
		long count = insert.execute();
		checkState(count == 1L,
				"failed to create QAsyncProcessCommandResult: asyncId=%s, exitValue=%s, stdout=%s, stderr=%s", asyncId,
				result.getExitValue(), result.getStdout(), result.getStderr());
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishWithException(long asyncId, LocalDateTime dtm, Throwable th) {

		finishAsyncProcess(asyncId, dtm, AsyncStatus.EXCEPTION);

		SQLInsertClause insert = queryFactory.insert(ape);
		insert.set(ape.asyncId, asyncId);
		insert.set(ape.exception, adjustSize(throwableToString(th), ape.exception));
		long count = insert.execute();
		checkState(count == 1L, "failed to create QAsyncProcessException: asyncId=%s, exception=%s", asyncId,
				th.getMessage());
	}

	private long createAsyncProcess(String launcherId, String description, AsyncType asyncType, LocalDateTime dtm) {
		SQLInsertClause insert = queryFactory.insert(ap);
		insert.set(ap.launchedBy, launcherId);
		insert.set(ap.description, adjustSize(description, ap.description));
		insert.set(ap.asyncType, asyncType.code());
		insert.set(ap.asyncStatus, AsyncStatus.LAUNCHING.code());
		insert.set(ap.registeredAt, dtm);
		Long id = insert.executeWithKey(Long.class);
		checkState(
				id != null,
				"failed to create QAsyncProcess: launchedBy=%s, description=%s, asyncType=%s, asyncStatus=%s, registeredAt=%s",
				launcherId, description, asyncType.code(), AsyncStatus.LAUNCHING.code(), dtm);
		return id.longValue();
	}

	private void finishAsyncProcess(long asyncId, LocalDateTime dtm, AsyncStatus status) {
		SQLUpdateClause update = queryFactory.update(ap);
		update.set(ap.asyncStatus, status.code());
		update.set(ap.finishedAt, dtm);
		update.set(ap.updatedAt, currentTimestamp(LocalDateTime.class));
		update.set(ap.lockVersion, ap.lockVersion.add(1));
		update.where(ap.id.eq(asyncId));
		update.where(ap.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
		long count = update.execute();
		checkState(count == 1L, "failed to update QAsyncProcess: id=%s, asyncStatus=%s, finishedAt=%s, count=%s",
				asyncId, status.code(), dtm, count);
	}

	private String throwableToString(Throwable th) {
		try {
			Map<String, Object> map = ToMapUtil.fromThrowable(th, Integer.MAX_VALUE);
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException ex) {
			throw new IllegalStateException();
		}
	}

}
