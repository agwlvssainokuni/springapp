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

package cherry.foundation;

import javax.el.VariableMapper;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

public class GetBeanTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 1L;

	private String var;

	private String beanName;

	private Class<?> beanType;

	public void setVar(String var) {
		this.var = var;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public void setBeanType(Class<?> beanType) {
		this.beanType = beanType;
	}

	public void setBeanTypeName(String beanTypeName) throws ClassNotFoundException {
		setBeanType(getClass().getClassLoader().loadClass(beanTypeName));
	}

	@Override
	protected int doStartTagInternal() throws JspException {
		Object bean = getBean(getRequestContext().getWebApplicationContext(), beanName, beanType);
		VariableMapper vm = pageContext.getELContext().getVariableMapper();
		if (vm != null) {
			vm.setVariable(var, null);
		}
		pageContext.setAttribute(var, bean, PageContext.PAGE_SCOPE);
		return SKIP_BODY;
	}

	private Object getBean(WebApplicationContext appCtx, String beanName, Class<?> beanType) throws JspException {
		if (beanName != null) {
			if (beanType != null) {
				return appCtx.getBean(beanName, beanType);
			} else {
				return appCtx.getBean(beanName);
			}
		} else {
			if (beanType != null) {
				return appCtx.getBean(beanType);
			} else {
				throw new JspTagException("beanName and/or beanType must be specified");
			}
		}
	}

}
