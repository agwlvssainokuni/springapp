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
<c:set var="hasResultList" value="${resultSet != null && pageSet != null}" />
<foundation:getBean var="dataSourceDef" beanTypeName="cherry.sqlman.tool.shared.DataSourceDef" />
<h2 class="page-header">句指定SQL実行</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#clauseForm">SQL指定</a>
			</h3>
		</div>
		<div id="clauseForm" class="panel-collapse collapse ${hasResultList ? '' : 'in'}">
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
				<f:form servletRelativeAction="${baseUri}/executeNew" method="POST" modelAttribute="sqlClauseForm" cssClass="form-horizontal" role="form">
					<f:hidden path="pno" value="0" />
					<f:hidden path="psz" />
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
						<f:label path="paramMap" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">埋込パラメタ</f:label>
						<div class="col-sm-10">
							<f:textarea path="paramMap" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary">SQL実行</f:button>
							<f:button type="submit" name="download" class="btn btn-default">ダウンロード</f:button>
							<f:button type="submit" name="create" class="btn btn-default">SQL登録</f:button>
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
					<a data-toggle="collapse" href="#clauseResult">実行結果</a>
				</h3>
			</div>
			<div id="clauseResult" class="panel-collapse collapse in">
				<f:form servletRelativeAction="${baseUri}/executeNew" method="POST" modelAttribute="sqlClauseForm" id="sqlClause2">
					<f:hidden id="pno2" path="pno" />
					<f:hidden id="psz2" path="psz" />
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
					<div class="row">
						<div class="col-sm-offset-5 col-sm-7 text-right">
							<div>
								<app:pagerDesc pageSet="${pagedList.pageSet}" />
								<app:pageSize form="#sqlClause2" psz="psz" />
							</div>
							<app:pagerLink pageSet="${pageSet}" form="#sqlClause2" pno="pno" />
						</div>
					</div>
					<app:resultSet id="resultSetList" resultSet="${resultSet}" pageSet="${pageSet}" />
					<div class="row">
						<div class="col-sm-offset-5 col-sm-7 text-right">
							<app:pagerLink pageSet="${pageSet}" form="#sqlClause2" pno="pno" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
