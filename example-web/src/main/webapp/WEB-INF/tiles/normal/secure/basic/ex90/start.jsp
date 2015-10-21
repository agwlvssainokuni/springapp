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
<h2 class="page-header">基本画面遷移: アップロード系1-入力</h2>
<div class="panel-body">
	<s:hasBindErrors name="ex90Form">
		<div class="col-md-offset-2 col-md-10">
			<div class="alert alert-danger" role="alert">
				<f:errors path="ex90Form" element="div" />
				<f:errors path="ex90Form.file" element="div" />
				<f:errors path="ex90Form.charset" element="div" />
				<f:errors path="ex90Form.dt" element="div" />
				<f:errors path="ex90Form.tm" element="div" />
				<f:errors path="ex90Form.dtm" element="div" />
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="/secure/basic/ex90/execute" method="POST" modelAttribute="ex90Form" cssClass="form-horizontal" role="form"
		enctype="multipart/form-data">
		<foundation:onetimetoken />
		<div class="form-group">
			<f:label path="file" cssClass="col-md-2 control-label">ファイル</f:label>
			<div class="col-md-10">
				<f:input path="file" type="file" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="charset" cssClass="col-md-2 control-label">文字コード</f:label>
			<div class="col-md-10">
				<f:input path="charset" cssClass="form-control" cssErrorClass="form-control has-error" />
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
				<f:button type="submit" class="btn btn-primary">登録実行</f:button>
			</div>
		</div>
	</f:form>
</div>