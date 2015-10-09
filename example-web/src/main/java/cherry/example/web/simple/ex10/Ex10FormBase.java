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

package cherry.example.web.simple.ex10;

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

import cherry.foundation.validator.MaxLength;
import cherry.foundation.validator.MinLength;
import cherry.foundation.validator.NumberScale;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ex10FormBase {

	@NotEmpty
	@MinLength(5)
	@MaxLength(10)
	private String text;

	@NotNull
	@Min(-10000)
	@Max(10000)
	private Integer inum;

	@NotNull
	@Min(-10000)
	@Max(10000)
	private Long lnum;

	@NotNull
	@DecimalMin("-10000")
	@DecimalMax("10000")
	@NumberFormat(pattern = "#,##0.00####")
	@NumberScale(2)
	private BigDecimal decimal;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate ldt;

	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime ltm;

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime ldtm;

}
