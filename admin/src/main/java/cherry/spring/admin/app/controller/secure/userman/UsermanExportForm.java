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

package cherry.spring.admin.app.controller.secure.userman;

import javax.validation.constraints.NotNull;

import cherry.spring.admin.app.controller.BaseForm;
import cherry.spring.common.validator.DateTimeOpt;

public class UsermanExportForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "usermanExportForm";
	public static final String PREFIX = NAME + ".";

	public static final String APPLIED_FROM = "appliedFrom";

	public static final String APPLIED_TO = "appliedTo";

	@NotNull
	@DateTimeOpt
	private String appliedFrom;

	@NotNull
	@DateTimeOpt
	private String appliedTo;

	public String getAppliedFrom() {
		return appliedFrom;
	}

	public void setAppliedFrom(String appliedFrom) {
		this.appliedFrom = appliedFrom;
	}

	public String getAppliedTo() {
		return appliedTo;
	}

	public void setAppliedTo(String appliedTo) {
		this.appliedTo = appliedTo;
	}

}
