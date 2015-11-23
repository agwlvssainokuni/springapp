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
<h2 class="page-header">応用画面遷移: アップロード系1-完了</h2>
<div class="panel-body">
	<div class="col-md-offset-2 col-md-10">
		<c:forEach var="count" begin="1" end="${appliedEx90ResultDtoList.size()}">
			<c:set var="appliedEx90ResultDto" value="${appliedEx90ResultDtoList.get(count-1)}" scope="request" />
			<c:choose>
				<c:when test="${appliedEx90ResultDto.ngCount <= 0}">
					<div class="alert alert-info" role="alert">
						<s:nestedPath path="appliedEx90ResultDto">
							<div>
								<ex:out path="totalCount" />
								登録しました。
							</div>
						</s:nestedPath>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger" role="alert">
						<s:nestedPath path="appliedEx90ResultDto">
							<div>
								全
								<ex:out path="totalCount" />
								件中、
								<ex:out path="okCount" />
								件登録しました。不正
								<ex:out path="ngCount" />
								件。
							</div>
						</s:nestedPath>
						<c:forEach var="entry" items="${appliedEx90ResultDto.ngInfo.entrySet()}">
							<div>
								<foundation:render value="${entry.key}" />
								件目:
								<c:forEach var="msg" items="${entry.value}">
									<c:out value="${msg}" />
								</c:forEach>
							</div>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<div class="form-horizontal">
		<s:nestedPath path="appliedEx90Form">
			<div class="form-group">
				<div>
					<f:label path="originalFilename" cssClass="col-md-2 control-label">ファイル</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="originalFilename" cssClass="form-control" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<div>
					<f:label path="charset" cssClass="col-md-2 control-label">文字コード</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="charset" cssClass="form-control" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<div>
					<f:label path="dt" cssClass="col-md-2 control-label">日付</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="dt" cssClass="form-control" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<div>
					<f:label path="tm" cssClass="col-md-2 control-label">時刻</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="tm" cssClass="form-control" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<div>
					<f:label path="dtm" cssClass="col-md-2 control-label">日時</f:label>
				</div>
				<div class="col-md-10">
					<f:input path="dtm" cssClass="form-control" readonly="true" />
				</div>
			</div>
		</s:nestedPath>
	</div>
</div>
