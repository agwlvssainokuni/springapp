<%@ tag language="java" pageEncoding="UTF-8" body-content="empty"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="form" required="true" rtexprvalue="true"%>
<%@ attribute name="selector" required="true" rtexprvalue="true"%>
<fieldset class="refresh-button">
	<button data-form="${form}" data-selector="${selector}">
		<s:message code="tag/refreshButton.label" />
	</button>
</fieldset>
