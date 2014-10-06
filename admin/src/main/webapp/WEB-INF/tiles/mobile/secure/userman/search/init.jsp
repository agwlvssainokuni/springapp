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
	<s:message code="secure/userman/search/index.message.0" />
</h1>
<s:hasBindErrors name="usermanSearchForm">
	<div class="error">
		<f:errors path="usermanSearchForm" element="div" />
		<f:errors path="usermanSearchForm.loginId" element="div" />
		<f:errors path="usermanSearchForm.registeredFrom" element="div" />
		<f:errors path="usermanSearchForm.registeredTo" element="div" />
		<f:errors path="usermanSearchForm.lastName" element="div" />
		<f:errors path="usermanSearchForm.firstName" element="div" />
	</div>
</s:hasBindErrors>
<c:if test="${result != null && result.usersList.isEmpty()}">
	<div class="error">
		<s:message code="secure/userman/search/index.message.2" />
	</div>
</c:if>
<c:set var="resultIsNotEmpty">
	<c:choose>
		<c:when test="${result == null}">false</c:when>
		<c:when test="${result.usersList.isEmpty()}">false</c:when>
		<c:otherwise>true</c:otherwise>
	</c:choose>
</c:set>
<div data-role="collapsible" data-collapsed="${resultIsNotEmpty}">
	<h4>
		<s:message code="secure/userman/search/index.message.1" />
	</h4>
	<f:form servletRelativeAction="/secure/userman/search/execute"
		method="POST" modelAttribute="usermanSearchForm">
		<div data-role="fieldcontain">
			<f:label path="loginId">
				<s:message code="usermanSearchForm.loginId" />
			</f:label>
			<f:input path="loginId" cssErrorClass="error" />
		</div>
		<div data-role="fieldcontain">
			<f:label path="registeredFrom">
				<s:message code="usermanSearchForm.registeredFrom" />
			</f:label>
			<f:input path="registeredFrom" cssErrorClass="error" />
		</div>
		<div data-role="fieldcontain">
			<f:label path="registeredTo">
				<s:message code="usermanSearchForm.registeredTo" />
			</f:label>
			<f:input path="registeredTo" cssErrorClass="error" />
		</div>
		<div data-role="fieldcontain">
			<f:label path="lastName">
				<s:message code="usermanSearchForm.lastName" />
			</f:label>
			<f:input path="lastName" cssErrorClass="error" />
		</div>
		<div data-role="fieldcontain">
			<f:label path="firstName">
				<s:message code="usermanSearchForm.firstName" />
			</f:label>
			<f:input path="firstName" cssErrorClass="error" />
		</div>
		<input type="hidden" id="sz" name="sz" value="${param.sz}">
		<f:button type="submit">
			<s:message code="secure/userman/search/index.searchButton" />
		</f:button>
		<f:button type="submit" name="export">
			<s:message code="secure/userman/search/index.exportButton" />
		</f:button>
	</f:form>
</div>
<c:if test="${resultIsNotEmpty}">
	<f:form servletRelativeAction="/secure/userman/search/req"
		method="POST" modelAttribute="usermanSearchForm"
		id="usermanSearchFormHidden" cssClass="app-pager-form">
		<f:hidden path="loginId" id="loginId2" />
		<f:hidden path="registeredFrom" id="registeredFrom2" />
		<f:hidden path="registeredTo" id="registeredTo2" />
		<f:hidden path="lastName" id="lastName2" />
		<f:hidden path="firstName" id="firstName2" />
		<input type="hidden" id="no" name="no">
		<input type="hidden" id="sz" name="sz" value="${param.sz}">
	</f:form>
	<div data-role="navbar" class="app-pager-link">
		<span class="app-current" title="${result.pageSet.current.no+1}"></span>
		<ul>
			<li><a href="#"
				class="ui-icon-arrow-u ui-btn-icon-top ui-disabled app-page"
				title="${result.pageSet.prev.no+1}"><s:message
						code="secure/userman/search/index.paginate.prev" /></a></li>
		</ul>
	</div>
	<table id="usermanSearchList" data-role="table"
		class="table-stripe ui-responsive">
		<thead>
			<tr>
				<th>#</th>
				<th><s:message
						code="secure/userman/search/index.column.loginId" /></th>
				<th><s:message
						code="secure/userman/search/index.column.lastName" /></th>
				<th><s:message
						code="secure/userman/search/index.column.firstName" /></th>
				<th><s:message
						code="secure/userman/search/index.column.registeredAt" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="count" begin="1" end="${result.usersList.size()}">
				<tr>
					<td><c:out value="${result.pageSet.current.from + count}" /></td>
					<s:nestedPath path="result.usersList[${count - 1}]">
						<td><s:bind path="loginId">${status.value}</s:bind></td>
						<td><s:bind path="lastName">${status.value}</s:bind></td>
						<td><s:bind path="firstName">${status.value}</s:bind></td>
						<td><s:bind path="registeredAt">${status.value}</s:bind></td>
					</s:nestedPath>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div data-role="navbar" class="app-pager-link">
		<span class="app-current" title="${result.pageSet.current.no+1}"></span>
		<ul>
			<li><a href="#"
				class="ui-icon-arrow-d ui-btn-icon-top ui-disabled app-page"
				title="${result.pageSet.next.no+1}"><s:message
						code="secure/userman/search/index.paginate.next" /></a></li>
		</ul>
	</div>
</c:if>
