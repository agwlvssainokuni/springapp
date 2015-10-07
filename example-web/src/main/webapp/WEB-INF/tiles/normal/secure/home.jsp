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
	<li role="presentation"><a href="#create" aria-controls="create" role="tab" data-toggle="tab">登録系画面遷移</a></li>
	<li role="presentation"><a href="#list" aria-controls="list" role="tab" data-toggle="tab">一覧表示系画面遷移</a></li>
	<li role="presentation"><a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">詳細表示系画面遷移</a></li>
</ul>
<div class="tab-content">
	<div id="create" class="tab-pane" role="tabpanel">
		<div class="list-group">
			<div>
				<a href="${baseUri}/create/ex01/" class="list-group-item">基本型</a>
			</div>
		</div>
	</div>
	<div id="list" class="tab-pane" role="tabpanel">
		<div class="list-group">
			<div>
				<a href="${baseUri}/list/ex01" class="list-group-item">基本型</a>
			</div>
			<div>
				<a href="${baseUri}/list/ex02" class="list-group-item">検索結果(一覧)へ戻り得る場合</a>
			</div>
		</div>
	</div>
	<div id="detail" class="tab-pane" role="tabpanel">
		<div class="list-group">
			<div>
				<a href="${baseUri}/detail/ex01/" class="list-group-item">基本型</a>
			</div>
		</div>
	</div>
</div>
