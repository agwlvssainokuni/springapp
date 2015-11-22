<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<h2 class="page-header">パスワード変更</h2>
<c:if test="${updated != null && updated}">
	<div class="form-group has-success">
		<div class="col-sm-offset-2 col-sm-10 alert alert-info" role="alert">パスワードを更新しました。</div>
	</div>
</c:if>
<s:hasBindErrors name="passwordChangeForm">
	<div class="col-sm-offset-2 col-sm-10">
		<div class="alert alert-danger" role="alert">
			<f:errors path="passwordChangeForm" element="div" />
			<f:errors path="passwordChangeForm.password" element="div" />
			<f:errors path="passwordChangeForm.passwordConf" element="div" />
		</div>
	</div>
</s:hasBindErrors>
<c:url var="action" value="/tool/password/update" />
<f:form servletRelativeAction="${action}" method="POST" modelAttribute="passwordChangeForm" cssClass="form-horizontal" role="form">
	<foundation:onetimetoken />
	<f:hidden path="lockVersion" />
	<div class="form-group">
		<div>
			<f:label path="password" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">パスワード</f:label>
		</div>
		<div class="col-sm-10">
			<f:password path="password" cssClass="form-control" cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<div>
			<f:label path="passwordConf" cssClass="col-sm-2 control-label" cssErrorClass="col-sm-2 control-label has-error">パスワード(確認)</f:label>
		</div>
		<div class="col-sm-10">
			<f:password path="passwordConf" cssClass="form-control" cssErrorClass="form-control has-error" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<button class="btn btn-default btn-block" type="submit">更新</button>
		</div>
	</div>
</f:form>
