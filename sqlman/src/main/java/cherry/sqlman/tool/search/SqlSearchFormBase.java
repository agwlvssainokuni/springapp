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

package cherry.sqlman.tool.search;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.context.MessageSourceResolvable;

import cherry.foundation.logicalerror.LogicalErrorUtil;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class SqlSearchFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@cherry.foundation.validator.MaxLength(value = 50, groups = { javax.validation.groups.Default.class })
	private String name;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.FROM)
	private org.joda.time.LocalDate registeredFromDt;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.FROM)
	private org.joda.time.LocalTime registeredFromTm;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.TO)
	private org.joda.time.LocalDate registeredToDt;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.TO)
	private org.joda.time.LocalTime registeredToTm;

	private java.util.List<cherry.sqlman.SqlType> sqlType;

	private java.util.List<cherry.sqlman.Published> published;

	private long pageNo = 0L;

	private long pageSz = 0L;

	@Getter
	public enum Prop {
		Name("name", "sqlSearchForm.name"), //
		RegisteredFrom("registeredFrom", "sqlSearchForm.registeredFrom"), //
		RegisteredFromDt("registeredFromDt", "sqlSearchForm.registeredFromDt"), //
		RegisteredFromTm("registeredFromTm", "sqlSearchForm.registeredFromTm"), //
		RegisteredTo("registeredTo", "sqlSearchForm.registeredTo"), //
		RegisteredToDt("registeredToDt", "sqlSearchForm.registeredToDt"), //
		RegisteredToTm("registeredToTm", "sqlSearchForm.registeredToTm"), //
		SqlType("sqlType", "sqlSearchForm.sqlType"), //
		Published("published", "sqlSearchForm.published"), //
		PageNo("pageNo", "sqlSearchForm.pageNo"), //
		PageSz("pageSz", "sqlSearchForm.pageSz"), //
		DUMMY("dummy", "dummy");

		private final String name;
		private final String nameWithForm;

		private Prop(String name, String nameWithForm) {
			this.name = name;
			this.nameWithForm = nameWithForm;
		}

		public MessageSourceResolvable resolve() {
			return LogicalErrorUtil.resolve(nameWithForm);
		}
	}

}
