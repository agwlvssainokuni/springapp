/*
 * Copyright 2014,2015 agwlvssainokuni
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

package cherry.foundation.springmvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class OperationLogHandlerInterceptor implements HandlerInterceptor, InitializingBean {

	public static final String LOGIN_ID = "loginId";

	public static final String PARAM = "param";

	public static final String OPER_ENTER = "operation.ENTER";

	public static final String OPER_MIDDLE = "operation.MIDDLE";

	public static final String OPER_EXIT = "operation.EXIT";

	private final Logger loggerEnter = LoggerFactory.getLogger(OPER_ENTER);

	private final Logger loggerMiddle = LoggerFactory.getLogger(OPER_MIDDLE);

	private final Logger loggerExit = LoggerFactory.getLogger(OPER_EXIT);

	private List<Pattern> paramPattern;

	private List<String> paramMdcKey;

	public void setParamPattern(List<Pattern> paramPattern) {
		this.paramPattern = paramPattern;
	}

	@Override
	public void afterPropertiesSet() {
		if (paramPattern == null) {
			paramPattern = new ArrayList<>(0);
		}
		paramMdcKey = new ArrayList<>(paramPattern.size());
		for (int i = 0; i < paramPattern.size(); i++) {
			paramMdcKey.add(PARAM + i);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		SecurityContext context = SecurityContextHolder.getContext();
		MDC.put(LOGIN_ID, context.getAuthentication().getName());

		StringBuilder builder = createBasicInfo(request);

		builder.append(" {");
		boolean first = true;
		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {

			String key = entry.getKey();
			String lkey = key.toLowerCase();
			String[] val = entry.getValue();

			if (!first) {
				builder.append(", ");
			}
			first = false;
			builder.append(key).append(": ");
			if (lkey.contains("password")) {
				builder.append("<MASKED>");
			} else {
				builder.append(ToStringBuilder.reflectionToString(val, ToStringStyle.SIMPLE_STYLE));
			}

			for (int i = 0; i < paramPattern.size(); i++) {
				if (paramPattern.get(i).matcher(lkey).matches()) {
					if (val != null && val.length == 1) {
						MDC.put(paramMdcKey.get(i), val[0]);
					} else {
						MDC.put(paramMdcKey.get(i), ToStringBuilder.reflectionToString(val, ToStringStyle.SIMPLE_STYLE));
					}
				}
			}
		}
		builder.append("}");

		loggerEnter.info(builder.toString());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		StringBuilder builder = createBasicInfo(request);

		if (modelAndView == null) {
			builder.append(" ModelAndView=null");
		} else if (modelAndView.hasView()) {
			builder.append(" viewName=").append(modelAndView.getViewName());
		} else {
			builder.append(" noView");
		}

		loggerMiddle.info(builder.toString());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		StringBuilder builder = createBasicInfo(request);
		if (ex == null) {
			loggerExit.info(builder.toString());
		} else {
			loggerExit.info(builder.toString(), ex);
		}

		MDC.remove(LOGIN_ID);
		for (String mdcKey : paramMdcKey) {
			MDC.remove(mdcKey);
		}
	}

	private StringBuilder createBasicInfo(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		return builder.append(request.getRemoteAddr()).append(" ").append(request.getMethod()).append(" ")
				.append(request.getRequestURI());
	}

}
