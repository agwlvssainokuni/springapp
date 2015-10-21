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

package cherry.example.web.basic.ex30;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import cherry.example.web.SortParam;
import cherry.foundation.validator.CharTypeAlphaNumeric;
import cherry.foundation.validator.JodaTimeMax;
import cherry.foundation.validator.JodaTimeMin;
import cherry.foundation.validator.MaxLength;
import cherry.foundation.validator.NumberScale;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ex30FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@MaxLength(10)
	@CharTypeAlphaNumeric
	private String text10;

	@Min(-1000000000)
	@Max(1000000000)
	private Long int64From;

	@Min(-1000000000)
	@Max(1000000000)
	private Long int64To;

	@DecimalMin("-1000000000")
	@DecimalMax("1000000000")
	@NumberFormat(pattern = "#,##0.0#########")
	@NumberScale(1)
	private BigDecimal decimal1From;

	@DecimalMin("-1000000000")
	@DecimalMax("1000000000")
	@NumberFormat(pattern = "#,##0.0#########")
	@NumberScale(1)
	private BigDecimal decimal1To;

	@DecimalMin("-1000000000")
	@DecimalMax("1000000000")
	@NumberFormat(pattern = "#,##0.000#######")
	@NumberScale(3)
	private BigDecimal decimal3From;

	@DecimalMin("-1000000000")
	@DecimalMax("1000000000")
	@NumberFormat(pattern = "#,##0.000#######")
	@NumberScale(3)
	private BigDecimal decimal3To;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JodaTimeMin("1000-01-01")
	@JodaTimeMax("2999-12-31")
	private LocalDate dtFrom;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JodaTimeMin("1000-01-01")
	@JodaTimeMax("2999-12-31")
	private LocalDate dtTo;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@JodaTimeMin("00:00:00")
	@JodaTimeMax("23:59:59")
	private LocalTime tmFrom;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@JodaTimeMin("00:00:00")
	@JodaTimeMax("23:59:59")
	private LocalTime tmTo;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JodaTimeMin("1000-01-01")
	@JodaTimeMax("2999-12-31")
	private LocalDate dtmFromD;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@JodaTimeMin("00:00:00")
	@JodaTimeMax("23:59:59")
	private LocalTime dtmFromT;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JodaTimeMin("1000-01-01")
	@JodaTimeMax("2999-12-31")
	private LocalDate dtmToD;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@JodaTimeMin("00:00:00")
	@JodaTimeMax("23:59:59")
	private LocalTime dtmToT;

	@NotNull()
	@Valid()
	private SortParam sort1;

	@NotNull()
	@Valid()
	private SortParam sort2;

	private long pno = 0L;

	private long psz = 0L;

}
