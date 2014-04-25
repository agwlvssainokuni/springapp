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
<h1>
	<s:message code="secure/asyncproc/index.message.0" />
</h1>
<c:choose>
	<c:when test="${result == null}">
		<div class="error">
			<s:message code="secure/asyncproc/index.message.1" />
		</div>
	</c:when>
	<c:when test="${result.asyncProcList.isEmpty()}">
		<div class="error">
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
		<script type="text/javascript">
			function searchAsyncProcs(pageNo) {
				var form = $(".app-pager-form");
				form.children("input[name='no']").val(pageNo);
				form.submit();
				return false;
			}
		</script>
		<div data-role="navbar">
			<ul>
				<c:set var="prevUiDisabled">
					<c:if test="${result.pageSet.current == result.pageSet.prev}">ui-disabled</c:if>
				</c:set>
				<li><a href="#"
					class="ui-icon-arrow-u ui-btn-icon-top ${prevUiDisabled}"
					onclick="JavaScript:searchAsyncProcs(${result.pageSet.prev.no});"><s:message
							code="secure/asyncproc/index.paginate.prev" /></a></li>
			</ul>
		</div>
		<table id="asyncProcList" data-role="table"
			class="table-stripe ui-responsive">
			<thead>
				<tr>
					<th>#</th>
					<th><s:message code="secure/asyncproc/index.column.id" /></th>
					<th><s:message code="secure/asyncproc/index.column.name" /></th>
					<th><s:message code="secure/asyncproc/index.column.status" /></th>
					<th><s:message
							code="secure/asyncproc/index.column.registeredAt" /></th>
					<th><s:message code="secure/asyncproc/index.column.invokedAt" /></th>
					<th><s:message code="secure/asyncproc/index.column.startedAt" /></th>
					<th><s:message code="secure/asyncproc/index.column.finishedAt" /></th>
					<th><s:message code="secure/asyncproc/index.column.result" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${result.asyncProcList}" varStatus="status">
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
		<div data-role="navbar">
			<ul>
				<c:set var="nextUiDisabled">
					<c:if test="${result.pageSet.current == result.pageSet.next}">ui-disabled</c:if>
				</c:set>
				<li><a href="#"
					class="ui-icon-arrow-d ui-btn-icon-top ${nextUiDisabled}"
					onclick="JavaScript:searchAsyncProcs(${result.pageSet.next.no});"><s:message
							code="secure/asyncproc/index.paginate.next" /></a></li>
			</ul>
		</div>
	</c:otherwise>
</c:choose>
