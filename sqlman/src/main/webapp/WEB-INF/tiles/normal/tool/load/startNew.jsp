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
				<a data-toggle="collapse" href="#loadForm">SQL指定</a>
			</h3>
		</div>
		<div id="loadForm" class="panel-collapse collapse in">
			<div class="panel-body">
				<c:if test="${fileProcessResult != null}">
					<div class="col-sm-offset-2 col-sm-10">
						<div class=" alert alert-success" role="alert">
							<s:message text="全 {0} 件、OK {1} 件、NG {2} 件を処理しました。"
								arguments="${fileProcessResult.totalCount},${fileProcessResult.okCount},${fileProcessResult.ngCount}" />
						</div>
					</div>
				</c:if>
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
				<f:form servletRelativeAction="${baseUri}/executeNew" method="POST" modelAttribute="sqlLoadForm" enctype="multipart/form-data"
					cssClass="form-horizontal" role="form">
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
					<div class="form-group ${hasError}">
						<div>
							<f:label path="file" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">CSVファイル</f:label>
						</div>
						<div class="col-sm-10">
							<f:input path="file" type="file" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">SQL実行</f:button>
							<f:button type="submit" name="create" class="btn btn-default">登録</f:button>
						</div>
					</div>
				</f:form>
			</div>
		</div>
	</div>
</div>
