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

import java.util.List;

/**
 * メール送信機能。<br />
 * メールテンプレートを元に、送信するメールデータを生成する機能を提供する。<br />
 * 基本的な運用方法としては、メールテンプレートはDBに保管されていることを想定し、その名称 (テンプレート名称) によりメールテンプレートを識別する。送信するメールデータを生成する際は、テンプレート名称、宛先 (To)
 * のメールアドレス、テンプレートに埋め込むデータを指定する。<br />
 * 特殊な運用方法として、DBに保管されていないメールテンプレートのデータを元に、送信するメールデータを生成することにも対応する。
 */
public interface MailDataHandler {

	/**
	 * DBに保管されているメールテンプレートを元に、送信するメールデータを生成する。<br />
	 * 
	 * @param templateName テンプレート名称。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param mailModel テンプレートに埋め込むデータ。
	 * @return 送信するメールデータ。
	 */
	MailData createMailData(String templateName, String to, MailModel mailModel);

	/**
	 * DBに保管されていないメールテンプレートを元に、送信するメールデータを生成する。<br />
	 * 
	 * @param fromAddr 差出人 (From) のメールアドレス。
	 * @param toAddr 宛先 (To) のメールアドレス。
	 * @param ccAddr 宛先 (Cc) のメールアドレス。
	 * @param bccAddr 宛先 (Bcc) のメールアドレス。
	 * @param replyToAddr 返信先 (Reply-To) のメールアドレス。
	 * @param subject 件名 (Subject) のテンプレート。
	 * @param body 本文のテンプレート。
	 * @param mailModel テンプレートに埋め込むデータ。
	 * @return 送信するメールデータ。
	 */
	MailData createMailData(String fromAddr, List<String> toAddr, List<String> ccAddr, List<String> bccAddr,
			String replyToAddr, String subject, String body, MailModel mailModel);

}
