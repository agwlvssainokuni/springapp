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
<h2 class="page-header">応用画面遷移: アップロード系1-確認</h2>
<div class="panel-body">
	<f:form servletRelativeAction="/secure/applied/ex90/execute" method="POST" modelAttribute="appliedEx90Form" cssClass="form-horizontal"
		role="form">
		<foundation:onetimetoken />
		<div class="form-group">
			<div>
				<f:label path="originalFilename" cssClass="col-md-2 control-label">ファイル</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="originalFilename" cssClass="form-control" readonly="true" />
				<f:hidden path="dirname" />
				<f:hidden path="numOfFile" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="charset" cssClass="col-md-2 control-label">文字コード</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="charset" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="dt" cssClass="col-md-2 control-label">日付</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="dt" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="tm" cssClass="col-md-2 control-label">時刻</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="tm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="dtm" cssClass="col-md-2 control-label">日時</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="dtm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-2 col-md-10">
				<f:button type="submit" class="btn btn-primary">登録実行</f:button>
				<f:button type="submit" class="btn btn-default" name="back">入力へ戻る</f:button>
			</div>
		</div>
	</f:form>
</div>
