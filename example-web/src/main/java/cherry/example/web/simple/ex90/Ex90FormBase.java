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

package cherry.example.web.simple.ex90;

import java.nio.charset.Charset;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import cherry.foundation.validator.JodaTimeMax;
import cherry.foundation.validator.JodaTimeMin;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ex90FormBase {

	private MultipartFile file;

	@NotNull
	private Charset charset;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@JodaTimeMin("1000-01-01")
	@JodaTimeMax("2999-12-31")
	private LocalDate dt;

	@DateTimeFormat(pattern = "HH:mm:ss")
	@JodaTimeMin("00:00:00")
	@JodaTimeMax("23:59:59")
	private LocalTime tm;

	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JodaTimeMin("1000-01-01T00:00:00")
	@JodaTimeMax("2999-12-31T23:59:59")
	private LocalDateTime dtm;

}
