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
	<s:message code="download/index.message.0" />
</h1>
<s:hasBindErrors name="downloadForm">
	<div class="error">
		<f:errors path="downloadForm" element="div" />
		<f:errors path="downloadForm.columnName" element="div" />
		<f:errors path="downloadForm.rowCount" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/download/req" method="POST"
	modelAttribute="downloadForm">
	<div data-role="fieldcontain">
		<label for="columName"><s:message
				code="downloadForm.columnName" /></label>
		<f:input path="columnName" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="rowCount"><s:message code="downloadForm.rowCount" /></label>
		<f:input path="rowCount" cssErrorClass="error" />
	</div>
	<input type="submit" name="submit"
		value="<s:message code="download/index.downloadButton" />" />
</f:form>
