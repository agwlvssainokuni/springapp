<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fnd" uri="urn:cherry:foundation"%>
<%@ taglib prefix="app" tagdir="/WEB-INF/tags"%>
<h1 class="app-subject">
	<s:message code="secure/asyncproc/init.message.0" />
</h1>
<div class="app-portion">
	<c:choose>
		<c:when test="${pagedList == null}">
			<div class="app-portion ui-state-error">
				<s:message code="secure/asyncproc/init.message.1" />
			</div>
		</c:when>
		<c:when test="${pagedList.list.isEmpty()}">
			<div class="app-portion ui-state-error">
				<s:message code="secure/asyncproc/init.message.1" />
			</div>
		</c:when>
		<c:otherwise>
			<f:form method="POST" servletRelativeAction="/secure/asyncproc" modelAttribute="asyncProcForm" id="HasyncProcForm">
				<f:hidden path="pno" />
				<f:hidden path="psz" />
			</f:form>
			<div class="app-portion">
				<div class="app-pagerbox ui-helper-clearfix">
					<div class="app-floatright">
						<app:pagerDescription pageSet="${pagedList.pageSet}" />
					</div>
				</div>
				<div class="app-pagerbox ui-helper-clearfix">
					<div class="app-floatright">
						<app:pageSize form="#HasyncProcForm" psz="psz" />
						<app:pagerLink pageSet="${pagedList.pageSet}" form="#HasyncProcForm" pno="pno" />
					</div>
				</div>
				<table id="asyncProcList" class="app-stripe app-multihead">
					<thead>
						<tr class="app-head">
							<th>#</th>
							<th><s:message code="secure/asyncproc/init.column.id" /></th>
							<th><s:message code="secure/asyncproc/init.column.description" /></th>
							<th><s:message code="secure/asyncproc/init.column.launchedBy" /></th>
							<th><s:message code="secure/asyncproc/init.column.asyncType" /></th>
							<th><s:message code="secure/asyncproc/init.column.asyncStatus" /></th>
							<th><s:message code="secure/asyncproc/init.column.originalFilename" /></th>
							<th><s:message code="secure/asyncproc/init.column.fileSize" /></th>
							<th><s:message code="secure/asyncproc/init.column.totalCount" /></th>
							<th><s:message code="secure/asyncproc/init.column.okCount" /></th>
							<th><s:message code="secure/asyncproc/init.column.ngCount" /></th>
							<th><s:message code="secure/asyncproc/init.column.registeredAt" /></th>
							<th><s:message code="secure/asyncproc/init.column.launchedAt" /></th>
							<th><s:message code="secure/asyncproc/init.column.startedAt" /></th>
							<th><s:message code="secure/asyncproc/init.column.finishedAt" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="count" begin="1" end="${pagedList.list.size()}">
							<tr>
								<td><c:out value="${pagedList.pageSet.current.from + count}" /></td>
								<s:nestedPath path="pagedList">
									<td><s:bind path="list[${count-1}][id]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][description]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][launchedBy]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][asyncType]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][asyncStatus]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][originalFilename]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][fileSize]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][totalCount]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][okCount]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][ngCount]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][registeredAt]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][launchedAt]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][startedAt]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
									<td><s:bind path="list[${count-1}][finishedAt]">
											<fnd:render value="${status.actualValue}" />
										</s:bind></td>
								</s:nestedPath>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="app-pagerbox ui-helper-clearfix">
					<div class="app-floatright">
						<app:pagerLink pageSet="${pagedList.pageSet}" form="#HasyncProcForm" pno="pno" />
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>
