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

package cherry.foundation.numbering;

import org.springframework.transaction.annotation.Transactional;

import cherry.foundation.type.Code;

public class NumberingManagerImpl implements NumberingManager {

	@Transactional
	@Override
	public <T extends Code<String>> String issueAsString(T numberEnum) {
		return issueAsString(numberEnum.code());
	}

	@Transactional
	@Override
	public <T extends Code<String>> String[] issueAsString(T numberEnum, int count) {
		return issueAsString(numberEnum.code(), count);
	}

	@Transactional
	@Override
	public String issueAsString(String numberName) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional
	@Override
	public String[] issueAsString(String numberName, int count) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Transactional
	@Override
	public <T extends Code<String>> long issueAsLong(T numberEnum) {
		return issueAsLong(numberEnum.code());
	}

	@Transactional
	@Override
	public <T extends Code<String>> long[] issueAsLong(T numberEnum, int count) {
		return issueAsLong(numberEnum.code(), count);
	}

	@Transactional
	@Override
	public long issueAsLong(String numberName) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Transactional
	@Override
	public long[] issueAsLong(String numberName, int count) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
