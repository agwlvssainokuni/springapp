<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1>
	<s:message code="passwd/index.message.0" />
</h1>
<s:hasBindErrors name="passwdForm">
	<div class="error">
		<f:errors path="passwdForm" element="div" />
		<f:errors path="passwdForm.password" element="div" />
		<f:errors path="passwdForm.newPassword" element="div" />
		<f:errors path="passwdForm.newPasswordConf" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/passwd/req" method="POST"
	modelAttribute="passwdForm">
	<div data-role="fieldcontain">
		<label for="password"><s:message code="passwdForm.password" /></label>
		<f:password path="password" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="newPassword"><s:message
				code="passwdForm.newPassword" /></label>
		<f:password path="newPassword" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="newPasswordConf"><s:message
				code="passwdForm.newPasswordConf" /></label>
		<f:password path="newPasswordConf" cssErrorClass="error" />
	</div>
	<input type="submit" name="submit"
		value="<s:message code="passwd/index.changeButton" />" />
</f:form>
