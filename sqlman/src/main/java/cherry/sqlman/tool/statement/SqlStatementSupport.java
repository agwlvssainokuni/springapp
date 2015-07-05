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

package cherry.sqlman.tool.statement;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.download.DownloadAction;
import cherry.foundation.download.DownloadOperation;
import cherry.foundation.etl.CsvConsumer;
import cherry.goods.paginate.PageSet;
import cherry.sqlman.tool.shared.ExecQueryService;
import cherry.sqlman.tool.shared.ParamParser;
import cherry.sqlman.tool.shared.ResultSet;

public class SqlStatementSupport {

	@Value("${sqlman.export.contentType}")
	private String contentType;

	@Value("${sqlman.export.charset}")
	private Charset charset;

	@Value("${sqlman.export.filename}")
	private String filename;

	@Autowired
	private ParamParser paramParser;

	@Autowired
	private ExecQueryService execQueryService;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private DownloadOperation downloadOperation;

	public Pair<PageSet, ResultSet> search(SqlStatementForm form) {
		String databaseName = form.getDatabaseName();
		String sql = form.getSql();
		Map<String, ?> paramMap = paramParser.parseMap(form.getParamMap());

		ResultSet resultSet = new ResultSet();
		PageSet ps = execQueryService.query(databaseName, sql, paramMap, resultSet);

		return Pair.of(ps, resultSet);
	}

	public void download(SqlStatementForm form, HttpServletResponse response) {

		final String databaseName = form.getDatabaseName();
		final String sql = form.getSql();
		final Map<String, ?> paramMap = paramParser.parseMap(form.getParamMap());

		downloadOperation.download(response, contentType, charset, filename, bizDateTime.now(), new DownloadAction() {
			@Override
			public long doDownload(OutputStream out) throws IOException {
				try (Writer writer = new OutputStreamWriter(out, charset)) {
					PageSet ps = execQueryService.query(databaseName, sql, paramMap, new CsvConsumer(writer, true));
					return ps.getLast().getTo() + 1L;
				}
			}
		});
	}

}
