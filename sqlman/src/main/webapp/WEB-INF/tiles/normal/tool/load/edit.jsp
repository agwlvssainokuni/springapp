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
<s:url var="baseUri" value="/tool/load" />
<foundation:getBean var="dataSourceDef" beanTypeName="cherry.sqlman.tool.shared.DataSourceDef" />
<h2 class="page-header">CSV取込み</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#metadataForm">登録情報</a>
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
				<f:form servletRelativeAction="${baseUri}/metadata?id=${param.id}" method="POST" modelAttribute="sqlMetadataForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="lockVersion" />
					<div class="form-group">
						<div>
							<f:label path="name" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">SQL名称</f:label>
						</div>
						<div class="col-sm-10">
							<f:input path="name" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="description" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">説明</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="description" cssClass="form-control" cssErrorClass="form-control has-error" />
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
							<f:label path="publishedFlg" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">公開状況</f:label>
						</div>
						<div class="col-sm-10">
							<f:checkbox path="publishedFlg" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">更新</f:button>
							<a href="${baseUri}?id=${param.id}" class="btn btn-default">実行へ戻る</a>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#loadForm">SQL指定</a>
			</h3>
		</div>
		<div id="loadForm" class="panel-collapse collapse in">
			<div class="panel-body">
				<s:hasBindErrors name="sqlLoadForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqlLoadForm" element="div" />
							<f:errors path="sqlLoadForm.databaseName" element="div" />
							<f:errors path="sqlLoadForm.sql" element="div" />
							<f:errors path="sqlLoadForm.file" element="div" />
							<f:errors path="sqlLoadForm.lockVersion" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<f:form servletRelativeAction="${baseUri}/update?id=${param.id}" method="POST" modelAttribute="sqlLoadForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="lockVersion" />
					<div class="form-group">
						<div>
							<f:label path="databaseName" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">DB名称</f:label>
						</div>
						<div class="col-sm-10">
							<f:select path="databaseName" cssClass="form-control" cssErrorClass="form-control has-error">
								<f:options items="${dataSourceDef.names}" />
							</f:select>
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="sql" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">SQL</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="sql" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">更新</f:button>
							<a href="${baseUri}?id=${param.id}" class="btn btn-default">実行へ戻る </a>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
</div>
