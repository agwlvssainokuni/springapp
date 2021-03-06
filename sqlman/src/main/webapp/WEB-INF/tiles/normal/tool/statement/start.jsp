<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<s:url var="baseUri" value="/tool/statement" />
<c:set var="hasResultList" value="${resultSet != null && pageSet != null}" />
<foundation:getBean var="dataSourceDef" beanTypeName="cherry.sqlman.tool.shared.DataSourceDef" />
<h2 class="page-header">文指定SQL実行</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#metadataForm">登録情報</a>
			</h3>
		</div>
		<div id="metadataForm" class="panel-collapse collapse">
			<div class="panel-body">
				<s:nestedPath path="sqlMetadataForm">
					<div class="form-horizontal" role="form">
						<div class="form-group">
							<div>
								<f:label path="name" cssClass="col-sm-2 control-label">SQL名称</f:label>
							</div>
							<div class="col-sm-10">
								<f:input path="name" cssClass="form-control" readonly="true" />
							</div>
						</div>
						<div class="form-group">
							<div>
								<f:label path="description" cssClass="col-sm-2 control-label">説明</f:label>
							</div>
							<div class="col-sm-10">
								<f:textarea path="description" cssClass="form-control" readonly="true" />
							</div>
						</div>
						<div class="form-group">
							<div>
								<f:label path="ownedBy" cssClass="col-sm-2 control-label">所有者</f:label>
							</div>
							<div class="col-sm-10">
								<f:input path="ownedBy" cssClass="form-control" readonly="true" />
							</div>
						</div>
						<div class="form-group">
							<div>
								<f:label path="publishedFlg" cssClass="col-sm-2 control-label">公開状況</f:label>
							</div>
							<div class="col-sm-10">
								<f:checkbox path="publishedFlg" cssClass="form-control" disabled="true" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<a href="${baseUri}/edit?id=${param.id}" class="btn btn-default">編集</a>
							</div>
						</div>
					</div>
				</s:nestedPath>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#statementForm">SQL指定</a>
			</h3>
		</div>
		<div id="statementForm" class="panel-collapse collapse ${hasResultList ? '' : 'in'}">
			<div class="panel-body">
				<s:hasBindErrors name="sqlStatementForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqlStatementForm" element="div" />
							<f:errors path="sqlStatementForm.paramMap" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<s:nestedPath path="sqlStatementForm">
					<div class="form-horizontal" role="form">
						<div class="form-group">
							<div>
								<f:label path="databaseName" cssClass="col-sm-2 control-label">DB名称</f:label>
							</div>
							<div class="col-sm-10">
								<f:select path="databaseName" cssClass="form-control" disabled="true">
									<f:options items="${dataSourceDef.names}" />
								</f:select>
							</div>
						</div>
						<div class="form-group">
							<div>
								<f:label path="sql" cssClass="col-sm-2 control-label">SQL</f:label>
							</div>
							<div class="col-sm-10">
								<f:textarea path="sql" cssClass="form-control" readonly="true" />
							</div>
						</div>
					</div>
				</s:nestedPath>
				<f:form servletRelativeAction="${baseUri}/execute?id=${param.id}" method="POST" modelAttribute="sqlStatementForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="lockVersion" />
					<c:set var="hasError">
						<s:bind path="paramMap">${status.isError() ? "has-error" : ""}</s:bind>
					</c:set>
					<div class="form-group ${hasError}">
						<div>
							<f:label path="paramMap" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">埋込パラメタ</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="paramMap" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">SQL実行</f:button>
							<f:button type="submit" name="download" class="btn btn-default">ダウンロード</f:button>
							<a href="${baseUri}/edit?id=${param.id}" class="btn btn-default">編集</a>
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
					<a data-toggle="collapse" href="#statementResult">実行結果</a>
				</h3>
			</div>
			<div id="statementResult" class="panel-collapse collapse in">
				<div class="panel-body">
					<app:resultSet id="resultSetList" resultSet="${resultSet}" pageSet="${pageSet}" />
				</div>
			</div>
		</div>
	</c:if>
</div>
