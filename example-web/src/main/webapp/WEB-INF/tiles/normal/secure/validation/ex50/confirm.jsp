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
<h2 class="page-header">単項目チェック: グルーピング-確認</h2>
<div class="panel-body">
	<f:form servletRelativeAction="/secure/validation/ex50/execute" method="POST" modelAttribute="validationEx50Form" cssClass="form-horizontal"
		role="form">
		<div class="form-group">
			<div>
				<f:label path="text" cssClass="col-md-3 control-label">テキスト</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text1" cssClass="col-md-3 control-label">テキスト【1】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text1" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text2" cssClass="col-md-3 control-label">テキスト【2】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text2" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text3" cssClass="col-md-3 control-label">テキスト【3】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text3" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text12" cssClass="col-md-3 control-label">テキスト【12】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text12" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text123" cssClass="col-md-3 control-label">テキスト【123】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text123" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<f:button type="submit" class="btn btn-default" name="back">入力へ戻る</f:button>
			</div>
		</div>
	</f:form>
</div>
