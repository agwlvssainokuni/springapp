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

package cherry.sqlman.tool.load;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionOperations;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.multipart.MultipartFile;

import cherry.foundation.async.FileProcessResult;
import cherry.foundation.etl.CsvProvider;
import cherry.foundation.etl.LoadResult;
import cherry.foundation.etl.Loader;
import cherry.foundation.etl.NoneLimiter;
import cherry.sqlman.tool.shared.DataSourceDef;

public class SqlLoadSupport {

	@Value("${sqlman.import.charset}")
	private Charset charset;

	@Autowired
	private DataSourceDef dataSourceDef;

	@Autowired
	private Loader loader;

	public FileProcessResult handleFile(final MultipartFile file, String dataSourceName, final String sql) {
		final DataSource dataSource = dataSourceDef.getDataSource(dataSourceName);
		TransactionOperations txOp = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
		return txOp.execute(new TransactionCallback<FileProcessResult>() {
			@Override
			public FileProcessResult doInTransaction(TransactionStatus status) {
				try (InputStream in = file.getInputStream(); Reader reader = new InputStreamReader(in, charset)) {

					LoadResult r = loader.load(dataSource, sql, new CsvProvider(reader, true), new NoneLimiter());

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
