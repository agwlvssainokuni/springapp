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

package cherry.example.web.applied.ex60;

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
public abstract class AppliedEx62FormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDate dt;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalTime tm;

	@cherry.foundation.type.format.CustomDateTimeFormat()
	private org.joda.time.LocalDateTime dtm;

	@Getter
	public enum Prop {
		Dt("dt", "appliedEx62Form.dt"), //
		Tm("tm", "appliedEx62Form.tm"), //
		Dtm("dtm", "appliedEx62Form.dtm"), //
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
