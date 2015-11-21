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
<c:set var="hasResultList" value="${pagedList != null && !pagedList.list.isEmpty()}" />
<h2 class="page-header">登録済みSQL検索</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a href="#searchForm" data-toggle="collapse">検索条件</a>
			</h3>
		</div>
		<div id="searchForm" class="panel-collapse collapse ${hasResultList ? '' : 'in'}">
			<div class="panel-body">
				<s:hasBindErrors name="sqlSearchForm">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">
							<f:errors path="sqlSearchForm" element="div" />
							<f:errors path="sqlSearchForm.name" element="div" />
							<f:errors path="sqlSearchForm.registeredFromDt" element="div" />
							<f:errors path="sqlSearchForm.registeredFromTm" element="div" />
							<f:errors path="sqlSearchForm.registeredToDt" element="div" />
							<f:errors path="sqlSearchForm.registeredToTm" element="div" />
						</div>
					</div>
				</s:hasBindErrors>
				<c:if test="${pagedList != null && pagedList.list.isEmpty()}">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="alert alert-danger" role="alert">条件に合致するSQLは登録されていません。</div>
					</div>
				</c:if>
				<f:form servletRelativeAction="/tool/search/execute" method="POST" modelAttribute="sqlSearchForm" cssClass="form-horizontal" role="form">
					<f:hidden path="pageNo" value="0" />
					<f:hidden path="pageSz" />
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
							<label class="col-sm-2 control-label">SQL種別</label>
						</div>
						<div class="col-sm-10">
							<c:forEach var="item" items="${foundation:getLabeledEnumList('cherry.sqlman.SqlType')}">
								<div class="checkbox-inline">
									<f:checkbox path="sqlType" value="${item.enumName}" label="${item.enumLabel}" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<div>
							<label class="col-sm-2 control-label">公開状況</label>
						</div>
						<div class="col-sm-10">
							<c:forEach var="item" items="${foundation:getLabeledEnumList('cherry.sqlman.Published')}">
								<div class="checkbox-inline">
									<f:checkbox path="published" value="${item.enumName}" label="${item.enumLabel}" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="form-group">
						<div>
							<label class="col-sm-2 control-label">登録日時(FROM)</label>
							<f:label path="registeredFromDt" cssClass="hidden" cssErrorClass="hidden has-error"></f:label>
							<f:label path="registeredFromTm" cssClass="hidden" cssErrorClass="hidden has-error"></f:label>
						</div>
						<div class="col-sm-4">
							<f:input path="registeredFromDt" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
						<div class="col-sm-4">
							<f:input path="registeredFromTm" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div>
							<label class="col-sm-2 control-label">登録日時(TO)</label>
							<f:label path="registeredToDt" cssClass="hidden" cssErrorClass="hidden has-error"></f:label>
							<f:label path="registeredToTm" cssClass="hidden" cssErrorClass="hidden has-error"></f:label>
						</div>
						<div class="col-sm-4">
							<f:input path="registeredToDt" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
						<div class="col-sm-4">
							<f:input path="registeredToTm" cssClass="form-control" cssErrorClass="form-control has-error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<f:button type="submit" class="btn btn-primary btn-block">検索</f:button>
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
					<a data-toggle="collapse" href="#searchResult">検索結果</a>
				</h3>
			</div>
			<div id="searchResult" class="panel-collapse collapse in">
				<div class="panel-body">
					<f:form servletRelativeAction="/tool/search/execute" method="POST" modelAttribute="sqlSearchForm" id="sqlSearchForm2">
						<f:hidden id="pageNo2" path="pageNo" />
						<f:hidden id="pageSz2" path="pageSz" />
						<f:hidden id="name2" path="name" />
						<f:hidden id="sqlType2" path="sqlType" />
						<f:hidden id="published2" path="published" />
						<f:hidden id="registeredFromDt2" path="registeredFromDt" />
						<f:hidden id="registeredFromTm2" path="registeredFromTm" />
						<f:hidden id="registeredToDt2" path="registeredToDt" />
						<f:hidden id="registeredToTm2" path="registeredToTm" />
					</f:form>
					<div class="row">
						<div class="col-sm-offset-6 col-sm-6 text-right">
							<app:pagerDesc pageSet="${pagedList.pageSet}" />
							<app:pagerLink pageSet="${pagedList.pageSet}" form="#sqlSearchForm2" pno="pageNo" />
						</div>
					</div>
					<table id="searchResultList" class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>SQL名称</th>
								<th>SQL種別</th>
								<th>DB名称</th>
								<th>登録日時</th>
								<th>公開状況</th>
								<th>所有者</th>
								<th>内容説明</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="count" begin="1" end="${pagedList.list.size()}">
								<s:nestedPath path="pagedList">
									<s:url var="uri" value="/tool/{type}/{id}">
										<s:param name="type">
											<s:bind path="list[${count-1}][sqlType]">${status.value}</s:bind>
										</s:param>
										<s:param name="id">
											<s:bind path="list[${count-1}][id]">${status.value}</s:bind>
										</s:param>
									</s:url>
									<tr>
										<td class="text-right"><c:out value="${pagedList.pageSet.current.from + count}" /></td>
										<td><s:bind path="list[${count-1}][name]">
												<a href="${uri}" title="${status.value}"><c:out value="${status.value}" /></a>
											</s:bind></td>
										<td><app:out path="list[${count-1}][sqlType]" /></td>
										<td><app:out path="list[${count-1}][databaseName]" /></td>
										<td><app:out path="list[${count-1}][registeredAt]" /></td>
										<td><s:bind path="list[${count-1}][publishedFlg]">
												<c:choose>
													<c:when test="${status.actualValue == 0}">非公開</c:when>
													<c:otherwise>公開</c:otherwise>
												</c:choose>
											</s:bind></td>
										<td><app:out path="list[${count-1}][ownedBy]" /></td>
										<td><app:out path="list[${count-1}][description]" /></td>
									</tr>
								</s:nestedPath>
							</c:forEach>
						</tbody>
					</table>
					<div class="row">
						<div class="col-sm-offset-6 col-sm-6 text-right">
							<app:pagerLink pageSet="${pagedList.pageSet}" form="#sqlSearchForm2" pno="pageNo" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
