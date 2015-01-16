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

import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.Code;

/**
 * 区分値管理機能。<br />
 */
public class CodeManagerImpl implements CodeManager {

	private CodeStore codeStore;

	private String valueTemplate;

	public void setCodeStore(CodeStore codeStore) {
		this.codeStore = codeStore;
	}

	public void setValueTemplate(String valueTemplate) {
		this.valueTemplate = valueTemplate;
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> boolean isValidValue(T codeEnum, String value) {
		return isValidValue(codeEnum.code(), value);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean isValidValue(String codeName, String value) {
		return codeStore.isValidValue(codeName, value);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> CodeEntry findByValue(T codeEnum, String value) {
		return findByValue(codeEnum.code(), value, false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> CodeEntry findByValue(T codeEnum, String value, boolean plainLabel) {
		return findByValue(codeEnum.code(), value, plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public CodeEntry findByValue(String codeName, String value) {
		return findByValue(codeName, value, false);
	}

	@Transactional(readOnly = true)
	@Override
	public CodeEntry findByValue(String codeName, String value, boolean plainLabel) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> List<CodeEntry> getCodeList(T codeEnum) {
		return getCodeList(codeEnum.code(), false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> List<CodeEntry> getCodeList(T codeEnum, boolean plainLabel) {
		return getCodeList(codeEnum.code(), plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CodeEntry> getCodeList(String codeName) {
		return getCodeList(codeName, false);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CodeEntry> getCodeList(String codeName, boolean plainLabel) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> Map<String, String> getCodeMap(T codeEnum) {
		return getCodeMap(codeEnum.code(), false);
	}

	@Transactional(readOnly = true)
	@Override
	public <T extends Code<String>> Map<String, String> getCodeMap(T codeEnum, boolean plainLabel) {
		return getCodeMap(codeEnum.code(), plainLabel);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, String> getCodeMap(String codeName) {
		return getCodeMap(codeName, false);
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, String> getCodeMap(String codeName, boolean plainLabel) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
