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
			<form action="<c:url value="/secure/asyncproc" />" method="POST"
				class="app-pager-form">
				<input type="hidden" id="no" name="no"> <input type="hidden"
					id="sz" name="sz" value="${param.sz}">
				<security:csrfInput />
			</form>
			<div class="app-portion">
				<div class="app-pager">
					<div class="app-pager-desc">
						<s:message code="common/pager.message.0"
							arguments="${pagedList.pageSet.last.to+1},${pagedList.pageSet.current.from+1},${pagedList.pageSet.current.to+1}" />
					</div>
					<app:pagerLink pageSet="${pagedList.pageSet}" />
				</div>
				<table id="asyncProcList" class="app-stripe app-width-full">
					<thead>
						<tr>
							<th>#</th>
							<th><s:message code="secure/asyncproc/init.column.id" /></th>
							<th><s:message
									code="secure/asyncproc/init.column.description" /></th>
							<th><s:message
									code="secure/asyncproc/init.column.launchedBy" /></th>
							<th><s:message code="secure/asyncproc/init.column.asyncType" /></th>
							<th><s:message
									code="secure/asyncproc/init.column.asyncStatus" /></th>
							<th><s:message
									code="secure/asyncproc/init.column.registeredAt" /></th>
							<th><s:message
									code="secure/asyncproc/init.column.launchedAt" /></th>
							<th><s:message code="secure/asyncproc/init.column.startedAt" /></th>
							<th><s:message
									code="secure/asyncproc/init.column.finishedAt" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="count" begin="1" end="${pagedList.list.size()}">
							<tr>
								<td><c:out
										value="${pagedList.pageSet.current.from + count}" /></td>
								<s:nestedPath path="pagedList.list[${count - 1}]">
									<td><s:bind path="id">${status.value}</s:bind></td>
									<td><s:bind path="description">${status.value}</s:bind></td>
									<td><s:bind path="launchedBy">${status.value}</s:bind></td>
									<td><s:bind path="asyncType">${status.value}</s:bind></td>
									<td><s:bind path="asyncStatus">${status.value}</s:bind></td>
									<td><s:bind path="registeredAt">${status.value}</s:bind></td>
									<td><s:bind path="launchedAt">${status.value}</s:bind></td>
									<td><s:bind path="startedAt">${status.value}</s:bind></td>
									<td><s:bind path="finishedAt">${status.value}</s:bind></td>
								</s:nestedPath>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="app-pager">
					<app:pagerLink pageSet="${pagedList.pageSet}" />
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>
