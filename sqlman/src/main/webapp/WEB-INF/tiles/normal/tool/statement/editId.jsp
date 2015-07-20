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
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<s:url var="baseUri" value="/tool/statement/{id}">
	<s:param name="id" value="${id}" />
</s:url>
<foundation:getBean var="dataSourceDef"
	beanTypeName="cherry.sqlman.tool.shared.DataSourceDef" />
<h2 class="page-header">
	<s:message code="tool/statement/page.message.0" />
</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#metadataForm"><s:message
						code="tool/statement/page.message.1" /></a>
			</h3>
		</div>
		<c:set var="hasError">
			<s:hasBindErrors name="sqlMetadataForm">in</s:hasBindErrors>
		</c:set>
		<div id="metadataForm" class="panel-collapse collapse ${hasError}">
			<div class="panel-body">
				<s:hasBindErrors name="sqlMetadataForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqlMetadataForm" element="div" />
							<f:errors path="sqlMetadataForm.name" element="div" />
							<f:errors path="sqlMetadataForm.description" element="div" />
							<f:errors path="sqlMetadataForm.lockVersion" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<f:form servletRelativeAction="${baseUri}/metadata" method="POST"
					modelAttribute="sqlMetadataForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="lockVersion" />
					<c:set var="hasError">
						<s:bind path="name">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="name" cssClass="col-sm-2 control-label">
							<s:message code="sqlMetadataForm.name" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="name" cssClass="form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="name">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="description" cssClass="col-sm-2 control-label">
							<s:message code="sqlMetadataForm.description" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="description" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<f:label path="ownedBy" cssClass="col-sm-2 control-label">
							<s:message code="sqlMetadataForm.ownedBy" />
						</f:label>
						<div class="col-sm-10">
							<f:input path="ownedBy" cssClass="form-control" readonly="true" />
						</div>
					</div>
					<div class="form-group">
						<f:label path="publishedFlg" cssClass="col-sm-2 control-label">
							<s:message code="sqlMetadataForm.publishedFlg" />
						</f:label>
						<div class="col-sm-10">
							<f:checkbox path="publishedFlg" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">
								<s:message code="tool/statement/page.updateButton" />
							</f:button>
							<a href="${baseUri}" class="btn btn-default"><s:message
									code="tool/statement/page.finishButton" /></a>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#statementForm"><s:message
						code="tool/statement/page.message.2" /></a>
			</h3>
		</div>
		<div id="statementForm" class="panel-collapse collapse in">
			<div class="panel-body">
				<s:hasBindErrors name="sqlStatementForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqlStatementForm" element="div" />
							<f:errors path="sqlStatementForm.databaseName" element="div" />
							<f:errors path="sqlStatementForm.sql" element="div" />
							<f:errors path="sqlStatementForm.paramMap" element="div" />
							<f:errors path="sqlStatementForm.lockVersion" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<f:form servletRelativeAction="${baseUri}/update" method="POST"
					modelAttribute="sqlStatementForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="lockVersion" />
					<c:set var="hasError">
						<s:bind path="databaseName">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="databaseName" cssClass="col-sm-2 control-label">
							<s:message code="sqlStatementForm.databaseName" />
						</f:label>
						<div class="col-sm-10">
							<f:select path="databaseName" cssClass="form-control">
								<f:options items="${dataSourceDef.names}" />
							</f:select>
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="sql">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="sql" cssClass="col-sm-2 control-label">
							<s:message code="sqlStatementForm.sql" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="sql" cssClass="form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="paramMap">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="paramMap" cssClass="col-sm-2 control-label">
							<s:message code="sqlStatementForm.paramMap" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="paramMap" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">
								<s:message code="tool/statement/page.updateButton" />
							</f:button>
							<a href="${baseUri}" class="btn btn-default"><s:message
									code="tool/statement/page.finishButton" /></a>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
</div>
