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
	<s:message code="secure/userman/search/init.message.0" />
</h1>
<div class="app-portion">
	<h4 class="app-subject">
		<s:message code="secure/userman/search/init.message.1" />
	</h4>
	<s:hasBindErrors name="usermanSearchForm">
		<div class="app-portion ui-state-error">
			<f:errors path="usermanSearchForm" element="div" />
			<f:errors path="usermanSearchForm.loginId" element="div" />
			<f:errors path="usermanSearchForm.registeredFrom" element="div" />
			<f:errors path="usermanSearchForm.registeredTo" element="div" />
			<f:errors path="usermanSearchForm.lastName" element="div" />
			<f:errors path="usermanSearchForm.firstName" element="div" />
			<f:errors path="usermanSearchForm.pno" element="div" />
			<f:errors path="usermanSearchForm.psz" element="div" />
		</div>
	</s:hasBindErrors>
	<c:if test="${pagedList != null && pagedList.list.isEmpty()}">
		<div class="app-portion ui-state-error">
			<s:message code="secure/userman/search/init.message.2" />
		</div>
	</c:if>
	<c:set var="resultIsNotEmpty">
		<c:choose>
			<c:when test="${pagedList == null}">false</c:when>
			<c:when test="${pagedList.list.isEmpty()}">false</c:when>
			<c:otherwise>true</c:otherwise>
		</c:choose>
	</c:set>
	<div class="app-portion">
		<f:form servletRelativeAction="/secure/userman/search/execute"
			method="POST" modelAttribute="usermanSearchForm">
			<table>
				<tbody class="app-transparent">
					<tr>
						<td><label for="loginId"><s:message
									code="usermanSearchForm.loginId" /></label></td>
						<td><f:input path="loginId" cssClass="app-width30"
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
							value="${param.sz}"> <f:button type="submit"
								class="app-button">
								<s:message code="secure/userman/search/init.searchButton" />
							</f:button> <f:button type="submit" name="download" class="app-button">
								<s:message code="secure/userman/search/init.downloadButton" />
							</f:button></td>
					</tr>
				</tfoot>
			</table>
		</f:form>
	</div>
	<c:if test="${resultIsNotEmpty}">
		<f:form servletRelativeAction="/secure/userman/search/execute"
			method="POST" modelAttribute="usermanSearchForm"
			id="HusermanSearchForm" class="app-pager-form">
			<f:hidden path="loginId" id="HloginId" />
			<f:hidden path="registeredFrom" id="HregisteredFrom" />
			<f:hidden path="registeredTo" id="HregisteredTo" />
			<f:hidden path="lastName" id="HlastName" />
			<f:hidden path="firstName" id="HfirstName" />
			<f:hidden path="pno" id="Hpno" />
			<f:hidden path="psz" id="Hpsz" />
		</f:form>
		<div class="app-portion">
			<div class="app-pager">
				<app:downloadButton form="#HusermanSearchForm" />
			</div>
			<div class="app-pager">
				<app:pagerDescription pageSet="${pagedList.pageSet}" />
			</div>
			<div class="app-pager">
				<app:pageSize id="HusermanSearchFormPsz" form="#HusermanSearchForm"
					psz="psz" />
				<app:pagerLink pageSet="${pagedList.pageSet}"
					form="#HusermanSearchForm" pno="pno" />
			</div>
			<table id="usermanSearchList"
				class="app-stripe app-width-full app-multihead">
				<thead>
					<tr>
						<th>#</th>
						<th><s:message
								code="secure/userman/search/init.column.loginId" /></th>
						<th><s:message
								code="secure/userman/search/init.column.lastName" /></th>
						<th><s:message
								code="secure/userman/search/init.column.firstName" /></th>
						<th><s:message
								code="secure/userman/search/init.column.registeredAt" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="count" begin="1" end="${pagedList.list.size()}">
						<tr>
							<td><c:out value="${pagedList.pageSet.current.from + count}" /></td>
							<s:nestedPath path="pagedList.list[${count - 1}]">
								<td><s:bind path="loginId">${status.value}</s:bind></td>
								<td><s:bind path="lastName">${status.value}</s:bind></td>
								<td><s:bind path="firstName">${status.value}</s:bind></td>
								<td><s:bind path="registeredAt">${status.value}</s:bind></td>
							</s:nestedPath>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="app-pager">
				<app:pagerLink pageSet="${pagedList.pageSet}"
					form="#HusermanSearchForm" pno="pno" />
			</div>
		</div>
	</c:if>
</div>
