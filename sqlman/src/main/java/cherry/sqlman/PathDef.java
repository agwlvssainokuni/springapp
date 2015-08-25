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

public class PathDef {

	// 利用者画面
	public static final String URI_TOOL = "/tool/**";

	public static final String URI_TOOL_HOME = "/tool";

	public static final String URI_TOOL_PASSWORD = "/tool/password";

	public static final String URI_TOOL_SEARCH = "/tool/search";

	public static final String URI_TOOL_CLAUSE = "/tool/clause";

	public static final String URI_TOOL_CLAUSE_ID = "/tool/clause/{id}";

	public static final String URI_TOOL_STATEMENT = "/tool/statement";

	public static final String URI_TOOL_STATEMENT_ID = "/tool/statement/{id}";

	public static final String URI_TOOL_LOAD = "/tool/load";

	public static final String URI_TOOL_LOAD_ID = "/tool/load/{id}";

	public static final String VIEW_TOOL_HOME = "tool/home";

	public static final String VIEW_TOOL_PASSWORD = "tool/password/page";

	public static final String VIEW_TOOL_SEARCH = "tool/search/page";

	public static final String VIEW_TOOL_CLAUSE = "tool/clause/page";

	public static final String VIEW_TOOL_CLAUSE_ID = "tool/clause/pageId";

	public static final String VIEW_TOOL_CLAUSE_ID_EDIT = "tool/clause/editId";

	public static final String VIEW_TOOL_STATEMENT = "tool/statement/page";

	public static final String VIEW_TOOL_STATEMENT_ID = "tool/statement/pageId";

	public static final String VIEW_TOOL_STATEMENT_ID_EDIT = "tool/statement/editId";

	public static final String VIEW_TOOL_LOAD = "tool/load/page";

	public static final String VIEW_TOOL_LOAD_ID = "tool/load/pageId";

	public static final String VIEW_TOOL_LOAD_ID_EDIT = "tool/load/editId";

	// 管理者画面
	public static final String URI_ADMIN = "/admin/**";

	public static final String URI_ADMIN_HOME = "/admin";

	public static final String URI_ADMIN_USER = "/admin/user";

	public static final String URI_ADMIN_MAILMGMT = "/admin/mailmgmt";

	public static final String VIEW_ADMIN_HOME = "admin/home";

	public static final String VIEW_ADMIN_USER_ADD = "admin/user/add";

	public static final String VIEW_ADMIN_USER_EDIT = "admin/user/edit";

	public static final String VIEW_ADMIN_USER_SEARCH = "admin/user/search";

	// サブURI
	public static final String SUBURI_START = "start";

	public static final String SUBURI_EXECUTE = "execute";

	public static final String SUBURI_EDIT = "edit";

	public static final String SUBURI_UPDATE = "update";

	public static final String SUBURI_METADATA = "metadata";

	// メソッド識別パラメタ
	public static final String METHOD_DOWNLOAD = "download";

	public static final String METHOD_CREATE = "create";

	public static final String METHOD_UPDATE = "update";

	// リクエストパラメタ
	public static final String PARAM_REF = "ref";

	public static final String PARAM_ID = "id";

	public static final String PATHVAR_ID = "id";

	// ログイン画面
	public static final String URI_LOGIN = "/login/start";

	public static final String METHOD_LOGIN_FAILED = "loginFailed";

	public static final String METHOD_LOGGED_OUT = "loggedOut";

	// パスワード発行画面
	public static final String URI_PASSWORD = "/password";

	public static final String VIEW_PASSWORD_START = "password/start";

	public static final String VIEW_PASSWORD_EDIT = "password/edit";

	// リクエストパラメタ
	public static final String PARAM_TOKEN = "token";

}
