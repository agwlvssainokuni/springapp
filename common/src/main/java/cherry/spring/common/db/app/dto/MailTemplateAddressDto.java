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

package cherry.spring.common.db.app.dto;

import cherry.spring.common.db.BaseDto;

public class MailTemplateAddressDto extends BaseDto {

	/** シリアルバージョン。 */
	private static final long serialVersionUID = 1L;

	static enum Type {
		CC, BCC
	}

	/** 宛先ID。 */
	private Integer addressId;

	/** 宛先区分。 */
	private String type;

	/** メールアドレス。 */
	private String mailAddress;

	public boolean isCc() {
		return Type.CC.name().equalsIgnoreCase(type);
	}

	public boolean isBcc() {
		return Type.BCC.name().equalsIgnoreCase(type);
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

}
