/*
 * Copyright 2012,2014 agwlvssainokuni
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

package cherry.spring.fwcore.log.trace;

import org.springframework.stereotype.Component;

import cherry.foundation.log.trace.TraceInterceptor;

/**
 * {@link TraceInterceptor} のテスト.
 */
@Component("traceTest0")
public class TraceTest0Impl implements TraceTest0 {

	@Override
	public void test0() {
		return;
	}

	@Override
	public int test1(int arg) {
		return arg;
	}

	@Override
	public int[] test2(int[] arg) {
		return arg;
	}

	@Override
	public String test3(String arg1, String arg2) {
		return "" + arg1 + arg2;
	}

	@Override
	public String test4(String arg1, String arg2) {
		return "" + arg1 + arg2;
	}

	public void test5() throws Exception {
		throw new Exception();
	}

}
