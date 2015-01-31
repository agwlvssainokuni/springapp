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

import cherry.foundation.type.Code;

public interface NumberingManager {

	<T extends Code<String>> String issueAsString(T numberEnum);

	<T extends Code<String>> String[] issueAsString(T numberEnum, int count);

	String issueAsString(String numberName);

	String[] issueAsString(String numberName, int count);

	<T extends Code<String>> long issueAsLong(T numberEnum);

	<T extends Code<String>> long[] issueAsLong(T numberEnum, int count);

	long issueAsLong(String numberName);

	long[] issueAsLong(String numberName, int count);

}
