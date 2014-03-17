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
<h1>
	<s:message code="home/index.message.0" />
</h1>
<ul data-role="listview" data-inset=“true” class="ui-listview-outer">
	<li><a href="<c:url value="/secure/pwdtool" />"><s:message
				code="home/index.menu.pwdtool" /></a></li>
	<li data-role="collapsible" data-iconpos="right" data-shadow="false"
		data-corners="false">
		<h2>
			<s:message code="home/index.menu.userman" />
		</h2>
		<ul data-role="listview">
			<li><a href="<c:url value="/secure/userman/search" />"><s:message
						code="home/index.menu.userman.search" /></a></li>
			<li><a href="<c:url value="/secure/userman/export" />"><s:message
						code="home/index.menu.userman.export" /></a></li>
			<li><a href="<c:url value="/secure/userman/import" />"><s:message
						code="home/index.menu.userman.import" /></a></li>
		</ul>
	</li>
	<li><a href="<c:url value="/secure/jfreechart" />"><s:message
				code="home/index.menu.jfreechart" /></a></li>
</ul>
