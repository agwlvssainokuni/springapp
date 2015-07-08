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
<s:url var="baseUri" value="/tool/clause" />
<c:set var="hasResultList"
	value="${resultSet != null && pageSet != null}" />
<foundation:getBean var="dataSourceDef"
	beanTypeName="cherry.sqlman.tool.shared.DataSourceDef" />
<h2 class="page-header">
	<s:message code="tool/clause/page.message.0" />
</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#clauseForm"><s:message
						code="tool/clause/page.message.2" /></a>
			</h3>
		</div>
		<div id="clauseForm"
			class="panel-collapse collapse ${hasResultList ? '' : 'in'}">
			<div class="panel-body">
				<s:hasBindErrors name="sqlClauseForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqlClauseForm" element="div" />
							<f:errors path="sqlClauseForm.databaseName" element="div" />
							<f:errors path="sqlClauseForm.select" element="div" />
							<f:errors path="sqlClauseForm.from" element="div" />
							<f:errors path="sqlClauseForm.where" element="div" />
							<f:errors path="sqlClauseForm.groupBy" element="div" />
							<f:errors path="sqlClauseForm.having" element="div" />
							<f:errors path="sqlClauseForm.orderBy" element="div" />
							<f:errors path="sqlClauseForm.paramMap" element="div" />
							<f:errors path="sqlClauseForm.lockVersion" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<f:form servletRelativeAction="${baseUri}/execute" method="POST"
					modelAttribute="sqlClauseForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="pageNo" value="0" />
					<f:hidden path="pageSz" />
					<f:hidden path="lockVersion" />
					<c:set var="hasError">
						<s:bind path="databaseName">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="databaseName" cssClass="col-sm-2 control-label">
							<s:message code="sqlClauseForm.databaseName" />
						</f:label>
						<div class="col-sm-10">
							<f:select path="databaseName" cssClass="col-sm-2 form-control">
								<f:options items="${dataSourceDef.names}" />
							</f:select>
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="select">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="select" cssClass="col-sm-2 control-label">
							<s:message code="sqlClauseForm.select" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="select" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="from">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="from" cssClass="col-sm-2 control-label">
							<s:message code="sqlClauseForm.from" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="from" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="where">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="where" cssClass="col-sm-2 control-label">
							<s:message code="sqlClauseForm.where" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="where" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="groupBy">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="groupBy" cssClass="col-sm-2 control-label">
							<s:message code="sqlClauseForm.groupBy" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="groupBy" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="having">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="having" cssClass="col-sm-2 control-label">
							<s:message code="sqlClauseForm.having" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="having" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="orderBy">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="orderBy" cssClass="col-sm-2 control-label">
							<s:message code="sqlClauseForm.orderBy" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="orderBy" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<c:set var="hasError">
						<s:bind path="paramMap">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<f:label path="paramMap" cssClass="col-sm-2 control-label">
							<s:message code="sqlClauseForm.paramMap" />
						</f:label>
						<div class="col-sm-10">
							<f:textarea path="paramMap" cssClass="col-sm-2 form-control" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">
								<s:message code="tool/clause/page.execButton" />
							</f:button>
							<f:button type="submit" name="download" class="btn btn-default">
								<s:message code="tool/clause/page.downloadButton" />
							</f:button>
							<f:button type="submit" name="create" class="btn btn-default">
								<s:message code="tool/clause/page.createButton" />
							</f:button>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
	<c:if test="${hasResultList}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<a data-toggle="collapse" href="#clauseResult"><s:message
							code="tool/clause/page.message.3" /></a>
				</h3>
			</div>
			<div id="clauseResult" class="panel-collapse collapse in">
				<f:form servletRelativeAction="${baseUri}/execute" method="POST"
					modelAttribute="sqlClauseForm" id="sqlClause2">
					<f:hidden id="pageNo2" path="pageNo" />
					<f:hidden id="pageSz2" path="pageSz" />
					<f:hidden id="lockVersion2" path="lockVersion" />
					<f:hidden id="databaseName2" path="databaseName" />
					<f:hidden id="select2" path="select" />
					<f:hidden id="from2" path="from" />
					<f:hidden id="where2" path="where" />
					<f:hidden id="groupBy2" path="groupBy" />
					<f:hidden id="having2" path="having" />
					<f:hidden id="orderBy2" path="orderBy" />
					<f:hidden id="paramMap2" path="paramMap" />
				</f:form>
				<div class="panel-body">
					<div>
						<div class="app-pager-desc">
							<s:message code="common/pager.message.0"
								arguments="${pageSet.last.to+1},${pageSet.current.from+1},${pageSet.current.to+1}" />
						</div>
						<app:pagerLink pageSet="${pageSet}" form="#sqlClause2"
							pno="pageNo" />
					</div>
					<app:resultSet id="resultSetList" resultSet="${resultSet}"
						pageSet="${pageSet}" />
					<div>
						<app:pagerLink pageSet="${pageSet}" form="#sqlClause2"
							pno="pageNo" />
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>