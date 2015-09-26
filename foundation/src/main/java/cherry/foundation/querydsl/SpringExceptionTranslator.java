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

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.support.SQLExceptionTranslator;

public class SpringExceptionTranslator implements com.mysema.query.sql.SQLExceptionTranslator {

	private SQLExceptionTranslator exceptionTranslator;

	public void setExceptionTranslator(SQLExceptionTranslator exceptionTranslator) {
		this.exceptionTranslator = exceptionTranslator;
	}

	@Override
	public RuntimeException translate(String sql, List<Object> bindings, SQLException e) {
		return exceptionTranslator.translate("querydsl", sql, e);
	}

	@Override
	public RuntimeException translate(SQLException e) {
		return exceptionTranslator.translate("querydsl", null, e);
	}

}
