/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.common.db.gen.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import cherry.spring.common.custom.DeletedFlag;
import cherry.spring.common.custom.FlagCode;
import cherry.spring.common.custom.SecureBigDecimal;
import cherry.spring.common.custom.SecureBigInteger;
import cherry.spring.common.custom.SecureInteger;
import cherry.spring.common.custom.SecureLong;
import cherry.spring.common.custom.SecureString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ConversionTest {

	private int id;

	private LocalDate jodaDate;

	private LocalTime jodaTime;

	private LocalDateTime jodaDatetime;

	private SecureString secStr;

	private SecureInteger secInt;

	private SecureLong secLong;

	private SecureBigInteger secBigint;

	private SecureBigDecimal secBigdec;

	private FlagCode flagCode;

	private DeletedFlag deletedFlg;

}
