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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cherry.spring.common.db.service.AsyncProcService;
import cherry.spring.common.helper.JsonHelper;
import cherry.spring.common.lib.db.CsvDataProvider;
import cherry.spring.common.lib.db.DataLoader;
import cherry.spring.common.lib.db.DataLoader.Result;

@Component
public class UsermanImportServiceImpl implements UsermanImportService {

	public static final String PROC_ID = "procId";

	public static final String TEMP_FILE = "tempFile";

	@Value("${admin.app.userman.import.tempDir")
	private File tempDir;

	@Value("${admin.app.userman.import.tempPrefix")
	private String prefix;

	@Value("${admin.app.userman.import.tempSuffix")
	private String suffix;

	@Value("${admin.app.userman.import.charset}")
	private Charset charset;

	@Autowired
	private AsyncProcService asyncProcService;

	@Autowired
	private JsonHelper jsonHelper;

	@Autowired
	@Qualifier("usersLoader")
	private DataLoader usersLoader;

	@Transactional
	@Override
	public Result importUsers(MultipartFile file) {
		try (InputStream in = file.getInputStream()) {
			Reader reader = new InputStreamReader(in, charset);
			CsvDataProvider provider = new CsvDataProvider(reader, true);
			return usersLoader.load(provider);
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	@Transactional
	@Override
	public Map<String, String> launchImportUsers(MultipartFile file) {
		String name = UsermanImportService.class.getName();
		Integer procId = asyncProcService.createAsyncProc(name);
		try {
			File tempFile = createFile(file);

			Map<String, String> message = new HashMap<>();
			message.put(PROC_ID, procId.toString());
			message.put(TEMP_FILE, tempFile.getAbsolutePath());

			// TODO JMSでメッセージ送信。

			asyncProcService.invokeAsyncProc(procId);

			return message;
		} catch (IOException ex) {
			asyncProcService.errorAsyncProc(procId,
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
			asyncProcService.startAsyncProc(procId);

			Result result = loadFile(tempFile);

			Map<String, Integer> map = new HashMap<>();
			map.put("total", result.getTotalCount());
			map.put("success", result.getSuccessCount());
			map.put("failed", result.getFailedCount());
			asyncProcService.successAsyncProc(procId, jsonHelper.fromMap(map));

		} catch (IOException ex) {
			asyncProcService.errorAsyncProc(procId,
					jsonHelper.fromThrowable(ex));
			throw new IllegalStateException(ex);
		}
	}

	private File createFile(MultipartFile file) throws IOException {
		File tempFile = File.createTempFile(prefix, suffix, tempDir);
		try {
			file.transferTo(tempFile);
			return tempFile;
		} catch (IOException ex) {
			tempFile.delete();
			throw ex;
		}
	}

	private Result loadFile(File file) throws IOException {
		try (InputStream in = new FileInputStream(file)) {
			Reader reader = new InputStreamReader(in, charset);
			return usersLoader.load(new CsvDataProvider(reader, true));
		}
	}

}
