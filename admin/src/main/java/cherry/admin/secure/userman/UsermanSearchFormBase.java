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

package cherry.admin.secure.userman;

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
public abstract class UsermanSearchFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@cherry.foundation.validator.MaxLength(value = 512, groups = { javax.validation.groups.Default.class })
	private String loginId;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.FROM)
	private org.joda.time.LocalDateTime registeredFrom;

	@cherry.foundation.type.format.CustomDateTimeFormat(cherry.foundation.type.format.CustomDateTimeFormat.Range.TO)
	private org.joda.time.LocalDateTime registeredTo;

	@cherry.foundation.validator.MaxLength(value = 64, groups = { javax.validation.groups.Default.class })
	private String firstName;

	@cherry.foundation.validator.MaxLength(value = 64, groups = { javax.validation.groups.Default.class })
	private String lastName;

	private long pno = 0L;

	private long psz = 0L;

	@Getter
	public enum Prop {
		LoginId("loginId", "usermanSearchForm.loginId"), //
		RegisteredFrom("registeredFrom", "usermanSearchForm.registeredFrom"), //
		RegisteredTo("registeredTo", "usermanSearchForm.registeredTo"), //
		FirstName("firstName", "usermanSearchForm.firstName"), //
		LastName("lastName", "usermanSearchForm.lastName"), //
		Pno("pno", "usermanSearchForm.pno"), //
		Psz("psz", "usermanSearchForm.psz"), //
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
