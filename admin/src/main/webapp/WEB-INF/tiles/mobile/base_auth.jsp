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
<%@ taglib prefix="fwcore" uri="urn:springapp:fwcore"%>
<c:set var="name">
	<tiles:getAsString name="name" />
</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=5.0, user-scalable=yes" />
<title><s:message code="base/common.title" /> / <s:message
		code="${name}.title" /></title>
<link rel="stylesheet" media="screen"
	href="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<link rel="stylesheet" media="screen"
	href="<c:url value="/mobile/style/general.css"/>" />
<link rel="stylesheet" media="screen"
	href="<c:url value="/mobile/style/custom.css"/>" />
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.1.js"></script>
<script type="text/javascript"
	src="<c:url value="/mobile/script/config.js" />"></script>
<script type="text/javascript"
	src="//code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/mobile/script/general.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/mobile/script/custom.js" />"></script>
</head>
<body>
	<div data-role="page">
		<div data-role="header">
			<h1>
				<s:message code="base/common.title" />
			</h1>
			<form action="<c:url value="/logout" />" method="POST"
				class="ui-btn-right">
				<input type="submit" value="<s:message code="base/auth.logout" />">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}">
			</form>
		</div>
		<div data-role="content">
			<tiles:insertAttribute name="content" />
		</div>
		<div data-role="footer">
			<div data-role="navbar" data-iconpos="left">
				<ul>
					<li><a hreF="<c:url value="/secure/" />" data-icon="home">
							<s:message code="base/auth.menu.home" />
					</a></li>
				</ul>
			</div>
			<h1>
				<s:message code="base/common.copyright" />
			</h1>
		</div>
	</div>
</body>
</html>
