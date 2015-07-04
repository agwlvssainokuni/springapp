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

package cherry.sqlman.secure.clause;

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
public abstract class SqlClauseFormBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	private String databaseName;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MaxLength(value = 500, groups = { javax.validation.groups.Default.class })
	private String select;

	@org.hibernate.validator.constraints.NotEmpty(groups = { javax.validation.groups.Default.class })
	@cherry.foundation.validator.MaxLength(value = 500, groups = { javax.validation.groups.Default.class })
	private String from;

	@cherry.foundation.validator.MaxLength(value = 500, groups = { javax.validation.groups.Default.class })
	private String where;

	@cherry.foundation.validator.MaxLength(value = 500, groups = { javax.validation.groups.Default.class })
	private String groupBy;

	@cherry.foundation.validator.MaxLength(value = 500, groups = { javax.validation.groups.Default.class })
	private String having;

	@cherry.foundation.validator.MaxLength(value = 500, groups = { javax.validation.groups.Default.class })
	private String orderBy;

	@cherry.foundation.validator.MaxLength(value = 5000, groups = { javax.validation.groups.Default.class })
	private String paramMap;

	private Integer lockVersion;

	private long pageNo = 0L;

	private long pageSz = 0L;

	@Getter
	public enum Prop {
		DatabaseName("databaseName", "sqlClauseForm.databaseName"), //
		Select("select", "sqlClauseForm.select"), //
		From("from", "sqlClauseForm.from"), //
		Where("where", "sqlClauseForm.where"), //
		GroupBy("groupBy", "sqlClauseForm.groupBy"), //
		Having("having", "sqlClauseForm.having"), //
		OrderBy("orderBy", "sqlClauseForm.orderBy"), //
		ParamMap("paramMap", "sqlClauseForm.paramMap"), //
		LockVersion("lockVersion", "sqlClauseForm.lockVersion"), //
		PageNo("pageNo", "sqlClauseForm.pageNo"), //
		PageSz("pageSz", "sqlClauseForm.pageSz"), //
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
