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

package cherry.foundation.testtool.stub;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class StubInterceptor implements MethodInterceptor {

	private StubRepository repository;

	public void setRepository(StubRepository repository) {
		this.repository = repository;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		if (repository.contains(method)) {
			Stub<?> stub = repository.get(method);
			if (stub.hasNext()) {
				if (stub.isThrowable()) {
					throw stub.nextThrowable().newInstance();
				} else if (stub.isMock()) {
					return method.invoke(stub.nextMock(), invocation.getArguments());
				} else {
					return stub.next();
				}
			}
		}
		return invocation.proceed();
	}

}
