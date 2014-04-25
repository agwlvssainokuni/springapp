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

package cherry.spring.common.service;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

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

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public int createAsyncProc(String name) {
		AsyncProcs entity = new AsyncProcs();
		entity.setName(name);
		int count = asyncProcMapper.createAsyncProc(entity);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not created for name=" + name);
		}
		return entity.getId();
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void invokeAsyncProc(int id) {
		AsyncProcs entity = new AsyncProcs();
		entity.setId(id);
		int count = asyncProcMapper.invokeAsyncProc(entity);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not updated (invoke) for id=" + id);
		}
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void startAsyncProc(int id) {
		AsyncProcs entity = new AsyncProcs();
		entity.setId(id);
		int count = asyncProcMapper.startAsyncProc(entity);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not updated (start) for id=" + id);
		}
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void successAsyncProc(int id, String json) {
		AsyncProcs entity = new AsyncProcs();
		entity.setId(id);
		entity.setResult(json);
		int count = asyncProcMapper.successAsyncProc(entity);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not updated (success) for id=" + id);
		}
	}

	@Transactional(propagation = REQUIRES_NEW)
	@Override
	public void errorAsyncProc(int id, String json) {
		AsyncProcs entity = new AsyncProcs();
		entity.setId(id);
		entity.setResult(json);
		int count = asyncProcMapper.errorAsyncProc(entity);
		if (count != 1) {
			throw new IllegalStateException(
					"async_procs is not updated (error) for id=" + id);
		}
	}

	@Transactional
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
