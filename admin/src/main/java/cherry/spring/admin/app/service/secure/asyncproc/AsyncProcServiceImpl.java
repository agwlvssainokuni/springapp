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

package cherry.spring.admin.app.service.secure.asyncproc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cherry.spring.common.db.app.mapper.AsyncProcMapper;
import cherry.spring.common.db.gen.dto.AsyncProcs;
import cherry.spring.common.lib.pager.PageSet;
import cherry.spring.common.lib.pager.Paginator;

@Component
public class AsyncProcServiceImpl implements AsyncProcService {

	@Autowired
	private AsyncProcMapper asyncProcMapper;

	@Autowired
	private Paginator paginator;

	@Transactional
	@Override
	public Result searchAsyncProc(int pageNo, int pageSz) {

		int count = asyncProcMapper.countAsyncProc();
		PageSet pageSet = paginator.paginate(pageNo, count, pageSz);
		int offset = pageSet.getCurrent().getFrom();
		List<AsyncProcs> list = asyncProcMapper.searchAsyncProc(pageSz, offset);

		Result result = new Result();
		result.setPageSet(pageSet);
		result.setAsyncProcList(list);
		return result;
	}

}
