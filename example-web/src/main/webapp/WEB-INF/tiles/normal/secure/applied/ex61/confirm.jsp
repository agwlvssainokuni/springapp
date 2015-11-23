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
<h2 class="page-header">応用画面遷移: 一括変更系2-一括変更確認</h2>
<div class="panel-body">
	<ul class="list-inline text-right">
		<li><a href="<ex:uri to="/secure/applied/ex60/execute" />">検索結果一覧へ戻る</a></li>
		<li><a href="<ex:uri to="/secure/applied/ex60/start" />">検索条件入力へ戻る(維持)</a></li>
		<li><a href="<ex:uri to="/secure/applied/ex60/" />">検索条件入力へ戻る(初期)</a></li>
	</ul>
	<f:form servletRelativeAction="/secure/applied/ex61/execute" method="POST" modelAttribute="appliedEx61Form" cssClass="form-horizontal"
		role="form">
		<foundation:onetimetoken />
		<div class="form-group">
			<div>
				<f:label path="dt" cssClass="col-md-1 control-label">日付</f:label>
			</div>
			<div class="col-md-2">
				<f:input path="dt" cssClass="form-control" readonly="true" />
			</div>
			<div>
				<f:label path="tm" cssClass="col-md-1 control-label">時刻</f:label>
			</div>
			<div class="col-md-2">
				<f:input path="tm" cssClass="form-control" readonly="true" />
			</div>
			<div>
				<f:label path="dtm" cssClass="col-md-1 control-label">日時</f:label>
			</div>
			<div class="col-md-2">
				<f:input path="dtm" cssClass="form-control" readonly="true" />
			</div>
		</div>
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
				<c:forEach var="count" begin="1" end="${appliedEx61Form.item.size()}">
					<s:nestedPath path="item[${count-1}]">
						<tr>
							<td class="text-right"><c:out value="${count}" /></td>
							<td class="text-right"><foundation:render value="${r.id}" /> <f:hidden path="id" /> <f:hidden path="lockVersion" /></td>
							<td><f:input path="text10" cssClass="form-control input-sm" readonly="true" /></td>
							<td><f:input path="int64" cssClass="form-control input-sm text-right" readonly="true" /></td>
							<td><f:input path="decimal1" cssClass="form-control input-sm text-right" readonly="true" /></td>
							<td><f:input path="decimal3" cssClass="form-control input-sm text-right" readonly="true" /></td>
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
				<f:button type="submit" class="btn btn-primary">一括変更実行</f:button>
				<f:button type="submit" class="btn btn-default" name="back">入力へ戻る</f:button>
			</div>
		</div>
	</f:form>
</div>
