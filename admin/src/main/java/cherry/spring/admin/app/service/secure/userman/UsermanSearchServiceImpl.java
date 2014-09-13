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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.admin.app.controller.secure.userman.UsermanSearchForm;
import cherry.spring.common.db.gen.dto.User;
import cherry.spring.common.db.gen.dto.UserCriteria;
import cherry.spring.common.db.gen.mapper.UserMapper;
import cherry.spring.common.lib.paginate.PageSet;
import cherry.spring.common.lib.paginate.Paginator;
import cherry.spring.common.lib.util.SqlUtil;

@Component
public class UsermanSearchServiceImpl implements UsermanSearchService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private Paginator paginator;

	private SqlUtil sqlUtil = new SqlUtil();

	@Transactional
	@Override
	public Result searchUsers(UsermanSearchForm form, int pageNo, int pageSz) {

		UserCriteria criteria = createUserCriteria(form);
		int count = userMapper.countByExample(criteria);
		PageSet pageSet = paginator.paginate(pageNo, count, pageSz);
		RowBounds bound = new RowBounds(pageSet.getCurrent().getFrom(), pageSz);
		List<User> list = userMapper.selectByExampleWithRowbounds(criteria,
				bound);

		Result result = new Result();
		result.setPageSet(pageSet);
		result.setUsersList(list);
		return result;
	}

	private UserCriteria createUserCriteria(UsermanSearchForm form) {
		UserCriteria uc = new UserCriteria();
		UserCriteria.Criteria c = uc.createCriteria();
		if (StringUtils.isNotBlank(form.getLoginId())) {
			c.andLoginIdLike(sqlUtil.escapeForLike(form.getLoginId()));
		}
		if (form.getRegisteredFrom() != null) {
			c.andRegisteredAtGreaterThanOrEqualTo(form.getRegisteredFrom());
		}
		if (form.getRegisteredTo() != null) {
			c.andRegisteredAtLessThan(form.getRegisteredTo().plusSeconds(1));
		}
		if (StringUtils.isNotBlank(form.getFirstName())) {
			c.andFirstNameLike(sqlUtil.escapeForLike(form.getFirstName()) + "%");
		}
		if (StringUtils.isNotBlank(form.getLastName())) {
			c.andLastNameLike(sqlUtil.escapeForLike(form.getLastName()) + "%");
		}
		c.andDeletedFlgEqualTo(0);
		return uc;
	}

}
