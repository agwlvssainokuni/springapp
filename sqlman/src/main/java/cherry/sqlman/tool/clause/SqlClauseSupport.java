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

package cherry.sqlman.tool.clause;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.download.DownloadAction;
import cherry.foundation.download.DownloadOperation;
import cherry.foundation.etl.CsvConsumer;
import cherry.goods.paginate.PageSet;
import cherry.sqlman.Config;
import cherry.sqlman.tool.shared.ExecQueryService;
import cherry.sqlman.tool.shared.ParamParser;
import cherry.sqlman.tool.shared.QueryBuilder;
import cherry.sqlman.tool.shared.ResultSet;

public class SqlClauseSupport {

	@Autowired
	private Config config;

	@Autowired
	private ParamParser paramParser;

	@Autowired
	private ExecQueryService execQueryService;

	@Autowired
	private BizDateTime bizDateTime;

	@Autowired
	private DownloadOperation downloadOperation;

	public Pair<PageSet, ResultSet> search(SqlClauseForm form) {
		String databaseName = form.getDatabaseName();
		QueryBuilder builder = getQueryBuilder(form);
		Map<String, ?> paramMap = paramParser.parseMap(form.getParamMap());

		if (form.getPageNo() <= 0L) {
			form.setPageNo(0L);
		}
		if (form.getPageSz() <= 0L) {
			form.setPageSz(config.getPaginatorDefaultPageSize());
		}

		ResultSet resultSet = new ResultSet();
		PageSet ps = execQueryService.query(databaseName, builder, paramMap, form.getPageNo(), form.getPageSz(),
				resultSet);

		return Pair.of(ps, resultSet);
	}

	public void download(SqlClauseForm form, HttpServletResponse response) {

		final String databaseName = form.getDatabaseName();
		final QueryBuilder builder = getQueryBuilder(form);
		final Map<String, ?> paramMap = paramParser.parseMap(form.getParamMap());

		downloadOperation.download(response, config.getExportContentType(), config.getExportCharset(),
				config.getExportFilename(), bizDateTime.now(), new DownloadAction() {
					@Override
					public long doDownload(OutputStream out) throws IOException {
						try (Writer writer = new OutputStreamWriter(out, config.getExportCharset())) {
							PageSet ps = execQueryService.query(databaseName, builder, paramMap, new CsvConsumer(
									writer, true));
							return ps.getLast().getTo() + 1L;
						}
					}
				});
	}

	public QueryBuilder getQueryBuilder(SqlClauseForm form) {
		QueryBuilder builder = new QueryBuilder();
		builder.setSelect(form.getSelect());
		builder.setFrom(form.getFrom());
		builder.setWhere(form.getWhere());
		builder.setGroupBy(form.getGroupBy());
		builder.setHaving(form.getHaving());
		builder.setOrderBy(form.getOrderBy());
		return builder;
	}

}
