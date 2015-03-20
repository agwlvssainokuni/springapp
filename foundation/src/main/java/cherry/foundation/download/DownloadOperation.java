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

package cherry.foundation.download;

import java.nio.charset.Charset;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;

/**
 * ダウンロードフレームワーク。<br />
 * ダウンロード機能を実現する際に発生する典型的な処理手順を共通機能として提供する。具体的には、以下の二つの処理を実行する。
 * <ul>
 * <li>HTTPレスポンスヘッダに、ダウンロードするデータに関する情報を返却する。</li>
 * <li>HTTPレスポンスボディに、ダウンロードするデータの実体を返却する。</li>
 * </ul>
 */
public interface DownloadOperation {

	/**
	 * ダウンロード機能の典型的な処理手順を共通機能として提供する。<br />
	 * 具体的には、以下のの二つの処理を実行する。
	 * <ul>
	 * <li>HTTPレスポンスヘッダに、ダウンロードするデータに関する情報を返却する。</li>
	 * <li>HTTPレスポンスボディに、ダウンロードするデータの実体を返却する。</li>
	 * </ul>
	 * 
	 * @param response ダウンロードするデータの返却先。
	 * @param contentType ダウンロードするデータの種類。HTTPヘッダ「Content-Type」に指定される。
	 * @param charset ダウンロードするデータの文字コード。バイナリデータを返却する場合は指定してはならない。HTTPヘッダ「Content-Type」に指定される。
	 * @param filename ダウンロードするデータのファイル名のテンプレート。後続の引数で指定する日時を{@link MessageFormat}
	 *            で埋め込んだものを実際のファイル名とする。HTTPヘッダ「Content-Disposition」に指定される。
	 * @param timestamp ダウンロードするデータのファイル名に埋め込む日時。
	 * @param action ダウンロードするデータの実体を形成する処理。
	 */
	void download(HttpServletResponse response, String contentType, Charset charset, String filename,
			LocalDateTime timestamp, DownloadAction action);

}
