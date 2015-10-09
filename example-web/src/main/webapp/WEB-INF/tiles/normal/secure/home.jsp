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
	<li role="presentation"><a href="#simple" aria-controls="simple" role="tab" data-toggle="tab">単純画面遷移事例</a></li>
	<li role="presentation"><a href="#form" aria-controls="form" role="tab" data-toggle="tab">入力フォーム事例</a></li>
	<li role="presentation"><a href="#validation" aria-controls="validation" role="tab" data-toggle="tab">単項目チェック事例</a></li>
</ul>
<div class="tab-content">
	<div id="simple" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/simple/ex10/" class="list-group-item">登録-確認-完了</a></li>
			<li><a href="${baseUri}/simple/ex20/" class="list-group-item">登録-確認-単票表示</a></li>
			<li><a href="${baseUri}/simple/ex30/" class="list-group-item">検索条件-一覧表示</a></li>
			<li><a href="${baseUri}/simple/ex40/" class="list-group-item">検索条件-一覧表示-単票表示-一覧表示</a></li>
		</ul>
	</div>
	<div id="form" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/form/ex10/" class="list-group-item">単票入力</a></li>
			<li><a href="${baseUri}/form/ex10/" class="list-group-item">一覧入力</a></li>
		</ul>
	</div>
	<div id="validation" class="tab-pane" role="tabpanel">
		<ul class="list-group list-unstyled">
			<li><a href="${baseUri}/validation/ex10/" class="list-group-item">文字列項目</a></li>
			<li><a href="${baseUri}/validation/ex20/" class="list-group-item">数値項目</a></li>
			<li><a href="${baseUri}/validation/ex30/" class="list-group-item">日時項目</a></li>
		</ul>
	</div>
</div>
