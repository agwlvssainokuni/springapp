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

package cherry.batch;

import static cherry.goods.log.Level.ERROR;
import static cherry.goods.log.Level.INFO;
import static cherry.goods.log.Level.WARN;
import cherry.goods.log.ILogId;
import cherry.goods.log.Level;

public enum LogId implements ILogId {
	// ここから
	// ここまで
	DMY_INFO(INFO), DMY_WARN(WARN), DMY_ERROR(ERROR);

	private Level level;

	private LogId(Level level) {
		this.level = level;
	}

	@Override
	public String getId() {
		return name();
	}

	@Override
	public Level getLevel() {
		return level;
	}

}
