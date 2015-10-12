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

package cherry.example.web.simple.ex30;

import static java.util.Arrays.asList;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cherry.example.db.gen.query.BExTbl1;
import cherry.example.db.gen.query.QExTbl1;
import cherry.foundation.bizdtm.BizDateTime;
import cherry.foundation.download.TableDownloadOperation;
import cherry.foundation.querydsl.QueryConfigurer;
import cherry.foundation.querydsl.QueryDslSupport;
import cherry.goods.paginate.PagedList;

import com.mysema.query.sql.SQLQuery;

@Service
public class Ex30ServiceImpl implements Ex30Service {

	@Autowired
	private QueryDslSupport queryDslSupport;

	@Autowired
	private TableDownloadOperation tableDownloadOperation;

	@Autowired
	private BizDateTime bizDateTime;

	private final QExTbl1 et1 = new QExTbl1("et1");

	private final String filename = "ex30_{0}.xlsx";
	private final List<String> header = asList("ID", "文字列【10】", "文字列【100】", "整数【64bit】", "小数【1桁】", "小数【3桁】", "日付",
			"時刻", "日時");

	@Transactional
	@Override
	public PagedList<BExTbl1> search(Ex30Form form) {
		return queryDslSupport.search(commonClause(form), orderByClause(form), form.getPno(), form.getPsz(), et1);
	}

	@Transactional
	@Override
	public void downloadXlsx(Ex30Form form, HttpServletResponse response) {
		LocalDateTime now = bizDateTime.now();
		tableDownloadOperation.downloadXlsx(response, filename, now, header, commonClause(form), orderByClause(form),
				et1.id, et1.text10, et1.text100, et1.int64, et1.decimal1, et1.decimal3, et1.dt, et1.tm, et1.dtm);
	}

	private QueryConfigurer commonClause(final Ex30Form form) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				query.from(et1);
				return query;
			}
		};
	}

	private QueryConfigurer orderByClause(final Ex30Form form) {
		return new QueryConfigurer() {
			@Override
			public SQLQuery configure(SQLQuery query) {
				return query;
			}
		};
	}

}
