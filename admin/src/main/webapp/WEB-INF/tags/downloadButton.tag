<%@ tag language="java" pageEncoding="UTF-8" body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="form" required="true" rtexprvalue="true"%>
<fieldset class="download-button">
	<button data-form="${form}">
		<s:message code="tag/downloadButton.label" />
	</button>
</fieldset>
