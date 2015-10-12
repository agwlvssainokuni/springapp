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

import cherry.foundation.logicalerror.ILogicalError;

public enum LogicalError implements ILogicalError {
	/** {1} を指定する時は {0} が必須です。 */
	RequiredWhen,
	/** 登録済みです。{0} を見直してください。 */
	AlreadyExists;

	@Override
	public String code() {
		return name();
	}

}
