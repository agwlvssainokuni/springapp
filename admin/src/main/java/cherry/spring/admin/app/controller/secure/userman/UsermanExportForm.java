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

import org.joda.time.LocalDateTime;

import cherry.spring.admin.app.controller.BaseForm;
import cherry.spring.common.custom.format.CustomDateTimeFormat;
import cherry.spring.common.custom.format.CustomDateTimeFormat.Range;

public class UsermanExportForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "usermanExportForm";
	public static final String PREFIX = NAME + ".";

	public static final String REGISTERED_FROM = "registeredFrom";

	public static final String REGISTERED_TO = "registeredTo";

	@CustomDateTimeFormat(Range.FROM)
	private LocalDateTime registeredFrom;

	@CustomDateTimeFormat(Range.TO)
	private LocalDateTime registeredTo;

	public LocalDateTime getRegisteredFrom() {
		return registeredFrom;
	}

	public void setRegisteredFrom(LocalDateTime registeredFrom) {
		this.registeredFrom = registeredFrom;
	}

	public LocalDateTime getRegisteredTo() {
		return registeredTo;
	}

	public void setRegisteredTo(LocalDateTime registeredTo) {
		this.registeredTo = registeredTo;
	}

}
