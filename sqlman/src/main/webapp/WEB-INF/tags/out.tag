<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="foundation" uri="urn:cherry:foundation"%>
<%@ attribute name="path" required="true" rtexprvalue="true" type="java.lang.String"%>
<s:bind path="${path}">
	<foundation:render value="${status.actualValue}" />
</s:bind>
