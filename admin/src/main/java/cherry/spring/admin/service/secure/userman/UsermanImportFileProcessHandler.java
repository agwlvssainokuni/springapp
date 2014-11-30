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

package cherry.spring.admin.service.secure.userman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.transaction.support.TransactionTemplate;

import cherry.foundation.async.FileProcessHandler;
import cherry.foundation.async.FileProcessResult;
import cherry.foundation.etl.CsvProvider;
import cherry.foundation.etl.LoadResult;
import cherry.foundation.etl.Loader;
import cherry.foundation.etl.NoneLimiter;

@Component("usermanImportFileProcessHandler")
public class UsermanImportFileProcessHandler implements FileProcessHandler {

	@Value("${admin.app.userman.import.charset}")
	private Charset charset;

	@Autowired
	@Qualifier("usermanImportSql")
	private String usermanImportSql;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private Loader loader;

	@Override
	public FileProcessResult handleFile(final File file, String name,
			String originalFilename, String contentType, long size,
			long asyncId, String... args) throws IOException {
		TransactionOperations txOp = new TransactionTemplate(
				new DataSourceTransactionManager(dataSource));
		return txOp.execute(new TransactionCallback<FileProcessResult>() {
			@Override
			public FileProcessResult doInTransaction(TransactionStatus status) {
				try (InputStream in = new FileInputStream(file);
						Reader reader = new InputStreamReader(in, charset)) {

					LoadResult r = loader.load(dataSource, usermanImportSql,
							new CsvProvider(reader, true), new NoneLimiter());

					FileProcessResult result = new FileProcessResult();
					result.setTotalCount(r.getTotalCount());
					result.setOkCount(r.getSuccessCount());
					result.setNgCount(r.getFailedCount());
					return result;

				} catch (IOException ex) {
					throw new IllegalStateException(ex);
				}
			}
		});
	}

}
