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
	<s:message code="signup/entry/index.message.0" />
</h2>
<s:hasBindErrors name="signupEntryForm">
	<div class="form-group has-error">
		<div class="col-sm-10 col-sm-offset-2 help-block bg-danger">
			<f:errors path="signupEntryForm" element="div" />
			<f:errors path="signupEntryForm.email" element="div" />
		</div>
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/signup/req" method="POST"
	modelAttribute="signupEntryForm" role="form" cssClass="form-horizontal">
	<div class="form-group">
		<f:label path="email" class="col-sm-2 control-label">
			<s:message code="signupEntryForm.email" />
		</f:label>
		<div class="col-sm-10">
			<f:input path="email" cssClass="form-control"
				cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<f:button type="submit" class="btn btn-default btn-block">
				<s:message code="signup/entry/index.entryButton" />
			</f:button>
		</div>
	</div>
</f:form>
