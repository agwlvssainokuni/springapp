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

package cherry.spring.fwcore.async;

import java.io.File;
import java.io.IOException;

/**
 * 非同期実行フレームワーク。<br />
 * 非同期で実行するファイル処理の処理本体のインタフェース。ファイル処理の実体は本インタフェースを実装することとする。
 */
public interface FileProcessHandler {

	/**
	 * 非同期で実行するファイル処理の本体。
	 * 
	 * @param file
	 *            処理対象のファイル。
	 * @param name
	 *            HTTPリクエストで受け渡された元のパラメタ名。
	 * @param originalFilename
	 *            HTTPリクエストで受け渡された元のファイル名。
	 * @param contentType
	 *            HTTPリクエストで受け渡されたコンテントタイプ。
	 * @param size
	 *            HTTPリクエストで受け渡された元のファイルのサイズ。
	 * @param asyncId
	 *            非同期実行状況を管理するためのID。
	 * @return 非同期で実行したファイル処理の結果。
	 * @throws IOException
	 *             ファイルI/Oで異常が発生したことを表す。ファイル処理で発生したI/O例外は非同期処理フレームワークがまとめて捕捉して処理
	 *             (ログ出力して異常終了として記録) する。
	 */
	FileProcessResult handleFile(File file, String name,
			String originalFilename, String contentType, long size, long asyncId)
			throws IOException;

}
