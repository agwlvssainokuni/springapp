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

package cherry.spring.common.db.app.mapper;

import java.util.Date;

import cherry.spring.common.db.BaseDto;

public class UserCondition extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String mailAddr;

	private Date registeredFrom;

	private Date registeredTo;

	private String firstName;

	private String lastName;

	public String getMailAddr() {
		return mailAddr;
	}

	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}

	public Date getRegisteredFrom() {
		return registeredFrom;
	}

	public void setRegisteredFrom(Date registeredFrom) {
		this.registeredFrom = registeredFrom;
	}

	public Date getRegisteredTo() {
		return registeredTo;
	}

	public void setRegisteredTo(Date registeredTo) {
		this.registeredTo = registeredTo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
