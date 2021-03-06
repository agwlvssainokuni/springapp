<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h2 class="page-header">SQL管理ツール ホーム</h2>
<c:url var="baseUri" value="/tool" />
<ul class="list-group list-unstyled">
	<li><a class="list-group-item" href="${baseUri}/search">登録済みSQL検索</a></li>
	<li><a class="list-group-item" href="${baseUri}/clause">句指定SQL実行</a></li>
	<li><a class="list-group-item" href="${baseUri}/statement">文指定SQL実行</a></li>
	<li><a class="list-group-item" href="${baseUri}/load">CSV取込み</a></li>
</ul>
