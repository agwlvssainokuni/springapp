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

package cherry.spring.site;

public enum LogicError {
	/** サインアップ：回数制限超過 */
	SignupTooManyRequest,
	/** サインアップ：エントリが不適合 */
	SignupEntryUnmatch,
	/** パスワード変更：新パスワードの入力値が不適合 */
	NewPasswordUnmatch,
	/** パスワード変更：現行ログインIDまたはパスワードが不適切 */
	CurAuthFailed,
	// ダミー
	DUMMY
}
