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
	<s:message code="secure/userman/search/index.message.0" />
</h1>
<div class="app-portion">
	<h4 class="app-subject">
		<s:message code="secure/userman/search/index.message.1" />
	</h4>
	<s:hasBindErrors name="usermanSearchForm">
		<div class="app-portion ui-state-error">
			<f:errors path="usermanSearchForm" element="div" />
			<f:errors path="usermanSearchForm.mailAddr" element="div" />
			<f:errors path="usermanSearchForm.registeredFrom" element="div" />
			<f:errors path="usermanSearchForm.registeredTo" element="div" />
			<f:errors path="usermanSearchForm.lastName" element="div" />
			<f:errors path="usermanSearchForm.firstName" element="div" />
		</div>
	</s:hasBindErrors>
	<c:if test="${result != null && result.usersList.isEmpty()}">
		<div class="app-portion ui-state-error">
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
	<div class="app-portion">
		<f:form servletRelativeAction="/secure/userman/search/req"
			method="POST" modelAttribute="usermanSearchForm">
			<table>
				<tbody class="app-transparent">
					<tr>
						<td><label for="mailAddr"><s:message
									code="usermanSearchForm.mailAddr" /></label></td>
						<td><f:input path="mailAddr" cssClass="app-width30"
								cssErrorClass="app-width30 ui-state-error" /></td>
					</tr>
					<tr>
						<td><label for="registeredFrom"><s:message
									code="usermanSearchForm.registeredFrom" /></label></td>
						<td><f:input path="registeredFrom" cssClass="app-width30"
								cssErrorClass="app-width30 ui-state-error" /></td>
					</tr>
					<tr>
						<td><label for="registeredTo"><s:message
									code="usermanSearchForm.registeredTo" /></label></td>
						<td><f:input path="registeredTo" cssClass="app-width30"
								cssErrorClass="app-width30 ui-state-error" /></td>
					</tr>
					<tr>
						<td><label for="lastName"><s:message
									code="usermanSearchForm.lastName" /></label></td>
						<td><f:input path="lastName" cssClass="app-width30"
								cssErrorClass="app-width30 ui-state-error" /></td>
					</tr>
					<tr>
						<td><label for="firstName"><s:message
									code="usermanSearchForm.firstName" /></label></td>
						<td><f:input path="firstName" cssClass="app-width30"
								cssErrorClass="app-width30 ui-state-error" /></td>
					</tr>
				</tbody>
				<tfoot class="app-transparent">
					<tr>
						<td></td>
						<td><input type="hidden" id="sz" name="sz"
							value="${param.sz}">
							<button type="submit" name="submit" class="app-button">
								<s:message code="secure/userman/search/index.searchButton" />
							</button></td>
					</tr>
				</tfoot>
			</table>
		</f:form>
	</div>
	<c:if test="${resultIsNotEmpty}">
		<f:form servletRelativeAction="/secure/userman/search/req"
			method="POST" modelAttribute="usermanSearchForm"
			id="usermanSearchWithPage" class="app-pager-form">
			<f:hidden path="mailAddr" id="mailAddr2" />
			<f:hidden path="registeredFrom" id="registeredFrom2" />
			<f:hidden path="registeredTo" id="registeredTo2" />
			<f:hidden path="lastName" id="lastName2" />
			<f:hidden path="firstName" id="firstName2" />
			<input type="hidden" id="no" name="no">
			<input type="hidden" id="sz" name="sz" value="${param.sz}">
		</f:form>
		<div class="app-portion">
			<div class="app-pager">
				<div class="app-pager-desc">
					<s:message code="common/pager.message.0"
						arguments="${result.pageSet.last.to+1},${result.pageSet.current.from+1},${result.pageSet.current.to+1}" />
				</div>
				<app:pagerLink pageSet="${result.pageSet}" />
			</div>
			<table id="usermanSearchList" class="app-stripe app-width-full">
				<thead>
					<tr>
						<th>#</th>
						<th><s:message
								code="secure/userman/search/index.column.mailAddr" /></th>
						<th><s:message
								code="secure/userman/search/index.column.lastName" /></th>
						<th><s:message
								code="secure/userman/search/index.column.firstName" /></th>
						<th><s:message
								code="secure/userman/search/index.column.registeredAt" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${result.usersList}"
						varStatus="status">
						<tr>
							<td><c:out
									value="${result.pageSet.current.from + status.count}" /></td>
							<td><c:out value="${item.mailAddr}" /></td>
							<td><c:out value="${item.lastName}" /></td>
							<td><c:out value="${item.firstName}" /></td>
							<td><c:out value="${item.registeredAt}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="app-pager">
				<app:pagerLink pageSet="${result.pageSet}" />
			</div>
		</div>
	</c:if>
</div>
