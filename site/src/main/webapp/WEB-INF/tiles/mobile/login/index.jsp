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
	<s:message code="login/index.message.0" />
</h1>
<c:if test="${param.err == 'login'}">
	<div class="error">
		<div>
			<s:message code="login/index.message.1" />
		</div>
	</div>
</c:if>
<f:form servletRelativeAction="/login/req" method="POST"
	modelAttribute="loginForm">
	<div data-role="fieldcontain">
		<label for="loginId"><s:message code="loginForm.loginId" /></label>
		<f:input path="loginId" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="password"><s:message code="loginForm.password" /></label>
		<f:password path="password" cssErrorClass="error" />
	</div>
	<input type="submit" name="submit"
		value="<s:message code="login/index.loginButton" />" />
</f:form>
