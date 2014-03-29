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
	<s:message code="secure/jfreechart/index.message.0" />
</h1>
<s:hasBindErrors name="jFreeChartForm">
	<div class="ui-state-error">
		<f:errors path="jFreeChartForm" element="div" />
		<f:errors path="jFreeChartForm.file" element="div" />
	</div>
</s:hasBindErrors>
<div class="ui-widget">
	<f:form servletRelativeAction="/secure/jfreechart/req" method="POST"
		modelAttribute="jFreeChartForm" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td><label for="file"><s:message
								code="jFreeChartForm.file" /></label></td>
					<td><input id="file" name="file" type="file"></td>
				</tr>
			</tbody>
		</table>
		<button type="submit" class="app-button">
			<s:message code="secure/jfreechart/index.uploadButton" />
		</button>
	</f:form>
</div>
