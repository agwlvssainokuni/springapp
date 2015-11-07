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

package cherry.example.web.validation.ex20;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.format.annotation.NumberFormat;

import cherry.foundation.validator.NumberScale;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ValidationEx20Form {

	@NotNull()
	private Long notnull;

	@NumberFormat(pattern = "###0")
	private Short shortnum;

	@NumberFormat(pattern = "###0")
	private Integer intnum;

	@NumberFormat(pattern = "###0")
	private Long longnum;

	@NumberFormat(pattern = "###0")
	private BigInteger bigint;

	@NumberFormat(pattern = "###0.##########")
	private Float floatnum;

	@NumberFormat(pattern = "###0.##########")
	private Double doublenum;

	@NumberFormat(pattern = "###0.##########")
	private BigDecimal bigdec;

	@NumberFormat(pattern = "#,##0")
	private Short shortnumWithComma;

	@NumberFormat(pattern = "#,##0")
	private Integer intnumWithComma;

	@NumberFormat(pattern = "#,##0")
	private Long longnumWithComma;

	@NumberFormat(pattern = "#,##0")
	private BigInteger bigintWithComma;

	@NumberFormat(pattern = "#,##0.##########")
	private Float floatnumWithComma;

	@NumberFormat(pattern = "#,##0.##########")
	private Double doublenumWithComma;

	@NumberFormat(pattern = "#,##0.##########")
	private BigDecimal bigdecWithComma;

	@NumberFormat(pattern = "###0.##########")
	@NumberScale(2)
	private Float floatnumWithScale;

	@NumberFormat(pattern = "###0.##########")
	@NumberScale(2)
	private Double doublenumWithScale;

	@NumberFormat(pattern = "###0.##########")
	@NumberScale(2)
	private BigDecimal bigdecWithScale;

	@NumberFormat(pattern = "###0")
	@Min(-9999999999L)
	private Long min9999999999;

	@NumberFormat(pattern = "###0")
	@Max(9999999999L)
	private Long max9999999999;

	@NumberFormat(pattern = "###0.##########")
	@DecimalMin(value = "-10000000000", inclusive = true)
	private BigDecimal decmin10000000000in;

	@NumberFormat(pattern = "###0.##########")
	@DecimalMax(value = "10000000000", inclusive = true)
	private BigDecimal decmax10000000000in;

	@NumberFormat(pattern = "###0.##########")
	@DecimalMin(value = "-10000000000", inclusive = false)
	private BigDecimal decmin10000000000ex;

	@NumberFormat(pattern = "###0.##########")
	@DecimalMax(value = "10000000000", inclusive = false)
	private BigDecimal decmax10000000000ex;

}
