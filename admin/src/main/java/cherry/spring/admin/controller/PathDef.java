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

package cherry.spring.admin.controller;

public class PathDef {

	public static final String URI_LOGIN = "/login";

	public static final String URI_HOME = "/secure";

	public static final String URI_ASYNCPROC = "/secure/asyncproc";

	public static final String URI_PWDTOOL = "/secure/pwdtool";

	public static final String URI_USERMAN = "/secure/userman";

	public static final String URI_USERMAN_IMPORT = "/secure/userman/import";

	public static final String URI_USERMAN_SEARCH = "/secure/userman/search";

	public static final String SUBURI_EXECUTE = "execute";

	public static final String SUBURI_FINISH = "finish";

	public static final String SUBURI_ENCODE = "encode";

	public static final String SUBURI_MATCH = "match";

	public static final String VIEW_LOGIN_INIT = "login/init";

	public static final String VIEW_HOME_INIT = "secure/home/init";

	public static final String VIEW_ASYNCPROC_INIT = "secure/asyncproc/init";

	public static final String VIEW_PWDTOOL_INIT = "secure/pwdtool/init";

	public static final String VIEW_USERMAN_INIT = "secure/userman/init";

	public static final String VIEW_USERMAN_SEARCH_INIT = "secure/userman/search/init";

	public static final String VIEW_USERMAN_IMPORT_INIT = "secure/userman/import/init";

	public static final String VIEW_USERMAN_IMPORT_FINISH = "secure/userman/import/finish";

	public static final String METHOD_LOGIN_FAILED = "loginFailed";

	public static final String METHOD_LOGGED_OUT = "download";

	public static final String METHOD_DOWNLOAD = "download";

	public static final String PARAM_NO = "no";

	public static final String PARAM_SZ = "sz";

	public static final String PARAM_PLAIN_TEXT = "plainText";

	public static final String PARAM_ENCODED_TEXT = "encodedText";

}
