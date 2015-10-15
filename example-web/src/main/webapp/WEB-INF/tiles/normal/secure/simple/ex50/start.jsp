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
<c:set var="hasResultList" value="${pagedList != null && !pagedList.list.isEmpty()}" />
<h2 class="page-header">単純画面遷移: 一括変更系1-検索条件結果一覧</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<a data-toggle="collapse" href="#searchForm">検索条件</a>
			</h3>
		</div>
		<div id="searchForm" class="panel-body panel-collapse collapse ${hasResultList ? '' : 'in'}">
			<s:hasBindErrors name="ex50Form">
				<div class="col-md-offset-2 col-md-10">
					<div class="alert alert-danger" role="alert">
						<f:errors path="ex50Form" element="div" />
						<f:errors path="ex50Form.text10" element="div" />
						<f:errors path="ex50Form.int64From" element="div" />
						<f:errors path="ex50Form.int64To" element="div" />
						<f:errors path="ex50Form.decimal1From" element="div" />
						<f:errors path="ex50Form.decimal1To" element="div" />
						<f:errors path="ex50Form.decimal3From" element="div" />
						<f:errors path="ex50Form.decimal3To" element="div" />
						<f:errors path="ex50Form.dtFrom" element="div" />
						<f:errors path="ex50Form.dtTo" element="div" />
						<f:errors path="ex50Form.tmFrom" element="div" />
						<f:errors path="ex50Form.tmTo" element="div" />
						<f:errors path="ex50Form.dtmFromD" element="div" />
						<f:errors path="ex50Form.dtmFromT" element="div" />
						<f:errors path="ex50Form.dtmToD" element="div" />
						<f:errors path="ex50Form.dtmToT" element="div" />
					</div>
				</div>
			</s:hasBindErrors>
			<f:form servletRelativeAction="/secure/simple/ex50/execute" method="POST" modelAttribute="ex50Form" cssClass="form-horizontal" role="form">
				<f:hidden path="sortBy1" />
				<f:hidden path="sortOrder1" />
				<f:hidden path="sortBy2" />
				<f:hidden path="sortOrder2" />
				<f:hidden path="pno" />
				<f:hidden path="psz" />
				<div class="form-group">
					<f:label path="text10" cssClass="col-md-2 control-label">文字列【10】</f:label>
					<div class="col-md-10">
						<f:input path="text10" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="int64From" cssClass="col-md-2 control-label">整数【64bit】</f:label>
					<div class="col-md-3">
						<f:input path="int64From" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-md-3">
						<f:input path="int64To" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="decimal1From" cssClass="col-md-2 control-label">小数【1桁】</f:label>
					<div class="col-md-3">
						<f:input path="decimal1From" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-md-3">
						<f:input path="decimal1To" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="decimal3From" cssClass="col-md-2 control-label">小数【3桁】</f:label>
					<div class="col-md-3">
						<f:input path="decimal3From" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-md-3">
						<f:input path="decimal3To" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="dtFrom" cssClass="col-md-2 control-label">日付</f:label>
					<div class="col-md-3">
						<f:input path="dtFrom" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-md-3">
						<f:input path="dtTo" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="tmFrom" cssClass="col-md-2 control-label">時刻</f:label>
					<div class="col-md-3">
						<f:input path="tmFrom" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-md-3">
						<f:input path="tmTo" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="dtmFromD" cssClass="col-md-2 control-label">日時(から)</f:label>
					<div class="col-md-3">
						<f:input path="dtmFromD" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-md-3">
						<f:input path="dtmFromT" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="dtmToD" cssClass="col-md-2 control-label">日時(まで)</f:label>
					<div class="col-md-3">
						<f:input path="dtmToD" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-md-3">
						<f:input path="dtmToT" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-10">
						<f:button type="submit" class="btn btn-primary">検索</f:button>
					</div>
				</div>
			</f:form>
		</div>
	</div>
	<c:if test="${hasResultList}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">検索結果一覧</h3>
			</div>
			<div class="panel-body">
				<f:form servletRelativeAction="/secure/simple/ex50/execute" method="POST" modelAttribute="ex50Form" id="ex50Form2">
					<f:hidden path="sortBy1" id="sortBy12" />
					<f:hidden path="sortOrder1" id="sortOrder12" />
					<f:hidden path="sortBy2" id="sortBy22" />
					<f:hidden path="sortOrder2" id="sortOrder22" />
					<f:hidden path="pno" id="pno2" />
					<f:hidden path="psz" id="psz2" />
					<f:hidden path="text10" id="text102" />
					<f:hidden path="int64From" id="int64From2" />
					<f:hidden path="int64To" id="int64To2" />
					<f:hidden path="decimal1From" id="decimal1From2" />
					<f:hidden path="decimal1To" id="decimal1To2" />
					<f:hidden path="decimal3From" id="decimal3From2" />
					<f:hidden path="decimal3To" id="decimal3To2" />
					<f:hidden path="dtFrom" id="dtFrom2" />
					<f:hidden path="dtTo" id="dtTo2" />
					<f:hidden path="tmFrom" id="tmFrom2" />
					<f:hidden path="tmTo" id="tmTo2" />
					<f:hidden path="dtmFromD" id="dtmFromD2" />
					<f:hidden path="dtmFromT" id="dtmFromT2" />
					<f:hidden path="dtmToD" id="dtmToD2" />
					<f:hidden path="dtmToT" id="dtmToT2" />
				</f:form>
				<div>
					<div class="col-md-3">
						<s:nestedPath path="ex50Form">
							<ex:pageSorter cssClass="ex50Sorter" sortOrder="sortOrder1" sortBy="sortBy1"
								sortByItems="${foundation:getLabeledEnumCodeList('cherry.example.web.simple.ex50.SortBy')}" />
							<ex:pageSorter cssClass="ex50Sorter" sortOrder="sortOrder2" sortBy="sortBy2"
								sortByItems="${foundation:getLabeledEnumCodeList('cherry.example.web.simple.ex50.SortBy')}" />
						</s:nestedPath>
					</div>
					<div class="col-md-1">
						<ex:refreshButton selector=".ex50Sorter" form="#ex50Form2" />
					</div>
					<div class="col-md-offset-1 col-md-7 text-right">
						<div>
							<ex:pagerDesc pageSet="${pagedList.pageSet}" />
							<ex:pageSize form="#ex50Form2" psz="psz" />
						</div>
						<ex:pagerLink pageSet="${pagedList.pageSet}" form="#ex50Form2" pno="pno" />
					</div>
				</div>
				<table id="searchResultList" class="table table-striped">
					<thead>
						<tr>
							<th></th>
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
						<c:forEach var="count" begin="1" end="${pagedList.list.size()}">
							<s:nestedPath path="pagedList.list[${count-1}]">
								<tr>
									<td></td>
									<td class="text-right"><c:out value="${pagedList.pageSet.current.from + count}" /></td>
									<td class="text-right"><ex:out path="id" /></td>
									<td><ex:out path="text10" /></td>
									<td class="text-right"><ex:out path="int64" /></td>
									<td class="text-right"><ex:out path="decimal1" mode="1" /></td>
									<td class="text-right"><ex:out path="decimal3" mode="3" /></td>
									<td><ex:out path="dt" /></td>
									<td><ex:out path="tm" /></td>
									<td><ex:out path="dtm" /></td>
								</tr>
							</s:nestedPath>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<div class="col-md-offset-5 col-md-7 text-right">
						<ex:pagerLink pageSet="${pagedList.pageSet}" form="#ex50Form2" pno="pno" />
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
