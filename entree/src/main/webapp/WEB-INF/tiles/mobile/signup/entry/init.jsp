<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1>
	<s:message code="signup/entry/init.message.0" />
</h1>
<s:hasBindErrors name="signupEntryForm">
	<div class="error">
		<f:errors path="signupEntryForm" element="div" />
		<f:errors path="signupEntryForm.email" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/signup/execute" method="POST" modelAttribute="signupEntryForm">
	<div data-role="fieldcontain">
		<f:label path="email">
			<s:message code="signupEntryForm.email" />
		</f:label>
		<f:input path="email" cssErrorClass="error" />
	</div>
	<f:button type="submit">
		<s:message code="signup/entry/init.entryButton" />
	</f:button>
</f:form>
