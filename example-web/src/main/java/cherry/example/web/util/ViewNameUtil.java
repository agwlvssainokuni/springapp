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

package cherry.example.web.util;

import java.lang.reflect.Method;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.MethodInvocationInfo;
import org.springframework.web.util.UriComponentsBuilder;

public class ViewNameUtil {

	public static String fromMethodCall(Object invocationInfo) {

		MethodInvocationInfo info = (MethodInvocationInfo) invocationInfo;
		Method method = info.getControllerMethod();
		Class<?> type = method.getDeclaringClass();

		UriComponentsBuilder ucb = UriComponentsBuilder.newInstance();
		String typePath = getMappedPath(type.getAnnotation(RequestMapping.class));
		if (StringUtils.isNotEmpty(typePath)) {
			ucb.path(typePath);
		}

		String methodPath = getMappedPath(method.getAnnotation(RequestMapping.class));
		if (StringUtils.isNotEmpty(methodPath)) {
			ucb.pathSegment(methodPath);
		}

		String path = ucb.build().getPath();
		if (path.startsWith("/")) {
			return path.substring(1);
		}
		return path;
	}

	public static String getMappedPath(RequestMapping mapping) {
		String[] path = mapping.value();
		if (CollectionUtils.sizeIsEmpty(path)) {
			return null;
		}
		return path[0];
	}

}
