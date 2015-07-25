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
<h2>
	<s:message code="password/edit.message.0" />
</h2>
<c:if test="${execute != null && execute}">
	<div class="form-group has-success">
		<div class="col-sm-offset-2 col-sm-10 alert alert-info" role="alert">
			<s:message code="password/edit.message.1" />
		</div>
	</div>
</c:if>
<s:hasBindErrors name="passwordRequestForm">
	<div class="col-sm-offset-2 col-sm-10">
		<div class="alert alert-danger" role="alert">
			<f:errors path="passwordRequestForm" element="div" />
			<f:errors path="passwordRequestForm.mailAddr" element="div" />
			<f:errors path="passwordRequestForm.password" element="div" />
			<f:errors path="passwordRequestForm.passwordConf" element="div" />
		</div>
	</div>
</s:hasBindErrors>
<c:url var="action" value="/password/execute">
	<c:param name="token" value="${param.token}" />
</c:url>
<f:form servletRelativeAction="${action}" method="POST"
	modelAttribute="passwordRequestForm" cssClass="form-horizontal"
	role="form">
	<c:set var="hasError">
		<s:bind path="mailAddr">${status.isError() ? "has-error" : ""}</s:bind>
	</c:set>
	<div class="form-group ${hasError}">
		<f:label path="mailAddr" cssClass="col-sm-2 control-label">
			<s:message code="passwordRequestForm.mailAddr" />
		</f:label>
		<div class="col-sm-10">
			<f:input path="mailAddr" cssClass="form-control" />
		</div>
	</div>
	<c:set var="hasError">
		<s:bind path="password">${status.isError() ? "has-error" : ""}</s:bind>
	</c:set>
	<div class="form-group ${hasError}">
		<f:label path="password" cssClass="col-sm-2 control-label">
			<s:message code="passwordRequestForm.password" />
		</f:label>
		<div class="col-sm-10">
			<f:password path="password" cssClass="form-control" />
		</div>
	</div>
	<c:set var="hasError">
		<s:bind path="passwordConf">${status.isError() ? "has-error" : ""}</s:bind>
	</c:set>
	<div class="form-group ${hasError}">
		<f:label path="passwordConf" cssClass="col-sm-2 control-label">
			<s:message code="passwordRequestForm.passwordConf" />
		</f:label>
		<div class="col-sm-10">
			<f:password path="passwordConf" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<button class="btn btn-default btn-block" type="submit">
				<s:message code="password/edit.requestButton" />
			</button>
		</div>
	</div>
</f:form>
