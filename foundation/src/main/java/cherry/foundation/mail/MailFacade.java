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

import org.joda.time.LocalDateTime;

/**
 * メール送信機能。<br />
 * 業務アプリケーションが直接的に使用する機能を集めたインタフェース。
 */
public interface MailFacade {

	/**
	 * DBに保管されているメールテンプレートを元に、送信するメールデータを生成する。<br />
	 * {@link MailDataHandler#createMailData(String, String, MailModel)}に相当する。
	 * 
	 * @param templateName テンプレート名称。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param mailModel テンプレートに埋め込むデータ。
	 * @return 送信するメールデータ。
	 */
	MailData createMailData(String templateName, String to, MailModel mailModel);

	/**
	 * DBに保管されていないメールテンプレートを元に、送信するメールデータを生成する。<br />
	 * {@link MailDataHandler#createMailData(String, List, List, List, String, String, String, MailModel)}に相当する。
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

	/**
	 * 送信するメールデータを、キューに蓄積する。<br />
	 * {@link MailSendHandler#sendLater(String, String, String, List, List, List, String, String, String, LocalDateTime)}
	 * に相当し、送信予定日時として現在日時を指定するのに等しい。
	 * 
	 * @param launcherId 当メソッドを呼出した利用者のログインID。
	 * @param messageName メールデータの分類名称。典型的には、メールテンプレート名称。
	 * @param from 差出人 (From) のメールアドレス。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param cc 宛先 (Cc) のメールアドレス。
	 * @param bcc 宛先 (Bcc) のメールアドレス。
	 * @param replyTo 返信先 (Reply-To) のメールアドレス。
	 * @param subject 件名 (Subject)。
	 * @param body 本文。
	 * @return メールデータの識別番号。
	 */
	long send(String launcherId, String messageName, String from, List<String> to, List<String> cc, List<String> bcc,
			String replyTo, String subject, String body);

	/**
	 * 送信するメールデータを、キューに蓄積する。<br />
	 * {@link MailSendHandler#sendLater(String, String, String, List, List, List, String, String, String, LocalDateTime)}
	 * に相当する。
	 * 
	 * @param launcherId 当メソッドを呼出した利用者のログインID。
	 * @param messageName メールデータの分類名称。典型的には、メールテンプレート名称。
	 * @param from 差出人 (From) のメールアドレス。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param cc 宛先 (Cc) のメールアドレス。
	 * @param bcc 宛先 (Bcc) のメールアドレス。
	 * @param replyTo 返信先 (Reply-To) のメールアドレス。
	 * @param subject 件名 (Subject)。
	 * @param body 本文。
	 * @param scheduledAt 送信予定日時。
	 * @return メールデータの識別番号。
	 */
	long sendLater(String launcherId, String messageName, String from, List<String> to, List<String> cc,
			List<String> bcc, String replyTo, String subject, String body, LocalDateTime scheduledAt);

	/**
	 * メールを即時送信する。<br />
	 * {@link MailSendHandler#sendNow(String, String, String, List, List, List, String, String, String)}に相当する。
	 * 
	 * @param launcherId 当メソッドを呼出した利用者のログインID。
	 * @param messageName メールデータの分類名称。典型的には、メールテンプレート名称。
	 * @param from 差出人 (From) のメールアドレス。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param cc 宛先 (Cc) のメールアドレス。
	 * @param bcc 宛先 (Bcc) のメールアドレス。
	 * @param replyTo 返信先 (Reply-To) のメールアドレス。
	 * @param subject 件名 (Subject)。
	 * @param body 本文。
	 * @return メールデータの識別番号。
	 */
	long sendNow(String launcherId, String messageName, String from, List<String> to, List<String> cc,
			List<String> bcc, String replyTo, String subject, String body);

	/**
	 * メールを即時送信する。<br />
	 * {@link MailSendHandler#sendNow(String, String, String, List, List, List, String, String, String, AttachmentPreparator)}
	 * に相当する。
	 * 
	 * @param launcherId 当メソッドを呼出した利用者のログインID。
	 * @param messageName メールデータの分類名称。典型的には、メールテンプレート名称。
	 * @param from 差出人 (From) のメールアドレス。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param cc 宛先 (Cc) のメールアドレス。
	 * @param bcc 宛先 (Bcc) のメールアドレス。
	 * @param replyTo 返信先 (Reply-To) のメールアドレス。
	 * @param subject 件名 (Subject)。
	 * @param body 本文。
	 * @param preparator 添付ファイルを構成する処理を受け渡す。
	 * @return メールデータの識別番号。
	 */
	long sendNow(String launcherId, String messageName, String from, List<String> to, List<String> cc,
			List<String> bcc, String replyTo, String subject, String body, AttachmentPreparator preparator);

}
