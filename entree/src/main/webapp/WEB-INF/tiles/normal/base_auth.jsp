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
<%@ taglib prefix="common" uri="urn:springapp:common"%>
<c:set var="name">
	<tiles:getAsString name="name" />
</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title><s:message code="base/common.title" /> / <s:message
		code="${name}.title" /></title>
<link rel="stylesheet" media="screen"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
<link rel="stylesheet" media="screen"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" media="screen"
	href="<c:url value="/normal/style/custom.css"/>" />
<link rel="stylesheet" media="screen"
	href="<c:url value="/normal/style/general.css"/>" />
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="<c:url value="/normal/script/config.js" />"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/normal/script/custom.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/normal/script/general.js" />"></script>
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
					<li><a hreF="<c:url value="/secure/passwd" />"
						data-icon="gear"> <s:message code="base/auth.menu.passwd" />
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
