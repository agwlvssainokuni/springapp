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
<h2 class="page-header">単項目チェック: 文字列項目-確認</h2>
<div class="panel-body">
	<f:form servletRelativeAction="/secure/validation/ex10/execute" method="POST" modelAttribute="validationEx10Form" cssClass="form-horizontal"
		role="form">
		<div class="form-group">
			<div>
				<f:label path="notempty" cssClass="col-md-3 control-label">必須判定</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="notempty" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="minlength05" cssClass="col-md-3 control-label">文字列長判定【最短(5文字)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="minlength05" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="maxlength10" cssClass="col-md-3 control-label">文字列長判定【最長(10文字)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="maxlength10" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="basiclatin" cssClass="col-md-3 control-label">文字種判定【BASIC LATIN】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="basiclatin" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="alpha" cssClass="col-md-3 control-label">文字種判定【半角英字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="alpha" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="numeric" cssClass="col-md-3 control-label">文字種判定【半角数字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="numeric" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="alphanumeric" cssClass="col-md-3 control-label">文字種判定【半角英数字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="alphanumeric" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="halfwidth" cssClass="col-md-3 control-label">文字種判定【半角文字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="halfwidth" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="halfkatakana" cssClass="col-md-3 control-label">文字種判定【半角カタカナ】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="halfkatakana" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullwidth" cssClass="col-md-3 control-label">文字種判定【全角文字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullwidth" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullalpha" cssClass="col-md-3 control-label">文字種判定【全角英字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullalpha" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullnumeric" cssClass="col-md-3 control-label">文字種判定【全角数字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullnumeric" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullalphanumeric" cssClass="col-md-3 control-label">文字種判定【全角英数字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullalphanumeric" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullhiragana" cssClass="col-md-3 control-label">文字種判定【全角ひらがな】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullhiragana" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullkatakana" cssClass="col-md-3 control-label">文字種判定【全角カタカナ】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullkatakana" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="cp932" cssClass="col-md-3 control-label">文字種判定【CP932】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="cp932" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<f:button type="submit" class="btn btn-default" name="back">入力へ戻る</f:button>
			</div>
		</div>
	</f:form>
</div>
