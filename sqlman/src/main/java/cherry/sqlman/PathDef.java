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

	public static final String URI_TOOL_SEARCH = "/tool/search";

	public static final String VIEW_TOOL_HOME = "tool/home";

	public static final String VIEW_TOOL_SEARCH = "tool/search/page";

	// 管理者画面
	public static final String URI_ADMIN = "/admin/**";

	public static final String URI_ADMIN_HOME = "/admin";

	public static final String VIEW_ADMIN_HOME = "admin/home";

	// サブURI
	public static final String SUBURI_EXECUTE = "execute";

	// ログイン画面
	public static final String URI_LOGIN = "/login/start";

	public static final String METHOD_LOGIN_FAILED = "loginFailed";

	public static final String METHOD_LOGGED_OUT = "loggedOut";

}
