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
	<f:form servletRelativeAction="${baseUri}/simple/ex10/execute" method="POST" modelAttribute="ex10Form" cssClass="form-horizontal" role="form">
		<foundation:onetimetoken />
		<div class="form-group">
			<f:label path="text" cssClass="col-sm-2 control-label">文字列</f:label>
			<div class="col-sm-10">
				<f:input path="text" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="inum" cssClass="col-sm-2 control-label">整数(Integer)</f:label>
			<div class="col-sm-10">
				<f:input path="inum" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="lnum" cssClass="col-sm-2 control-label">整数(Long)</f:label>
			<div class="col-sm-10">
				<f:input path="lnum" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="decimal" cssClass="col-sm-2 control-label">小数(2桁)</f:label>
			<div class="col-sm-10">
				<f:input path="decimal" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="ldt" cssClass="col-sm-2 control-label">日付(年月日)</f:label>
			<div class="col-sm-10">
				<f:input path="ldt" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="ltm" cssClass="col-sm-2 control-label">時刻(時分秒)</f:label>
			<div class="col-sm-10">
				<f:input path="ltm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="ldtm" cssClass="col-sm-2 control-label">日時(年月日時分秒)</f:label>
			<div class="col-sm-10">
				<f:input path="ldtm" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<f:button type="submit" class="btn btn-primary">登録</f:button>
			</div>
		</div>
	</f:form>
</div>
