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
<h2 class="page-header">基本画面遷移: 単票入力系1-変更完了</h2>
<div class="panel-body">
	<div class="col-md-offset-2 col-md-10">
		<div class="alert alert-info" role="alert">
			<div>変更しました。</div>
			<div>
				<a href="${baseUri}/basic/ex11/?id=${param.id}">変更する</a>
			</div>
		</div>
	</div>
	<div class="form-horizontal">
		<s:nestedPath path="basicEx10Form">
			<div class="form-group">
				<div>
					<f:label path="text10" cssClass="col-md-2 control-label">文字列【10】</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="text10" cssClass="form-control" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<div>
					<f:label path="text100" cssClass="col-md-2 control-label">文字列【100】</f:label>
				</div>
				<div class="col-md-10">
					<f:textarea path="text100" cssClass="form-control" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<div>
					<f:label path="int64" cssClass="col-md-2 control-label">整数【64bit】</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="int64" cssClass="form-control" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<div>
					<f:label path="decimal1" cssClass="col-md-2 control-label">小数【1桁】</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="decimal1" cssClass="form-control" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<div>
					<f:label path="decimal3" cssClass="col-md-2 control-label">小数【3桁】</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="decimal3" cssClass="form-control" readonly="true" />
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
		</s:nestedPath>
	</div>
</div>
