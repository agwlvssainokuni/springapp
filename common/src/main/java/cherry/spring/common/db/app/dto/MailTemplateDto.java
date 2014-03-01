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

import java.util.List;

import cherry.spring.common.db.BaseDto;

public class MailTemplateDto extends BaseDto {

	/** シリアルバージョン。 */
	private static final long serialVersionUID = 1L;

	/** メールテンプレートID。 */
	private Integer id;

	/** 差出人。 */
	private String sender;

	/** 宛先。 */
	private List<MailTemplateAddressDto> mailAddressList;

	/** ロケール。 */
	private String locale;

	/** メール件名。 */
	private String subject;

	/** メール本文。 */
	private String body;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public List<MailTemplateAddressDto> getMailAddressList() {
		return mailAddressList;
	}

	public void setMailAddressList(List<MailTemplateAddressDto> mailAddressList) {
		this.mailAddressList = mailAddressList;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
