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

package cherry.spring.admin.app.service.secure.userman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cherry.spring.common.helper.JsonHelper;
import cherry.spring.common.lib.etl.CsvProvider;
import cherry.spring.common.lib.etl.LimiterException;
import cherry.spring.common.lib.etl.LoadResult;
import cherry.spring.common.lib.etl.Loader;
import cherry.spring.common.lib.etl.NoneLimiter;
import cherry.spring.common.log.Log;
import cherry.spring.common.log.LogFactory;
import cherry.spring.common.service.AsyncProcStatusService;

@Component
public class UsermanImportServiceImpl implements UsermanImportService {

	public static final String PROC_ID = "procId";

	public static final String TEMP_FILE = "tempFile";

	public static final String LAUNCHER_ID = "launcherId";

	private final Log log = LogFactory.getLog(getClass());

	@Value("${admin.app.userman.import.tempDir}")
	private File tempDir;

	@Value("${admin.app.userman.import.tempPrefix}")
	private String prefix;

	@Value("${admin.app.userman.import.tempSuffix}")
	private String suffix;

	@Value("${admin.app.userman.import.charset}")
	private Charset charset;

	@Value("${admin.app.userman.import.queue}")
	private String queue;

	@Autowired
	private AsyncProcStatusService asyncProcStatusService;

	@Autowired
	private JsonHelper jsonHelper;

	@Autowired
	@Qualifier("usermanImportSql")
	private String usermanImportSql;

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("usersLoader")
	private Loader loader;

	@Autowired
	private JmsOperations jmsOperations;

	@Transactional
	@Override
	public LoadResult importUsers(MultipartFile file) {
		try (InputStream in = file.getInputStream();
				Reader reader = new InputStreamReader(in, charset)) {
			CsvProvider provider = new CsvProvider(reader, true);
			return loader.load(dataSource, usermanImportSql, provider,
					new NoneLimiter());
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Transactional
	@Override
	public Map<String, String> launchImportUsers(MultipartFile file,
			String launcherId) {
		String name = UsermanImportService.class.getSimpleName();
		Integer procId = asyncProcStatusService.createAsyncProc(name,
				launcherId);
		try {
			File tempFile = createFile(file);

			Map<String, String> message = new HashMap<>();
			message.put(PROC_ID, procId.toString());
			message.put(TEMP_FILE, tempFile.getAbsolutePath());
			message.put(LAUNCHER_ID, launcherId);
			jmsOperations.convertAndSend(queue, message);

			asyncProcStatusService.invokeAsyncProc(procId);

			return message;
		} catch (IOException ex) {
			asyncProcStatusService.errorAsyncProc(procId,
					jsonHelper.fromThrowable(ex));
			throw new IllegalStateException(ex);
		}
	}

	@Transactional
	@Override
	public void handleImportUsers(Map<String, String> message) {
		Integer procId = Integer.parseInt(message.get(PROC_ID));
		File tempFile = new File(message.get(TEMP_FILE));
		try {
			asyncProcStatusService.startAsyncProc(procId);

			LoadResult loadResult = loadFile(tempFile);

			Map<String, Integer> map = new HashMap<>();
			map.put("total", loadResult.getTotalCount());
			map.put("success", loadResult.getSuccessCount());
			map.put("failed", loadResult.getFailedCount());
			asyncProcStatusService.successAsyncProc(procId,
					jsonHelper.fromMap(map));

		} catch (DataAccessException | LimiterException ex) {
			asyncProcStatusService.errorAsyncProc(procId,
					jsonHelper.fromThrowable(ex));
			throw ex;
		} catch (IOException ex) {
			asyncProcStatusService.errorAsyncProc(procId,
					jsonHelper.fromThrowable(ex));
			throw new IllegalStateException(ex);
		} finally {
			if (!tempFile.delete()) {
				log.debug("failed to delete a temporary file: {0}",
						tempFile.getAbsolutePath());
			}
		}
	}

	private File createFile(MultipartFile file) throws IOException {
		File tempFile = File.createTempFile(
				MessageFormat.format(prefix, new Date()), suffix, tempDir);
		try (InputStream in = file.getInputStream();
				OutputStream out = new FileOutputStream(tempFile)) {
			byte[] buff = new byte[4096];
			int size;
			while ((size = in.read(buff, 0, buff.length)) >= 0) {
				out.write(buff, 0, size);
			}
			return tempFile;
		} catch (IOException ex) {
			if (!tempFile.delete()) {
				log.debug("failed to delete a temporary file: {0}",
						tempFile.getAbsolutePath());
			}
			throw ex;
		}
	}

	private LoadResult loadFile(File file) throws IOException {
		try (InputStream in = new FileInputStream(file);
				Reader reader = new InputStreamReader(in, charset)) {
			CsvProvider provider = new CsvProvider(reader, true);
			return loader.load(dataSource, usermanImportSql, provider,
					new NoneLimiter());
		}
	}

}
