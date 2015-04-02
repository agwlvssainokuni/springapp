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

import java.io.File;

import javax.activation.DataSource;
import javax.mail.MessagingException;

import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * メール送信機能。<br />
 * 添付ファイルを指定する対象を表す。
 */
public class Attachment {

	private MimeMessageHelper mimeMessageHelper;

	public Attachment(MimeMessageHelper mimeMessageHelper) {
		this.mimeMessageHelper = mimeMessageHelper;
	}

	/**
	 * データソースとして表されるデータを添付ファイルとして添付する。<br />
	 * 
	 * @param filename 添付ファイル名称。
	 * @param dataSource 添付ファイルのデータ。
	 * @throws MessagingException メールデータを構成する処理で異常が発生したことを表す。
	 */
	public void add(String filename, DataSource dataSource) throws MessagingException {
		mimeMessageHelper.addAttachment(filename, dataSource);
	}

	/**
	 * ファイルとして表されるデータを添付ファイルとして添付する。<br />
	 * 
	 * @param filename 添付ファイル名称。
	 * @param file 添付ファイルのデータ。
	 * @throws MessagingException メールデータを構成する処理で異常が発生したことを表す。
	 */
	public void add(String filename, File file) throws MessagingException {
		mimeMessageHelper.addAttachment(filename, file);
	}

	/**
	 * ストリームとして表されるデータを添付ファイルとして添付する。<br />
	 * 
	 * @param filename 添付ファイル名称。
	 * @param source 添付ファイルのデータ。
	 * @throws MessagingException メールデータを構成する処理で異常が発生したことを表す。
	 */
	public void add(String filename, InputStreamSource source) throws MessagingException {
		mimeMessageHelper.addAttachment(filename, source);
	}

	/**
	 * ストリームとして表されるデータを添付ファイルとして添付する。<br />
	 * 
	 * @param filename 添付ファイル名称。
	 * @param source　添付ファイルのデータ。
	 * @param contentType データの型 (Content-Type)。
	 * @throws MessagingException メールデータを構成する処理で異常が発生したことを表す。
	 */
	public void add(String filename, InputStreamSource source, String contentType) throws MessagingException {
		mimeMessageHelper.addAttachment(filename, source, contentType);
	}

}
