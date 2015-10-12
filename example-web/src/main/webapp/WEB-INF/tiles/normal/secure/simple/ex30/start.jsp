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
<h2 class="page-header">単純画面遷移-検索条件</h2>
<div class="panel-group">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">検索条件</h3>
		</div>
		<div class="panel-body">
			<s:hasBindErrors name="ex30Form">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="alert alert-danger" role="alert">
						<f:errors path="ex30Form" element="div" />
						<f:errors path="ex30Form.text10" element="div" />
						<f:errors path="ex30Form.int64From" element="div" />
						<f:errors path="ex30Form.int64To" element="div" />
						<f:errors path="ex30Form.dtFrom" element="div" />
						<f:errors path="ex30Form.dtTo" element="div" />
						<f:errors path="ex30Form.tmFrom" element="div" />
						<f:errors path="ex30Form.tmTo" element="div" />
						<f:errors path="ex30Form.dtmFromD" element="div" />
						<f:errors path="ex30Form.dtmFromT" element="div" />
						<f:errors path="ex30Form.dtmToD" element="div" />
						<f:errors path="ex30Form.dtmToT" element="div" />
					</div>
				</div>
			</s:hasBindErrors>
			<f:form servletRelativeAction="/secure/simple/ex30/execute" method="POST" modelAttribute="ex30Form" cssClass="form-horizontal" role="form">
				<f:hidden path="sortBy" />
				<f:hidden path="sortOrder" />
				<f:hidden path="pno" />
				<f:hidden path="psz" />
				<div class="form-group">
					<f:label path="text10" cssClass="col-sm-2 control-label">文字列【10】</f:label>
					<div class="col-sm-10">
						<f:input path="text10" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="dtFrom" cssClass="col-sm-2 control-label">日付</f:label>
					<div class="col-sm-3">
						<f:input path="dtFrom" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-sm-3">
						<f:input path="dtTo" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="tmFrom" cssClass="col-sm-2 control-label">時刻</f:label>
					<div class="col-sm-3">
						<f:input path="tmFrom" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-sm-3">
						<f:input path="tmTo" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="dtmFromD" cssClass="col-sm-2 control-label">日時(から)</f:label>
					<div class="col-sm-3">
						<f:input path="dtmFromD" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-sm-3">
						<f:input path="dtmFromT" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<f:label path="dtmToD" cssClass="col-sm-2 control-label">日時(まで)</f:label>
					<div class="col-sm-3">
						<f:input path="dtmToD" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
					<div class="col-sm-3">
						<f:input path="dtmToT" cssClass="form-control" cssErrorClass="form-control has-error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<f:button type="submit" class="btn btn-primary">検索</f:button>
					</div>
				</div>
			</f:form>
		</div>
	</div>
	<c:if test="${hasResultList}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">検索結果</h3>
			</div>
			<div class="panel-body">
				<f:form servletRelativeAction="/secure/simple/ex30/execute" method="POST" modelAttribute="ex30Form" id="ex30Form2">
					<f:hidden path="sortBy" id="sortBy2" />
					<f:hidden path="sortOrder" id="sortOrder2" />
					<f:hidden path="pno" id="pno2" />
					<f:hidden path="psz" id="psz2" />
					<f:hidden path="text10" id="text102" />
					<f:hidden path="int64From" id="int64From2" />
					<f:hidden path="int64To" id="int64To2" />
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
					<div class="text-right">
						<div>
							<s:message text="全{0}件 {1}件目〜{2}件目"
								arguments="${pagedList.pageSet.last.to+1},${pagedList.pageSet.current.from+1},${pagedList.pageSet.current.to+1}" />
						</div>
						<ex:pagerLink pageSet="${pagedList.pageSet}" form="#ex30Form2" pno="pno" />
					</div>
					<table id="searchResultList" class="table table-striped">
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
							<c:forEach var="count" begin="1" end="${pagedList.list.size()}">
								<s:nestedPath path="pagedList.list[${count-1}]">
									<tr>
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
					<div class="text-right">
						<ex:pagerLink pageSet="${pagedList.pageSet}" form="#ex30Form2" pno="pno" />
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>
