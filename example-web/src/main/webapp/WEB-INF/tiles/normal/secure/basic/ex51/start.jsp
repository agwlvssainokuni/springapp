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
<h2 class="page-header">基本画面遷移: 一括変更系1-一括変更入力</h2>
<div class="panel-body">
	<ul class="list-inline text-right">
		<li><a href="<ex:uri to="/secure/basic/ex50/execute" />">検索結果一覧へ戻る</a></li>
		<li><a href="<ex:uri to="/secure/basic/ex50/start" />">検索条件入力へ戻る(維持)</a></li>
		<li><a href="<ex:uri to="/secure/basic/ex50/" />">検索条件入力へ戻る(初期)</a></li>
	</ul>
	<s:hasBindErrors name="basicEx51Form">
		<div class="col-md-12">
			<div class="alert alert-danger" role="alert">
				<f:errors path="basicEx51Form" element="div" />
				<c:forEach var="count" begin="1" end="${basicEx51Form.item.size()}">
					<s:nestedPath path="basicEx51Form.item[${count-1}]">
						<f:errors path="text10" element="div" />
						<f:errors path="text100" element="div" />
						<f:errors path="int64" element="div" />
						<f:errors path="decimal1" element="div" />
						<f:errors path="decimal3" element="div" />
						<f:errors path="dt" element="div" />
						<f:errors path="tm" element="div" />
						<f:errors path="dtm" element="div" />
					</s:nestedPath>
				</c:forEach>
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="/secure/basic/ex51/confirm" method="POST" modelAttribute="basicEx51Form" cssClass="form-horizontal" role="form">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>ID</th>
					<th>文字列【10】</th>
					<th>整数【64bit】</th>
					<th>小数【1桁】</th>
					<th>小数【3桁】</th>
					<th>日付</th>
					<th>時刻</th>
					<th>日時</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="count" begin="1" end="${basicEx51Form.item.size()}">
					<s:nestedPath path="item[${count-1}]">
						<tr>
							<td class="text-right"><c:out value="${count}" /></td>
							<td class="text-right"><foundation:render value="${r.id}" /> <f:hidden path="id" /> <f:hidden path="lockVersion" /></td>
							<td><f:input path="text10" cssClass="form-control input-sm" readonly="true" /></td>
							<td><f:input path="int64" cssClass="form-control input-sm text-right" cssErrorClass="form-control input-sm has-error text-right" /></td>
							<td><f:input path="decimal1" cssClass="form-control input-sm text-right" cssErrorClass="form-control input-sm has-error text-right" /></td>
							<td><f:input path="decimal3" cssClass="form-control input-sm text-right" cssErrorClass="form-control input-sm has-error text-right" /></td>
							<td><f:input path="dt" cssClass="form-control input-sm" readonly="true" /></td>
							<td><f:input path="tm" cssClass="form-control input-sm" readonly="true" /></td>
							<td><f:input path="dtm" cssClass="form-control input-sm" readonly="true" /></td>
						</tr>
					</s:nestedPath>
				</c:forEach>
			</tbody>
		</table>
		<div class="row">
			<div class="col-md-3">
				<f:button type="submit" class="btn btn-primary">一括変更確認</f:button>
			</div>
		</div>
	</f:form>
</div>
