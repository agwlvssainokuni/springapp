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
<h2 class="page-header">HOME</h2>
<ul class="nav nav-tabs togglable-tabs" role="tablist">
	<li role="presentation"><a href="#simple" aria-controls="simple" role="tab" data-toggle="tab">単純画面遷移</a></li>
	<li role="presentation"><a href="#create" aria-controls="create" role="tab" data-toggle="tab">登録系画面遷移</a></li>
	<li role="presentation"><a href="#list" aria-controls="list" role="tab" data-toggle="tab">一覧表示系画面遷移</a></li>
	<li role="presentation"><a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">詳細表示系画面遷移</a></li>
</ul>
<div class="tab-content">
	<div id="simple" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/simple/ex10/" class="list-group-item">登録-確認-完了</a></li>
			<li><a href="${baseUri}/simple/ex20/" class="list-group-item">登録-確認-単票表示</a></li>
			<li><a href="${baseUri}/simple/ex30/" class="list-group-item">検索条件-一覧表示-単票表示-検索条件</a></li>
			<li><a href="${baseUri}/simple/ex40/" class="list-group-item">検索条件-一覧表示-単票表示-一覧表示</a></li>
		</ul>
	</div>
	<div id="create" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/create/ex01/" class="list-group-item">基本型</a></li>
		</ul>
	</div>
	<div id="list" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/list/ex01/" class="list-group-item">基本型</a></li>
			<li><a href="${baseUri}/list/ex02/" class="list-group-item">検索結果(一覧)へ戻り得る場合</a></li>
		</ul>
	</div>
	<div id="detail" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/detail/ex01/" class="list-group-item">基本型</a></li>
		</ul>
	</div>
</div>
