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
<h2 class="page-header">単項目チェック: グルーピング-入力</h2>
<div class="panel-body">
	<s:hasBindErrors name="validationEx50Form">
		<div class="col-md-offset-3 col-md-9">
			<div class="alert alert-danger" role="alert">
				<s:nestedPath path="validationEx50Form">
					<f:errors path="text" element="div" />
					<f:errors path="text1" element="div" />
					<f:errors path="text2" element="div" />
					<f:errors path="text3" element="div" />
					<f:errors path="text12" element="div" />
					<f:errors path="text123" element="div" />
				</s:nestedPath>
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="/secure/validation/ex50/confirm" method="POST" modelAttribute="validationEx50Form" cssClass="form-horizontal"
		role="form">
		<div class="form-group">
			<div>
				<f:label path="text" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">テキスト</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text1" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">テキスト【1】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text1" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text2" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">テキスト【2】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text2" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text3" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">テキスト【3】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text3" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text12" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">テキスト【12】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text12" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text123" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">テキスト【123】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="text123" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<f:button type="submit" class="btn btn-primary">確認</f:button>
				<f:button type="submit" class="btn btn-default" name="g1">確認【1】</f:button>
				<f:button type="submit" class="btn btn-default" name="g2">確認【2】</f:button>
				<f:button type="submit" class="btn btn-default" name="g3">確認【3】</f:button>
				<f:button type="submit" class="btn btn-default" name="g12">確認【12】</f:button>
				<f:button type="submit" class="btn btn-default" name="g13">確認【13】</f:button>
			</div>
		</div>
	</f:form>
</div>
