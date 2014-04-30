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

package cherry.spring.admin.app.service.secure.userman;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.admin.app.controller.secure.userman.UsermanSearchForm;
import cherry.spring.common.db.app.mapper.UserCondition;
import cherry.spring.common.db.app.mapper.UserMapper;
import cherry.spring.common.db.gen.dto.Users;
import cherry.spring.common.lib.pager.PageSet;
import cherry.spring.common.lib.pager.Paginator;

@Component
public class UsermanSearchServiceImpl implements UsermanSearchService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private Paginator paginator;

	@Transactional
	@Override
	public Result searchUsers(UsermanSearchForm form, int pageNo, int pageSz) {

		UserCondition cond = createUserCondition(form);
		int count = userMapper.countUsers(cond);
		PageSet pageSet = paginator.paginate(pageNo, count, pageSz);
		int offset = pageSet.getCurrent().getFrom();
		List<Users> list = userMapper.searchUsers(cond, pageSz, offset);

		Result result = new Result();
		result.setPageSet(pageSet);
		result.setUsersList(list);
		return result;
	}

	private UserCondition createUserCondition(UsermanSearchForm form) {
		UserCondition cond = new UserCondition();
		cond.setMailAddr(stringCond(form.getMailAddr()));
		cond.setRegisteredFrom(dateFromCond(form.getRegisteredFrom()));
		cond.setRegisteredTo(dateToCond(form.getRegisteredTo()));
		cond.setFirstName(stringCond(form.getFirstName()));
		cond.setLastName(stringCond(form.getLastName()));
		return cond;
	}

	private String stringCond(String string) {
		if (StringUtils.isBlank(string)) {
			return null;
		}
		return string.replaceAll("([%_\\\\])", "\\$1") + "%";
	}

	private Date dateFromCond(LocalDateTime dt) {
		if (dt == null) {
			return null;
		}
		return dt.toDate();
	}

	private Date dateToCond(LocalDateTime dt) {
		if (dt == null) {
			return null;
		}
		return dt.plusSeconds(1).toDate();
	}

}
