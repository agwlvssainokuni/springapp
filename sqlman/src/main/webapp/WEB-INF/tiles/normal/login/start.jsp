<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h2 class="page-header">SQL管理ツール ログイン</h2>
<c:if test="${loginFailed != null && loginFailed}">
	<div class="form-group has-error">
		<div class="col-sm-10 col-sm-offset-2 alert alert-warning" role="alert">ログインIDまたはパスワードが誤っています。</div>
	</div>
</c:if>
<c:if test="${loggedOut != null && loggedOut}">
	<div class="form-group has-success">
		<div class="col-sm-offset-2 col-sm-10 alert alert-info" role="alert">ログアウトしました。</div>
	</div>
</c:if>
<f:form servletRelativeAction="/login/execute" modelAttribute="loginForm" role="form" cssClass="form-horizontal">
	<div class="form-group">
		<f:label path="loginId" cssClass="col-sm-2 control-label">ログインID</f:label>
		<div class="col-sm-10">
			<f:input path="loginId" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<f:label path="password" cssClass="col-sm-2 control-label">パスワード</f:label>
		<div class="col-sm-10">
			<f:password path="password" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-10 col-sm-offset-2">
			<button type="submit" class="btn btn-default btn-block">ログイン</button>
		</div>
	</div>
</f:form>
