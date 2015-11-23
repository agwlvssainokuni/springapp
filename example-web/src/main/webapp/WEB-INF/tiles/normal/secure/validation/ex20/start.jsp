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
<h2 class="page-header">単項目チェック: 数値項目-入力</h2>
<div class="panel-body">
	<s:hasBindErrors name="validationEx20Form">
		<div class="col-md-offset-3 col-md-9">
			<div class="alert alert-danger" role="alert">
				<s:nestedPath path="validationEx20Form">
					<f:errors path="notnull" element="div" />
					<f:errors path="shortnum" element="div" />
					<f:errors path="intnum" element="div" />
					<f:errors path="longnum" element="div" />
					<f:errors path="bigint" element="div" />
					<f:errors path="floatnum" element="div" />
					<f:errors path="doublenum" element="div" />
					<f:errors path="bigdec" element="div" />
					<f:errors path="shortnumWithComma" element="div" />
					<f:errors path="intnumWithComma" element="div" />
					<f:errors path="longnumWithComma" element="div" />
					<f:errors path="bigintWithComma" element="div" />
					<f:errors path="floatnumWithComma" element="div" />
					<f:errors path="doublenumWithComma" element="div" />
					<f:errors path="bigdecWithComma" element="div" />
					<f:errors path="floatnumWithScale" element="div" />
					<f:errors path="doublenumWithScale" element="div" />
					<f:errors path="bigdecWithScale" element="div" />
					<f:errors path="min9999999999" element="div" />
					<f:errors path="max9999999999" element="div" />
					<f:errors path="decmin10000000000in" element="div" />
					<f:errors path="decmax10000000000in" element="div" />
					<f:errors path="decmin10000000000ex" element="div" />
					<f:errors path="decmax10000000000ex" element="div" />
				</s:nestedPath>
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="/secure/validation/ex20/confirm" method="POST" modelAttribute="validationEx20Form" cssClass="form-horizontal"
		role="form">
		<div class="form-group">
			<div>
				<f:label path="notnull" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">必須判定</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="notnull" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="shortnum" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換【Short】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="shortnum" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="intnum" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換【Integer】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="intnum" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="longnum" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換【Long】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="longnum" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="bigint" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換【BigInteger】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="bigint" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="floatnum" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換【Float】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="floatnum" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="doublenum" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換【Double】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="doublenum" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="bigdec" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換【BigDecimal】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="bigdec" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="shortnumWithComma" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(カンマ)【Short】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="shortnumWithComma" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="intnumWithComma" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(カンマ)【Integer】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="intnumWithComma" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="longnumWithComma" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(カンマ)【Long】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="longnumWithComma" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="bigintWithComma" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(カンマ)【BigInteger】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="bigintWithComma" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="floatnumWithComma" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(カンマ)【Float】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="floatnumWithComma" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="doublenumWithComma" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(カンマ)【Double】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="doublenumWithComma" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="bigdecWithComma" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(カンマ)【BigDecimal】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="bigdecWithComma" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="floatnumWithScale" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(小数桁)【Float】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="floatnumWithScale" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="doublenumWithScale" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(小数桁)【Double】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="doublenumWithScale" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="bigdecWithScale" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">数値変換(小数桁)【BigDecimal】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="bigdecWithScale" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="min9999999999" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">範囲判定【最小-9999999999】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="min9999999999" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="max9999999999" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">範囲判定【最大9999999999】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="max9999999999" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="decmin10000000000in" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">範囲判定【最小-10000000000(in)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="decmin10000000000in" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="decmax10000000000in" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">範囲判定【最大10000000000(in)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="decmax10000000000in" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="decmin10000000000ex" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">範囲判定【最小-10000000000(ex)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="decmin10000000000ex" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="decmax10000000000ex" cssClass="col-md-3 control-label" cssErrorClass="col-md-3 control-label has-error">範囲判定【最大10000000000(ex)】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="decmax10000000000ex" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<f:button type="submit" class="btn btn-primary">確認</f:button>
			</div>
		</div>
	</f:form>
</div>
