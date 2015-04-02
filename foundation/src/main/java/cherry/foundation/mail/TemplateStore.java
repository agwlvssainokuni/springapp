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

package cherry.foundation.mail;

/**
 * メール送信機能。<br />
 * 
 * メールテンプレートを管理する機能を表すインタフェース。
 */
public interface TemplateStore {

	/**
	 * メールテンプレートを取得する。<br />
	 * 
	 * @param templateName メールテンプレート名称。
	 * @return メールテンプレート。
	 */
	MailData getTemplate(String templateName);

	/**
	 * メールテンプレートを保管する。<br />
	 * 
	 * @param templateName メールテンプレート名称。
	 * @param mailData メールテンプレート。
	 */
	void putTemplate(String templateName, MailData mailData);

}
