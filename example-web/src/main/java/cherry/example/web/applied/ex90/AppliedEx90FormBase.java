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

package cherry.example.web.applied.ex90;

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
public abstract class AppliedEx90FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G1.class })
	private org.springframework.web.multipart.MultipartFile file;

	private String originalFilename;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	@cherry.foundation.validator.CharTypeBasicLatin(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	private String dirname;

	@javax.validation.constraints.NotNull(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	@cherry.foundation.type.format.CustomNumberFormat
	private Integer numOfFile;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDate dt;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalTime tm;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDateTime dtm;

	@Getter
	public enum Prop {
		File("file", "appliedEx90Form.file"), //
		OriginalFilename("originalFilename", "appliedEx90Form.originalFilename"), //
		Dirname("dirname", "appliedEx90Form.dirname"), //
		NumOfFile("numOfFile", "appliedEx90Form.numOfFile"), //
		Charset("charset", "appliedEx90Form.charset"), //
		Dt("dt", "appliedEx90Form.dt"), //
		Tm("tm", "appliedEx90Form.tm"), //
		Dtm("dtm", "appliedEx90Form.dtm"), //
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
