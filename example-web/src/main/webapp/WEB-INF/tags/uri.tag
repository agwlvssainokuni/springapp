<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="var" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="to" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="from" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="navi" required="false" rtexprvalue="true" type="java.lang.Boolean"%>
<c:set var="uri">
	<c:choose>
		<c:when test="${navi}">/secure/navi/</c:when>
		<c:otherwise>./</c:otherwise>
	</c:choose>
</c:set>
<c:choose>
	<c:when test="${var == null}">
		<s:url value="${uri}">
			<s:param name="to" value="${to}" />
			<c:if test="${from != null}">
				<s:param name="fm" value="${from}" />
			</c:if>
		</s:url>
	</c:when>
	<c:otherwise>
		<s:url var="${var}" value="${uri}">
			<s:param name="to" value="${to}" />
			<c:if test="${from != null}">
				<s:param name="fm" value="${from}" />
			</c:if>
		</s:url>
	</c:otherwise>
</c:choose>
