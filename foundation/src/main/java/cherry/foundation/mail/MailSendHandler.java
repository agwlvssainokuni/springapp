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
 * 送信するメールデータをキューに蓄積する機能、キューに蓄積されているメールデータの識別番号(long)のリストを取得する機能、メールデータの識別番号(long)を指定して当該メールデータを実際に送信する機能を提供する。<br />
 * 基本的な運用方法としては、キューを介して、非同期的にメールを送信する方式を想定する。<br />
 * 特殊な運用方法として、実際のメール送信処理まで実行する方法も提供する。
 */
public interface MailSendHandler {

	/**
	 * 送信するメールデータを、キューに蓄積する。<br />
	 * 
	 * @param launcherId 当メソッドを呼出した利用者のログインID。
	 * @param messageName メールデータの分類名称。典型的には、メールテンプレート名称。
	 * @param from 差出人 (From) のメールアドレス。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param cc 宛先 (Cc) のメールアドレス。
	 * @param bcc 宛先 (Bcc) のメールアドレス。
	 * @param subject 件名 (Subject)。
	 * @param body 本文。
	 * @param scheduledAt 送信予定日時。
	 * @return メールデータの識別番号。
	 */
	long sendLater(String launcherId, String messageName, String from, List<String> to, List<String> cc,
			List<String> bcc, String subject, String body, LocalDateTime scheduledAt);

	/**
	 * メールを即時送信する。<br />
	 * 
	 * @param launcherId 当メソッドを呼出した利用者のログインID。
	 * @param messageName メールデータの分類名称。典型的には、メールテンプレート名称。
	 * @param from 差出人 (From) のメールアドレス。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param cc 宛先 (Cc) のメールアドレス。
	 * @param bcc 宛先 (Bcc) のメールアドレス。
	 * @param subject 件名 (Subject)。
	 * @param body 本文。
	 * @return メールデータの識別番号。
	 */
	long sendNow(String launcherId, String messageName, String from, List<String> to, List<String> cc,
			List<String> bcc, String subject, String body);

	/**
	 * メールを即時送信する。<br />
	 * 
	 * @param launcherId 当メソッドを呼出した利用者のログインID。
	 * @param messageName メールデータの分類名称。典型的には、メールテンプレート名称。
	 * @param from 差出人 (From) のメールアドレス。
	 * @param to 宛先 (To) のメールアドレス。
	 * @param cc 宛先 (Cc) のメールアドレス。
	 * @param bcc 宛先 (Bcc) のメールアドレス。
	 * @param subject 件名 (Subject)。
	 * @param body 本文。
	 * @param preparator 添付ファイルを構成する処理を受け渡す。
	 * @return メールデータの識別番号。
	 */
	long sendNow(String launcherId, String messageName, String from, List<String> to, List<String> cc,
			List<String> bcc, String subject, String body, AttachmentPreparator preparator);

	/**
	 * キューに蓄積されているメールデータの識別番号のリストを取得する。<br />
	 * キューに蓄積する際に指定された送信予定日時が、当メソッドの引数に指定された日時以降のものを取得する。
	 * 
	 * @param dtm 現在日時。
	 * @return メールデータの識別番号のリスト。
	 */
	List<Long> listMessage(LocalDateTime dtm);

	/**
	 * メールデータの識別番号を指定して当該メールデータを実際に送信する。
	 * 
	 * @param messageId メールデータの識別番号。
	 * @return 指定された識別番号に該当するメールデータを送信したらtrue、該当するメールデータが存在しない場合はfalse。なお、該当するメールデータが存在したのに、メールの送信処理で異常が発生した場合は例外が発生する。
	 */
	boolean sendMessage(long messageId);

}
