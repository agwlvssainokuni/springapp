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
<h2 class="page-header">入力フォーム: 単票サブフォーム-確認</h2>
<div class="panel-body">
	<f:form servletRelativeAction="/secure/form/ex10/execute" method="POST" modelAttribute="formEx10Form" cssClass="form-horizontal" role="form">
		<div class="form-group">
			<div>
				<f:label path="item1" cssClass="col-md-3 control-label">項目1(親)</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="item1" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="item2" cssClass="col-md-3 control-label">項目2(親)</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="item2" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="item3.item1" cssClass="col-md-3 control-label">項目3-1</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="item3.item1" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="item3.item2" cssClass="col-md-3 control-label">項目3-2</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="item3.item2" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="item3.item3" cssClass="col-md-3 control-label">項目3-3</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="item3.item3" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<f:button type="submit" class="btn btn-default" name="back">入力へ戻る</f:button>
			</div>
		</div>
	</f:form>
</div>
