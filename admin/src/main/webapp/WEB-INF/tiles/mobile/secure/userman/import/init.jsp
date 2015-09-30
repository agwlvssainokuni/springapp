<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1>
	<s:message code="secure/userman/import/init.message.0" />
</h1>
<s:hasBindErrors name="usermanImportForm">
	<div class="error">
		<f:errors path="usermanImportForm" element="div" />
		<f:errors path="usermanImportForm.file" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/userman/import/execute" method="POST" modelAttribute="usermanImportForm" enctype="multipart/form-data">
	<div class="ui-field-contain">
		<f:label path="file">
			<s:message code="usermanImportForm.file" />
		</f:label>
		<f:input type="file" path="file" />
	</div>
	<f:button type="submit">
		<s:message code="secure/userman/import/init.importButton" />
	</f:button>
</f:form>
