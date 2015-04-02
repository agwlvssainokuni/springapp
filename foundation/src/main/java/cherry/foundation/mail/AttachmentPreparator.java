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

import javax.mail.MessagingException;

/**
 * メール送信機能。<br />
 * 添付ファイルを構成する処理を表す。
 */
public interface AttachmentPreparator {

	/**
	 * 添付ファイルを構成する処理を定義する。<br />
	 * 
	 * @param attachment 添付ファイルを指定する対象を表す。
	 * @throws MessagingException メールデータを構成する処理で異常が発生したことを表す。
	 */
	void prepare(Attachment attachment) throws MessagingException;

}
