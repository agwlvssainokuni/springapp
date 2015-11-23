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
<h2 class="page-header">単項目チェック: 日時項目-確認</h2>
<div class="panel-body">
	<f:form servletRelativeAction="/secure/validation/ex30/execute" method="POST" modelAttribute="validationEx30Form" cssClass="form-horizontal"
		role="form">
		<div class="form-group">
			<div>
				<f:label path="notnull" cssClass="col-md-3 control-label">必須判定</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="notnull" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmiso" cssClass="col-md-3 control-label">日時【標準ISO】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmiso" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmpat" cssClass="col-md-3 control-label">日時【標準パターン】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmpat" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmfm" cssClass="col-md-3 control-label">日時【カスタム(範囲fm)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmfm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmto" cssClass="col-md-3 control-label">日時【カスタム(範囲to)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmto" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtiso" cssClass="col-md-3 control-label">日付【標準ISO】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtiso" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtpat" cssClass="col-md-3 control-label">日付【標準パターン】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtpat" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtfm" cssClass="col-md-3 control-label">日付【カスタム(範囲fm)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtfm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtto" cssClass="col-md-3 control-label">日付【カスタム(範囲to)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtto" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ltmiso" cssClass="col-md-3 control-label">時刻【標準ISO】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ltmiso" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ltmpat" cssClass="col-md-3 control-label">時刻【標準パターン】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ltmpat" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ltmfm" cssClass="col-md-3 control-label">時刻【カスタム(範囲fm)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ltmfm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ltmto" cssClass="col-md-3 control-label">時刻【カスタム(範囲to)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ltmto" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmmin19000101in" cssClass="col-md-3 control-label">日時範囲【最小19000101(in)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmmin19000101in" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmmax30000101in" cssClass="col-md-3 control-label">日時範囲【最小30000101(in)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmmax30000101in" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmmin19000101ex" cssClass="col-md-3 control-label">日時範囲【最小19000101(ex)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmmin19000101ex" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmmax30000101ex" cssClass="col-md-3 control-label">日時範囲【最小30000101(ex)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmmax30000101ex" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmin19000101in" cssClass="col-md-3 control-label">日付範囲【最小19000101(in)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmin19000101in" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmax30000101in" cssClass="col-md-3 control-label">日付範囲【最小30000101(in)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmax30000101in" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmin19000101ex" cssClass="col-md-3 control-label">日付範囲【最小19000101(ex)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmin19000101ex" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="ldtmax30000101ex" cssClass="col-md-3 control-label">日付範囲【最小30000101(ex)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="ldtmax30000101ex" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<f:button type="submit" class="btn btn-default" name="back">入力へ戻る</f:button>
			</div>
		</div>
	</f:form>
</div>
