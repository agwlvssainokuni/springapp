<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h2 class="page-header">パスワード発行</h2>
<c:if test="${created != null && created}">
	<div class="form-group has-success">
		<div class="col-sm-offset-2 col-sm-10 alert alert-info" role="alert">受付けました。</div>
	</div>
</c:if>
<s:hasBindErrors name="passwordRequestForm">
	<div class="col-sm-offset-2 col-sm-10">
		<div class="alert alert-danger" role="alert">
			<f:errors path="passwordRequestForm" element="div" />
			<f:errors path="passwordRequestForm.mailAddr" element="div" />
		</div>
	</div>
</s:hasBindErrors>
<c:url var="action" value="/password/create" />
<f:form servletRelativeAction="${action}" method="POST" modelAttribute="passwordRequestForm" cssClass="form-horizontal" role="form">
	<div class="form-group">
		<div>
			<f:label path="mailAddr" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">メールアドレス</f:label>
		</div>
		<div class="col-sm-10">
			<f:input path="mailAddr" cssClass="form-control" cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<button class="btn btn-default btn-block" type="submit">発行要求する</button>
		</div>
	</div>
</f:form>
