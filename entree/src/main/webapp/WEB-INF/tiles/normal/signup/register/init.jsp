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
	<s:message code="signup/register/init.message.0" />
</h2>
<s:hasBindErrors name="signupRegisterForm">
	<div class="form-group has-error">
		<div class="col-sm-10 col-sm-offset-2 help-block bg-danger">
			<f:errors path="signupRegisterForm" element="div" />
			<f:errors path="signupRegisterForm.email" element="div" />
			<f:errors path="signupRegisterForm.lastName" element="div" />
			<f:errors path="signupRegisterForm.firstName" element="div" />
		</div>
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/signup/${token}/execute" method="POST"
	modelAttribute="signupRegisterForm" role="form"
	cssClass="form-horizontal">
	<div class="form-group">
		<f:label path="email" class="col-sm-2 control-label">
			<s:message code="signupRegisterForm.email" />
		</f:label>
		<div class="col-sm-10">
			<f:input path="email" cssClass="form-control"
				cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<f:label path="lastName" class="col-sm-2 control-label">
			<s:message code="signupRegisterForm.lastName" />
		</f:label>
		<div class="col-sm-10">
			<f:input path="lastName" cssClass="form-control"
				cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<f:label path="firstName" class="col-sm-2 control-label">
			<s:message code="signupRegisterForm.firstName" />
		</f:label>
		<div class="col-sm-10">
			<f:input path="firstName" cssClass="form-control"
				cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<f:button type="submit" class="btn btn-default btn-block">
				<s:message code="signup/register/init.registerButton" />
			</f:button>
		</div>
	</div>
</f:form>
