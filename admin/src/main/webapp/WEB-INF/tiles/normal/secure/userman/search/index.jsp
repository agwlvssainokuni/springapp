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
	<s:message code="userman/search/index.message.0" />
</h1>
<s:hasBindErrors name="usermanSearchForm">
	<div class="error">
		<f:errors path="usermanSearchForm" element="div" />
		<f:errors path="usermanSearchForm.mailAddr" element="div" />
		<f:errors path="usermanSearchForm.registeredFrom" element="div" />
		<f:errors path="usermanSearchForm.registeredTo" element="div" />
		<f:errors path="usermanSearchForm.lastName" element="div" />
		<f:errors path="usermanSearchForm.firstName" element="div" />
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/userman/search/req" method="POST"
	modelAttribute="usermanSearchForm">
	<div data-role="fieldcontain">
		<label for="mailAddr"><s:message
				code="usermanSearchForm.mailAddr" /></label>
		<f:input path="mailAddr" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="registeredFrom"><s:message
				code="usermanSearchForm.registeredFrom" /></label>
		<f:input path="registeredFrom" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="registeredTo"><s:message
				code="usermanSearchForm.registeredTo" /></label>
		<f:input path="registeredTo" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="lastName"><s:message
				code="usermanSearchForm.lastName" /></label>
		<f:input path="lastName" cssErrorClass="error" />
	</div>
	<div data-role="fieldcontain">
		<label for="firstName"><s:message
				code="usermanSearchForm.firstName" /></label>
		<f:input path="firstName" cssErrorClass="error" />
	</div>
	<input type="submit" name="submit"
		value="<s:message code="userman/search/index.searchButton" />" />
</f:form>
<c:if test="${result != null}">
	<f:form servletRelativeAction="/secure/userman/search/req"
		method="POST" modelAttribute="usermanSearchForm"
		id="usermanSearchWithPage">
		<f:hidden path="mailAddr" id="mailAddr2" />
		<f:hidden path="registeredFrom" id="registeredFrom2" />
		<f:hidden path="registeredTo" id="registeredTo2" />
		<f:hidden path="lastName" id="lastName2" />
		<f:hidden path="firstName" id="firstName2" />
		<input type="hidden" id="no" name="no">
	</f:form>
	<script type="text/javascript">
		function searchUsers(pageNo) {
			var form = $("#usermanSearchWithPage");
			form.children("input[name='no']").val(pageNo);
			form.submit();
			return false;
		}
	</script>
	<c:choose>
		<c:when test="${result.usersList.isEmpty()}">
			<s:message code="userman/search/index.message.1" />
		</c:when>
		<c:otherwise>
			<div data-role="navbar">
				<ul>
					<li><a
						<c:if test="${result.pageSet.current == result.pageSet.prev}">class="ui-disabled"</c:if>
						href="#"
						onclick="JavaScript:searchUsers(${result.pageSet.prev.no})"> <s:message
								code="userman/search/index.paginate.prev" />
					</a></li>
					<li><a
						<c:if test="${result.pageSet.current == result.pageSet.next}">class="ui-disabled"</c:if>
						href="#"
						onclick="JavaScript:searchUsers(${result.pageSet.next.no})"> <s:message
								code="userman/search/index.paginate.next" />
					</a></li>
				</ul>
			</div>
			<table id="usermanSearchList" data-role="table"
				class="ui-body-d ui-shadow table-stripe ui-responsive">
				<thead>
					<tr class="ui-bar-d">
						<th>#</th>
						<th><s:message code="userman/search/index.column.mailAddr" /></th>
						<th><s:message code="userman/search/index.column.lastName" /></th>
						<th><s:message code="userman/search/index.column.firstName" /></th>
						<th><s:message
								code="userman/search/index.column.registeredAt" /></th>
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
		</c:otherwise>
	</c:choose>
</c:if>