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

import org.apache.ibatis.annotations.Param;

import cherry.spring.common.db.gen.dto.SignupRequests;
import cherry.spring.common.db.gen.dto.Users;

public interface SignupRequestMapper {

	boolean validateMailAddr(@Param("mailAddr") String mailAddr,
			@Param("intervalInSec") int intervalInSec,
			@Param("rangeInSec") int rangeInSec, @Param("numOfReq") int numOfReq);

	int createSignupRequest(SignupRequests entity);

	boolean validateToken(@Param("mailAddr") String mailAddr,
			@Param("token") String token, @Param("validInSec") int validInSec);

	int createUser(Users entity);

}