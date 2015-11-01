/*
 * Copyright 2015 agwlvssainokuni
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

package cherry.example.web;

public class PathDef {

	// ログイン画面
	/** ログイン画面のURI */
	public static final String URI_LOGIN = "/login";

	// ホーム画面
	/** ホーム画面のURI */
	public static final String URI_HOME = "/secure";
	/** ホーム画面のビュー */
	public static final String VIEW_HOME = "secure/home";

	public static final String URI_NAVI = "/secure/navi";

	// 基本画面遷移: 単票入力系1
	public static final String URI_BASIC_EX10 = "/secure/basic/ex10";
	public static final String URI_BASIC_EX11 = "/secure/basic/ex11";

	// 基本画面遷移: 単票入力系2
	public static final String URI_BASIC_EX20 = "/secure/basic/ex20";
	public static final String URI_BASIC_EX21 = "/secure/basic/ex21";

	// 基本画面遷移: 検索一覧系1
	public static final String URI_BASIC_EX30 = "/secure/basic/ex30";
	public static final String URI_BASIC_EX31 = "/secure/basic/ex31";

	// 基本画面遷移: 検索一覧系2
	public static final String URI_BASIC_EX40 = "/secure/basic/ex40";
	public static final String URI_BASIC_EX41 = "/secure/basic/ex41";

	// 基本画面遷移: 一括変更系1
	public static final String URI_BASIC_EX50 = "/secure/basic/ex50";
	public static final String URI_BASIC_EX51 = "/secure/basic/ex51";

	public static final String URI_BASIC_EX52 = "/secure/basic/ex52";
	public static final String URI_BASIC_EX53 = "/secure/basic/ex53";

	// 基本画面遷移: 一括変更系2
	public static final String URI_BASIC_EX60 = "/secure/basic/ex60";
	public static final String URI_BASIC_EX61 = "/secure/basic/ex61";

	// 基本画面遷移: アップロード
	public static final String URI_BASIC_EX90 = "/secure/basic/ex90";

	// 応用画面遷移：単票入力系1
	public static final String URI_APPLIED_EX10 = "/secure/applied/ex10";
	public static final String URI_APPLIED_EX11 = "/secure/applied/ex11";
	public static final String URI_APPLIED_EX12 = "/secure/applied/ex12";
	public static final String URI_APPLIED_EX13 = "/secure/applied/ex13";

	// 応用画面遷移：単票入力系2
	public static final String URI_APPLIED_EX20 = "/secure/applied/ex20";
	public static final String URI_APPLIED_EX21 = "/secure/applied/ex21";
	public static final String URI_APPLIED_EX22 = "/secure/applied/ex22";
	public static final String URI_APPLIED_EX23 = "/secure/applied/ex23";

	// 応用画面遷移: 検索一覧系1
	public static final String URI_APPLIED_EX30 = "/secure/applied/ex30";
	public static final String URI_APPLIED_EX31 = "/secure/applied/ex31";
	public static final String URI_APPLIED_EX32 = "/secure/applied/ex32";

	// 応用画面遷移: 検索一覧系2
	public static final String URI_APPLIED_EX40 = "/secure/applied/ex40";
	public static final String URI_APPLIED_EX41 = "/secure/applied/ex41";
	public static final String URI_APPLIED_EX42 = "/secure/applied/ex42";

	// 応用画面遷移: 一括変更系1
	public static final String URI_APPLIED_EX50 = "/secure/applied/ex50";
	public static final String URI_APPLIED_EX51 = "/secure/applied/ex51";
	public static final String URI_APPLIED_EX52 = "/secure/applied/ex52";

	// 応用画面遷移: 一括変更系2
	public static final String URI_APPLIED_EX60 = "/secure/applied/ex60";
	public static final String URI_APPLIED_EX61 = "/secure/applied/ex61";
	public static final String URI_APPLIED_EX62 = "/secure/applied/ex62";

	// サブURI
	/** サブURI: /start */
	public static final String SUBURI_START = "start";
	/** サブURI: /update */
	public static final String SUBURI_UPDATE = "update";
	/** サブURI: /confirm */
	public static final String SUBURI_CONFIRM = "confirm";
	/** サブURI: /execute */
	public static final String SUBURI_EXECUTE = "execute";
	/** サブURI: /completed */
	public static final String SUBURI_COMPLETED = "completed";

	// リクエストパラメタ
	/** リクエストパラメタ: id */
	public static final String PARAM_ID = "id";

}
