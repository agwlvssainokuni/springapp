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

import cherry.foundation.log.trace.Mask;
import cherry.foundation.log.trace.TraceInterceptor;

/**
 * {@link TraceInterceptor} のテスト.
 */
public interface TraceTest0 {

	void test0();

	int test1(int arg);

	int[] test2(int[] arg);

	String test3(String arg1, String arg2);

	@Mask
	String test4(String arg1, @Mask String arg2);

	void test5() throws Exception;

}
