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

package cherry.foundation.generator.test;

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
public abstract class GroupFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G1.class })
	private String group1;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G2.class })
	private String group2;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G3.class })
	private String group3;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G4.class })
	private String group4;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G5.class })
	private String group5;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G6.class })
	private String group6;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G7.class })
	private String group7;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G8.class })
	private String group8;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G9.class })
	private String group9;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class, cherry.foundation.validator.groups.G1.class, cherry.foundation.validator.groups.G2.class, cherry.foundation.validator.groups.G3.class, cherry.foundation.validator.groups.G4.class, cherry.foundation.validator.groups.G5.class, cherry.foundation.validator.groups.G6.class, cherry.foundation.validator.groups.G7.class, cherry.foundation.validator.groups.G8.class, cherry.foundation.validator.groups.G9.class })
	private String group10;

	@Getter
	public enum Prop {
		Group1("group1", "groupForm.group1"), //
		Group2("group2", "groupForm.group2"), //
		Group3("group3", "groupForm.group3"), //
		Group4("group4", "groupForm.group4"), //
		Group5("group5", "groupForm.group5"), //
		Group6("group6", "groupForm.group6"), //
		Group7("group7", "groupForm.group7"), //
		Group8("group8", "groupForm.group8"), //
		Group9("group9", "groupForm.group9"), //
		Group10("group10", "groupForm.group10"), //
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
