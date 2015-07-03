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

package cherry.foundation.querydsl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import cherry.foundation.type.DeletedFlag;

import com.mysema.query.sql.types.AbstractType;

public class DeletedFlagType extends AbstractType<DeletedFlag> {

	public DeletedFlagType() {
		super(Types.INTEGER);
	}

	public DeletedFlagType(int type) {
		super(type);
	}

	@Override
	public String getLiteral(DeletedFlag value) {
		return Integer.toString(value.code());
	}

	@Override
	public Class<DeletedFlag> getReturnedClass() {
		return DeletedFlag.class;
	}

	@Override
	public DeletedFlag getValue(ResultSet rs, int startIndex) throws SQLException {
		int value = rs.getInt(startIndex);
		if (rs.wasNull()) {
			return null;
		}
		return new DeletedFlag(value);
	}

	@Override
	public void setValue(PreparedStatement st, int startIndex, DeletedFlag value) throws SQLException {
		st.setInt(startIndex, value.code());
	}

}
