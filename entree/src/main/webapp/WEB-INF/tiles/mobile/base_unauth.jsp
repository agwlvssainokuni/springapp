<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<c:set var="name">
	<tiles:getAsString name="name" />
</c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=5.0, user-scalable=yes" />
<title><s:message code="base/common.title" /> / <s:message code="${name}.title" /></title>
<link rel="stylesheet" media="screen" href="//code.jquery.com/mobile/1.4.0/jquery.mobile-1.4.0.min.css" />
<link rel="stylesheet" media="screen" href="<c:url value="/mobile/style/custom.css"/>" />
<link rel="stylesheet" media="screen" href="<c:url value="/mobile/style/general.css"/>" />
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.js"></script>
<script type="text/javascript" src="<c:url value="/mobile/script/config.js" />"></script>
<script type="text/javascript" src="//code.jquery.com/mobile/1.4.0/jquery.mobile-1.4.0.min.js"></script>
<script type="text/javascript" src="<c:url value="/mobile/script/custom.js" />"></script>
<script type="text/javascript" src="<c:url value="/mobile/script/general.js" />"></script>
</head>
<body>
	<div data-role="page">
		<div data-role="header">
			<h1>
				<s:message code="base/common.title" />
			</h1>
		</div>
		<div data-role="content">
			<tiles:insertAttribute name="content" />
		</div>
		<div data-role="footer">
			<h1>
				<s:message code="base/common.copyright" />
			</h1>
		</div>
	</div>
</body>
</html>
