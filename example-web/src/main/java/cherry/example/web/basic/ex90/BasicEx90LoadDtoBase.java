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

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import cherry.foundation.validator.CharTypeAlphaNumeric;
import cherry.foundation.validator.MaxLength;
import cherry.foundation.validator.NumberScale;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BasicEx90LoadDtoBase {

	@NotEmpty()
	@MaxLength(10)
	@CharTypeAlphaNumeric()
	private String text10;

	@MaxLength(100)
	private String text100;

	@Min(-1000000000)
	@Max(1000000000)
	private String int64;

	@NumberFormat(pattern = "#,##0.0#########")
	@DecimalMin("-1000000000")
	@DecimalMax("1000000000")
	@NumberScale(1)
	private BigDecimal decimal1;

	@NumberFormat(pattern = "#,##0.000#######")
	@DecimalMin("-1000000000")
	@DecimalMax("1000000000")
	@NumberScale(3)
	private BigDecimal decimal3;

}
