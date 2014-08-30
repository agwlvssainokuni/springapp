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
	<s:message code="secure/userman/export/index.message.0" />
</h1>
<s:hasBindErrors name="usermanExportForm">
	<div class="error">
		<f:errors path="usermanExportForm" element="div" />
		<f:errors path="usermanExportForm.registeredFrom" element="div" />
		<f:errors path="usermanExportForm.registeredTo" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/userman/export/req" method="POST"
	modelAttribute="usermanExportForm">
	<div data-role="fieldcontain">
		<label for="registeredFrom"><s:message
				code="usermanExportForm.registeredFrom" /></label>
		<f:input path="registeredFrom" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="registeredTo"><s:message
				code="usermanExportForm.registeredTo" /></label>
		<f:input path="registeredTo" cssErrorClass="error" />
	</div>
	<button type="submit">
		<s:message code="secure/userman/export/index.exportButton" />
	</button>
</f:form>
