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
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;

import cherry.foundation.querydsl.QueryConfigurer;

import com.mysema.query.types.Expression;

/**
 * ダウンロードフレームワーク。<br />
 * DBMSに発行したクエリ (SELECT文) の結果をダウンロードする機能を実現する際に発生する典型的な処理手順を共通機能として提供する。具体的には、以下の処理を実行する。
 * <ul>
 * <li>HTTPレスポンスヘッダに、ダウンロードするデータに関する情報を返却する。</li>
 * <li>HTTPレスポンスボディに、ダウンロードするデータの実体を返却する。
 * <ul>
 * <li>DBMSにクエリ (SELECT文) を発行する。</li>
 * <li>クエリの結果を読み取り、「1つのDBカラムを1つの列へ」「1つのDBレコードを1行へ」それぞれ対応するよう、CSV形式、あるいは、MS-Excel形式(.xls, .xlsx)のデータを形成する。</li>
 * <li>形成したデータを、HTTPレスポンスボディに返却する。</li>
 * <li>
 * なお、データの形成にあたっては、CSV形式は、1つのDBレコードを読むごとに、1行分のCSVデータを形成して、都度返却する (メモリ上に蓄積しない)。また、MS-Excel形式は、全DBレコードを含むデータを形成してから、
 * まとめて返却する (メモリ上に蓄積する)。MS-Excel形式でダウンロードする場合は、クライアントのリクエストタイムアウトの制約、および、サーバのメモリ割当量の制約に留意すること。
 * </ul>
 * </li>
 * </ul>
 */
public interface TableDownloadOperation {

	/**
	 * DBMSに発行したクエリ (SELECT文) の結果を、CSV形式でダウンロードする機能の典型的な処理手順を共通機能として提供する。<br />
	 * 具体的には、以下のの二つの処理を実行する。
	 * <ul>
	 * <li>DBMSにクエリ (SELECT文) を発行する。</li>
	 * <li>クエリの結果を読み取り、「1つのDBカラムを1つの列へ」「1つのDBレコードを1行へ」それぞれ対応するよう、CSV形式のデータを形成する。
	 * データの形成にあたっては、1つのDBレコードを読むごとに、1行分のCSVデータを形成して、都度返却する (メモリ上に蓄積しない)。</li>
	 * <li>形成したデータを、HTTPレスポンスボディに返却する。</li>
	 * </ul>
	 * 
	 * @param response ダウンロードするデータの返却先。
	 * @param charset ダウンロードするデータの文字コード。バイナリデータを返却する場合は指定してはならない。HTTPヘッダ「Content-Type」に指定される。
	 * @param filename ダウンロードするデータのファイル名のテンプレート。後続の引数で指定する日時を{@link MessageFormat}
	 *            で埋め込んだものを実際のファイル名とする。HTTPヘッダ「Content-Disposition」に指定される。
	 * @param timestamp ダウンロードするデータのファイル名に埋め込む日時。
	 * @param header 先頭行に出力する文字データ。nullを指定した場合は、出力しない。
	 * @param commonClause 発行するクエリの共通部分 (FROM句、WHERE句、GROUP BY句、HAVING句) を形成する。
	 * @param orderByClause 発行するクエリのORDER BY句を形成する。
	 * @param expressions 発行するクエリのSELECT句を指定する。
	 */
	void downloadCsv(HttpServletResponse response, Charset charset, String filename, LocalDateTime timestamp,
			List<String> header, QueryConfigurer commonClause, QueryConfigurer orderByClause,
			Expression<?>... expressions);

	/**
	 * DBMSに発行したクエリ (SELECT文) の結果を、CSV形式でダウンロードする機能の典型的な処理手順を共通機能として提供する。<br />
	 * 具体的には、以下のの二つの処理を実行する。
	 * <ul>
	 * <li>DBMSにクエリ (SELECT文) を発行する。</li>
	 * <li>クエリの結果を読み取り、「1つのDBカラムを1つの列へ」「1つのDBレコードを1行へ」それぞれ対応するよう、MS-Excel形式(.xls)のデータを形成する。
	 * データの形成にあたっては、全DBレコードを含むデータを形成してから、まとめて返却する (メモリ上に蓄積する)。クライアントのリクエストタイムアウトの制約、および、サーバのメモリ割当量の制約に留意すること。</li>
	 * <li>形成したデータを、HTTPレスポンスボディに返却する。</li>
	 * </ul>
	 * 
	 * @param response ダウンロードするデータの返却先。
	 * @param filename ダウンロードするデータのファイル名のテンプレート。後続の引数で指定する日時を{@link MessageFormat}
	 *            で埋め込んだものを実際のファイル名とする。HTTPヘッダ「Content-Disposition」に指定される。
	 * @param timestamp ダウンロードするデータのファイル名に埋め込む日時。
	 * @param header 先頭行に出力する文字データ。nullを指定した場合は、出力しない。
	 * @param commonClause 発行するクエリの共通部分 (FROM句、WHERE句、GROUP BY句、HAVING句) を形成する。
	 * @param orderByClause 発行するクエリのORDER BY句を形成する。
	 * @param expressions 発行するクエリのSELECT句を指定する。
	 */
	void downloadXls(HttpServletResponse response, String filename, LocalDateTime timestamp, List<String> header,
			QueryConfigurer commonClause, QueryConfigurer orderByClause, Expression<?>... expressions);

	/**
	 * DBMSに発行したクエリ (SELECT文) の結果を、CSV形式でダウンロードする機能の典型的な処理手順を共通機能として提供する。<br />
	 * 具体的には、以下のの二つの処理を実行する。
	 * <ul>
	 * <li>DBMSにクエリ (SELECT文) を発行する。</li>
	 * <li>クエリの結果を読み取り、「1つのDBカラムを1つの列へ」「1つのDBレコードを1行へ」それぞれ対応するよう、MS-Excel形式(.xlsx)のデータを形成する。
	 * データの形成にあたっては、全DBレコードを含むデータを形成してから、まとめて返却する (メモリ上に蓄積する)。クライアントのリクエストタイムアウトの制約、および、サーバのメモリ割当量の制約に留意すること。</li>
	 * <li>形成したデータを、HTTPレスポンスボディに返却する。</li>
	 * </ul>
	 * 
	 * @param response ダウンロードするデータの返却先。
	 * @param filename ダウンロードするデータのファイル名のテンプレート。後続の引数で指定する日時を{@link MessageFormat}
	 *            で埋め込んだものを実際のファイル名とする。HTTPヘッダ「Content-Disposition」に指定される。
	 * @param timestamp ダウンロードするデータのファイル名に埋め込む日時。
	 * @param header 先頭行に出力する文字データ。nullを指定した場合は、出力しない。
	 * @param commonClause 発行するクエリの共通部分 (FROM句、WHERE句、GROUP BY句、HAVING句) を形成する。
	 * @param orderByClause 発行するクエリのORDER BY句を形成する。
	 * @param expressions 発行するクエリのSELECT句を指定する。
	 */
	void downloadXlsx(HttpServletResponse response, String filename, LocalDateTime timestamp, List<String> header,
			QueryConfigurer commonClause, QueryConfigurer orderByClause, Expression<?>... expressions);

}
