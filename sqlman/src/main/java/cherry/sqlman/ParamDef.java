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

package cherry.sqlman;

public class ParamDef {

	// 画面遷移制御
	/** 画面遷移制御: 遷移先 */
	public static final String REQ_TO = "to";

	/** 画面遷移制御: 遷移元(戻り先) */
	public static final String REQ_FM = "fm";

	/** 画面遷移制御: 戻る */
	public static final String REQ_BACK = "back";

	/** 画面遷移制御: ダウンロード */
	public static final String REQ_DOWNLOAD = "download";

	/** 画面遷移制御: 作成 */
	public static final String REQ_CREATE = "create";

	/** 画面遷移制御: 更新 */
	public static final String REQ_UPDATE = "update";

	// 画面引継項目
	/** 画面引継項目: ID */
	public static final String REQ_ID = "id";

	/** 画面引継項目: 複製元指定 */
	public static final String REQ_REF = "ref";

	/** 画面引継項目: 識別トークン */
	public static final String REQ_TOKEN = "token";

	// フラッシュスコープ
	/** フラッシュスコープ: 登録した */
	public static final String FLASH_CREATED = "created";

	/** フラッシュスコープ: 変更した */
	public static final String FLASH_UPDATED = "updated";

	// ログイン制御
	/** ログイン制御: ログインNG */
	public static final String LOGIN_FAILED = "loginFailed";

	/** ログイン制御: ログアウト */
	public static final String LOGGED_OUT = "loggedOut";

	// リクエストパラメタ
	public static final String PATHVAR_ID = "id";

}
