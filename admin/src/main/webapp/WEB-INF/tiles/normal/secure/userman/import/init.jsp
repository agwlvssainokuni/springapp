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
<h1 class="app-subject">
	<s:message code="secure/userman/import/index.message.0" />
</h1>
<s:hasBindErrors name="usermanImportForm">
	<div class="app-portion ui-state-error">
		<f:errors path="usermanImportForm" element="div" />
		<f:errors path="usermanImportForm.file" element="div" />
	</div>
</s:hasBindErrors>
<div class="app-portion">
	<f:form servletRelativeAction="/secure/userman/import/execute"
		method="POST" modelAttribute="usermanImportForm"
		enctype="multipart/form-data">
		<table>
			<tbody class="app-transparent">
				<tr>
					<td><label for="file"><s:message
								code="usermanImportForm.file" /></label></td>
					<td><input id="file" name="file" type="file"
						class="app-width30"></td>
				</tr>
			</tbody>
			<tfoot class="app-transparent">
				<tr>
					<td></td>
					<td>
						<button type="submit" class="app-button">
							<s:message code="secure/userman/import/index.importButton" />
						</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</f:form>
</div>
