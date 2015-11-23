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
<h2 class="page-header">応用画面遷移: 検索一覧系1-単票変更入力</h2>
<div class="panel-body">
	<ul class="list-inline text-right">
		<li><a href="<ex:uri to="/secure/applied/ex30/execute" />">検索結果一覧へ戻る</a></li>
		<li><a href="<ex:uri to="/secure/applied/ex30/start" />">検索条件入力へ戻る(維持)</a></li>
		<li><a href="<ex:uri to="/secure/applied/ex30/" />">検索条件入力へ戻る(初期)</a></li>
	</ul>
	<s:hasBindErrors name="appliedEx31Form">
		<div class="col-md-offset-2 col-md-10">
			<div class="alert alert-danger" role="alert">
				<f:errors path="appliedEx31Form" element="div" />
				<s:nestedPath path="appliedEx31Form">
					<f:errors path="text10" element="div" />
					<f:errors path="text100" element="div" />
					<f:errors path="int64" element="div" />
					<f:errors path="decimal1" element="div" />
					<f:errors path="decimal3" element="div" />
					<f:errors path="dt" element="div" />
					<f:errors path="tm" element="div" />
					<f:errors path="dtm" element="div" />
					<f:errors path="item" element="div" />
				</s:nestedPath>
				<c:if test="${!appliedEx31Form.item.isEmpty()}">
					<c:forEach var="count" begin="1" end="${appliedEx31Form.item.size()}">
						<s:nestedPath path="appliedEx31Form.item[${count-1}]">
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
				</c:if>
			</div>
		</div>
	</s:hasBindErrors>
	<f:form servletRelativeAction="/secure/applied/ex31/confirm?id=${param.id}" method="POST" modelAttribute="appliedEx31Form"
		cssClass="form-horizontal" role="form">
		<f:hidden path="lockVersion" />
		<div class="form-group">
			<div>
				<f:label path="text10" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">文字列【10】</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="text10" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="text100" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">文字列【100】</f:label>
			</div>
			<div class="col-md-10">
				<f:textarea path="text100" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="int64" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">整数【64bit】</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="int64" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="decimal1" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">小数【1桁】</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="decimal1" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="decimal3" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">小数【3桁】</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="decimal3" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="dt" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">日付</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="dt" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="tm" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">時刻</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="tm" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div>
				<f:label path="dtm" cssClass="col-md-2 control-label" cssErrorClass="col-md-2 control-label has-error">日時</f:label>
			</div>
			<div class="col-md-10">
				<f:input path="dtm" cssClass="form-control" cssErrorClass="form-control has-error" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-2 col-md-10">
				<f:button type="submit" class="btn btn-primary">変更確認</f:button>
			</div>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th></th>
					<th>#</th>
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
				<c:forEach var="count" begin="1" end="${appliedEx31Form.item.size()}">
					<s:nestedPath path="item[${count-1}]">
						<tr>
							<td><a href="${baseUri}/applied/ex32/?id=${param.id}&rownum=${count-1}" class="text-nowrap"><span
									class="glyphicon glyphicon-edit"></span>変更</a></td>
							<td class="text-right"><c:out value="${count}" /></td>
							<td><f:input path="text10" cssClass="form-control input-sm" readonly="true" /> <f:hidden path="text100" /></td>
							<td><f:input path="int64" cssClass="form-control input-sm text-right" readonly="true" /></td>
							<td><f:input path="decimal1" cssClass="form-control input-sm text-right" readonly="true" /></td>
							<td><f:input path="decimal3" cssClass="form-control input-sm text-right" readonly="true" /></td>
							<td><f:input path="dt" cssClass="form-control input-sm" readonly="true" /></td>
							<td><f:input path="tm" cssClass="form-control input-sm" readonly="true" /></td>
							<td><f:input path="dtm" cssClass="form-control input-sm" readonly="true" /></td>
						</tr>
					</s:nestedPath>
				</c:forEach>
				<tr>
					<td><a href="${baseUri}/applied/ex32/?id=${param.id}&rownum=${appliedEx31Form.item == null ? 0 : appliedEx31Form.item.size()}"
						class="text-nowrap"><span class="glyphicon glyphicon-plus"></span>追加</a></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</f:form>
</div>
