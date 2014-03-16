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

import cherry.spring.admin.app.controller.BaseForm;
import cherry.spring.common.validator.DateTimeOptPattern;

public class UsermanExportForm extends BaseForm {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "usermanExportForm";
	public static final String PREFIX = NAME + ".";

	public static final String REGISTERED_FROM = "registeredFrom";

	public static final String REGISTERED_TO = "registeredTo";

	@DateTimeOptPattern
	private String registeredFrom;

	@DateTimeOptPattern
	private String registeredTo;

	public String getRegisteredFrom() {
		return registeredFrom;
	}

	public void setRegisteredFrom(String registeredFrom) {
		this.registeredFrom = registeredFrom;
	}

	public String getRegisteredTo() {
		return registeredTo;
	}

	public void setRegisteredTo(String registeredTo) {
		this.registeredTo = registeredTo;
	}

}
