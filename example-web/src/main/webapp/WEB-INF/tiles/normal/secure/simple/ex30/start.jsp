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
<h2 class="page-header">単純画面遷移-検索条件</h2>
<div class="panel-body">
	<s:hasBindErrors name="ex30Form">
		<div class="col-sm-offset-2 col-sm-10">
			<div class="alert alert-danger" role="alert">
				<f:errors path="ex30Form" element="div" />
				<f:errors path="ex30Form.text" element="div" />
				<f:errors path="ex30Form.fromDt" element="div" />
				<f:errors path="ex30Form.fromTm" element="div" />
				<f:errors path="ex30Form.toDt" element="div" />
				<f:errors path="ex30Form.toTm" element="div" />
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="${baseUri}/simple/ex30/execute" method="POST" modelAttribute="ex30Form" cssClass="form-horizontal" role="form">
		<f:hidden path="sortBy" />
		<f:hidden path="sortOrder" />
		<f:hidden path="pno" />
		<f:hidden path="psz" />
		<div class="form-group">
			<f:label path="text" cssClass="col-sm-2 control-label">文字列</f:label>
			<div class="col-sm-10">
				<f:input path="text" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="fromDt" cssClass="col-sm-2 control-label">いつから</f:label>
			<div class="col-sm-3">
				<f:input path="fromDt" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
			<div class="col-sm-3">
				<f:input path="fromTm" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<f:label path="toDt" cssClass="col-sm-2 control-label">いつまで</f:label>
			<div class="col-sm-3">
				<f:input path="toDt" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
			<div class="col-sm-3">
				<f:input path="toTm" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<f:button type="submit" class="btn btn-primary">検索</f:button>
			</div>
		</div>
	</f:form>
</div>
