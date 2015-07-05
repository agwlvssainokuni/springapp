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

package cherry.sqlman.tool.shared;

import java.util.LinkedList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import cherry.foundation.etl.Column;
import cherry.foundation.etl.Consumer;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class ResultSet implements Consumer {

	private Column[] header;

	private List<Object[]> recordSet = new LinkedList<>();

	@Override
	public void begin(Column[] col) {
		header = col;
	}

	@Override
	public void consume(Object[] record) {
		recordSet.add(record);
	}

	@Override
	public void end() {
		// NOTHING TO DO
	}

}
