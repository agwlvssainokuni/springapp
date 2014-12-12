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

package cherry.spring.common.helper.async;

import static com.google.common.base.Preconditions.checkState;
import static com.mysema.query.types.expr.DateTimeExpression.currentTimestamp;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcOperations;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;
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
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

public class AsyncProcessStoreImpl implements AsyncProcessStore {

	@Autowired
	private QueryDslJdbcOperations queryDslJdbcOperations;

	@Autowired
	private ObjectMapper objectMapper;

	@Transactional(value = "jtaTransactionManager", propagation = REQUIRES_NEW)
	@Override
	public long createFileProcess(String launcherId, LocalDateTime dtm,
			final String description, final String name,
			final String originalFilename, final String contentType,
			final long size, final String handlerName, String... args) {

		final long asyncId = createAsyncProcess(launcherId, description,
				AsyncType.FILE, dtm);

		final QAsyncProcessFile a = new QAsyncProcessFile("a");
		queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.asyncId, asyncId);
				insert.set(a.paramName, name);
				insert.set(a.originalFilename, originalFilename);
				insert.set(a.contentType, contentType);
				insert.set(a.fileSize, size);
				insert.set(a.handlerName, handlerName);
				Long id = insert.executeWithKey(Long.class);
				checkState(
						id != null,
						"failed to create QAsyncProcessFile: asyncId={0}, paramName={1}, originalFilename={2}, contentType={3}, fileSize={4}, handlerName={5}",
						asyncId, name, originalFilename, contentType, size,
						handlerName);
				return id.longValue();
			}
		});

		final QAsyncProcessFileArg b = new QAsyncProcessFileArg("b");
		for (final String arg : args) {
			queryDslJdbcOperations.insert(b, new SqlInsertCallback() {
				@Override
				public long doInSqlInsertClause(SQLInsertClause insert) {
					insert.set(b.asyncId, asyncId);
					insert.set(b.argument, arg);
					Long id = insert.executeWithKey(Long.class);
					checkState(
							id != null,
							"failed to create QAsyncProcessFileArg: asyncId={0}, arg={1}",
							asyncId, arg);
					return id.longValue();
				}
			});
		}

		return asyncId;
	}

	@Transactional(value = "jtaTransactionManager", propagation = REQUIRES_NEW)
	public long createCommand(String launcherId, LocalDateTime dtm,
			final String description, final String command, String... args) {

		final long asyncId = createAsyncProcess(launcherId, description,
				AsyncType.COMMAND, dtm);

		final QAsyncProcessCommand a = new QAsyncProcessCommand("a");
		queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.asyncId, asyncId);
				insert.set(a.command, command);
				Long id = insert.executeWithKey(Long.class);
				checkState(
						id != null,
						"failed to create QAsyncProcessCommand: asyncId={0}, command={1}",
						asyncId, command);
				return id.longValue();
			}
		});

		final QAsyncProcessCommandArg b = new QAsyncProcessCommandArg("b");
		for (final String arg : args) {
			queryDslJdbcOperations.insert(b, new SqlInsertCallback() {
				@Override
				public long doInSqlInsertClause(SQLInsertClause insert) {
					insert.set(b.asyncId, asyncId);
					insert.set(b.argument, arg);
					Long id = insert.executeWithKey(Long.class);
					checkState(
							id != null,
							"failed to create QAsyncProcessCommandArg: asyncId={0}, arg={1}",
							asyncId, arg);
					return id.longValue();
				}
			});
		}

		return asyncId;
	}

	@Transactional("jtaTransactionManager")
	public void updateToLaunched(final long asyncId, final LocalDateTime dtm) {
		final QAsyncProcess a = new QAsyncProcess("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.asyncStatus, AsyncStatus.LAUNCHED.code());
				update.set(a.launchedAt, dtm);
				update.set(a.updatedAt, currentTimestamp(LocalDateTime.class));
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq(asyncId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(
				count == 1,
				"failed to update QAsyncProcess: id={0}, asyncStatus={1}, launchedAt={2}, count={3}",
				asyncId, AsyncStatus.LAUNCHED.code(), dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void updateToProcessing(final long asyncId, final LocalDateTime dtm) {
		final QAsyncProcess a = new QAsyncProcess("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.asyncStatus, AsyncStatus.PROCESSING.code());
				update.set(a.startedAt, dtm);
				update.set(a.updatedAt, currentTimestamp(LocalDateTime.class));
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq(asyncId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(
				count == 1,
				"failed to update QAsyncProcess: id={0}, asyncStatus={1}, startedAt={2}, count={3}",
				asyncId, AsyncStatus.PROCESSING.code(), dtm, count);
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishFileProcess(final long asyncId, final LocalDateTime dtm,
			final AsyncStatus status, final FileProcessResult result) {

		finishAsyncProcess(asyncId, dtm, status);

		final QAsyncProcessFileResult a = new QAsyncProcessFileResult("a");
		queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.asyncId, asyncId);
				insert.set(a.totalCount, result.getTotalCount());
				insert.set(a.okCount, result.getOkCount());
				insert.set(a.ngCount, result.getNgCount());
				Long id = insert.executeWithKey(Long.class);
				checkState(
						id != null,
						"failed to create QAsyncProcessFileResult: asyncId={0}, totalCount={1}, okCount={2}, ngCount={3}",
						asyncId, result.getTotalCount(), result.getOkCount(),
						result.getNgCount());
				return id.longValue();
			}
		});

		final QAsyncProcessFileResultDetail b = new QAsyncProcessFileResultDetail(
				"b");
		List<FileRecordInfo> list = (result.getNgRecordInfoList() == null ? new ArrayList<FileRecordInfo>()
				: result.getNgRecordInfoList());
		for (final FileRecordInfo r : list) {
			if (r.isOk()) {
				continue;
			}
			queryDslJdbcOperations.insert(b, new SqlInsertCallback() {
				@Override
				public long doInSqlInsertClause(SQLInsertClause insert) {
					insert.set(b.asyncId, asyncId);
					insert.set(b.recordNumber, r.getNumber());
					insert.set(b.description, r.getDescription());
					Long id = insert.executeWithKey(Long.class);
					checkState(
							id != null,
							"failed to create QAsyncProcessFileResultDetail: asyncId={0}, recordNumber={1}, description={2}",
							asyncId, r.getNumber(), r.getDescription());
					return id.longValue();
				}
			});
		}
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishCommand(final long asyncId, final LocalDateTime dtm,
			final AsyncStatus status, final CommandResult result) {

		finishAsyncProcess(asyncId, dtm, status);

		final QAsyncProcessCommandResult a = new QAsyncProcessCommandResult("a");
		queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.asyncId, asyncId);
				insert.set(a.exitValue, result.getExitValue());
				insert.set(a.stdout, result.getStdout());
				insert.set(a.stderr, result.getStderr());
				Long id = insert.executeWithKey(Long.class);
				checkState(
						id != null,
						"failed to create QAsyncProcessCommandResult: asyncId={0}, exitValue={1}, stdout={2}, stderr={3}",
						asyncId, result.getExitValue(), result.getStdout(),
						result.getStderr());
				return id.longValue();
			}
		});
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void finishWithException(final long asyncId,
			final LocalDateTime dtm, final Throwable th) {

		finishAsyncProcess(asyncId, dtm, AsyncStatus.EXCEPTION);

		final QAsyncProcessException a = new QAsyncProcessException("a");
		queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.asyncId, asyncId);
				try {
					Map<String, Object> map = ToMapUtil.fromThrowable(th,
							Integer.MAX_VALUE);
					insert.set(a.exception,
							objectMapper.writeValueAsString(map));
				} catch (JsonProcessingException ex) {
					throw new IllegalStateException();
				}
				Long id = insert.executeWithKey(Long.class);
				checkState(
						id != null,
						"failed to create QAsyncProcessException: asyncId={0}, exception={1}",
						asyncId, th.getMessage());
				return id.longValue();
			}
		});
	}

	private long createAsyncProcess(final String launcherId,
			final String description, final AsyncType asyncType,
			final LocalDateTime dtm) {
		final QAsyncProcess a = new QAsyncProcess("a");
		return queryDslJdbcOperations.insert(a, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				insert.set(a.launchedBy, launcherId);
				insert.set(a.description, description);
				insert.set(a.asyncType, asyncType.code());
				insert.set(a.asyncStatus, AsyncStatus.LAUNCHING.code());
				insert.set(a.registeredAt, dtm);
				Long id = insert.executeWithKey(Long.class);
				checkState(
						id != null,
						"failed to create QAsyncProcess: launchedBy={0}, description={1}, asyncType={2}, asyncStatus={3}, registeredAt={4}",
						launcherId, description, asyncType.code(),
						AsyncStatus.LAUNCHING.code(), dtm);
				return id.longValue();
			}
		});
	}

	private void finishAsyncProcess(final long asyncId,
			final LocalDateTime dtm, final AsyncStatus status) {
		final QAsyncProcess a = new QAsyncProcess("a");
		long count = queryDslJdbcOperations.update(a, new SqlUpdateCallback() {
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				update.set(a.asyncStatus, status.code());
				update.set(a.finishedAt, dtm);
				update.set(a.updatedAt, currentTimestamp(LocalDateTime.class));
				update.set(a.lockVersion, a.lockVersion.add(1));
				update.where(a.id.eq(asyncId));
				update.where(a.deletedFlg.eq(DeletedFlag.NOT_DELETED.code()));
				return update.execute();
			}
		});
		checkState(
				count == 1,
				"failed to update QAsyncProcess: id={0}, asyncStatus={1}, finishedAt={2}, count={3}",
				asyncId, status.code(), dtm, count);
	}

}
