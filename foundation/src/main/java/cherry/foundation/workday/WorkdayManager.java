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

package cherry.foundation.workday;

import org.joda.time.LocalDate;

import cherry.foundation.type.Code;

/**
 * 営業日管理機能。<br />
 */
public interface WorkdayManager {

	int getNumberOfWorkday(LocalDate to);

	<T extends Code<String>> int getNumberOfWorkday(T code, LocalDate to);

	int getNumberOfWorkday(String name, LocalDate to);

	int getNumberOfWorkday(LocalDate from, LocalDate to);

	<T extends Code<String>> int getNumberOfWorkday(T code, LocalDate from, LocalDate to);

	int getNumberOfWorkday(String name, LocalDate from, LocalDate to);

	LocalDate getNextWorkday(int numberOfWorkday);

	<T extends Code<String>> LocalDate getNextWorkday(T code, int numberOfWorkday);

	LocalDate getNextWorkday(String name, int numberOfWorkday);

	LocalDate getNextWorkday(LocalDate from, int numberOfWorkday);

	<T extends Code<String>> LocalDate getNextWorkday(T code, LocalDate from, int numberOfWorkday);

	LocalDate getNextWorkday(String name, LocalDate from, int numberOfWorkday);

}
