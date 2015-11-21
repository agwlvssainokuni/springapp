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
<s:url var="baseUri" value="/tool/clause" />
<foundation:getBean var="dataSourceDef" beanTypeName="cherry.sqlman.tool.shared.DataSourceDef" />
<h2 class="page-header">句指定SQL実行</h2>
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
				<a data-toggle="collapse" href="#clauseForm">SQL指定</a>
			</h3>
		</div>
		<div id="clauseForm" class="panel-collapse collapse in">
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
				<f:form servletRelativeAction="${baseUri}/update?id=${param.id}" method="POST" modelAttribute="sqlClauseForm" cssClass="form-horizontal"
					role="form">
					<f:hidden path="pageNo" value="0" />
					<f:hidden path="pageSz" />
					<f:hidden path="lockVersion" />
					<div class="form-group">
						<div>
							<f:label path="databaseName" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">DB名称</f:label>
						</div>
						<div class="col-sm-10">
							<f:select path="databaseName" cssClass="form-control">
								<f:options items="${dataSourceDef.names}" />
							</f:select>
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="select" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">SELECT</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="select" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="from" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">FROM</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="from" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="where" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">WHERE</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="where" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="groupBy" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">GROUP BY</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="groupBy" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="having" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">HAVING</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="having" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="orderBy" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">ORDER BY</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="orderBy" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div>
							<f:label path="paramMap" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">埋込パラメタ</f:label>
						</div>
						<div class="col-sm-10">
							<f:textarea path="paramMap" cssClass="form-control" cssErrorClass="form-control has-error" />
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
</div>
