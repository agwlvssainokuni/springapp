<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1>
	<s:message code="secure/passwd/init.message.0" />
</h1>
<s:hasBindErrors name="passwdForm">
	<div class="error">
		<f:errors path="passwdForm" element="div" />
		<f:errors path="passwdForm.loginId" element="div" />
		<f:errors path="passwdForm.password" element="div" />
		<f:errors path="passwdForm.newPassword" element="div" />
		<f:errors path="passwdForm.newPasswordConf" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/passwd/execute" method="POST" modelAttribute="passwdForm">
	<div data-role="fieldcontain">
		<f:label path="loginId">
			<s:message code="passwdForm.loginId" />
		</f:label>
		<f:input path="loginId" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<f:label path="password">
			<s:message code="passwdForm.password" />
		</f:label>
		<f:password path="password" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<f:label path="newPassword">
			<s:message code="passwdForm.newPassword" />
		</f:label>
		<f:password path="newPassword" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<f:label path="newPasswordConf">
			<s:message code="passwdForm.newPasswordConf" />
		</f:label>
		<f:password path="newPasswordConf" cssErrorClass="error" />
	</div>
	<f:button type="submit">
		<s:message code="secure/passwd/init.changeButton" />
	</f:button>
</f:form>
