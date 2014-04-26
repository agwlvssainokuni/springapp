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
<h1 class="app-subject">
	<s:message code="secure/asyncproc/index.message.0" />
</h1>
<div class="app-portion">
	<c:choose>
		<c:when test="${result == null}">
			<div class="app-portion ui-state-error">
				<s:message code="secure/asyncproc/index.message.1" />
			</div>
		</c:when>
		<c:when test="${result.asyncProcList.isEmpty()}">
			<div class="app-portion ui-state-error">
				<s:message code="secure/asyncproc/index.message.1" />
			</div>
		</c:when>
		<c:otherwise>
			<form action="<c:url value="/secure/asyncproc" />" method="POST"
				class="app-pager-form">
				<input type="hidden" id="no" name="no"> <input type="hidden"
					id="sz" name="sz" value="${param.sz}"> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}">
			</form>
			<div class="app-portion">
				<div class="app-pager">
					<div class="app-pager-desc">
						<s:message code="common/pager.message.0"
							arguments="${result.pageSet.last.to+1},${result.pageSet.current.from+1},${result.pageSet.current.to+1}" />
					</div>
					<div class="app-pager-link">
						<span class="app-current" title="${result.pageSet.current.no+1}"></span>
						<span class="app-page" title="${result.pageSet.prev.no+1}">&lt;</span>
						<span class="app-page" title="${result.pageSet.first.no+1}">${result.pageSet.first.no+1}</span>
						<c:if
							test="${result.pageSet.range[0].no > result.pageSet.first.no+1}">
							<span class="app-spacer">...</span>
						</c:if>
						<c:forEach var="page" items="${result.pageSet.range}">
							<span class="app-page" title="${page.no+1}">${page.no+1}</span>
						</c:forEach>
						<c:if
							test="${result.pageSet.range[fn:length(result.pageSet.range)-1].no <  result.pageSet.last.no-1}">
							<span class="app-spacer">...</span>
						</c:if>
						<span class="app-page" title="${result.pageSet.last.no+1}">${result.pageSet.last.no+1}</span>
						<span class="app-page" title="${result.pageSet.next.no+1}">&gt;</span>
					</div>
				</div>
				<table id="asyncProcList" class="app-stripe app-width-full">
					<thead>
						<tr>
							<th>#</th>
							<th><s:message code="secure/asyncproc/index.column.id" /></th>
							<th><s:message code="secure/asyncproc/index.column.name" /></th>
							<th><s:message code="secure/asyncproc/index.column.status" /></th>
							<th><s:message
									code="secure/asyncproc/index.column.registeredAt" /></th>
							<th><s:message
									code="secure/asyncproc/index.column.invokedAt" /></th>
							<th><s:message
									code="secure/asyncproc/index.column.startedAt" /></th>
							<th><s:message
									code="secure/asyncproc/index.column.finishedAt" /></th>
							<th><s:message code="secure/asyncproc/index.column.result" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${result.asyncProcList}"
							varStatus="status">
							<tr>
								<td><c:out
										value="${result.pageSet.current.from + status.count}" /></td>
								<td><c:out value="${item.id}" /></td>
								<td><c:out value="${item.name}" /></td>
								<td><c:out value="${item.status}" /></td>
								<td><c:out value="${item.registeredAt}" /></td>
								<td><c:out value="${item.invokedAt}" /></td>
								<td><c:out value="${item.startedAt}" /></td>
								<td><c:out value="${item.finishedAt}" /></td>
								<td><c:out value="${item.result}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="app-pager">
					<div class="app-pager-link">
						<span class="app-current" title="${result.pageSet.current.no+1}"></span>
						<span class="app-page" title="${result.pageSet.prev.no+1}">&lt;</span>
						<span class="app-page" title="${result.pageSet.first.no+1}">${result.pageSet.first.no+1}</span>
						<c:if
							test="${result.pageSet.range[0].no > result.pageSet.first.no+1}">
							<span class="app-spacer">...</span>
						</c:if>
						<c:forEach var="page" items="${result.pageSet.range}">
							<span class="app-page" title="${page.no+1}">${page.no+1}</span>
						</c:forEach>
						<c:if
							test="${result.pageSet.range[fn:length(result.pageSet.range)-1].no <  result.pageSet.last.no-1}">
							<span class="app-spacer">...</span>
						</c:if>
						<span class="app-page" title="${result.pageSet.last.no+1}">${result.pageSet.last.no+1}</span>
						<span class="app-page" title="${result.pageSet.next.no+1}">&gt;</span>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>
