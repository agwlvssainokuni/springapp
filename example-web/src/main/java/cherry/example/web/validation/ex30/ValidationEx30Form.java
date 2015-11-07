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

package cherry.example.web.validation.ex30;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import cherry.foundation.type.format.CustomDateTimeFormat;
import cherry.foundation.type.format.CustomDateTimeFormat.Range;
import cherry.foundation.validator.JodaTimeMax;
import cherry.foundation.validator.JodaTimeMin;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ValidationEx30Form {

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@NotNull()
	private LocalDateTime notnull;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime ldtmiso;

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime ldtmpat;

	@CustomDateTimeFormat(Range.FROM)
	private LocalDateTime ldtmfm;

	@CustomDateTimeFormat(Range.TO)
	private LocalDateTime ldtmto;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate ldtiso;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate ldtpat;

	@CustomDateTimeFormat(Range.FROM)
	private LocalDate ldtfm;

	@CustomDateTimeFormat(Range.TO)
	private LocalDate ldtto;

	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime ltmiso;

	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime ltmpat;

	@CustomDateTimeFormat(Range.FROM)
	private LocalTime ltmfm;

	@CustomDateTimeFormat(Range.TO)
	private LocalTime ltmto;

	@JodaTimeMin(value = "1900-01-01T00:00:00", inclusive = true)
	private LocalDateTime ldtmmin19000101in;

	@JodaTimeMax(value = "3000-01-01T00:00:00", inclusive = true)
	private LocalDateTime ldtmmax30000101in;

	@JodaTimeMin(value = "1900-01-01T00:00:00", inclusive = false)
	private LocalDateTime ldtmmin19000101ex;

	@JodaTimeMax(value = "3000-01-01T00:00:00", inclusive = false)
	private LocalDateTime ldtmmax30000101ex;

	@JodaTimeMin(value = "1900-01-01", inclusive = true)
	private LocalDate ldtmin19000101in;

	@JodaTimeMax(value = "3000-01-01", inclusive = true)
	private LocalDate ldtmax30000101in;

	@JodaTimeMin(value = "1900-01-01", inclusive = false)
	private LocalDate ldtmin19000101ex;

	@JodaTimeMax(value = "3000-01-01", inclusive = false)
	private LocalDate ldtmax30000101ex;

}
