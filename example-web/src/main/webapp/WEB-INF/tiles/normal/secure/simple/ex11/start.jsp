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
<h2 class="page-header">単純画面遷移: 単票入力系1-変更入力</h2>
<div class="panel-body">
	<s:hasBindErrors name="ex10Form">
		<div class="col-md-offset-2 col-md-10">
			<div class="alert alert-danger" role="alert">
				<f:errors path="ex10Form" element="div" />
				<f:errors path="ex10Form.text10" element="div" />
				<f:errors path="ex10Form.text100" element="div" />
				<f:errors path="ex10Form.int64" element="div" />
				<f:errors path="ex10Form.decimal1" element="div" />
				<f:errors path="ex10Form.decimal3" element="div" />
				<f:errors path="ex10Form.dt" element="div" />
				<f:errors path="ex10Form.tm" element="div" />
				<f:errors path="ex10Form.dtm" element="div" />
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="/secure/simple/ex11/confirm?id=${param.id}" method="POST" modelAttribute="ex10Form" cssClass="form-horizontal"
		role="form">
		<f:hidden path="lockVersion" />
		<div class="form-group">
			<f:label path="text10" cssClass="col-md-2 control-label">文字列【10】</f:label>
			<div class="col-md-10">
				<f:input path="text10" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="text100" cssClass="col-md-2 control-label">文字列【100】</f:label>
			<div class="col-md-10">
				<f:textarea path="text100" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="int64" cssClass="col-md-2 control-label">整数【64bit】</f:label>
			<div class="col-md-10">
				<f:input path="int64" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="decimal1" cssClass="col-md-2 control-label">小数【1桁】</f:label>
			<div class="col-md-10">
				<f:input path="decimal1" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="decimal3" cssClass="col-md-2 control-label">小数【3桁】</f:label>
			<div class="col-md-10">
				<f:input path="decimal3" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="dt" cssClass="col-md-2 control-label">日付</f:label>
			<div class="col-md-10">
				<f:input path="dt" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="tm" cssClass="col-md-2 control-label">時刻</f:label>
			<div class="col-md-10">
				<f:input path="tm" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="dtm" cssClass="col-md-2 control-label">日時</f:label>
			<div class="col-md-10">
				<f:input path="dtm" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-2 col-md-10">
				<f:button type="submit" class="btn btn-primary">変更確認</f:button>
			</div>
		</div>
	</f:form>
</div>