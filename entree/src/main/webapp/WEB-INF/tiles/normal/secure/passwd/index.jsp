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
	<s:message code="secure/passwd/index.message.0" />
</h2>
<s:hasBindErrors name="passwdForm">
	<div class="form-group has-error">
		<div class="col-sm-9 col-sm-offset-3 help-block bg-danger">
			<f:errors path="passwdForm" element="div" />
			<f:errors path="passwdForm.loginId" element="div" />
			<f:errors path="passwdForm.password" element="div" />
			<f:errors path="passwdForm.newPassword" element="div" />
			<f:errors path="passwdForm.newPasswordConf" element="div" />
		</div>
	</div>
</s:hasBindErrors>
<f:form servletRelativeAction="/secure/passwd/req" method="POST"
	modelAttribute="passwdForm" role="form" cssClass="form-horizontal">
	<div class="form-group">
		<label for="loginId" class="col-sm-3 control-label"><s:message
				code="passwdForm.loginId" /></label>
		<div class="col-sm-9">
			<f:input path="loginId" cssClass="form-control"
				cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-3 control-label"><s:message
				code="passwdForm.password" /></label>
		<div class="col-sm-9">
			<f:password path="password" cssClass="form-control"
				cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<label for="newPassword" class="col-sm-3 control-label"><s:message
				code="passwdForm.newPassword" /></label>
		<div class="col-sm-9">
			<f:password path="newPassword" cssClass="form-control"
				cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<label for="newPasswordConf" class="col-sm-3 control-label"><s:message
				code="passwdForm.newPasswordConf" /></label>
		<div class="col-sm-9">
			<f:password path="newPasswordConf" cssClass="form-control"
				cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-9 col-sm-offset-3">
			<button class="btn btn-default btn-block" type="submit">
				<s:message code="secure/passwd/index.changeButton" />
			</button>
		</div>
	</div>
</f:form>
