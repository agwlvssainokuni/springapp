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

import cherry.foundation.logicalerror.ILogicalError;

public enum LogicError implements ILogicalError {
	/** SQL実行：SQL文不正。 */
	BadSqlGrammer,
	/** パスワード要求：パスワード要求許容範囲超過。 */
	TooManyPasswordRequest,
	/** パスワード要求：パスワード要求不適合。 */
	PasswordRequestUnmatch,
	/** パスワード設定：パスワード確認入力不適合。 */
	PasswordConfUnmatch,
	// ダミー
	DUMMY;

	@Override
	public String code() {
		return name();
	}
}
