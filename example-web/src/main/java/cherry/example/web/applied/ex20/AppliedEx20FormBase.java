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

package cherry.example.web.applied.ex20;

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
public abstract class AppliedEx20FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MaxLength(value = 10, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.CharTypeAlphaNumeric(groups = { javax.validation.groups.Default.class })
	private String text10;

	@cherry.foundation.validator.MaxLength(value = 100, groups = { javax.validation.groups.Default.class })
	private String text100;

	@javax.validation.constraints.Min(value = -1000000000, groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.Max(value = 1000000000, groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat
	private Long int64;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat(1)
	private java.math.BigDecimal decimal1;

	@javax.validation.constraints.DecimalMin(value = "-1000000000", groups = { javax.validation.groups.Default.class })
	@javax.validation.constraints.DecimalMax(value = "1000000000", groups = { javax.validation.groups.Default.class })
	@cherry.foundation.type.format.CustomNumberFormat(3)
	private java.math.BigDecimal decimal3;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDate dt;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalTime tm;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDateTime dtm;

	private Integer lockVersion;

	@Getter
	public enum Prop {
		Text10("text10", "appliedEx20Form.text10"), //
		Text100("text100", "appliedEx20Form.text100"), //
		Int64("int64", "appliedEx20Form.int64"), //
		Decimal1("decimal1", "appliedEx20Form.decimal1"), //
		Decimal3("decimal3", "appliedEx20Form.decimal3"), //
		Dt("dt", "appliedEx20Form.dt"), //
		Tm("tm", "appliedEx20Form.tm"), //
		Dtm("dtm", "appliedEx20Form.dtm"), //
		LockVersion("lockVersion", "appliedEx20Form.lockVersion"), //
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
