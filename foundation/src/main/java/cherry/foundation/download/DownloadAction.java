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

import java.io.IOException;
import java.io.OutputStream;

/**
 * ダウンロードフレームワーク。<br />
 * ダウンロード処理の実体を定義するインタフェースを規定する。
 */
public interface DownloadAction {

	/**
	 * ダウンロードに供するデータを形成する処理を定義する。<br />
	 * 引数に渡されたストリームに対して、ダウンロードに供するデータを書き込む。また、書き込んだデータの総量を数値として返却する。<br />
	 * なお、返却値の意味づけはダウンロード処理の仕様として個々に規定することとする。典型的な事例としては、CSV形式データや表計算形式データは「レコード数」、バイナリデータは「オクテット数」を想定する。
	 * 
	 * @param stream ダウンロードに供するデータを書き込む先のストリーム。
	 * @return 書き込んだデータの総量を表す数値。
	 * @throws IOException データの書き込みにおける異常を意味する。
	 */
	long doDownload(OutputStream stream) throws IOException;

}
