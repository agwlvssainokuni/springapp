/*
 * Copyright 2014 agwlvssainokuni
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

package cherry.spring.common.helper.bizdate;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.transaction.annotation.Transactional;

public class BizdateHelperImpl implements BizdateHelper {

	@Autowired
	private BizdateDao bizdateDao;

	@Transactional(readOnly = true)
	@Override
	public LocalDate today() {
		try {
			BizdateDto dto = bizdateDao.findBizdate();
			return dto.getBizdate();
		} catch (IncorrectResultSizeDataAccessException ex) {
			return LocalDate.now();
		}
	}

	@Transactional(readOnly = true)
	@Override
	public LocalDateTime now() {
		try {
			BizdateDto dto = bizdateDao.findBizdate();
			return dto.getCurrentDateTime().plusDays(dto.getOffsetDay())
					.plusHours(dto.getOffsetHour())
					.plusMinutes(dto.getOffsetMinute())
					.plusSeconds(dto.getOffsetSecond());
		} catch (IncorrectResultSizeDataAccessException ex) {
			return LocalDateTime.now();
		}
	}

}
