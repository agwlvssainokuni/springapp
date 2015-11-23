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
<h2 class="page-header">単項目チェック: サブフォーム-確認</h2>
<div class="panel-body">
	<f:form servletRelativeAction="/secure/validation/ex40/execute" method="POST" modelAttribute="validationEx40Form" cssClass="form-horizontal"
		role="form">
		<div class="form-group">
			<div>
				<f:label path="subform.text" cssClass="col-md-3 control-label">サブフォーム【直】</f:label>
			</div>
			<div class="col-md-9">
				<f:input path="subform.text" cssClass="form-control" readonly="true" />
			</div>
		</div>
		<c:forEach var="count" begin="1" end="${validationEx40Form.list1.size()}">
			<div class="form-group">
				<div>
					<f:label path="list1[${count-1}].text" cssClass="col-md-3 control-label">リスト１[${count-1}]</f:label>
				</div>
				<div class="col-md-9">
					<f:input path="list1[${count-1}].text" cssClass="form-control" readonly="true" />
				</div>
			</div>
		</c:forEach>
		<c:forEach var="count1" begin="1" end="${validationEx40Form.list2.size()}">
			<c:forEach var="count2" begin="1" end="${validationEx40Form.list2.get(count1-1).v.size()}">
				<div class="form-group">
					<div>
						<f:label path="list2[${count1-1}].v[${count2-1}].text" cssClass="col-md-3 control-label">リスト２[${count1-1}][${count2-1}]</f:label>
					</div>
					<div class="col-md-9">
						<f:input path="list2[${count1-1}].v[${count2-1}].text" cssClass="form-control" readonly="true" />
					</div>
				</div>
			</c:forEach>
		</c:forEach>
		<c:forEach var="key" items="${validationEx40Form.map1.keySet()}">
			<div class="form-group">
				<div>
					<f:label path="map1[${key}].text" cssClass="col-md-3 control-label">マップ１[${key}]</f:label>
				</div>
				<div class="col-md-9">
					<f:input path="map1[${key}].text" cssClass="form-control" readonly="true" />
				</div>
			</div>
		</c:forEach>
		<c:forEach var="key1" items="${validationEx40Form.map2.keySet()}">
			<c:forEach var="key2" items="${validationEx40Form.map2.get(key1).v.keySet()}">
				<div class="form-group">
					<div>
						<f:label path="map2[${key1}].v[${key2}].text" cssClass="col-md-3 control-label">マップ２[${key1}][${key2}]</f:label>
					</div>
					<div class="col-md-9">
						<f:input path="map2[${key1}].v[${key2}].text" cssClass="form-control" readonly="true" />
					</div>
				</div>
			</c:forEach>
		</c:forEach>
		<div class="form-group">
			<div class="col-md-offset-3 col-md-9">
				<f:button type="submit" class="btn btn-default" name="back">入力へ戻る</f:button>
			</div>
		</div>
	</f:form>
</div>
