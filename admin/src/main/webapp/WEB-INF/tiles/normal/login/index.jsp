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
<h1 class="ui-widget-header">
	<s:message code="login/index.message.0" />
</h1>
<c:if test="${param.err == 'login'}">
	<div class="ui-state-error">
		<div>
			<s:message code="login/index.message.1" />
		</div>
	</div>
</c:if>
<div class="ui-widget">
	<f:form servletRelativeAction="/login/req" method="POST"
		modelAttribute="loginForm">
		<table>
			<tbody>
				<tr>
					<td><label for="loginId"><s:message
								code="loginForm.loginId" /></label></td>
					<td><f:input path="loginId" cssErrorClass="ui-state-error" /></td>
				</tr>
				<tr>
					<td><label for="password"><s:message
								code="loginForm.password" /></label></td>
					<td><f:password path="password" cssErrorClass="ui-state-error" /></td>
				</tr>
			</tbody>
		</table>
		<button type="submit" name="submit" class="app-button">
			<s:message code="login/index.loginButton" />
		</button>
	</f:form>
</div>
