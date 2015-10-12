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

	// 単純画面遷移
	public static final String URI_SIMPLE_EX10 = "/secure/simple/ex10";
	public static final String VIEW_SIMPLE_EX10_START = "secure/simple/ex10/start";
	public static final String URI_SIMPLE_EX11 = "/secure/simple/ex11";
	public static final String VIEW_SIMPLE_EX11_START = "secure/simple/ex11/start";

	public static final String URI_SIMPLE_EX20 = "/secure/simple/ex20";
	public static final String VIEW_SIMPLE_EX20_START = "secure/simple/ex20/start";
	public static final String URI_SIMPLE_EX21 = "/secure/simple/ex21";
	public static final String VIEW_SIMPLE_EX21_START = "secure/simple/ex21/start";

	public static final String URI_SIMPLE_EX30 = "/secure/simple/ex30";
	public static final String VIEW_SIMPLE_EX30_START = "secure/simple/ex30/start";

	// サブURI
	/** サブURI: /start */
	public static final String SUBURI_START = "start";
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
