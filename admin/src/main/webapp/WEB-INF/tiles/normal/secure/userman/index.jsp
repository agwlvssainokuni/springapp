<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<h1 class="ui-widget-header">
	<s:message code="secure/userman/index.message.0" />
</h1>
<div class="ui-widget">
	<ul class="app-menu">
		<li><a href="<c:url value="/secure/userman/search" />"><s:message
					code="secure/home/index.menu.userman.search" /></a></li>
		<li><a href="<c:url value="/secure/userman/export" />"><s:message
					code="secure/home/index.menu.userman.export" /></a></li>
		<li><a href="<c:url value="/secure/userman/import" />"><s:message
					code="secure/home/index.menu.userman.import" /></a></li>
	</ul>
</div>