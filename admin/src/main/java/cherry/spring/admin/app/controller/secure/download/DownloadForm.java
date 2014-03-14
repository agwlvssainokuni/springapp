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

package cherry.spring.admin.app.controller.secure.download;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import cherry.spring.admin.app.controller.BaseForm;

public class DownloadForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "downloadForm";
	public static final String PREFIX = NAME + ".";

	public static final String COLUMN_NAME = "columnName";
	public static final int COLUMN_NAME_MIN_LENGTH = 4;
	public static final int COLUMN_NAME_MAX_LENGTH = 32;
	public static final String COLUMN_NAME_REGEX = "[.-_0-9a-zA-Z]*";

	public static final String ROW_COUNT = "rowCount";
	public static final long ROW_COUNT_MIN = 1L;
	public static final long ROW_COUNT_MAX = 1024L * 1024L;
	public static final String ROW_COUNT_REGEX = "[0-9]*";

	@NotNull
	@Size(min = COLUMN_NAME_MIN_LENGTH, max = COLUMN_NAME_MAX_LENGTH)
	@Pattern(regexp = COLUMN_NAME_REGEX)
	private String columnName;

	@NotNull
	@Range(min = ROW_COUNT_MIN, max = ROW_COUNT_MAX)
	@Pattern(regexp = ROW_COUNT_REGEX)
	private String rowCount;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getRowCount() {
		return rowCount;
	}

	public void setRowCount(String rowCount) {
		this.rowCount = rowCount;
	}

}
