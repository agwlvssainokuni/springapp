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

package cherry.foundation.validator;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;

/**
 * 単項目バリデーション適用機能。<br />
 * Spring MVCの枠組みの外でアノテーション指定に基づき単項目チェックする機能を提供する。
 */
public interface DataBinderHelper {

	/**
	 * 対象オブジェクトに値を格納し (bind)、該当プロパティに指定されたアノテーションに基づき単項目チェックする (validate)。
	 * 
	 * @param target 対象オブジェクト。
	 * @param pvs 対象オブジェクトに格納する値。
	 * @return 単項目チェックの結果。
	 */
	BindingResult bindAndValidate(Object target, PropertyValues pvs);

	/**
	 * 対象オブジェクトに値を格納し (bind)、該当プロパティに指定されたアノテーションに基づき単項目チェックする (validate)。
	 * 
	 * @param target 対象オブジェクト。
	 * @param objectName 対象オブジェクトの名称 (物理名称 = 項目名を名前解決する際のForm名)。
	 * @param pvs 対象オブジェクトに格納する値。
	 * @return 単項目チェックの結果。
	 */
	BindingResult bindAndValidate(Object target, String objectName, PropertyValues pvs);

	/**
	 * 単項目チェックの結果を全てエラーメッセージ (文字列) として取り出す。
	 * 
	 * @param binding 単項目チェックの結果。
	 * @param locale 取り出すメッセージ (文字列) の言語指定。
	 * @return エラーメッセージ (文字列) のリスト。
	 */
	List<String> resolveAllMessage(BindingResult binding, Locale locale);

	/**
	 * 単項目チェックの結果のうちフィールドに紐付かないものをエラーメッセージ (文字列) として取り出す。
	 * 
	 * @param binding 単項目チェックの結果。
	 * @param locale 取り出すメッセージ (文字列) の言語指定。
	 * @return エラーメッセージ (文字列) のリスト。
	 */
	List<String> resolveGlobalMessage(BindingResult binding, Locale locale);

	/**
	 * 単項目チェックの結果のうちフィールドに紐付くものをエラーメッセージ (文字列) として取り出す。
	 * 
	 * @param binding 単項目チェックの結果。
	 * @param locale 取り出すメッセージ (文字列) の言語指定。
	 * @return エラーメッセージ (文字列) のリスト。
	 */
	List<String> resolveFieldMessage(BindingResult binding, Locale locale);

	/**
	 * 単項目の結果のうち指定されたフィールドに紐付くものをエラーメッセージ (文字列) として取り出す。
	 * 
	 * @param binding 単項目チェックの結果。
	 * @param field フィールド名称 (物理名称)。
	 * @param locale 取り出すメッセージ (文字列) の言語指定。
	 * @return エラーメッセージ (文字列) のリスト。
	 */
	List<String> resolveFieldMessage(BindingResult binding, String field, Locale locale);

}
