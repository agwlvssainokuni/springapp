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

package cherry.goods.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

public class ReflectionUtil {

	public static String getClassDescription(Class<?> klass, boolean canonical) {
		if (canonical) {
			return klass.getCanonicalName();
		} else {
			return klass.getSimpleName();
		}
	}

	public static String getMethodDescription(Method method, boolean returnType, boolean declaringClass,
			boolean methodName, boolean paramType, boolean canonical) {

		List<String> desc = new ArrayList<>();
		if (returnType) {
			desc.add(getClassDescription(method.getReturnType(), canonical));
		}

		StringBuilder sb = new StringBuilder();
		if (declaringClass) {
			sb.append(getClassDescription(method.getDeclaringClass(), canonical));
		}
		if (declaringClass && methodName) {
			sb.append(".");
		}
		if (methodName) {
			sb.append(method.getName());
		}
		if (paramType) {
			sb.append("(");
			List<String> param = new ArrayList<>(method.getParameterTypes().length);
			for (Class<?> klass : method.getParameterTypes()) {
				param.add(getClassDescription(klass, canonical));
			}
			Joiner.on(",").appendTo(sb, param);
			sb.append(")");
		}
		desc.add(sb.toString());

		return Joiner.on(" ").join(desc);
	}
}
