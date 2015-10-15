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

package cherry.example.web.simple.ex50;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import cherry.foundation.validator.CharTypeAlphaNumeric;
import cherry.foundation.validator.JodaTimeMax;
import cherry.foundation.validator.JodaTimeMin;
import cherry.foundation.validator.MaxLength;
import cherry.foundation.validator.NumberScale;
import cherry.foundation.validator.groups.G1;
import cherry.foundation.validator.groups.G2;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ex51SubFormBase {

	@NotNull(groups = { G1.class, G2.class })
	private Long id;

	@NotEmpty(groups = { G2.class })
	@MaxLength(value = 10, groups = { G2.class })
	@CharTypeAlphaNumeric(groups = { G2.class })
	private String text10;

	@MaxLength(value = 100, groups = { G2.class })
	private String text100;

	@Min(value = -1000000000, groups = { G2.class })
	@Max(value = 1000000000, groups = { G2.class })
	private Long int64;

	@NumberFormat(pattern = "#,##0.0#########")
	@DecimalMin(value = "-1000000000", groups = { G2.class })
	@DecimalMax(value = "1000000000", groups = { G2.class })
	@NumberScale(value = 1, groups = { G2.class })
	private BigDecimal decimal1;

	@NumberFormat(pattern = "#,##0.000#######")
	@DecimalMin(value = "-1000000000", groups = { G2.class })
	@DecimalMax(value = "1000000000", groups = { G2.class })
	@NumberScale(value = 3, groups = { G2.class })
	private BigDecimal decimal3;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JodaTimeMin(value = "1000-01-01", groups = { G2.class })
	@JodaTimeMax(value = "2999-12-31", groups = { G2.class })
	private LocalDate dt;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@JodaTimeMin(value = "00:00:00", groups = { G2.class })
	@JodaTimeMax(value = "23:59:59", groups = { G2.class })
	private LocalTime tm;

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JodaTimeMin(value = "1000-01-01T00:00:00", groups = { G2.class })
	@JodaTimeMax(value = "2999-12-31T23:59:59", groups = { G2.class })
	private LocalDateTime dtm;

	@NotNull(groups = { G1.class, G2.class })
	private Integer lockVersion;

	private boolean checked;

}
