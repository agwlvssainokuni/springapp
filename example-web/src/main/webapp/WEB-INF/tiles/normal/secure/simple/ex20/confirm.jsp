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
<h2 class="page-header">単純画面遷移-登録確認</h2>
<div class="panel-body">
	<f:form servletRelativeAction="/secure/simple/ex20/execute" method="POST" modelAttribute="ex20Form" cssClass="form-horizontal" role="form">
		<foundation:onetimetoken />
		<div class="form-group">
			<f:label path="text10" cssClass="col-sm-2 control-label">文字列【10】</f:label>
			<div class="col-sm-10">
				<f:input path="text10" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="text100" cssClass="col-sm-2 control-label">文字列【100】</f:label>
			<div class="col-sm-10">
				<f:textarea path="text100" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="int64" cssClass="col-sm-2 control-label">整数【64bit】</f:label>
			<div class="col-sm-10">
				<f:input path="int64" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="decimal1" cssClass="col-sm-2 control-label">小数【1桁】</f:label>
			<div class="col-sm-10">
				<f:input path="decimal1" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="decimal3" cssClass="col-sm-2 control-label">小数【3桁】</f:label>
			<div class="col-sm-10">
				<f:input path="decimal3" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="dt" cssClass="col-sm-2 control-label">日付</f:label>
			<div class="col-sm-10">
				<f:input path="dt" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="tm" cssClass="col-sm-2 control-label">時刻</f:label>
			<div class="col-sm-10">
				<f:input path="tm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="dtm" cssClass="col-sm-2 control-label">日時</f:label>
			<div class="col-sm-10">
				<f:input path="dtm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<f:button type="submit" class="btn btn-primary">登録</f:button>
				<f:button type="submit" class="btn btn-default" name="back">戻る</f:button>
			</div>
		</div>
	</f:form>
</div>
