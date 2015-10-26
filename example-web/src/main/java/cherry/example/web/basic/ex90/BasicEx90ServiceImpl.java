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

package cherry.example.web.basic.ex90;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import cherry.example.db.gen.query.QExTbl1;
import cherry.example.web.LogicalError;
import cherry.example.web.basic.ex90.BasicEx90LoadFormBase.Prop;
import cherry.foundation.logicalerror.LogicalErrorUtil;
import cherry.foundation.validator.DataBinderHelper;
import cherry.goods.csv.CsvParser;

import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.dml.SQLInsertClause;

@Service
public class BasicEx90ServiceImpl implements BasicEx90Service {

	@Autowired
	private SQLQueryFactory qf;

	@Autowired
	private DataBinderHelper dataBinderHelper;

	private final QExTbl1 et1 = new QExTbl1("et1");

	@Transactional
	@Override
	public BasicEx90ResultDto load(BasicEx90Form form) {

		try (InputStream in = form.getFile().getInputStream();
				Reader r = new InputStreamReader(in, form.getCharset());
				CsvParser csv = new CsvParser(r)) {

			String[] header = csv.read();
			if (header == null) {
				return new BasicEx90ResultDto();
			}

			long totalCount = 0L;
			long okCount = 0L;
			long ngCount = 0L;
			Map<Long, List<String>> ngInfo = new TreeMap<>();

			String formName = UPPER_CAMEL.to(UPPER_CAMEL, BasicEx90LoadForm.class.getSimpleName());
			String[] field = createFieldName(header);
			String[] record;
			while ((record = csv.read()) != null) {

				totalCount += 1L;

				BasicEx90LoadForm dto = new BasicEx90LoadForm();
				BindingResult binding = dataBinderHelper.bindAndValidate(dto, formName, new MutablePropertyValues(
						createValueMap(field, record)));
				if (binding.hasErrors()) {
					ngInfo.put(totalCount, dataBinderHelper.resolveAllMessage(binding, LocaleContextHolder.getLocale()));
					ngCount += 1L;
					continue;
				}

				if (qf.from(et1).where(et1.text10.eq(dto.getText10())).exists()) {
					LogicalErrorUtil.rejectValue(binding, Prop.Text10.getName(), LogicalError.AlreadyExists,
							Prop.Text10.resolve());
					ngInfo.put(totalCount, dataBinderHelper.resolveAllMessage(binding, LocaleContextHolder.getLocale()));
					ngCount += 1L;
					continue;
				}

				try {

					SQLInsertClause insert = qf.insert(et1).populate(dto);
					insert.set(et1.dt, form.getDt());
					insert.set(et1.tm, form.getTm());
					insert.set(et1.dtm, form.getDtm());

					long count = insert.execute();
					if (count == 1L) {
						okCount += 1L;
					} else {
						ngInfo.put(totalCount, asList(form.toString()));
						ngCount += 1L;
					}
				} catch (DataIntegrityViolationException ex) {
					ngInfo.put(totalCount, asList(ex.getMessage()));
					ngCount += 1L;
				}
			}

			BasicEx90ResultDto result = new BasicEx90ResultDto();
			result.setTotalCount(totalCount);
			result.setOkCount(okCount);
			result.setNgCount(ngCount);
			result.setNgInfo(ngInfo);
			return result;

		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
	}

	private String[] createFieldName(String[] h) {
		String[] r = new String[h.length];
		for (int i = 0; i < h.length; i++) {
			r[i] = LOWER_UNDERSCORE.to(LOWER_CAMEL, h[i]);
		}
		return r;
	}

	private Map<String, String> createValueMap(String[] field, String[] record) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < field.length && i < record.length; i++) {
			map.put(field[i], record[i]);
		}
		return map;
	}

}
