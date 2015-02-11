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

package cherry.foundation.generator.test;

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
public abstract class DateTimeFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDate date1;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.FROM)
	private org.joda.time.LocalDate date2;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.TO)
	private org.joda.time.LocalDate date3;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalTime time1;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.FROM)
	private org.joda.time.LocalTime time2;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.TO)
	private org.joda.time.LocalTime time3;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDateTime datetime1;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.FROM)
	private org.joda.time.LocalDateTime datetime2;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.TO)
	private org.joda.time.LocalDateTime datetime3;

	@Getter
	public enum Prop {
		Date1("date1", "dateTimeForm.date1"), //
		Date2("date2", "dateTimeForm.date2"), //
		Date3("date3", "dateTimeForm.date3"), //
		Time1("time1", "dateTimeForm.time1"), //
		Time2("time2", "dateTimeForm.time2"), //
		Time3("time3", "dateTimeForm.time3"), //
		Datetime1("datetime1", "dateTimeForm.datetime1"), //
		Datetime2("datetime2", "dateTimeForm.datetime2"), //
		Datetime3("datetime3", "dateTimeForm.datetime3"), //
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
