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

package cherry.spring.common.db.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cherry.spring.common.db.gen.dto.Users;

public interface UserMapper {

	int createUser(@Param("ent") Users entity);

	int updateUser(@Param("id") Integer id, @Param("ent") Users entity);

	int updatePassword(@Param("id") Integer id,
			@Param("password") String password);

	int changePassword(@Param("mailAddr") String mailAddr,
			@Param("password") String password);

	long countUsers(@Param("cond") UserCondition userCondition);

	List<Users> searchUsers(@Param("cond") UserCondition userCondition,
			@Param("limit") Integer limit, @Param("offset") Integer offset);

}
