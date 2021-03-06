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

package cherry.example.web.applied.ex60;

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
public abstract class AppliedEx60FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@cherry.foundation.validator.MaxLength(value = 10, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.CharTypeAlphaNumeric(groups = { javax.validation.groups.Default.class })
	private String text10;

	@javax.validation.constraints.Min(value = -1000000000, groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.Max(value = 1000000000, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat
	private Long int64From;

	@javax.validation.constraints.Min(value = -1000000000, groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.Max(value = 1000000000, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat
	private Long int64To;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat(1)
	private java.math.BigDecimal decimal1From;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat(1)
	private java.math.BigDecimal decimal1To;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat(3)
	private java.math.BigDecimal decimal3From;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat(3)
	private java.math.BigDecimal decimal3To;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDate dtFrom;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDate dtTo;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalTime tmFrom;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalTime tmTo;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDate dtmFromD;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalTime dtmFromT;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDate dtmToD;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalTime dtmToT;

	private long pno = 0L;

	private long psz = 0L;

	@Getter
	public enum Prop {
		Text10("text10", "appliedEx60Form.text10"), //
		Int64From("int64From", "appliedEx60Form.int64From"), //
		Int64To("int64To", "appliedEx60Form.int64To"), //
		Decimal1From("decimal1From", "appliedEx60Form.decimal1From"), //
		Decimal1To("decimal1To", "appliedEx60Form.decimal1To"), //
		Decimal3From("decimal3From", "appliedEx60Form.decimal3From"), //
		Decimal3To("decimal3To", "appliedEx60Form.decimal3To"), //
		DtFrom("dtFrom", "appliedEx60Form.dtFrom"), //
		DtTo("dtTo", "appliedEx60Form.dtTo"), //
		TmFrom("tmFrom", "appliedEx60Form.tmFrom"), //
		TmTo("tmTo", "appliedEx60Form.tmTo"), //
		DtmFromD("dtmFromD", "appliedEx60Form.dtmFromD"), //
		DtmFromT("dtmFromT", "appliedEx60Form.dtmFromT"), //
		DtmToD("dtmToD", "appliedEx60Form.dtmToD"), //
		DtmToT("dtmToT", "appliedEx60Form.dtmToT"), //
		Sort1("sort1", "appliedEx60Form.sort1"), //
		Sort2("sort2", "appliedEx60Form.sort2"), //
		Pno("pno", "appliedEx60Form.pno"), //
		Psz("psz", "appliedEx60Form.psz"), //
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
