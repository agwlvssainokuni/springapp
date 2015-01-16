/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.code;

import java.util.List;
import java.util.Map;

import cherry.foundation.type.Code;

/**
 * 区分値管理機能。<br />
 */
public interface CodeManager {

	<T extends Code<String>> boolean isValidValue(T codeEnum, String value);

	boolean isValidValue(String codeName, String value);

	<T extends Code<String>> CodeEntry findByValue(T codeEnum, String value);

	<T extends Code<String>> CodeEntry findByValue(T codeEnum, String value, boolean plainLabel);

	CodeEntry findByValue(String codeName, String value);

	CodeEntry findByValue(String codeName, String value, boolean plainLabel);

	<T extends Code<String>> List<CodeEntry> getCodeList(T codeEnum);

	<T extends Code<String>> List<CodeEntry> getCodeList(T codeEnum, boolean plainLabel);

	List<CodeEntry> getCodeList(String codeName);

	List<CodeEntry> getCodeList(String codeName, boolean plainLabel);

	<T extends Code<String>> Map<String, String> getCodeMap(T codeEnum);

	<T extends Code<String>> Map<String, String> getCodeMap(T codeEnum, boolean plainLabel);

	Map<String, String> getCodeMap(String codeName);

	Map<String, String> getCodeMap(String codeName, boolean plainLabel);

}
