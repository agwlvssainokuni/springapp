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

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CodeTag implements ApplicationContextAware {

	private static CodeManager codeManager;

	@Override
	public void setApplicationContext(ApplicationContext appCtx) {
		codeManager = appCtx.getBean(CodeManager.class);
	}

	public static CodeEntry getCodeEntry(String codeName, String value) {
		return codeManager.findByValue(codeName, value);
	}

	public static CodeEntry getCodeEntryPlainLabel(String codeName, String value) {
		return codeManager.findByValue(codeName, value, true);
	}

	public static List<CodeEntry> getCodeList(String codeName) {
		return codeManager.getCodeList(codeName);
	}

	public static List<CodeEntry> getCodeListPlainLabel(String codeName) {
		return codeManager.getCodeList(codeName, true);
	}

	public static Map<String, String> getCodeMap(String codeName) {
		return codeManager.getCodeMap(codeName);
	}

	public static Map<String, String> getCodeMapPlainLabel(String codeName) {
		return codeManager.getCodeMap(codeName, true);
	}

}
