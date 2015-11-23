<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<%@ taglib prefix="ex" tagdir="/WEB-INF/tags"%>
<c:url var="baseUri" value="/secure" />
<h2 class="page-header">応用画面遷移: アップロード系1-入力</h2>
<div class="panel-body">
	<s:hasBindErrors name="appliedEx90Form">
		<div class="col-md-offset-2 col-md-10">
			<div class="alert alert-danger" role="alert">
				<f:errors path="appliedEx90Form" element="div" />
				<s:nestedPath path="appliedEx90Form">
					<f:errors path="file" element="div" />
					<f:errors path="charset" element="div" />
					<f:errors path="dt" element="div" />
					<f:errors path="tm" element="div" />
					<f:errors path="dtm" element="div" />
				</s:nestedPath>
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="/secure/applied/ex90/confirm" method="POST" modelAttribute="appliedEx90Form" cssClass="form-horizontal"
		role="form" enctype="multipart/form-data">
		<div class="form-group">
			<div>
				<f:label path="file" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">ファイル</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="file" type="file" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="charset" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">文字コード</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="charset" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="dt" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">日付</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="dt" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="tm" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">時刻</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="tm" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="dtm" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">日時</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="dtm" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-2 col-md-10">
				<f:button type="submit" class="btn btn-primary">登録確認</f:button>
			</div>
		</div>
	</f:form>
</div>
