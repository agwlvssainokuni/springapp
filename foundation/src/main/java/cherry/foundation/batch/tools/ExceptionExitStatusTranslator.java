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

package cherry.foundation.batch.tools;

import cherry.foundation.batch.ExitStatus;

import com.google.common.base.Optional;

/**
 * バッチプログラムの起動および実行にあたり発生した例外を終了ステータスへ変換する。
 */
public interface ExceptionExitStatusTranslator {

	/**
	 * 例外を終了ステータスへ変換する。
	 * 
	 * @param ex　変換対称の例外。
	 * @return 終了ステータス。
	 */
	Optional<ExitStatus> translate(Exception ex);

}
