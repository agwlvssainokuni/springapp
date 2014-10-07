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

package cherry.spring.entree.controller;

public class PathDef {

	public static final String URI_LOGIN = "/login";

	public static final String URI_HOME = "/secure";

	public static final String URI_PASSWD = "/secure/passwd";

	public static final String URI_SIGNUP_ENTRY = "/signup";

	public static final String URI_SIGNUP_REGISTER = "/signup/{token}";

	public static final String SUBURI_EXECUTE = "execute";

	public static final String SUBURI_FINISH = "finish";

	public static final String PATH_VAR_TOKEN = "token";

	public static final String VIEW_LOGIN_INIT = "login/init";

	public static final String VIEW_HOME_INIT = "secure/home/init";

	public static final String VIEW_PASSWD_INIT = "secure/passwd/init";

	public static final String VIEW_PASSWD_FINISH = "secure/passwd/finish";

	public static final String VIEW_SIGNUP_ENTRY_INIT = "signup/entry/init";

	public static final String VIEW_SIGNUP_ENTRY_FINISH = "signup/entry/finish";

	public static final String VIEW_SIGNUP_REGISTER_INIT = "signup/register/init";

	public static final String VIEW_SIGNUP_REGISTER_FINISH = "signup/register/finish";

	public static final String METHOD_LOGIN_FAILED = "loginFailed";

	public static final String METHOD_LOGGED_OUT = "loggedOut";

}
