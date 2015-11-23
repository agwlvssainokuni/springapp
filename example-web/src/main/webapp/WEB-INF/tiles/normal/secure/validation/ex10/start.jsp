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
<h2 class="page-header">単項目チェック: 文字列項目-入力</h2>
<div class="panel-body">
	<s:hasBindErrors name="validationEx10Form">
		<div class="col-md-offset-3 col-md-9">
			<div class="alert alert-danger" role="alert">
				<s:nestedPath path="validationEx10Form">
					<f:errors path="notempty" element="div" />
					<f:errors path="minlength05" element="div" />
					<f:errors path="maxlength10" element="div" />
					<f:errors path="basiclatin" element="div" />
					<f:errors path="alpha" element="div" />
					<f:errors path="numeric" element="div" />
					<f:errors path="alphanumeric" element="div" />
					<f:errors path="halfwidth" element="div" />
					<f:errors path="halfkatakana" element="div" />
					<f:errors path="fullwidth" element="div" />
					<f:errors path="fullalpha" element="div" />
					<f:errors path="fullnumeric" element="div" />
					<f:errors path="fullalphanumeric" element="div" />
					<f:errors path="fullhiragana" element="div" />
					<f:errors path="fullkatakana" element="div" />
					<f:errors path="cp932" element="div" />
				</s:nestedPath>
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="/secure/validation/ex10/confirm" method="POST" modelAttribute="validationEx10Form" cssClass="form-horizontal"
		role="form">
		<div class="form-group">
			<div>
				<f:label path="notempty" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">必須判定</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="notempty" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="minlength05" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字列長判定【最短(5文字)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="minlength05" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="maxlength10" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字列長判定【最長(10文字)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="maxlength10" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="basiclatin" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【BASIC LATIN】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="basiclatin" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="alpha" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【半角英字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="alpha" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="numeric" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【半角数字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="numeric" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="alphanumeric" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【半角英数字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="alphanumeric" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="halfwidth" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【半角文字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="halfwidth" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="halfkatakana" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【半角カタカナ】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="halfkatakana" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullwidth" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【全角文字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullwidth" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullalpha" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【全角英字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullalpha" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullnumeric" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【全角数字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullnumeric" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullalphanumeric" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【全角英数字】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullalphanumeric" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullhiragana" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【全角ひらがな】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullhiragana" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="fullkatakana" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【全角カタカナ】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="fullkatakana" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="cp932" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">文字種判定【CP932】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="cp932" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<f:button type="submit" class="btn btn-primary">確認</f:button>
			</div>
		</div>
	</f:form>
</div>
