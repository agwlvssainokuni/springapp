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
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
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
<body role="document">
	<div class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="nav navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="navbar-brand">
					<s:message code="base/common.title" />
				</div>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a hreF="<c:url value="/secure/" />"><span
							class="glyphicon glyphicon-home"></span> <s:message
								code="base/auth.menu.home" /></a></li>
					<li><a hreF="<c:url value="/secure/passwd" />"><span
							class="glyphicon glyphicon-user"></span> <s:message
								code="base/auth.menu.passwd" /></a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<c:url value="/logout" />"
						onclick="JavaScript:$('#logout').submit(); return false;"><span
							class="glyphicon glyphicon-off"></span> <s:message
								code="base/auth.logout" /></a>
						<form action="<c:url value="/logout" />" method="POST" id="logout">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}">
						</form></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container" role="main">
		<tiles:insertAttribute name="content" />
	</div>
	<div class="container" role="main">
		<address class="text-center">
			<s:message code="base/common.copyright" />
		</address>
	</div>
</body>
</html>
