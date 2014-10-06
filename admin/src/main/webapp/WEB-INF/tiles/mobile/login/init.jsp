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
	<s:message code="login/init.message.0" />
</h1>
<c:if test="${loginFailed}">
	<div class="error">
		<div>
			<s:message code="login/init.message.1" />
		</div>
	</div>
</c:if>
<c:if test="${loggedOut}">
	<div>
		<div>
			<s:message code="login/init.message.2" />
		</div>
	</div>
</c:if>
<form id="loginForm" action="<c:url value="/login/req" />" method="POST">
	<div data-role="fieldcontain">
		<label for="loginId"><s:message code="loginForm.loginId" /></label> <input
			type="text" id="loginId" name="loginId" />
	</div>
	<div data-role="fieldcontain">
		<label for="password"><s:message code="loginForm.password" /></label>
		<input type="password" id="password" name="password" />
	</div>
	<button type="submit">
		<s:message code="login/init.loginButton" />
	</button>
</form>
