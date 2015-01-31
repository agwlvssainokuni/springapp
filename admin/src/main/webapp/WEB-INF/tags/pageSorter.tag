<%@ tag language="java" pageEncoding="UTF-8" body-content="scriptless"
	trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ attribute name="label" required="true" rtexprvalue="true"%>
<%@ attribute name="markerClass" required="true" rtexprvalue="true"%>
<%@ attribute name="sortBy" required="true" rtexprvalue="true"%>
<%@ attribute name="sortOrder" required="true" rtexprvalue="true"%>
<fieldset class="page-sorter">
	<label><c:out value="${label}" /><select name="${sortBy}"
		class="${markerClass}">
			<jsp:doBody />
	</select></label> <label><input name="${sortOrder}" class="${markerClass}"
		type="radio" value="ASC" /> <s:message code="tag/sorter.asc.label" /></label>
	<label><input name="${sortOrder}" class="${markerClass}"
		type="radio" value="DESC" /> <s:message code="tag/sorter.desc.label" /></label>
</fieldset>
