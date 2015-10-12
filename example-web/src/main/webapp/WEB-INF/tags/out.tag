<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<%@ attribute name="path" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="mode" required="false" rtexprvalue="true" type="java.lang.Integer"%>
<s:bind path="${path}">
	<c:choose>
		<c:when test="${mode == null}">
			<foundation:render value="${status.actualValue}" />
		</c:when>
		<c:otherwise>
			<foundation:render value="${status.actualValue}" mode="${mode}" />
		</c:otherwise>
	</c:choose>
</s:bind>
