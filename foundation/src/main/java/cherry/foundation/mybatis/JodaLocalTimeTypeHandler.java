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

package cherry.foundation.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.joda.time.LocalTime;

@MappedTypes(LocalTime.class)
public class JodaLocalTimeTypeHandler extends BaseTypeHandler<LocalTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalTime parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setTime(i, getSqlTime(parameter));
	}

	@Override
	public LocalTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Time time = rs.getTime(columnName);
		if (time == null) {
			return null;
		}
		return getLocalTime(time);
	}

	@Override
	public LocalTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Time time = rs.getTime(columnIndex);
		if (time == null) {
			return null;
		}
		return getLocalTime(time);
	}

	@Override
	public LocalTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Time time = cs.getTime(columnIndex);
		if (time == null) {
			return null;
		}
		return getLocalTime(time);
	}

	private Time getSqlTime(LocalTime ltm) {
		Calendar cal = Calendar.getInstance();
		cal.set(0, 0, 0, ltm.getHourOfDay(), ltm.getMinuteOfHour(), ltm.getSecondOfMinute());
		cal.set(Calendar.MILLISECOND, ltm.getMillisOfSecond());
		return new Time(cal.getTimeInMillis());
	}

	private LocalTime getLocalTime(Time time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		return new LocalTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND),
				cal.get(Calendar.MILLISECOND));
	}

}
