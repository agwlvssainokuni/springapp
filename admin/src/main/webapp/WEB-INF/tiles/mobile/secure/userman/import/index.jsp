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
	<s:message code="secure/userman/import/index.message.0" />
</h1>
<s:hasBindErrors name="usermanImportForm">
	<div class="error">
		<f:errors path="usermanImportForm" element="div" />
		<f:errors path="usermanImportForm.file" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/userman/import/req" method="POST"
	modelAttribute="usermanImportForm" enctype="multipart/form-data">
	<div data-role="fieldcontain">
		<label for="file"><s:message code="usermanImportForm.file" /></label>
		<input id="file" name="file" type="file">
	</div>
	<input type="submit" name="submit"
		value="<s:message code="secure/userman/import/index.importButton" />" />
</f:form>
