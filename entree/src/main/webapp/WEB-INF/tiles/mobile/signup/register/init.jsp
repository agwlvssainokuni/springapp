<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1>
	<s:message code="signup/register/init.message.0" />
</h1>
<s:hasBindErrors name="signupRegisterForm">
	<div class="error">
		<f:errors path="signupRegisterForm" element="div" />
		<f:errors path="signupRegisterForm.email" element="div" />
		<f:errors path="signupRegisterForm.lastName" element="div" />
		<f:errors path="signupRegisterForm.firstName" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/signup/${token}/execute" method="POST" modelAttribute="signupRegisterForm">
	<div data-role="fieldcontain">
		<f:label path="email">
			<s:message code="signupRegisterForm.email" />
		</f:label>
		<f:input path="email" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<f:label path="lastName">
			<s:message code="signupRegisterForm.lastName" />
		</f:label>
		<f:input path="lastName" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<f:label path="firstName">
			<s:message code="signupRegisterForm.firstName" />
		</f:label>
		<f:input path="firstName" cssErrorClass="error" />
	</div>
	<f:button type="submit">
		<s:message code="signup/register/init.registerButton" />
	</f:button>
</f:form>
