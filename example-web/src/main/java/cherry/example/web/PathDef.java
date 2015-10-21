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

	// 基本画面遷移: 単票入力系1
	public static final String URI_BASIC_EX10 = "/secure/basic/ex10";
	public static final String VIEW_BASIC_EX10_START = "secure/basic/ex10/start";
	public static final String URI_BASIC_EX11 = "/secure/basic/ex11";
	public static final String VIEW_BASIC_EX11_START = "secure/basic/ex11/start";

	// 基本画面遷移: 単票入力系2
	public static final String URI_BASIC_EX20 = "/secure/basic/ex20";
	public static final String VIEW_BASIC_EX20_START = "secure/basic/ex20/start";
	public static final String URI_BASIC_EX21 = "/secure/basic/ex21";
	public static final String VIEW_BASIC_EX21_START = "secure/basic/ex21/start";

	// 基本画面遷移: 検索一覧系1
	public static final String URI_BASIC_EX30 = "/secure/basic/ex30";
	public static final String VIEW_BASIC_EX30_START = "secure/basic/ex30/start";
	public static final String URI_BASIC_EX31 = "/secure/basic/ex31";
	public static final String VIEW_BASIC_EX31_START = "secure/basic/ex31/start";

	// 基本画面遷移: 検索一覧系2
	public static final String URI_BASIC_EX40 = "/secure/basic/ex40";
	public static final String VIEW_BASIC_EX40_START = "secure/basic/ex40/start";
	public static final String URI_BASIC_EX41 = "/secure/basic/ex41";
	public static final String VIEW_BASIC_EX41_START = "secure/basic/ex41/start";

	// 基本画面遷移: 一括変更系1
	public static final String URI_BASIC_EX50 = "/secure/basic/ex50";
	public static final String VIEW_BASIC_EX50_START = "secure/basic/ex50/start";
	public static final String URI_BASIC_EX51 = "/secure/basic/ex51";
	public static final String VIEW_BASIC_EX51_START = "secure/basic/ex51/start";

	// 基本画面遷移: 一括変更系2
	public static final String URI_BASIC_EX60 = "/secure/basic/ex60";
	public static final String VIEW_BASIC_EX60_START = "secure/basic/ex60/start";
	public static final String URI_BASIC_EX61 = "/secure/basic/ex61";
	public static final String VIEW_BASIC_EX61_START = "secure/basic/ex61/start";

	// 基本画面遷移: 一括変更系3
	public static final String URI_BASIC_EX70 = "/secure/basic/ex70";
	public static final String VIEW_BASIC_EX70_START = "secure/basic/ex70/start";
	public static final String URI_BASIC_EX71 = "/secure/basic/ex71";
	public static final String VIEW_BASIC_EX71_START = "secure/basic/ex71/start";
	public static final String URI_BASIC_EX72 = "/secure/basic/ex72";
	public static final String VIEW_BASIC_EX72_START = "secure/basic/ex72/start";

	// 基本画面遷移: 一括変更系4
	public static final String URI_BASIC_EX80 = "/secure/basic/ex80";
	public static final String VIEW_BASIC_EX80_START = "secure/basic/ex80/start";
	public static final String URI_BASIC_EX81 = "/secure/basic/ex81";
	public static final String VIEW_BASIC_EX81_START = "secure/basic/ex81/start";
	public static final String URI_BASIC_EX82 = "/secure/basic/ex82";
	public static final String VIEW_BASIC_EX82_START = "secure/basic/ex82/start";

	// 基本画面遷移: アップロード
	public static final String URI_BASIC_EX90 = "/secure/basic/ex90";
	public static final String VIEW_BASIC_EX90_START = "secure/basic/ex90/start";

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
